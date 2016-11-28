<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%

if((session.getAttribute("userHandle") == null || session.getAttribute("userHandle").equals("")) && !request.getRequestURI().equals("/CodeMateMVC/SignUp.jsp") && !request.getRequestURI().equals("/CodeMateMVC/index.jsp") && !request.getRequestURI().equals("/CodeMateMVC/SignUp")){
	response.sendRedirect("/CodeMateMVC/SignUp");
}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

<!-- jQuery -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

<!-- jQuery UI -->
<script src="_js/jquery-ui.js"></script>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.10.0/css/bootstrap-select.min.css">

<!-- Highlight.js -->
<script src="//cdnjs.cloudflare.com/ajax/libs/highlight.js/9.5.0/highlight.min.js"></script>


<!-- Highlight.js css -->
<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/highlight.js/9.5.0/styles/default.min.css">

<!-- Customized css -->
<link rel="stylesheet"  href = "_css/CustomStyle.css">


<!-- jquery ui css -->
<link rel="stylesheet"  href = "_css/jquery-ui.css">

<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

<!-- Latest compiled and minified JavaScript -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.10.0/js/bootstrap-select.min.js"></script>

<!-- skulpt js -->
<script src="_js/skulpt.min.js"></script>
<script src="_js/skulpt-stdlib.js"></script>

<!-- Customized js -->
<script src="_js/customJS.js" type="text/javascript"></script>

<!-- calendar -->
<script src="_js/moment.min.js" type="text/javascript"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/fullcalendar/3.0.1/fullcalendar.min.js"></script>
<link rel="stylesheet"  href = "//cdnjs.cloudflare.com/ajax/libs/fullcalendar/3.0.1/fullcalendar.min.css">


</head>
<body>
<div id="wrap-for-footer">

<!-- Modal for invalid login -->

<div class="modal fade" id="bs-loginfailure-modal-sm" tabindex="-1"
				role="dialog" aria-labelledby="mySmallModalLabel">
				<div class="modal-dialog modal-sm" role="document">
					<div class="modal-content ">
						<div class=" modal-header red_modal_header">
							<button type="button" class="close white_close_button"
								data-dismiss="modal" aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<img src="_images/error.png" class="run_smiley">
							<h4 class="modal-title run_message" id="error_label">Login unsuccessful</h4>

						</div>
						<div class="modal-body ">Invalid username/password!</div>
					</div>
				</div>
			</div>


<nav class="navbar navbar-static-top header">
<div class="container-fluid">
<div class="row">
<div class = "col-xs-4 col-md-2">
<a href="/CodeMateMVC/Home.jsp">
<img src = "_images/logo.png"  height="50" width="227" class="headerImage"></a>
</div>
<c:choose>
<c:when test="${sessionScope.userHandle == null}">
<div class="form-inline" >
  <div class="form-group col-md-2 col-md-offset-2 login">
    <input type="text" class="form-control" id="loginUname" name="username" placeholder="Username">
  </div>
  <div class="form-group col-md-2 login">
    <input type="password" class="form-control" id="loginPassword" name="userPassword" placeholder="Password">
  </div>
  <div class="col-md-1 login">
  <button class="btn loginButton" id="loginButton">Login</button>
  </div>
  </div>
  <!-- 
  <div class="col-md-1 loginButtons login">
  
  <button class="btn imgSize" id="googleBtn"></button>
<button class="btn imgSize" id="facebookBtn"></button>
</div> -->





</c:when>
<c:otherwise>
<div class="btn-group col-xs-4 col-md-1 col-md-offset-7 dropdown" id="gotoDDCol">
  	<a class="btn dropdown-toggle DD" data-toggle="dropdown" id="dropdownMenuUser" aria-haspopup="true" aria-expanded="false">
  		<span class="titleDD">GO TO</span>
	</a>
  	<ul class="dropdown-menu" aria-labelledby="dropdownMenuUser" class="DDul">
		<li><a href="/CodeMateMVC/Home.jsp">Home</a></li>
		<li><a href="/CodeMateMVC/Learn">Learn</a></li>
		<li><a href="/CodeMateMVC/Practice">Practice</a></li>
		<li><a href="/CodeMateMVC/Competitions">Compete</a></li>
		<li><a href="/CodeMateMVC/LeaderBoard">Leader Board</a></li>
		<li><a href="/CodeMateMVC/Forum">Forum</a></li>
		<li><a href="/CodeMateMVC/Calendar">Calendar</a></li>
	</ul>
</div>

<div class="btn-group col-xs-4 col-md-2 dropdown" id="userDDCol">
  	<a class="btn dropdown-toggle DD" data-toggle="dropdown" id="dropdownMenuUser" aria-haspopup="true" aria-expanded="false">
  		<span id="userImageDD"><div class="circleDiv"><img src="_images/user.png" id="userImage"/></div></span>
		<span class="titleDD" id="userNameDD"><%= session.getAttribute( "userHandle" ) %></span>
	</a>
  	<ul class="dropdown-menu" aria-labelledby="dropdownMenuUser" class="DDul">
		<li><a href="/CodeMateMVC/Profile">Profile</a></li>
		<li><a href="Howto.jsp">Help</a></li>
		<li><a href="#" id="logout">Logout</a></li>
		
		
	</ul>
</div>
</c:otherwise>
</c:choose>
</div>
</div>
</nav>
