<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@include file="dbConnect.jsp"%>
<%@include file="header.jsp"%>
<%@include file="dbConnectJava.jsp"%>

<!-- scripts -->
<script type="text/javascript" src="_js/practiceProblem.js"></script>
<script type="text/javascript" src="_js/codemirror.js"></script>
<script type="text/javascript" src="_js/python.js"></script>

<!-- CodeMirror css -->
<link rel="stylesheet" href="_css/codemirror.css">

<!-- Shows problem content, code editor and terminal in three panels -->
<div class="tutorial_containers">

	<div class="tutorial">

		<div id="problemContent" data-id="<%=request.getParameter("id")%>">


			<h2>
				<c:out value="${practiceProblem.problemName}" />
			</h2>
			<p>
				<c:out value="${practiceProblem.problemQuestion}" escapeXml="false" />
			</p>
		</div>

	</div>
	<div class="codeeditor">
		<div class="editor"></div>
		<div class="action_buttons">
			<button type="button" class="btn loginButton action_btn" id="test">Test</button>
			<button type="button" class="btn loginButton action_btn"
				id="programSubmit">Submit</button>

		</div>

		<div class="modal fade" id="bs-success-modal-sm" tabindex="-1"
			role="dialog" aria-labelledby="mySmallModalLabel">
			<div class="modal-dialog modal-sm" role="document">
				<div class="modal-content ">
					<div class=" modal-header green_modal_header">
						<button type="button" class="close white_close_button"
							data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<img src="_images/runSuccess.png" class="run_smiley">
						<h4 class="modal-title run_message" id="sucess_label">Success!</h4>
					</div>
					<div class="modal-body ">Good job! You have passed all
						testcases.</div>
					<div class="modal-footer">

						<button type="button"
							onclick="window.location='/CodeMateMVC/Home.jsp;'"
							class="btn loginButton">Home</button>
						<button type="button"
							onclick="window.location='/CodeMateMVC/Practice;'"
							class="btn loginButton">Practice problems</button>

					</div>
				</div>
			</div>
		</div>

		<div class="modal fade" id="bs-failure-modal-sm" tabindex="-1"
			role="dialog" aria-labelledby="mySmallModalLabel">
			<div class="modal-dialog modal-sm" role="document">
				<div class="modal-content ">
					<div class=" modal-header red_modal_header">
						<button type="button" class="close white_close_button"
							data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<img src="_images/error.png" class="run_smiley">
						<h4 class="modal-title run_message" id="error_label">You have
							an error!</h4>

					</div>
					<div class="modal-body ">See the error details in the
						terminal.</div>
				</div>
			</div>
		</div>


		<div class="modal fade" id="bs-unsuccessful-modal-sm" tabindex="-1"
			role="dialog" aria-labelledby="mySmallModalLabel">
			<div class="modal-dialog modal-sm" role="document">
				<div class="modal-content ">
					<div class=" modal-header red_modal_header">
						<button type="button" class="close white_close_button"
							data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<img src="_images/testcaseFail.png" class="run_smiley">
						<h4 class="modal-title run_message" id="error_label">Unsuccessful!</h4>

					</div>
					<div class="modal-body ">Your program is not passing all
						testcases.</div>
				</div>
			</div>
		</div>
	</div>
	<div class="terminal">

		<div class="terminalStyle">
			<pre id="output">

				
				
				</pre>
		</div>

	</div>
</div>





<%@include file="footer.jsp"%>
