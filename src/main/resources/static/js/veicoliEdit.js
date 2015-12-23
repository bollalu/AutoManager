$(document).ready(function(){
	caricaModelliMarca();
});

function caricaModelliMarca()
{
		var cp = $("#contextPath").val();
		cp = (cp == "/")? "":cp;
		var url = cp+"/json/modelli/marca?q="+$("#marca").val();
		$.ajax({
			url:url, 
			dataType:"json"
		})
		.done(function(data) {
			$('#modello').empty();
			$(data.modelli).each(function(index, element){
				$("#modello").append('<option value='+ element.id +'>'+element.descrizione+'</option>');
			});
			aggiornaModelliMarca();
		})
}

function aggiornaModelliMarca()
{
	$("#marca").change(function () {
		var cp = $("#contextPath").val();
		cp = (cp == "/")? "":cp;
		var url = cp+"/json/modelli/marca?q="+$("#marca").val();
		$.ajax({
			url:url, 
			dataType:"json"
		})
		.done(function(data) {
			$('#modello').empty();
			$(data.modelli).each(function(index, element){
				$("#modello").append('<option value='+ element.id +'>'+element.descrizione+'</option>');
			});
		})
	})
}

