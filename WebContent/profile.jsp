<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<%@include file="dbConnect.jsp"%>
<%@include file="header.jsp"%>

<!-- script -->
<script src="_js/profile.js" type="text/javascript"></script>

<!-- Shows user profile -->
<div class="container-fluid" id="signupContainer">
	<div class="row">
		<div class="col-md-6 col-md-offset-3">
			<div class="panel panel-default">
				<div class="panel-body">
					<h3 align="center">
						Hello
						<%=session.getAttribute("userHandle")%>!
					</h3>




					<table class="table table-borderless">
						<tr>
							<td colspan="2">
								<ul id="errorMessages">
								</ul>
							</td>
						</tr>
						<tr>
							<td><img alt="" src="${userProfile.awards.badgePicture}"
								class="profile_smiley"> &nbsp; <span
								class="user_award_title"><c:out
										value="${userProfile.awards.title}" /></span>
								<h2>
									<c:out value="${userProfile.points}" />
									&nbsp;Points
								</h2></td>

						</tr>
						<tr>
							<td><label> Name: </label></td>
							<td><c:out value="${userProfile.firstName}" />&nbsp;<c:out
									value="${userProfile.lastName}" /></td>
						</tr>
						<tr>
							<td><label> Email: </label></td>
							<td><c:out value="${userProfile.email}" /></td>
						</tr>
						<tr>
							<td><label> School: </label></td>
							<td><c:out value="${userProfile.school.schoolName}" /></td>
						</tr>
						<tr>
							<td><label> Country: </label></td>
							<td><c:out value="${userProfile.school.country.countryName}" /></td>
						</tr>
						<tr>
							<td><label> City: </label></td>
							<td><c:out value="${userProfile.school.schoolCity}" /></td>
						</tr>
						<tr>
							<td align='center' colspan="2">
								<button type="submit" id='editProfile' class="btn loginButton">Edit
									Profile</button>
							</td>
						</tr>

					</table>
				</div>
			</div>
		</div>
	</div>
</div>

<%@include file="../footer.jsp"%>