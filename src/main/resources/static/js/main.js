$(document).ready(function(){
	fetchVeicoli();
	/*$("#search").click(function(e){
		fetchVeicoli($("#filter").val());
		e.preventDefault();
	});*/
});


function fetchVeicoli(searchText)
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
	    		 						'<a href="/veicolo?id='+element.id+'"><span class="glyphicon glyphicon-pencil" style="padding-right: 8px;"></span></a>'+		    		 				
		    		 					'<a href="/veicolo/remove?id='+element.id+'"><span class="glyphicon glyphicon-trash"></span></a>'+
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