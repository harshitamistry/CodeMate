$(function(){
	$('select#countryDropDownLB').change(function(){
		
		 var country_name = $('select#countryDropDownLB option:selected').text();
		 var country_id=$('select#countryDropDownLB option:selected').val();
		 $.get("DbServlet",$.param({query:"load_schools",countryname:country_name}),
				 function(response) {
			 	
			 	//console.log(response[0]);
		        var select = $('#schoolDropDownLB');
				 $(select).find('option').remove().end().selectpicker('refresh');
				 $(select).append($("<option selected='selected'>All Schools</option>")).selectpicker('refresh');
		          for(i=0;i<response.length;i+=2){
		        	  $(select).append($("<option value='"+response[i]+"'>"+response[i+1]+"</option>")).selectpicker('refresh');
		          };
		          console.log("success");
		 })
		 .fail(function(){
			 alert("request failed");
		 });
		 
		 $.post("LeaderBoard",$.param({query:"topTenCountry",countryId:country_id}),
				 function(response){
			 $("#LBbody").html("");
			 console.log(response);
			 var json=JSON.parse(response);
			 console.log(json);
			 for(var user in json){
				 $("#LBbody").html($("#LBbody").html()+"<tr><td>"+json[user]['rank']+"</td><td>"+json[user]['handle']+"</td><td><img height='20' width='30' src='/CodeMateMVC/LeaderBoard?query=getFlag&userId="+json[user]['userId']+"'/></td><td>"+json[user]['points']+"</td></tr>");
			 }
		 }
		 );
	  });
	$('select#schoolDropDownLB').change(function(){
		 var school_id=$('select#schoolDropDownLB option:selected').val();
		 $.post("LeaderBoard",$.param({query:"topTenSchool",schoolId:school_id}),
				 function(response){
			 $("#LBbody").html("");
			 console.log(response);
			 var json=JSON.parse(response);
			 console.log(json);
			 for(var user in json){
				 $("#LBbody").html($("#LBbody").html()+"<tr><td>"+json[user]['rank']+"</td><td>"+json[user]['handle']+"</td><td><img height='20' width='30' src='/CodeMateMVC/LeaderBoard?query=getFlag&userId="+json[user]['userId']+"'/></td><td>"+json[user]['points']+"</td></tr>");
			 }
		 }
		 );
	});
})