package sample.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "marche")
@XmlAccessorType(XmlAccessType.FIELD)
public class Marche {
	
	@XmlElement(name="marca")
	private List<Marca> marche = new ArrayList<Marca>();
	
	public Marche() {}
	 
    public Marche(Iterable<Marca> marche) {
    	for (Marca a : marche){
    		this.marche.add(a);
    	}
    }
 
    public List<Marca> getMarca() {
        return marche;
    }
 
    public void setMarche(List<Marca> marche) {
        this.marche = marche;
    } 
}