$(document).ready(function(){
	caricaMarche();
});

function caricaMarche(searchText)
{	
	var cp = $("#contextPath").val();
	cp = (cp == "/")? "":cp;
	var url = cp+"/json/marche" + ((searchText) ? "/search?q="+searchText : "");
	$.ajax({
		url:url, 
		dataType:"json"
	})
	.done(function(data) {
		$('#marche').empty();
		$(data.marche).each(function(index, element){
			 $('#marche').append('<tr>'+
					 				'<td>'+ element.id +'</td>'+
		    		 				'<td>'+ element.descrizione +' </td>'+
		    		 				'<td>'+
	    		 						'<a href="/marca?id='+ element.id +'"><span class="glyphicon glyphicon-pencil" style="padding-right: 8px;"></span></a>'+		    		 				
		    		 					'<a href="/marca/remove?id='+ element.id +'"><span class="glyphicon glyphicon-trash"></span></a>'+
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