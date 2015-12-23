package sample.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "veicolo")
@Entity
public class Veicolo {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    private long marca;
    private long modello;
	@Column(unique=true)
    private String targa;
    private long carburante;

    public Veicolo(){
    	//id = Math.abs(new Random().nextLong());
    }

	public Veicolo(long modello, String... tags){
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
 
    public long getMarca() {
        return marca;
    }

    public void setMarca(long marca) {
        this.marca = marca;
    }
    
	public long getModello() {
		return modello;
	}

	public void setModello(long modello) {
		this.modello = modello;
	}
	
    public String getTarga() {
        return targa;
    }

    public void setTarga(String targa) {
        this.targa = targa;
    }

	
	public long getCarburante() {
		return carburante;
	}

	public void setCarburante(long carburante) {
		this.carburante = carburante;
	}

	@Override
	public String toString() {
		return "Veicolo [id=" + id + ", marca=" + marca + ", modello="
				+ modello + ", targa=" + targa + ", carburante=" + carburante + "]";
	}

}
