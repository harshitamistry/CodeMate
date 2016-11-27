<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>


<%@include file="dbConnect.jsp" %>
<%@include file="header.jsp"%>
<script src="_js/EditProfile.js" type="text/javascript"></script>
<div class="container-fluid" id="signupContainer">
<div class="row">
<div class = "col-md-6 col-md-offset-3">
<div class="panel panel-default">
  <div class="panel-body"><h3 align="center">Edit Profile</h3>
  
   <table class = "table table-borderless">
   <tr>
   <td colspan="2">
   <ul id="errorMessages">
   </ul>
   </td>
   </tr>
   <tr>
   <td>
   <input type="text" id='fname' class="form-control" placeholder="FIRST NAME" value="${userProfile.firstName}">
   </td>
   <td>
  <input type="text" id='lname' class="form-control" placeholder="LAST NAME" value="${userProfile.lastName}">
   </td>
   </tr>
   <tr> <td colspan="2"> <input type="text" id="usereid" class="form-control" placeholder="EMAIL" value="${userProfile.email}"> </td></tr>
   <tr><td>

  <select class="selectpicker" id="countryDropDown">
    <c:forEach var="country" items="${countryList}">
    
    	<option ${country.countryName == userProfile.school.country.countryName ? 'selected = "selected"' : '' } value="<c:out value="${country.countryId}"/>"><c:out value="${country.countryName}"/></option>
   
    </c:forEach>
  </select>
  

   </td>
   <td>
   
 
   
  <select class="selectpicker" id="schoolDropDown">
  <option value="Select School"> Select School</option>
  <option value="Other"> Other </option>
  <c:forEach var="schools" items="${schoolList}">
  	<option ${schools.schoolName == userProfile.school.schoolName ? 'selected = "selected"' : '' } value="<c:out value="${schools.schoolId}"/>"> <c:out value="${schools.schoolName}"/></option>
  	</c:forEach>
   
  </select>
 </td>
   </tr>
   <tr>
   <td colspan="2">
   <input type="password" id="upwd" class="form-control" data-html="true" data-toggle="tooltip"
   title="Password must be at least 7 characters and 1 number and 1 alphabet. " 
   data-placement="right" placeholder="PASSWORD">
   </td>
   </tr>
   <tr>
   <td colspan="2">
   <input type="password" id="con-upwd" class="form-control" placeholder="CONFIRM PASSWORD">
   </td>
   </tr>
   <tr>
   
   </tr>
   <tr><td align='center' colspan="2"> <button type="submit" id='updateUser' class="btn loginButton">Update</button></td></tr>
   </table>
  </div>
</div>
</div>
</div>
</div>
 
<%@include file="../footer.jsp"%>