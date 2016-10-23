$(function () {
  $('[data-toggle="tooltip"]').tooltip();
  $('.selectpicker').selectpicker({
	  size: 6
	});
  $("#logout").click(function(){
	  $.post("UserServlet",$.param({query:"logout"}));
	  window.location="/CodeMate/SignUp.jsp";
  });
  $('#loginButton').click(function(){
	  var loginUname = $('#loginUname').val();
	  var loginPwd = $('#loginPassword').val();
	  console.log(loginUname);
	  $.post("UserServlet",$.param({query:"LoginUserQuery",
		  username:loginUname,userPassword:loginPwd}),function(response){
		  if(response.trim()=='success'){
			  window.location="Home.jsp";
		  }
		  else{
$("#bs-loginfailure-modal-sm").modal();
$("#loginUname").parent().addClass('has-error');
$("#loginPassword").parent().addClass('has-error');
		  }
		  });
  });
  hljs.initHighlightingOnLoad();
});

