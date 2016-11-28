$(function(){
	$(".header").css("margin-bottom","0px");
	var testcaseCounter=0;
	hljs.initHighlightingOnLoad();
	var testcase_fail = 0;
	var testcase_run=0;
	var num_testcases=0;
	var button_clicked_type="";
	var extra_inputs=0;
	//
	$('#codeText').blur(function(){
		$('pre.code').each(function(i, block) {
			  hljs.highlightBlock(block);
			});
	});
	var myCodeMirror = CodeMirror(document.getElementsByClassName("editor")[0],{
		value: "",
		  mode:  "python",
		  indentUnit: 4,
		  lineWrapping: true
	});
	$(".action_btn").mousedown(function () {
	    $(this).addClass("active");
	});
	$(".action_btn").mouseup(function () {
	    $(this).removeClass("active");
	});
	
	function outf(text) { 
	    var mypre = document.getElementById("output"); 
	    mypre.innerHTML = mypre.innerHTML + text; 
	} 
	
	function testcaseOut(id,text){
		if(text.trim()==""){
			return;
		}
		//console.log(text+" "+id);
		checkOutput(id,text);
	}
	
	function checkOutput(testcaseId, testcaseOutput){
		$.ajax({type:"POST",url:"CompilerServlet",data:{query:"checkTestcaseOutput",testcaseID:testcaseId, testcaseOP:testcaseOutput},
			success:function(data) {
			//console.log( "Data Loaded: " + data );
			if(data.trim()=="true"){
				$("#output").append(("Testcase passed<br>").fontcolor("green"));
			}
			else{
				$("#output").append(("Testcase failed<br>").fontcolor("red"));
				testcase_fail += 1;
			}
		},
		async:false});
	}
	
	function builtinRead(x) {
	    if (Sk.builtinFiles === undefined || Sk.builtinFiles["files"][x] === undefined)
	            throw "File not found: '" + x + "'";
	    return Sk.builtinFiles["files"][x];
	}
	// Here's everything you need to run a python program in skulpt
	// grab the code from your textarea
	// get a reference to your pre element for output
	// configure the output function
	// call Sk.importMainWithBody()
	function myinput(type){
		var problem_id = $('#problemContent').attr("data-id");
		$.ajax({
			  type: 'POST',
			  url: "CompilerServlet",
			  data: {query:"testcase",problemId:problem_id,testcaseType:type},
			  success: function(data) {
				  var inputArray=JSON.parse(data)['testcases'];
				  var testcaseIDArray=JSON.parse(data)['testcaseIDs'];
				  num_testcases=inputArray.length;
				  for(var i=0;i<inputArray.length;i++){
					  runit(inputArray[i],testcaseIDArray[i]);
				  }
				},
			  async:false
			});
		
	}	
	
	function runit(input,testcaseID) { 
	   var prog = myCodeMirror.getValue();  
	   Sk.pre = "output";
	   var inputlist=input.split("\n");
	   var cnt=0;
	   //console.log(input);
	   Sk.configure({output:testcaseOut.bind(null,testcaseID), 
		   read:builtinRead,
		   inputfun:function(args){
			   if(cnt>=inputlist.length)
				   {
				   extra_inputs=1;
				   return;
				   }
			   var input_token=inputlist[cnt++];
			   return input_token.trim();
		   }
	   });
	   
	   var myPromise = Sk.misceval.asyncToPromise(function() {
	       return Sk.importMainWithBody("<stdin>", false, prog, true);
	   });
	   
	   myPromise.then(function(mod) {
		   testcase_run++;
		   if(button_clicked_type=="test")
			   return;
	       var output_val=$("#output").text();
	       //console.log("output_val is "+output_val)
	       if(testcase_run==num_testcases && testcase_fail>0){
	    	   $('#bs-unsuccessful-modal-sm').modal();
				var problem_id = $('#problemContent').attr("data-id");

	    	   $.ajax({
					  type: 'POST',
					  url: "ContestProblem",
					  data: {query:"solutionAccept",problemId:problem_id,solAccept:"false"},
					  async:false
					});
	       }else if(testcase_run==num_testcases){
				$('#bs-success-modal-sm').modal();
				var problem_id = $('#problemContent').attr("data-id");
				$.ajax({
					  type: 'POST',
					  url: "ContestProblem",
					  data: {query:"solutionAccept",problemId:problem_id,solAccept:"true"},
					  async:false
					});
				console.log('request sent');
	       }
	   }
	   ,
	       function(err) {
		   testcase_run++;
		   testcase_fail++;
		   //console.log(testcase_fail+" "+testcase_run+" "+num_testcases);
		   var errstr=err.toString();
		   if(extra_inputs==1)
			   errstr="testcase failed<br/>";
		   else
			   errstr=errstr+"<br/>";
		   errstr=errstr.fontcolor("red");
		   $("#output").append(errstr);
		   if(testcase_run==num_testcases && button_clicked_type=="submit"){
			   console.log(button_clicked_type);
			   $('#bs-failure-modal-sm').modal();
			   var problem_id = $('#problemContent').attr("data-id");
			   $.ajax({
					  type: 'POST',
					  url: "ContestProblem",
					  data: {query:"solutionAccept",problemId:problem_id,solAccept:"false"},
					  async:false
					});
		   }
	   });
	   
	//console.log("testcase fail from runit is "+testcase_fail);
	}; 
	$("#test").click(function(){
		extra_inputs=0;
		button_clicked_type="test";
		testcaseCounter=0;
		testcase_run=0;
		testcase_fail=0;
		$("#output").text("");
		myinput("test");
		
	});
	$("#programSubmit").click(function(){
		extra_inputs=0;
		button_clicked_type="submit";
		testcase_run=0;
		testcase_fail=0;
		$("#output").text("");
		myinput("submit");
	});	
});