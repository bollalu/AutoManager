package sample.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ruoli")
@XmlAccessorType(XmlAccessType.FIELD)
public class Ruoli {
	
	@XmlElement(name="ruolo")
	private List<Ruolo> ruoli = new ArrayList<Ruolo>();
	
	public Ruoli() {}
	 
    public Ruoli(Iterable<Ruolo> ruoli) {
    	for (Ruolo a : ruoli){
    		this.ruoli.add(a);
    	}
    }
 
    public List<Ruolo> getRuoli() {
        return ruoli;
    }
 
    public void setRuoli(List<Ruolo> ruoli) {
        this.ruoli = ruoli;
    } 
}