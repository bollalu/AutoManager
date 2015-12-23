package sample.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "modelli")
@XmlAccessorType(XmlAccessType.FIELD)
public class Modelli {
	
	@XmlElement(name="modello")
	private List<Modello> modelli = new ArrayList<Modello>();
	
	public Modelli() {}
	 
    public Modelli(Iterable<Modello> modelli) {
    	for (Modello a : modelli){
    		this.modelli.add(a);
    	}
    }
 
    public List<Modello> getModelli() {
        return modelli;
    }
 
    public void setModelli(List<Modello> modelli) {
        this.modelli = modelli;
    } 
}