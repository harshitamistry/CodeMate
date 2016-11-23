

$(function(){
	
	$.fn.validation_check = function(valid_regex,err_msg){
		if($(this).val() == "" || !valid_regex.test($(this).val())){
			// $(this).tooltip('destroy');
			  $(this).parent().addClass('has-error');
			 
//			  $(this).attr("data-html","true").attr("data-toggle","tooltip").attr("title",err_msg).
//			  			attr("data-placement","right");
//			  $(this).tooltip({  template: '<div class="tooltip"><div class="error-tooltip-arrow"></div><div class="tooltip-inner error-tooltip-inner"></div></div>'});
//			  $('[data-toggle="tooltip"]').tooltip();
			  
			  //add error text
			  $("#errorMessages").html($("#errorMessages").html()+"<li>"+err_msg+"</li>");
			  
			  $(this).before('<i class="glyphicon glyphicon-remove"></i>');
			  $(this).parent().addClass('inner-addon right-addon');
			  return 1;
		  }
		  else{
			  $(this).parent().removeClass('has-error');
			  $(this).tooltip('destroy');
			  //$(this).removeAttr('data-html data-toggle title data-placement data-original-title');
			  $(this).parent().find('i').remove();
			  $(this).parent().removeClass('inner-addon right-addon');
			  return 0;
		  }
	}

	
	$('select#countryDropDown').change(function(){
		
		 var country_name = $('select#countryDropDown option:selected').text();
		 $.get("DbServlet",$.param({query:"load_schools",countryname:country_name}),
				 function(response) {
			 	
			 	//console.log(response[0]);
		        var select = $('#schoolDropDown');
				 $(select).find('option').remove().end().selectpicker('refresh');
				 $(select).append($("<option>Select School</option>")).selectpicker('refresh');
				 $(select).append($("<option value='Other'>Other</option>")).selectpicker('refresh');
		          for(i=0;i<response.length;i+=2){
		        	  $(select).append($("<option value='"+response[i]+"'>"+response[i+1]+"</option>")).selectpicker('refresh');
		          };
		          console.log("success");
		 })
		 .fail(function(){
			 alert("request failed");
		 });
	  });
	  
	  $('select#schoolDropDown').change(function(){
		  console.log($('select#schoolDropDown option:selected').val());
		  var school_name = $('select#schoolDropDown option:selected').val();
		  if(school_name=='Other'){
			  $("#userNameTR").before("<tr id='otherOptions'><td>"+
			  "<input type='text' id='cityInput' class='form-control' data-html='true' placeholder='Enter City'></td>" +
			  "<td><input type='text' id='schoolInput' class='form-control' data-html='true' placeholder='Enter School'></td></tr>");
		  }
		  else{
			  $("#otherOptions").remove();
		  }
		  if(/^Select School$/.test($('select#schoolDropDown').val())){
			  $("[data-id=schoolDropDown]").parent().parent().addClass('has-error');
			  $("[data-id=schoolDropDown]").tooltip('destroy');
			  $("[data-id=schoolDropDown]").attr("data-html","true").attr("data-toggle","tooltip").attr("title","wronggg").
			  			attr("data-placement","right");
			  $("[data-id=schoolDropDown]").tooltip({  template: '<div class="tooltip"><div class="error-tooltip-arrow"></div><div class="tooltip-inner error-tooltip-inner"></div></div>'});
			  $('[data-toggle="tooltip"]').tooltip();
			  $("[data-id=schoolDropDown]").attr("data-toggle","dropdown");
			  $("[data-id=schoolDropDown]").before('<i class="glyphicon glyphicon-remove"></i>');
			  $("[data-id=schoolDropDown]").parent().addClass('inner-addon right-addon');
		  }
		 
		  else{
			  $("[data-id=schoolDropDown]").parent().parent().removeClass('has-error');
			  $("[data-id=schoolDropDown]").removeAttr('data-html title data-placement data-original-title');
			  $("[data-id=schoolDropDown]").parent().find('i').remove();
			  $("[data-id=schoolDropDown]").parent().removeClass('inner-addon right-addon');
			  console.log("in-valid for ","[data-id=schoolDropDown]");
		  }
	  });
	  
	  
	
	  $('#createUser').click(function(){
		  $("#errorMessages").html("");
		  var reggg = new RegExp(/[A-Z0-9\.\_\%\+\-]+\@[A-Z0-9.-]+\.[A-Z]{2,6}/i);
		  var pwdReg = new RegExp($('#upwd').val());
		  
		  var err_flag = 0;
		  
		  err_flag = err_flag+$('#fname').validation_check(/^[a-zA-Z]+$/,"Please enter " +
			  		"valid first name without spaces or blank value");
		  err_flag = err_flag+$('#lname').validation_check(/^[a-zA-Z]+$/,"Please enter " +
			  		"valid last name without spaces or blank value");
		  err_flag = err_flag+$('#uname').validation_check(/^\S+$/,"Please enter " +
	  		"valid username without spaces or blank value");
		  err_flag=err_flag+$('#usereid').validation_check(reggg,"Please enter valid email address");
		  
		  if(/^Select School$/.test($('select#schoolDropDown').val())){
			  $("[data-id=schoolDropDown]").parent().parent().addClass('has-error');
			  $("[data-id=schoolDropDown]").tooltip('destroy');
			  $("[data-id=schoolDropDown]").attr("data-html","true").attr("data-toggle","tooltip").attr("title","School required").
			  			attr("data-placement","right");
			  $("[data-id=schoolDropDown]").tooltip({  template: '<div class="tooltip"><div class="error-tooltip-arrow"></div><div class="tooltip-inner error-tooltip-inner"></div></div>'});
			  $('[data-toggle="tooltip"]').tooltip();
			  $("[data-id=schoolDropDown]").attr("data-toggle","dropdown");
			  $("[data-id=schoolDropDown]").before('<i class="glyphicon glyphicon-remove"></i>');
			  $("[data-id=schoolDropDown]").parent().addClass('inner-addon right-addon');
			  err_flag++;
		  }
		 
		  else{
			  $("[data-id=schoolDropDown]").parent().parent().removeClass('has-error');
			  $("[data-id=schoolDropDown]").removeAttr('data-html title data-placement data-original-title');
			  $("[data-id=schoolDropDown]").parent().find('i').remove();
			  $("[data-id=schoolDropDown]").parent().removeClass('inner-addon right-addon');
			  console.log("in-valid for ","[data-id=schoolDropDown]");
		  }
		  
		  err_flag=err_flag+$('#upwd').validation_check(/^.*(?=.{7,})(?=.*[a-zA-Z])(?=.*\d).*$/,
				  "Password must be at least 7 characters and 1 number and 1 alphabet.");
		  
		  //check if password and confirm password matches
		  err_flag= err_flag + $('#con-upwd').validation_check(pwdReg,
		  "Password does not match");
		  
		  if(!$('#termsConditions').prop("checked")){
			  err_flag = err_flag+1;
			  $("#errorMessages").html($("#errorMessages").html()+"<li>"+"Please agree terms and conditions."+"</li>");

			 
		  }
		  
		  
		  
		//check if username already exist in the database
		  $.ajax({
              type:"POST",
              url: "UserServlet",
              async:false,
              data:{query:"checkUser",userName:$('#uname').val()},
              success: function(data) {
					if(data.trim()=="exist"){
						err_flag = err_flag+$('#uname').validation_check(/^$/,"Username already exist!"+
								"Please select different username");
						console.log("error flag is "+err_flag);
					}
				}
              });
		  
		  $.ajax({
              type:"POST",
              url: "UserServlet",
              async:false,
              data:{query:"checkUserEmail",userEmail:$('#usereid').val()},
              success: function(data) {
					if(data.trim()=="exist"){
						err_flag = err_flag+$('#usereid').validation_check(/^$/,"User is already registered with this email"+
								".Please check your email");
					}
				}
              });		  
		  if(err_flag == 0){
			  
			  //TODO add extra validations
			  var f_name = $('#fname').val();
			  var l_name = $('#lname').val();
			  var user_email = $('#usereid').val();
			  var user_countryID = $('select#countryDropDown option:selected').val();
			  var user_schoolID = $('select#schoolDropDown option:selected').val();
			  var user_uname = $('#uname').val();
			  var user_pwd = $('#upwd').val();
			  if(user_schoolID == "Other") {
				  school_city = $('#cityInput').val();
				  user_school = $('#schoolInput').val();
	
				  $.ajax({
		              type:"POST",
		              url: "UserServlet",
		              async:false,
		              data:{query:"CreateUserQuery",first_name:f_name,
						  last_name:l_name,email:user_email,
						  countryID:user_countryID,schoolCity:school_city,
						  schoolName:user_school,
						  username:user_uname,userPassword:user_pwd
		              },
		              success: function(data) {
							if(data.trim()=="true"){
								window.location="Home.jsp";
							}
		              }
		             });
				  
				 /* $.post("UserServlet",$.param({query:"CreateUserQuery",first_name:f_name,
					  last_name:l_name,email:user_email,
					  countryID:user_countryID,schoolCity:school_city,
					  schoolName:user_school,
					  username:user_uname,userPassword:user_pwd, }));*/
				 
			  }
			  else{
				  $.ajax({
		              type:"POST",
		              url: "UserServlet",
		              async:false,
		              data:{query:"CreateUserQuery",first_name:f_name,
						  last_name:l_name,email:user_email,
						  countryID:user_countryID,schoolID:user_schoolID,
						  username:user_uname,userPassword:user_pwd},
			              success: function(data) {
								if(data.trim()=="true"){
									window.location="Home.jsp";
								}
			              }
		             });
			 /* $.post("UserServlet",$.param({query:"CreateUserQuery",first_name:f_name,
				  last_name:l_name,email:user_email,
				  countryID:user_countryID,schoolID:user_schoolID,
				  username:user_uname,userPassword:user_pwd}));*/
			  }
		  }
	  });
});