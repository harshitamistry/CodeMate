<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@include file="dbConnect.jsp"%>
<%@include file="header.jsp"%>
<div class="container">
	<div class="row">
		<div class="col-sm-6 col-md-offset-2 col-md-8">
			<div class="list-group ">
				<sql:query dataSource="${snapshot}" var="result">
  				SELECT * from tutorial;
   			</sql:query>

				<sql:query dataSource="${snapshot}" var="resultUser">
  				SELECT TutorialProgressID,TutorialCompleted from user WHERE UserHandle = '<%=session.getAttribute("userHandle")%>';
   			</sql:query>

				<c:forEach var="tutorial" items="${result.rows}">
					<c:choose>

						<c:when
							test="${resultUser.rows[0].TutorialProgressID > tutorial.TutorialID || 
							resultUser.rows[0].TutorialCompleted == true}">
							<a href="Tutorial.jsp?id=${tutorial.TutorialID}"
								class="list-group-item learnlist">
								<h4 class="list-group-item-heading">
									<c:out value="${tutorial.TutorialName}" />
									<img class="emoticons" src="_images/tutFinished2.png">
								</h4>
								<p class="list-group-item-text ">
									<c:out value="${tutorial.TutorialDescription}" />
								</p>

							</a>

						</c:when>

						<c:otherwise>
							<c:choose>

								<c:when
									test="${resultUser.rows[0].TutorialProgressID == tutorial.TutorialID}">
									<a href="Tutorial.jsp?id=${tutorial.TutorialID}"
										class="list-group-item learnlist">
										<h4 class="list-group-item-heading">
											<c:out value="${tutorial.TutorialName}" />
											<img class="emoticons" src="_images/progress2.png">
										</h4>
										<p class="list-group-item-text">
											<c:out value="${tutorial.TutorialDescription}" />
										</p>

									</a>
								</c:when>



								<c:otherwise>
									<a href="#" class="list-group-item learnlist disabled">
										<h4 class="list-group-item-heading">
											<c:out value="${tutorial.TutorialName}" />
										</h4>
										<p class="list-group-item-text">
											<c:out value="${tutorial.TutorialDescription}" />
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

