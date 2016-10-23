<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@include file="dbConnect.jsp"%>
<%@include file="header.jsp"%>
<%@include file="dbConnectJava.jsp"%>
<script type="text/javascript" src="_js/tutorial.js"></script>
<script type="text/javascript" src="_js/codemirror.js"></script>
<script type="text/javascript" src="_js/python.js"></script>

<!-- CodeMirror css -->
<link rel="stylesheet" href="_css/codemirror.css">

<%! 
	public String checkTutorialCount(Statement stmt, HttpServletRequest request){
		String sql = "SELECT count(*) FROM tutorial";
		try{
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			int lastTutorial = rs.getInt(1);
			System.out.println("count is"+lastTutorial);
			rs.close();
			if(lastTutorial == Integer.parseInt(request.getParameter("id"))){
				return "finish";
			}
			else{
				return "incomplete";
			} 
		}
		catch(Exception e){
			e.printStackTrace();
			return "";
		}
	
}
%>
<%

String sql ="";
try{

	sql = "SELECT TutorialProgressID FROM user WHERE UserHandle='"
			+ session.getAttribute("userHandle") + "'";
	ResultSet rs = stmt.executeQuery(sql);
	rs.next();
	int userTutorial = rs.getInt("TutorialProgressID");
	rs.close();

	if (userTutorial < Integer.parseInt(request.getParameter("id"))) {
		response.sendRedirect("/CodeMate/learn.jsp");
	}

	sql = "SELECT TutorialType FROM tutorial WHERE TutorialID="
			+ request.getParameter("id");
	rs = stmt.executeQuery(sql);
	if(!rs.isBeforeFirst()){
		rs.close();
%>
<script>
window.location="/CodeMate/learn.jsp";
</script>
<%		
	}
	else{
	rs.next();
	String tutorialType = rs.getString("TutorialType");

	rs.close();
	if (tutorialType.equals("Program")) {
%>
<div class="tutorial_containers">
			<div class="tutorial">

			<div id="tutorialContent" data-id="<%=request.getParameter("id")%>">

				<sql:query dataSource="${snapshot}" var="tutContent">
  				SELECT TutorialName, TutorialContent from tutorial WHERE TutorialID = <%=request.getParameter("id")%>;
   			</sql:query>
				<h2>
					<c:out value="${tutContent.rows[0].TutorialName}" />
				</h2>
				<p>
					<c:out value="${tutContent.rows[0].TutorialContent}"
						escapeXml="false" />
				</p>
			</div>
			<div id="tutorialExercise" class="panel panel-default">
				<sql:query dataSource="${snapshot}" var="tutExercise">
  				SELECT * from problems p INNER JOIN exerciseProblem e ON p.ProblemID = e.ProblemID
  				WHERE e.TutorialID = <%=request.getParameter("id")%>;
   			</sql:query>

				<div class="panel-heading">
					<c:out value="${tutExercise.rows[0].ProblemName}" />
				</div>
				<div class="panel-body" id="questionBody">
					<c:out value="${tutExercise.rows[0].ProblemQuestion}"
						escapeXml="false" />
				</div>

			</div>
		</div>
		<div class="codeeditor">
			<div class="editor"></div>
			<div class="action_buttons">
				<button type="button" class="btn loginButton action_btn"
					data-toggle="modal" data-target=".bs-example-modal-sm" id="hint">Hint</button>
				<button type="button" class="btn loginButton action_btn" id="run">Run</button>

			</div>
			<div class="modal fade bs-example-modal-sm" tabindex="-1"
				role="dialog" aria-labelledby="mySmallModalLabel">
				<div class="modal-dialog modal-sm" role="document">
					<div class="modal-content ">
						<div class=" modal-header blue_modal_header">
							<button type="button" class="close white_close_button"
								data-dismiss="modal" aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<img src="_images/hint.png" class="run_smiley">

							<h4 class="modal-title run_message" id="myModalLabel">Hint</h4>
						</div>
						<div class="modal-body ">
							<c:out value="${tutExercise.rows[0].Hint}" />
						</div>
					</div>
				</div>
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
							<h4 class="modal-title run_message" id="sucess_label">Success</h4>
						</div>
						<div class="modal-body ">You have success</div>
						<div class="modal-footer">
						<%
						
						String tutorialCount = checkTutorialCount(stmt,request); 
						if(tutorialCount.equals("finish")){%>
						<button type="button" id="finishBtn" data-toggle="modal" data-target=".bs-finish-modal-sm" class="btn loginButton">Finish</button>
						<%} else{ %>
						<button type="button" id="nextLesson" class="btn loginButton">Next
								lesson!</button>
						<% }%>
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
							<h4 class="modal-title run_message" id="error_label">You
								have an error!</h4>

						</div>
						<div class="modal-body ">You have failure</div>
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

<%
	} else {
		//if tutorial type is not program
%>
<div class="container-fluid textTutorialMain">
	<div class="row textTutorial">
		<div class="col-md-offset-2 col-md-8 textTutorial textTutorialCol panel">

			<div id="textTutorialContent" data-id="<%=request.getParameter("id")%>">

				<sql:query dataSource="${snapshot}" var="tutContent">
  				SELECT TutorialName, TutorialContent from tutorial WHERE TutorialID = <%=request.getParameter("id")%>;
   			</sql:query>
				<h2>
					<c:out value="${tutContent.rows[0].TutorialName}" />
				</h2>
				<p>
					<c:out value="${tutContent.rows[0].TutorialContent}"
						escapeXml="false" />
				</p>
				
				<%
						
						String tutorialCount = checkTutorialCount(stmt,request); 
						if(tutorialCount.equals("finish")){%>
						<button type="button" id="finishBtn" data-toggle="modal" data-target=".bs-finish-modal-sm" class="btn loginButton">Finish</button>
						<%} else{ %>
						<button type="button" id="nextLessonText" class="btn loginButton">Next
								lesson!</button>
						<% }%>
			</div>
			
		</div>
	</div>
</div>
<%
	}
	}
}
catch(Exception e){
	e.printStackTrace();
}
%>

<!-- Complete tutorial pop up -->
<div class="modal fade bs-finish-modal-sm" tabindex="-1"
				role="dialog" aria-labelledby="mySmallModalLabel">
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
							<p> You have finished all tutorials ! </p>
							
						</div>
						<div class="modal-footer">
						<button type="button" onclick="window.location='/CodeMate/Home.jsp;'" class="btn loginButton"> Home</button>
						<button type="button" onclick="window.location='/CodeMate/practice.jsp;'" class="btn loginButton"> Practice problems</button>
						</div>
					</div>
				</div>
			</div>

<%@include file="footer.jsp"%>
