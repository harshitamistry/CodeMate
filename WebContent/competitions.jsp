<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@include file="header.jsp"%>

<!-- scripts -->
<script type="text/javascript" src="_js/competitions.js"></script>

<div class="container-fluid">
	<div class="row">
		<div class="col-md-10 col-md-offset-1">
			<!-- To show list of competitions and their details in a table format -->
			<table class="table table-striped">
				<thead>
					<tr>
						<th>Contest</th>
						<th>Duration</th>
						<th>Type</th>
						<th>Total</th>
						<th></th>
					</tr>
				</thead>
				<c:forEach items="${contestList}" var="contest" varStatus="loop">
					<tr>
						<td><c:out value="${contest.contestName }"></c:out></td>
						<td><c:out value="${contest.contestStartDate }"></c:out> - <c:out
								value="${contest.contestEndDate }"></c:out></td>
						<td><c:out value="${contest.contestType }"></c:out></td>
						<td><c:out value="${contestPoints.get(loop.index)}"></c:out></td>
						<td><jsp:useBean id="now" class="java.util.Date" /> <c:choose>
								<c:when test="${contest.contestEndDate gt now }">
									<c:choose>
										<c:when test="${contestRegistrations.get(loop.index)}">
											<c:choose>
												<c:when test="${contest.contestStartDate lt now}">
													<a
														href="/CodeMateMVC/CompetitionProblems?contestId=${contest.contestId }">Enter</a>
												</c:when>
												<c:otherwise>Registered</c:otherwise>
											</c:choose>
										</c:when>
										<c:otherwise>
											<c:choose>
												<c:when test="${contest.contestType == 'Group'}">
													<a href="javascript:$('.bs-finish-modal-sm').modal();"
														id="contestButton" data-id="${contest.contestId}">Register</a>
												</c:when>
												<c:otherwise>
													<a
														href="/CodeMateMVC/Competitions?query=register&contestId=${contest.contestId}">Register</a>
												</c:otherwise>
											</c:choose>
										</c:otherwise>
									</c:choose>
								</c:when>
								<c:otherwise> Contest ended</c:otherwise>
							</c:choose></td>
					</tr>


				</c:forEach>

			</table>


		</div>
	</div>
</div>

<!--Group contest pop up -->
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

				<h4 class="modal-title run_message" id="myModalLabel">Add Group</h4>
			</div>
			<div class="modal-body ">

				<div class="form-group">
					<ul id="errorMessages">
					</ul>
					<input type="text" class="form-control" id="groupName"
						placeholder="Enter Group Name"> <input type="text"
						class="form-control" id="user2"
						placeholder="Other username(optional)">
				</div>
				<button id="groupRegister" class="btn btn-default">Register</button>

			</div>
		</div>
	</div>
</div>
<%@include file="footer.jsp"%>