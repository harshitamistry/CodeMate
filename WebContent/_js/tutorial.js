$(function(){
	$(".header").css("margin-bottom","0px");
	hljs.initHighlightingOnLoad();
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
	function builtinRead(x) {
	    if (Sk.builtinFiles === undefined || Sk.builtinFiles["files"][x] === undefined)
	            throw "File not found: '" + x + "'";
	    return Sk.builtinFiles["files"][x];
	}
	
	// To run a python program in skulpt
	// grab the code from the textarea
	// get a reference to the pre element for output
	// configure the output function
	// call to Sk.importMainWithBody()
	function runit() { 
	   var prog = myCodeMirror.getValue(); 
	   console.log(prog);
	   var mypre = document.getElementById("output"); 
	   mypre.innerHTML = ''; 
	   Sk.pre = "output";
	   Sk.configure({output:outf, read:builtinRead}); 
	   (Sk.TurtleGraphics || (Sk.TurtleGraphics = {})).target = 'mycanvas';
	   var myPromise = Sk.misceval.asyncToPromise(function() {
	       return Sk.importMainWithBody("<stdin>", false, prog, true);
	   });
	   myPromise.then(function(mod) {
	       console.log('success');
	       var output_val=$("#output").text();
			console.log($("#output").text());
			var tutorial_id_val = $("#tutorialContent").attr("data-id");
			$.post("CompilerServlet",{query:"check_output",output: output_val, tutorial_id:tutorial_id_val},
					function(data) {
				console.log( "Data Loaded: " + data );
				if(data.trim()=="true"){
					$('#bs-success-modal-sm').modal();
				}
				else{
					$('#bs-failure-modal-sm').modal();
				}
			});
	   },
	       function(err) {
		   var errstr=err.toString();
		   errstr=errstr.fontcolor("red");
		   $("#output").append(errstr);
		   $('#bs-failure-modal-sm').modal();
	   });
	}; 
	$("#run").click(function(){
		runit();
		
		
		

	});
	$("#finishBtn").click(function(){
		$.post("UserServlet",{query:"markAsComplete"});
		
	});
	$("#nextLesson").click(function(){
		var tutorialIdValue = $("#tutorialContent").attr("data-id");
		
		$.post("UserServlet",{query:"goToNextLesson",currentTutorial: tutorialIdValue});
		window.location="/CodeMateMVC/Tutorial?id="+(parseInt(tutorialIdValue)+1);
	});
	$("#nextLessonText").click(function(){
		var tutorialIdValue = $("#textTutorialContent").attr("data-id");
		
		$.post("UserServlet",{query:"goToNextLesson",currentTutorial: tutorialIdValue});
		window.location="/CodeMateMVC/Tutorial?id="+(parseInt(tutorialIdValue)+1);
	})
});