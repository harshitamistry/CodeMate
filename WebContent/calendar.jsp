<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@include file="header.jsp"%>
<div class="container" id="calendarContainer">
    <div id="calendar"></div>
</div>
<script>
$('#calendar').fullCalendar({
	height:"parent",
	eventSources:[{events:${eventsJson},
	color: '#26B99A',    
    textColor: '#2A3F54'}]  
});

</script>
<%@include file="../footer.jsp"%>
