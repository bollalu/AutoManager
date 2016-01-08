$(document).ready(function(){
	caricaUsers();
});


function caricaUsers(searchText)
{	
	var cp = $("#contextPath").val();
	cp = (cp == "/")? "":cp;
	var url = cp+"/json/users" + ((searchText) ? "/search?q="+searchText : "");
	$.ajax({
		url:url, 
		dataType:"json"
	})
	.done(function(data) {
		$('#users').empty();
		$(data.users).each(function(index, element){
			 var elimina = '<a href="/admin/users/remove?id='+element.username+'" data-confirm="<table><tr><td>Utente</td><td>' + element.username + '</td></tr>' +
						'<tr><td>Ruolo&nbsp&nbsp</td><td>' + getRuoli(element.username) + '</td></tr></table>' +
						'<br><br><strong>Attenzione!</strong> Non sarà possibile recuperare queste informazioni.<br>' + 		 																							
						'Sei <strong>sicuro</strong> di voler eliminare questo elemento?">' +
						'<span class="glyphicon glyphicon-trash" ></span></a>';
			 $('#users').append('<tr>'+
					 				//'<td>' + element.id +'</td>'+
					 				'<td>' + element.username +'</td>'+
					 				'<td>' + element.email +'</td>'+					 				
		    		 				'<td>' + getRuoli(element.username) + '</td>'+
		    		 				'<td>' + ((element.enabled == true)? '<span class="glyphicon glyphicon-ok"></span>':'<span class="glyphicon glyphicon-remove"></span>') +' </td>'+		    		 				
		    		 				'<td>'+
	    		 					'<a href="/admin/users?id='+ element.username +'"><span class="glyphicon glyphicon-pencil" style="padding-right: 8px;"></span></a>'+		    		 				
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
    	alert( "errore nel reperire gli uteni dalla bancadati" );
    });
}