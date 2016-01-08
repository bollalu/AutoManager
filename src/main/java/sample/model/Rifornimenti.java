package sample.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "rifornimenti")
@XmlAccessorType(XmlAccessType.FIELD)
public class Rifornimenti {

	
	@XmlElement(name="rifornimento")
	private List<Rifornimento> rifornimenti = new ArrayList<Rifornimento>();
	
	public Rifornimenti() {}
	 
    public Rifornimenti(Iterable<Rifornimento> rifornimenti) {
    	for (Rifornimento a : rifornimenti){
    		this.rifornimenti.add(a);
    	}
    }
 
    public List<Rifornimento> getRifornimenti() {
        return rifornimenti;
    }
 
    public void setVeicoli(List<Rifornimento> rifornimenti) {
        this.rifornimenti = rifornimenti;
    } 
}