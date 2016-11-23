<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<%@include file="header.jsp"%>
<script src="_js/leaderboard.js"></script>
<div class="container">
<div class="row">
<div class="col-md-8 col-md-offset-2">
<h1 align="center">Leader Board</h1>
<div id="dropdownsLeaderboard">
<select class="selectpicker" id="countryDropDownLB">

     <option selected="selected" value="All Countries">All Countries</option>
    <c:forEach var="country" items="${countryList}">
    
    	<option  value="<c:out value="${country.countryId}"/>"><c:out value="${country.countryName}"/></option>
   
    </c:forEach>
  </select>
  <select class="selectpicker" id="schoolDropDownLB">
     <option value="All Schools">All Schools</option>
  </select>
</div>
<table class="table table-hover" id="rankTable">
<thead>
							<tr>
								<th>Rank</th>
								<th>User Handle</th>
								<th>Country</th>
								<th>Points</th>
							</tr>
						</thead>
						<tbody id="LBbody">
						<%int cnt=0; %>
						<c:forEach items="${topTen}" var="user">
						<tr>
						<td><%=++cnt %></td>
						<td><c:out value="${user.userHandle }"></c:out></td>
						<td><img src="/CodeMateMVC/LeaderBoard?query=getFlag&userId=${user.userId}" height="20" width="30"/></td>
						<td><c:out value="${user.points }"></c:out></td>
						</tr>
						</c:forEach>
						</tbody>
</table>
</div>
</div>
</div>
<%@include file="footer.jsp"%>
