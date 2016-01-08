package sample.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "cambioGomme")
@Entity
public class CambioGomme {
	public String getDescrizione() {
		return Descrizione;
	}

	public void setDescrizione(String descrizione) {
		Descrizione = descrizione;
	}

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

	public void setData(Date data) {
		this.data = data;
	}

	public Integer getKm() {
		return km;
	}

	public void setKm(Integer km) {
		this.km = km;
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
    private Float costo;
	
	@Column
    private String Descrizione;
}
