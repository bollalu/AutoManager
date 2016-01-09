package sample.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "cambiGomme")
@XmlAccessorType(XmlAccessType.FIELD)
public class CambiGomme {

	@XmlElement(name="cambioGomme")
	private List<CambioGomme> cambiGomme = new ArrayList<CambioGomme>();
	
	public CambiGomme() {}
	 
    public CambiGomme(Iterable<CambioGomme> cambiGomme) {
    	for (CambioGomme a : cambiGomme){
    		this.cambiGomme.add(a);
    	}
    }
 
    public List<CambioGomme> getcambiGomme() {
        return cambiGomme;
    }
 

}
