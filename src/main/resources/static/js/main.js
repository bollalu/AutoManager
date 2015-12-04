$(document).ready(function(){
	fetchArticles();
	$("#search").click(function(e){
		fetchArticles($("#filter").val());
		e.preventDefault();
	});
});

function fetchArticles(searchText)
{	
	var url = "/json/articles" + ((searchText) ? "/search?q="+searchText : ""); 
	
	$.ajax({
		url:url, 
		dataType:"json"
	})
	.done(function(data) {
		$('#articles').empty();
		$(data.articles).each(function(index, element){
			 $('#articles').append('<tr><td><a href="/article?id='+element.id+'">'+element.id+'</a></td>'+
		    		 				'<td> '+element.title+' </td>'+
		    		 				'<td><a href="/article/remove?id='+element.id+'"><span class="glyphicon glyphicon-trash"></span></a></td>'+
		    		 				'</tr>');       
		})
    })
    .fail(function() {
    	alert( "errore nel reperire gli articoli" );
    });
}