package sample.model;

import java.time.LocalDateTime;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "rifornimento")
@Entity
public class Rifornimento {
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Veicolo getVeicolo() {
		return veicolo;
	}

	public void setVeicolo(Veicolo veicolo) {
		this.veicolo = veicolo;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date pDate) {
		this.data = pDate;
	}

	public Integer getKm() {
		return km;
	}

	public void setKm(Integer km) {
		this.km = km;
	}

	public Float getLitri() {
		return litri;
	}

	public void setLitri(Float litri) {
		this.litri = litri;
	}

	public Float getCosto() {
		return costo;
	}

	public void setCosto(Float costo) {
		this.costo = costo;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
	
	@ManyToOne
	@JoinColumn(name="veicolo")
	private Veicolo veicolo;
	
	@Column
    private Date data;
	
	@Column
    private Integer km;

	@Column
    private Float litri;
	
	@Column
    private Float costo;

	public Rifornimento(Veicolo pVeicolo, Integer pKm, Float pLitri, Float pCosto){
		this.veicolo = pVeicolo;
    	this.km = pKm;
    	this.litri = pLitri;
    	this.costo = pCosto;
    }
        
	public Rifornimento() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Rifornimento [id=" + this.id + "]";
	}
}
