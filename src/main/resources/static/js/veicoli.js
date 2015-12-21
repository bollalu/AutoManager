$(document).ready(function(){
	//caricaCarburanti();
	caricaVeicoli();
	caricaConferma();
});

var carburante = ["Benzina Verde", "Diesel", "GPL", "Metano"];

function caricaCarburanti()
{
	$select = $('#carburante');			
	$.each(carburante, function(val){
      $select.append('<option [[*{carburante}]] value="' + val + '">' + carburante[val] + '</option>');
	}
	);
}

function caricaConferma()
{
	$('a[data-confirm]').click(function(ev) {
		var href = $(this).attr('href');
		$('#dataConfirmModal').find('.modal-body').html($(this).attr('data-confirm'));
		$('#dataConfirmOK').attr('href', href);
		$('#dataConfirmModal').modal({show:true});
		return false;
	});
}

function caricaVeicoli(searchText)
{	
	var cp = $("#contextPath").val();
	cp = (cp == "/")? "":cp;
	var url = cp+"/json/veicoli" + ((searchText) ? "/search?q="+searchText : "");
	$.ajax({
		url:url, 
		dataType:"json"
	})
	.done(function(data) {
		$('#veicoli').empty();
		$(data.veicoli).each(function(index, element){
			 $('#veicoli').append('<tr>'+
					 				'<td>'+element.id+'</td>'+
		 							'<td>'+element.targa+' </td>'+
		    		 				'<td>'+element.marca+' </td>'+
		    		 				'<td>'+element.modello+' </td>'+
		    		 				'<td>'+carburanti[element.carburante]+' </td>'+
		    		 				'<td>'+
	    		 						'<a href="/admin/veicolo?id='+element.id+'"><span class="glyphicon glyphicon-pencil" style="padding-right: 8px;"></span></a>'+
	    		 						'<a href="/admin/veicolo/remove?id='+element.id+'" data-confirm="<table><tr><td>ID</td><td>' + element.id + '</td></tr>' +
	    		 																							'<tr><td>Targa</td><td>' + element.targa + '</td></tr>' +	    		 						
	    		 																							'<tr><td>Marca</td><td>' + element.marca + '</td></tr>' +
	    		 																							'<tr><td>Modello&nbsp&nbsp</td><td>' + element.modello + '</td></tr></table>' +
	    		 																							'<br><br><strong>Attenzione!</strong> Non sarà possibile recuperare queste informazioni.<br>' + 		 																							
	    		 																							'Sei <strong>sicuro</strong> di voler eliminare questo elemento?">' +
		    		 					'<span class="glyphicon glyphicon-trash"></span></a>'+
		    		 				'</td>'+
		    		 				'</tr>'); 
		})
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