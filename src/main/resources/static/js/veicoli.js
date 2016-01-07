$(document).ready(function(){
	caricaVeicoli();
});

function caricaVeicoli(searchText)
{	
	var cp = $("#contextPath").val();
	cp = (cp == "/")? "":cp;
	
	//var listaMarche 	= caricaListaElementi("marche");
	//var listaCarburanti = caricaListaElementi("carburanti");
	//var listaModelli 	= caricaListaElementi("modelli");
	
	var url = cp+"/json/veicoli" + ((searchText) ? "/search?q="+searchText : "");
	$.ajax({
		url:url, 
		dataType:"json"
	})
	.done(function(data) {

		$('#veicoli').empty();
		$(data.veicoli).each(function(index, element){
			var elimina = (idReferenziato("Rifornimento","rifornimenti",element.id) > 0)? '<span class="badge alert-info">Veicolo Operativo</span>':'<a href="/admin/veicolo/remove?id='+element.id+'" data-confirm="<table><tr><td>ID</td><td>' + element.id + '</td></tr>' +
						'<tr><td>Targa</td><td>' + element.targa + '</td></tr>' +	    		 						
						'<tr><td>Marca</td><td>' + element.marca.descrizione + '</td></tr>' +
						'<tr><td>Modello&nbsp&nbsp</td><td>' + element.modello.descrizione + '</td></tr>' +
						'<tr><td>Carburante&nbsp&nbsp</td><td>' + element.carburante.descrizione + '</td></tr></table>' +						
						'<br><br><strong>Attenzione!</strong> Non sarà possibile recuperare queste informazioni.<br>' + 		 																							
						'Sei <strong>sicuro</strong> di voler eliminare questo elemento?">' +
						'<span class="glyphicon glyphicon-trash"></span></a>';					
			 $('#veicoli').append('<tr>'+
					 				'<td>'+element.id+'</td>'+
		 							'<td>'+element.targa+' </td>'+
		    		 				'<td>'+element.marca.descrizione+' </td>'+
		    		 				'<td>'+element.modello.descrizione+' </td>'+
		    		 				'<td>'+element.carburante.descrizione+' </td>'+
		    		 				'<td>'+
	    		 						'<a href="/admin/veicolo?id='+element.id+'"><span class="glyphicon glyphicon-pencil" style="padding-right: 8px;"></span></a>'+
	    		 						elimina+
		    		 				'</td>'+
		    		 				'</tr>'); 
		})
		$('a[data-confirm]').click(function(ev) {
			var href = $(this).attr('href');
			$('#dataConfirmModal').find('.modal-body').html($(this).attr('data-confirm'));
			$('#dataConfirmOK').attr('href', href);
			$('#dataConfirmModal').modal({show:true});
			return false;
		});
	    $('#tabella').DataTable( {
	        "language": {
	            "url": "//cdn.datatables.net/plug-ins/9dcbecd42ad/i18n/Italian.json"
	        }
	    });	
    })
    .fail(function() {
    	alert( "errore nel reperire i veicoli dalla bancadati" );
    });
}