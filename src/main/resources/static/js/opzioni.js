var carburanti = ["Benzina Verde", "Diesel", "GPL", "Metano"];
var modelli = ["Audi","BMW","Citroën","FIAT","Ford","Smart","Subaru","Volkswagen","Volvo"]

$select = $('#carburanti');
 $.each(carburanti, function(val){
      $select.append('<option id="' + val + '">' + carburanti[val] + '</option>');
 }
);
