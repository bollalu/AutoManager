$(document).ready(function(){
	caricaCarburanti();
});


function caricaCarburanti(searchText)
{	
	var cp = $("#contextPath").val();
	cp = (cp == "/")? "":cp;
	var url = cp+"/json/carburanti" + ((searchText) ? "/search?q="+searchText : "");
	var collegamenti=0;
	$.ajax({
		url:url, 
		dataType:"json"
	})
	.done(function(data) {
		$('#carburanti').empty();
		$(data.carburanti).each(function(index, element){
			 collegamenti=idReferenziato("Carburante","veicoli",element.id);
			 var elimina = (collegamenti > 0)? '<span class="badge alert-info">' +
					    collegamenti+' Collegament' + ((collegamenti > 1)? 'i' : 'o')+'</span>':'<a href="/admin/carburante/remove?id='+element.id+'" data-confirm="<table><tr><td>ID</td><td>' + element.id + '</td></tr>' +
						'<tr><td>Carburante&nbsp&nbsp</td><td>' + element.descrizione + '</td></tr></table>' +
						'<br><br><strong>Attenzione!</strong> Non sarà possibile recuperare queste informazioni.<br>' + 		 																							
						'Sei <strong>sicuro</strong> di voler eliminare questo elemento?">' +
						'<span class="glyphicon glyphicon-trash" ></span></a>';
			 $('#carburanti').append('<tr>'+
					 				'<td>'+ element.id +'</td>'+
		    		 				'<td>'+ element.descrizione +' </td>'+
		    		 				'<td>'+
	    		 						'<a href="/admin/carburante?id='+ element.id +'"><span class="glyphicon glyphicon-pencil" style="padding-right: 8px;"></span></a>'+		    		 				
	    		 						elimina +
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
    	alert( "errore nel reperire i carburanti dalla bancadati" );
    });
}