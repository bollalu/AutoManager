package sample.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "veicolo")
@Entity
public class Veicolo {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
	@ManyToOne
	@JoinColumn(name="marca")
	private Marca marca;
	@ManyToOne
	@JoinColumn(name="modello")	
    private Modello modello;
	@ManyToOne
	@JoinColumn(name="carburante")	
    private Carburante carburante;	
	@Column(unique=true)
    private String targa;

    public Veicolo(){
    	//id = Math.abs(new Random().nextLong());
    }

	public Veicolo(Modello modello, String... tags){
		//id = Math.abs(new Random().nextLong());
    	this.modello = modello;
    	//this.tags.addAll(Arrays.asList(tags));
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
    
	public Modello getModello() {
		return modello;
	}

	public void setModello(Modello modello) {
		this.modello = modello;
	}
	
    public String getTarga() {
        return targa;
    }

    public void setTarga(String targa) {
        this.targa = targa;
    }

	
	public Carburante getCarburante() {
		return carburante;
	}

	public void setCarburante(Carburante carburante) {
		this.carburante = carburante;
	}

	@Override
	public String toString() {
		return "Veicolo [id=" + id + ", marca=" + marca + ", modello="
				+ modello + ", targa=" + targa + ", carburante=" + carburante + "]";
	}

}
