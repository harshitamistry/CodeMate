$(function(){
	$("#question").autocomplete({
		source:function(request, response) {
			$.ajax({
				url: "/CodeMateMVC/Forum?query=searchQuestion",
				type: "POST",
				data: { term: request.term },
				success: function(data) {
					console.log(data);
					var json=JSON.parse(data);
					console.log(json['questions']);
					response(json['questions']);
				}
			});              
		},
	      focus: function( event, ui ) {
		    	$("#question").val(ui.item.label);
	      },

	    select: function(event, ui) {
	        //assign value back to the form element
	    	$("#question").val(ui.item.label);
	        window.location="/CodeMateMVC/Forumquestion?id="+ui.item.value;
	    },
	    maxLength:4
	});
});