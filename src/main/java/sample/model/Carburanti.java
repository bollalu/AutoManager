package sample.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "carburanti")
@XmlAccessorType(XmlAccessType.FIELD)
public class Carburanti {
	
	@XmlElement(name="carburante")
	private List<Carburante> carburanti = new ArrayList<Carburante>();
	
	public Carburanti() {}
	 
    public Carburanti(Iterable<Carburante> carburanti) {
    	for (Carburante a : carburanti){
    		this.carburanti.add(a);
    	}
    }
 
    public List<Carburante> getCarburanti() {
        return carburanti;
    }
 
    public void setCarburanti(List<Carburante> carburanti) {
        this.carburanti = carburanti;
    } 
}