<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<%@include file="dbConnect.jsp"%>
<%@include file="header.jsp"%>

<!--  scripts -->
<script src="_js/SignUp.js" type="text/javascript"></script>

<!-- Home page showing list of features -->
<div class="container homeContainer">
	<div class="row">
		<div class="col-sm-6 col-md-3">
			<a class="customLink" href="/CodeMateMVC/Learn">
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
			<a class="customLink" href="/CodeMateMVC/Practice">
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
			<a class="customLink" href="/CodeMateMVC/Competitions">
				<div class="thumbnail">
					<img class="thumbnail_image" src="_images/compete.png"
						alt="compete">
					<div class="caption">
						<h3>Compete</h3>
						<p>Compete here.</p>
					</div>
				</div>
			</a>
		</div>
		<div class="col-sm-6 col-md-3">
			<a class="customLink" href="/CodeMateMVC/LeaderBoard">
				<div class="thumbnail">
					<img class="thumbnail_image" src="_images/leaderboard.png"
						alt="leader board">
					<div class="caption">
						<h3>Leader Board</h3>
						<p>Leader board is here.</p>
					</div>
				</div>
			</a>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-6 col-md-3">
			<a class="customLink" href="/CodeMateMVC/Profile">
				<div class="thumbnail">
					<img class="thumbnail_image" src="_images/user.png" alt="user">
					<div class="caption">
						<h3>
							<%=session.getAttribute("userHandle")%></h3>
						<p>Go to user profile.</p>
					</div>
				</div>
			</a>
		</div>
		<div class="col-sm-6 col-md-3">
			<a class="customLink" href="Forum">
				<div class="thumbnail">
					<img class="thumbnail_image" src="_images/forum.png" alt="forum">
					<div class="caption">
						<h3>Forum</h3>
						<p>Forums here.</p>
					</div>
				</div>
			</a>
		</div>
		<div class="col-sm-6 col-md-3">
			<a class="customLink" href="/CodeMateMVC/Calendar">

				<div class="thumbnail">
					<img class="thumbnail_image" src="_images/calendar.png"
						alt="calendar">
					<div class="caption">
						<h3>Calendar</h3>
						<p>All events here.</p>
					</div>
				</div>
			</a>
		</div>
		<div class="col-sm-6 col-md-3">
			<a class="customLink" href="Howto.jsp">
				<div class="thumbnail">
					<img class="thumbnail_image" src="_images/guide.png"
						alt="leader board">
					<div class="caption">
						<h3>How to</h3>
						<p>How to instructions.</p>
					</div>
				</div>
			</a>
		</div>
	</div>
</div>
<%@include file="../footer.jsp"%>