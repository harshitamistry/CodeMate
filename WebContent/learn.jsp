<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@include file="dbConnect.jsp"%>
<%@include file="header.jsp"%>

<!-- Shows list of tutorials -->
<div class="container">
	<div class="row">
		<div class="col-sm-6 col-md-offset-2 col-md-8">
			<div class="list-group ">




				<c:forEach var="tutorial" items="${tutorialList}">
					<c:choose>
						<c:when
							test="${resultUser.tutorial.tutorialId > tutorial.tutorialId || 
							resultUser.tutorialCompleted == true}">
							<a href="Tutorial?id=${tutorial.tutorialId}"
								class="list-group-item learnlist">
								<h4 class="list-group-item-heading">
									<c:out value="${tutorial.tutorialName}" />
									<img class="emoticons" src="_images/tutFinished2.png">
								</h4>
								<p class="list-group-item-text ">
									<c:out value="${tutorial.tutorialDescription}" />
								</p>

							</a>

						</c:when>

						<c:otherwise>
							<c:choose>

								<c:when
									test="${resultUser.tutorial.tutorialId == tutorial.tutorialId}">
									<a href="Tutorial?id=${tutorial.tutorialId}"
										class="list-group-item learnlist">
										<h4 class="list-group-item-heading">
											<c:out value="${tutorial.tutorialName}" />
											<img class="emoticons" src="_images/progress2.png">
										</h4>
										<p class="list-group-item-text">
											<c:out value="${tutorial.tutorialDescription}" />
										</p>

									</a>
								</c:when>



								<c:otherwise>
									<a href="#" class="list-group-item learnlist disabled">
										<h4 class="list-group-item-heading">
											<c:out value="${tutorial.tutorialName}" />
										</h4>
										<p class="list-group-item-text">
											<c:out value="${tutorial.tutorialDescription}" />
										</p>


									</a>
								</c:otherwise>
							</c:choose>

						</c:otherwise>
					</c:choose>



				</c:forEach>
			</div>
		</div>
	</div>
</div>

<%@include file="footer.jsp"%>

