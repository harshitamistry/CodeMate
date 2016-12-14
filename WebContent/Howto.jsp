<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@include file="header.jsp"%>

<!-- Showing details of platform features -->
<div class="container-fluid HowToMain">
	<div class="row textTutorial">
		<div
			class="col-md-offset-2 col-md-8 textTutorial textTutorialCol panel">

			<div id="textTutorialContent"
				data-id="<%=request.getParameter("id")%>">

				<h2>About CodeMate</h2>
				<p>CodeMate is a non-commercial programming community created at
					Sheridan College. Our main objective is to help school students to
					learn about programming in a fun & friendly way. Currently we are
					designing our own problems but we are open-sourcing this project so
					that many people can learn from this platform as well as they can
					contribute to our platform.</p>
				<h2>Features</h2>
				<p>
					<span class="howToText"><a href="/CodeMateMVC/Learn">Learn</a></span>&nbsp;
					If you don't know about programming at all, we have a set of
					tutorials just designed for a newbie. We have are teaching <a
						href="https://en.wikipedia.org/wiki/Python_(programming_language)"
						target="_blank">Python</a> language.We are trying our best to help
					you understand all fundamentals of programming, which can lead to
					big things in the future.
				</p>

				<p>
					<span class="howToText"><a href="/CodeMateMVC/Practice">Practice</a></span>&nbsp;
					This page is mainly design to prepare you for a coding competition.
					It has three levels of difficulties, i.e. Easy, Medium, Hard. While
					practicing, you can also earn some points on a successful problem
					submission! Here the idea is a little bit different from the
					tutorial programs. Instead of running your program against one
					input case, here your program will be run against a number of
					inputs in order to test the correctness of your program.
				</p>

				<p>
					<span class="howToText"><a href="/CodeMateMVC/Competitions">Compete</a></span>&nbsp;
					This is where we host our programming competitions. The format is
					pretty much the same as <a href="/CodeMateMVC/Practice">Practice</a>
					page. Except, here the competitions are timed. You have to solve
					problems under a certain time constraint. If your problem passes
					all the test cases then, you will earn many points. We are hosting
					2 types of competitions, i.e. Individual and Group(max limit 2). In
					order to participate in the competition, you will have to register
					first. After successful registration you will be able to
					participate until a particular competition is ended.
				</p>

				<p>
					<span class="howToText"><a href="/CodeMateMVC/LeaderBoard">Leader
							Board</a></span>&nbsp; Here you can see the top ten users, who have
					performed well in the exercises, competitions and practice
					problems. You can narrow it down to your Country as well as you
					School, to see top ten CodeMates.
				</p>

				<p>
					<span class="howToText"><a href="/CodeMateMVC/Profile">Profile</a></span>&nbsp;
					Here you can see your profile, which includes your personal
					information as well as your points and the badge that you have
					earned. You can also edit your profile here.
				</p>

				<p>
					<span class="howToText"><a href="/CodeMateMVC/Forum">Forum</a></span>&nbsp;
					You can ask any question related to the platform, tutorial or
					problems here. Everyone will be able to see your questions and they
					can answer to your questions.
				</p>

				<p>
					<span class="howToText"><a href="/CodeMateMVC/Calendar">Calendar</a></span>&nbsp;
					All events are posted here. You can keep track of competitions, any
					upcoming talk or any other events from the Calendar.
				</p>
			</div>

		</div>
	</div>
</div>

<!-- Complete tutorial pop up -->
<div class="modal fade bs-finish-modal-sm" tabindex="-1" role="dialog"
	aria-labelledby="mySmallModalLabel">
	<div class="modal-dialog modal-sm" role="document">
		<div class="modal-content ">
			<div class=" modal-header blue_modal_header">
				<button type="button" class="close white_close_button"
					data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<img src="_images/congratulate.png" class="run_smiley">

				<h4 class="modal-title run_message" id="myModalLabel">Congratulations!</h4>
			</div>
			<div class="modal-body ">
				<p>You have finished all tutorials !</p>

			</div>
			<div class="modal-footer">
				<button type="button"
					onclick="window.location='/CodeMate/Home.jsp;'"
					class="btn loginButton">Home</button>
				<button type="button"
					onclick="window.location='/CodeMate/practice.jsp;'"
					class="btn loginButton">Practice problems</button>
			</div>
		</div>
	</div>
</div>

<%@include file="footer.jsp"%>
