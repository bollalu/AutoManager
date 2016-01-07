package sample.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.JoinColumnOrFormula;

@XmlRootElement(name = "modello")
@Entity
public class Modello {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
	@ManyToOne
	@JoinColumn(name="marca") Marca marca;
	@Column(unique=true)
    private String descrizione;

    public Modello(){
    	//id = Math.abs(new Random().nextLong());
    }

	public Modello(String descrizione, Marca marca){
		this.marca = marca;
    	this.descrizione = descrizione;
    }
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }    
    
    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }
    
	@Override
	public String toString() {
		return "Modello [id=" + id + ",marca=" + marca + ", descrizione=" + descrizione + "]";
	}

}
