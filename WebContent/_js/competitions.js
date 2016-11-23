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
			  $("#errorMessages").html("<li>"+err_msg+"</li>");
			  
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
	
	$("#groupRegister").click(function(){
		var err_flag = 0;
		var groupNameCheck = $("#groupName").val();
		var user2=$("#user2").val();
		var contestID=$("#contestButton").attr("data-id");
		
		err_flag = err_flag+$('#groupName').validation_check(/^\S+$/,"Please enter " +
  		"valid group name without spaces or blank value");
		
		$.ajax({
            type:"POST",
            url: "Competitions",
            async:false,
            data:{query:"addGroup",groupName:groupNameCheck,user:user2,contestId:contestID},
            success: function(data) {
            	console.log(data.trim());
					if(data.trim()=="exist"){
						err_flag = err_flag+$('#groupName').validation_check(/^$/,"Group name already exist!"+
						"Please select different group name");
					}
		
					else if(data.trim()=="user participated"){
						err_flag = err_flag+$('#groupName').validation_check(/^$/,"This user is already registered with "+
						"another group.");
					}
					else if(data.trim()=="user does not exist"){
						err_flag = err_flag+$('#user2').validation_check(/^$/,"User with this username does not exist. "+
						"Please select valid user.");
					}
					else if(data.trim()=="same users"){
						err_flag = err_flag+$('#user2').validation_check(/^$/,"Please choose another user as group member.");

					}
					else{
						location.reload();
					}
				}
            });
	})
});