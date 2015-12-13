package sample.model;

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
    private String marca;
    private String modello;
    private String targa;
    private int carburante;

    public Veicolo(){
    	//id = Math.abs(new Random().nextLong());
    }

	public Veicolo(String modello, String... tags){
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
 
    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }
    
	public String getModello() {
		return modello;
	}

	public void setModello(String modello) {
		this.modello = modello;
	}
	
    public String getTarga() {
        return targa;
    }

    public void setTarga(String targa) {
        this.targa = targa;
    }

	
	public int getCarburante() {
		return carburante;
	}

	public void setCarburante(int carburante) {
		this.carburante = carburante;
	}

	@Override
	public String toString() {
		return "Veicolo [id=" + id + ", marca=" + marca + ", modello="
				+ modello + ", targa=" + targa + ", carburante=" + carburante + "]";
	}

}
