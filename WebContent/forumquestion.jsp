<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@include file="header.jsp"%>
<div class="container">
<div class="row">

<div class="col-md-8 col-md-offset-2" >
<div class="page-header">
	<h1><c:out value="${questionHeading.question}"></c:out></h1>
	
</div>
<form class="form-inline" action="/CodeMateMVC/Forumquestion?query=addAnswer&questionId=${questionHeading.questionId}" method="post">
				<div class="form-group" style="width:calc(100% - 60px);">
					<input type="text" style="width:100%"
						class="form-control" name="answer" 
						placeholder="Your response here...">
				</div>
				<button type="submit" class="btn loginButton">Post</button>
			</form>
			<table class="table table-hover">
				<thead>
					<tr>
						<th>Responses</th>
					</tr>
				</thead>
				<c:forEach items="${answerList}" var="answer">
					<tr>
						<td><c:out value="${answer.id.answer}"></c:out>
						</td>
					</tr>
				</c:forEach>
			</table>

</div>
</div>

</div>
<%@include file="../footer.jsp"%>