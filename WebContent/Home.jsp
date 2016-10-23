<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<%@include file="dbConnect.jsp"%>
<%@include file="header.jsp"%>
<script src="_js/SignUp.js" type="text/javascript"></script>
<div class="container">
	<div class="row">
		<div class="col-sm-6 col-md-3">
			<a class="customLink" href="/CodeMate/learn.jsp">
				<div class="thumbnail">
					<img class="thumbnail_image" src="_images/learn.png" alt="learn">
					<div class="caption">
						<h3>Learn</h3>
						<p>Learn python here.</p>
					</div>
				</div>
			</a>
		</div>
		<div class="col-sm-6 col-md-3">
			<a class="customLink" href="practice.jsp">
				<div class="thumbnail">
					<img class="thumbnail_image" src="_images/practice.png"
						alt="practice">
					<div class="caption">
						<h3>Practice</h3>
						<p>Do practice here.</p>
					</div>
				</div>
			</a>
		</div>
		<div class="col-sm-6 col-md-3">
			<div class="thumbnail">
				<img class="thumbnail_image" src="_images/compete.png" alt="compete">
				<div class="caption">
					<h3>Compete</h3>
					<p>Compete here.</p>
				</div>
			</div>
		</div>
		<div class="col-sm-6 col-md-3">
			<div class="thumbnail">
				<img class="thumbnail_image" src="_images/leaderboard.png"
					alt="leader board">
				<div class="caption">
					<h3>Leader Board</h3>
					<p>Leader board is here.</p>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-6 col-md-3">
			<div class="thumbnail">
				<img class="thumbnail_image" src="_images/user.png" alt="user">
				<div class="caption">
					<h3>
						<%=session.getAttribute("userHandle")%></h3>
					<p>Go to user profile.</p>
				</div>
			</div>
		</div>
		<div class="col-sm-6 col-md-3">
			<div class="thumbnail">
				<img class="thumbnail_image" src="_images/forum.png" alt="forum">
				<div class="caption">
					<h3>Forum</h3>
					<p>Forums here.</p>
				</div>
			</div>
		</div>
		<div class="col-sm-6 col-md-3">
			<div class="thumbnail">
				<img class="thumbnail_image" src="_images/calendar.png"
					alt="calendar">
				<div class="caption">
					<h3>Calendar</h3>
					<p>All events here.</p>
				</div>
			</div>
		</div>
		<div class="col-sm-6 col-md-3">
			<div class="thumbnail">
				<img class="thumbnail_image" src="_images/guide.png"
					alt="leader board">
				<div class="caption">
					<h3>How to</h3>
					<p>How to instructions.</p>
				</div>
			</div>
		</div>
	</div>
</div>
<%@include file="../footer.jsp"%>