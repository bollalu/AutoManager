$(document).ready(function(){
	caricaModelli();
});

function caricaModelli(searchText)
{	
	var cp = $("#contextPath").val();
	cp = (cp == "/")? "":cp;
	var url = cp+"/json/modelli" + ((searchText) ? "/search?q="+searchText : "");
	var collegamenti=0;		
	$.ajax({
		url:url, 
		dataType:"json"
	})
	.done(function(data) {
		$('#modelli').empty();
		$(data.modelli).each(function(index, element){
			collegamenti=idReferenziato("Modello","veicoli",element.id);			
			var elimina = (collegamenti > 0)? '<span class="badge alert-info">'+
					    collegamenti+' Collegament' + ((collegamenti > 1)? 'i' : 'o')+'</span>':'<a href="/admin/modello/remove?id='+element.id+'" data-confirm="<table><tr><td>ID</td><td>' + element.id + '</td></tr>' +
						'<tr><td>Marca</td><td>' + element.marca.descrizione + '</td></tr>' +
						'<tr><td>Modello&nbsp&nbsp</td><td>' + element.descrizione + '</td></tr></table>' +											
						'<br><br><strong>Attenzione!</strong> Non sarà possibile recuperare queste informazioni.<br>' + 		 																							
						'Sei <strong>sicuro</strong> di voler eliminare questo elemento?">' +
						'<span class="glyphicon glyphicon-trash"></span></a>';
			 $('#modelli').append('<tr>'+
					 				'<td>'+ element.id +'</td>'+
		    		 				'<td>'+ element.marca.descrizione +' </td>'+
		    		 				'<td>'+ element.descrizione +' </td>'+
		    		 				'<td>'+
	    		 						'<a href="/admin/modello?id='+ element.id +'"><span class="glyphicon glyphicon-pencil" style="padding-right: 8px;"></span></a>'+		    		 				
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
    	alert( "errore nel reperire i modelli dalla bancadati" );
    });
}