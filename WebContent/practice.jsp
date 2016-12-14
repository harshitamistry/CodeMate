<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@include file="header.jsp"%>
<%@include file="dbConnectJava.jsp"%>

<!-- script -->
<script type="text/javascript" src="_js/practice.js"></script>

<div class="container-fluid">
	<div class="row">
		<div class="col-md-10 col-md-offset-1">
			<!-- Nav tabs -->
			<ul class="nav nav-tabs" role="tablist">
				<li role="presentation" class="active practiceTab"><a
					href="#easy" aria-controls="easy" role="tab" data-toggle="tab">easy</a></li>
				<li role="presentation" class="practiceTab"><a href="#medium"
					aria-controls="medium" role="tab" data-toggle="tab">medium</a></li>
				<li role="presentation" class="practiceTab"><a href="#hard"
					aria-controls="hard" role="tab" data-toggle="tab">hard</a></li>

			</ul>

			<!-- Tab panes -->
			<div class="tab-content">

				<c:forEach items="${tutorialComplexity}" var="complexity">
					<c:choose>
						<c:when test="${complexity ==\"easy\"}">
							<div role="tabpanel" class="tab-pane active" id="${complexity }"
								name="probComplexity">
						</c:when>
						<c:otherwise>
							<div role="tabpanel" class="tab-pane" id="${complexity }"
								name="probComplexity">
						</c:otherwise>
					</c:choose>
					<table class="table table-striped">
						<thead>
							<tr>
								<th>Problem ID</th>
								<th>Problem Title</th>
								<th>Result</th>
								<th>Points</th>
								<th>Points Earned</th>
							</tr>
						</thead>

						<%
							int problemNumber = 0;
						%>
						<c:forEach items="${problemList}" var="currentProblem">
							<c:if test="${currentProblem.complexity == complexity}">
								<%
									problemNumber += 1;
								%>


								<tr>
									<td><%=problemNumber%></td>
									<td><a
										href="/CodeMateMVC/practiceProblem?id=${currentProblem.problemId }">
											<c:out value="${currentProblem.problemName}"></c:out>
									</a></td>


									<%
										boolean solved = false;
									%>
									<c:forEach items="${userSubmission}" var="submission">
										<c:if
											test="${submission.problems.problemId == currentProblem.problemId}">
											<%
												solved = true;
											%>
											<td><c:choose>
													<c:when test="${submission.accepted ==true}"> Successful</c:when>
													<c:otherwise> Unsuccessful</c:otherwise>
												</c:choose></td>
											<td><c:out value="${ currentProblem.problemPoints}"></c:out></td>
											<td><c:out value="${submission.points }"></c:out></td>
										</c:if>
									</c:forEach>
									<td>
										<%
											if (solved == false) {
										%> Not solved
									</td>
									<td><c:out value="${ currentProblem.problemPoints}"></c:out></td>
									<td>0</td>
									<%
										}
									%>

								</tr>
							</c:if>
						</c:forEach>
					</table>
			</div>
			</c:forEach>

		</div>

	</div>
</div>
</div>
<%@include file="footer.jsp"%>