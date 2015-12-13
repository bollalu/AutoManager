package sample.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "veicoli")
@XmlAccessorType(XmlAccessType.FIELD)
public class Veicoli {
	
	@XmlElement(name="veicolo")
	private List<Veicolo> veicoli = new ArrayList<Veicolo>();
	
	public Veicoli() {}
	 
    public Veicoli(Iterable<Veicolo> veicoli) {
    	for (Veicolo a : veicoli){
    		this.veicoli.add(a);
    	}
    }
 
    public List<Veicolo> getVeicoli() {
        return veicoli;
    }
 
    public void setVeicoli(List<Veicolo> veicoli) {
        this.veicoli = veicoli;
    } 
}