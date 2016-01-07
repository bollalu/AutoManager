
function caricaListaElementi(elementi)
{
	var lista = new Object();
	var cp = $("#contextPath").val();
	cp = (cp == "/")? "":cp;
	var url = cp+"/json/"+elementi;
	$.ajax({
				url:url,
				dataType:"json",
	            async: false
	})
	.done(function(data) {
		$(eval('(data.' + elementi + ')')).each(function(index, element){
			lista[element.id]=element.descrizione;
	    });
    })
    return lista;

}

function idReferenziato(elementi,filtro)
{
	var numero = 0;
	var cp = $("#contextPath").val();
	cp = (cp == "/")? "":cp;
	var url = cp+"/json/veicoli/search"+elementi+"?q="+filtro;
	$.ajax({
				url:url, 
				dataType:"json",
	            async: false
	})
	.done(function(data) {
		$(data.veicoli).each(function(index, element){
			numero++;
	    });
    })
	return numero;
}

function contaElementi(elementi)
{	
	var numero = 0;
	var cp = $("#contextPath").val();
	cp = (cp == "/")? "":cp;
	var url = cp+"/json/"+elementi;
	$.ajax({
		url:url, 
		dataType:"json",
        async: false
	})
	.done(function(data) {
		$(eval('(data.' + elementi + ')')).each(function(index, element){
			numero++;
		});
	})
return numero;
}

function getRuoli(username)
{	
	var lista = "";
	var cp = $("#contextPath").val();
	cp = (cp == "/")? "":cp;
	var url = cp+"/json/authorities/search?q="+username;
	$.ajax({
		url:url, 
		dataType:"json",
        async: false
	})
	.done(function(data) {
		$(data.authorities).each(function(index, element){
			lista = element.authority;
		});
	})
	return lista;
}