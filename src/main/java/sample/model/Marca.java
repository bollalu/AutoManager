package sample.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "marca")
@Entity
public class Marca {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    private String marca;

    public Marca(){
    	//id = Math.abs(new Random().nextLong());
    }

	public Marca(String marca, String... tags){
		//id = Math.abs(new Random().nextLong());
    	this.marca = marca;
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
    

	@Override
	public String toString() {
		return "Marca [id=" + id + ", marca=" + marca + "]";
	}

}
