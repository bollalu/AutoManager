package sample.model;



import java.awt.List;
import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "veicolo")
@Entity
public class Veicolo {
	
	//@Autowired
	//protected RifornimentoRepository rif;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne
	@JoinColumn(name = "modello")
	private Modello modello;

	@ManyToOne
	@JoinColumn(name = "carburante")
	private Carburante carburante;


	@Column(unique = true)
	private String targa;
	


	/*@Transient
	@OneToMany(mappedBy="veicolo")*/
	@Transient
	@OneToMany
    @JoinTable(
      name = "rifornimento",
      joinColumns = @JoinColumn(name = "veicolo")/*,
      inverseJoinColumns = @JoinColumn(name = "id")*/)
	private ArrayList<Rifornimento> rifornimenti = new ArrayList<>(); ;

	public ArrayList<Rifornimento> getRifornimenti() {
		return rifornimenti;
	}

	public void setRifornimenti(ArrayList<Rifornimento> rifornimenti) {
		this.rifornimenti = rifornimenti;
	}

	public Veicolo() {
		
	}

	public Veicolo(Modello modello, String... tags) {
		this.modello = modello;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Marca getMarca() {
		return modello.marca;
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
		return "Veicolo [id=" + id + ", marca=" + modello.getMarca() + ", modello=" + modello + ", targa=" + targa
				+ ", carburante=" + carburante + "]";
	}
	

}
