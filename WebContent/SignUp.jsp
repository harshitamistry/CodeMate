<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>


<%@include file="dbConnect.jsp" %>
<%@include file="header.jsp"%>
<script src="_js/SignUp.js" type="text/javascript"></script>
<div class="container-fluid" id="signupContainer">
<div class="row">
<div class = "col-md-6 col-md-offset-3">
<div class="panel panel-default">
  <div class="panel-body"><h3 align="center">Join CodeMate!</h3>
  
   <table class = "table table-borderless">
   <tr>
   <td colspan="2">
   <ul id="errorMessages">
   </ul>
   </td>
   </tr>
   <tr>
   <td>
   <input type="text" id='fname' class="form-control" placeholder="FIRST NAME">
   </td>
   <td>
  <input type="text" id='lname' class="form-control" placeholder="LAST NAME">
   </td>
   </tr>
   <tr> <td colspan="2"> <input type="text" id="usereid" class="form-control" placeholder="EMAIL"> </td></tr>
   <tr><td>
   
  <sql:query dataSource="${snapshot}" var="result" >
  SELECT CountryName,CountryID from country;
   </sql:query> 

  <select class="selectpicker" id="countryDropDown">
    <c:forEach var="country" items="${result.rows}">
    
    	<option ${country.CountryName == "Canada" ? 'selected = "selected"' : '' } value="<c:out value="${country.CountryID}"/>"><c:out value="${country.CountryName}"/></option>
   
    </c:forEach>
  </select>

   </td>
   <td>
   
   <sql:query dataSource="${snapshot}" var="schoolRes">
   	SELECT SchoolName,SchoolID from school s INNER JOIN country c ON c.CountryID=s.CountryID WHERE
   	c.CountryName="Canada";
   </sql:query>
   
  <select class="selectpicker" id="schoolDropDown">
  <option value="Select School"> Select School</option>
  <option value="Other"> Other </option>
  <c:forEach var="schools" items="${schoolRes.rows}">
  	<option value="<c:out value="${schools.SchoolID}"/>"> <c:out value="${schools.SchoolName}"/></option>
  	</c:forEach>
   
  </select>
 </td>
   </tr>
   <tr id="userNameTR"><td colspan="2"><input type="text" id="uname" class="form-control" data-html="true" data-toggle="tooltip"
   title="You will be identified by your username. You can't change your username after signup." 
   data-placement="right" placeholder="USER NAME"></td></tr>
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
   <td colspan="2">
        <input type="checkbox" aria-label="..." id="termsConditions">
&nbsp;
      <label>I agree to CodeMate's <a href="#">terms and conditions.</a></label>
   </td>
   </tr>
   <tr><td align='center' colspan="2"> <button type="submit" id='createUser' class="btn loginButton">Join</button></td></tr>
   </table>
  </div>
</div>
</div>
</div>
</div>
 
<%@include file="../footer.jsp"%>