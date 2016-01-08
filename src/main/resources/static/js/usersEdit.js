$(document).ready(function(){
	caricaRuoli();
});

function caricaRuoli()
{
	var cp = $("#contextPath").val();
	cp = (cp == "/")? "":cp;
	var url = cp+"/json/users" + ((searchText) ? "/search?q="+searchText : "");
	$.ajax({
		url:url, 
		dataType:"json"
	})
	.done(function(data) {
			$('#ruolo').empty();
			$(data.authorities).each(function(index, element){
				$("#ruolo").append('<option value='+ element.authority +'>'+element.authority+'</option>');
			});
			aggiornaModelliMarca();
	})
}

