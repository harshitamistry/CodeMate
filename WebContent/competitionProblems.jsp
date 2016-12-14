<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@include file="header.jsp"%>
<%@include file="dbConnectJava.jsp"%>

<!-- script -->
<script type="text/javascript" src="_js/competitionProblems.js"></script>

<div class="container-fluid">
	<div class="row">
		<div class="col-md-10 col-md-offset-1">
			<!-- Nav tabs -->

			<!-- Tab panes -->
			<div class="tab-content">

				<!-- Show list of specific competition problems in a table format -->
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
						<%
							problemNumber += 1;
						%>


						<tr>
							<td><%=problemNumber%></td>
							<td><a
								href="/CodeMateMVC/ContestProblem?id=${currentProblem.problemId }">
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
					</c:forEach>
				</table>
			</div>

		</div>

	</div>
</div>
</div>
<%@include file="footer.jsp"%>