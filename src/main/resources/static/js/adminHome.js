$(document).ready(function(){
	$('#numeroVeicoli').text("Disponibili : " + contaElementi("veicoli"));
	$('#numeroMarche').text("Disponibili : " + contaElementi("marche"));
	$('#numeroModelli').text("Disponibili : " + contaElementi("modelli"));
	$('#numeroCarburanti').text("Disponibili : " + contaElementi("carburanti"));
});
