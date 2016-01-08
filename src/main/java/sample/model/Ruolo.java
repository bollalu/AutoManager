package sample.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "ruolo")
@Entity
public class Ruolo {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
	@Column(unique=true)
    private String descrizione;
	

    public Ruolo(){
    	//id = Math.abs(new Random().nextLong());
    }

	public Ruolo(String descrizione, String... tags){
		//id = Math.abs(new Random().nextLong());
    	this.descrizione = descrizione;
    	//this.tags.addAll(Arrays.asList(tags));
    }
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
 
    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }
    
	@Override
	public String toString() {
		return "Ruolo [id=" + id + ", descrizione=" + descrizione + "]";
	}

}
