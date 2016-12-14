<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@include file="header.jsp"%>

<!-- scripts -->
<script src="_js/forum.js"></script>

<!-- shows list of questions in tabular format -->
<div class="container">
	<div class="row">
		<div class="col-md-12">
			<form action="/CodeMateMVC/Forum?query=newQuestion" method="post">
				<div class="form-group">
					<label for="question">New question</label> <input type="text"
						class="form-control" name="question"
						placeholder="Ask your question here...">
				</div>
				<button type="submit" class="btn loginButton">Post</button>
			</form>
		</div>
	</div>
	<div class="row">
		<div class="col-md-12">
			<table class="table table-striped">
				<thead>
					<tr>
						<th>Questions</th>
						<th><input type="text" class="form-control" id="question"
							placeholder="Search for questions"></th>
					</tr>
				</thead>
				<c:forEach items="${questionList}" var="question">
					<tr>
						<td colspan="2"><a
							href="/CodeMateMVC/Forumquestion?id=${question.questionId}"><c:out
									value="${question.question}"></c:out></a></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</div>

<%@include file="../footer.jsp"%>