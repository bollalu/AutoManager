var carburanti = ["Benzina Verde", "Diesel", "GPL", "Metano"];

$select = $('#carburanti');
 $.each(carburanti, function(val){
      $select.append('<option id="' + val + '">' + carburanti[val] + '</option>');
 }
);
