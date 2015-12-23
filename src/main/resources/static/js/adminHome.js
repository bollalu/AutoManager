$(document).ready(function(){
	$('#numeroVeicoli').html('<span class="badge alert-info">Configurati : ' + contaElementi("veicoli")+ '</span>');
	$('#numeroMarche').html('<span class="badge alert-info">Disponibili : ' + contaElementi("marche")+ '</span>');
	$('#numeroModelli').html('<span class="badge alert-info">Configurati : ' + contaElementi("modelli")+ '</span>');
	$('#numeroCarburanti').html('<span class="badge alert-info">Disponibili : ' + contaElementi("carburanti")+ '</span>');
});
