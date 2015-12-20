var carburanti = ["Benzina Verde", "Diesel", "GPL", "Metano"];

function caricaCarburanti()
{
	$select = $('#carburante');
	$.each(carburanti, function(val){
      $select.append('<option value="' + val + '">' + carburanti[val] + '</option>');
	}
	);
}
