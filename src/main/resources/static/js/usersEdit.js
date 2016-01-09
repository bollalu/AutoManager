$(document).ready(function(){
	caricaRuoli();
});

function caricaRuoli()
{
	var cp = $("#contextPath").val();
	cp = (cp == "/")? "":cp;
	var url = cp+"/json/ruoli";
	$.ajax({
		url:url, 
		dataType:"json"
	})
	.done(function(data) {
			$('#ruolo').empty();
			$(data.ruoli).each(function(index, element){
				$("#ruolo").append('<option ' + ((getRuoli($("title").text())== element.descrizione) ? 'selected="selected"':'') + ' value='+ element.descrizione +'>'+ element.descrizione +'</option>');
			});
	})
}

