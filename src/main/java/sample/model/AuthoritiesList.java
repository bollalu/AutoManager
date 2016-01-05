package sample.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "authoritieslist")
@XmlAccessorType(XmlAccessType.FIELD)
public class AuthoritiesList {
	
	@XmlElement(name="user")
	private List<Authorities> authoritieslist = new ArrayList<Authorities>();
	
	public AuthoritiesList() {}
	 
    public AuthoritiesList(Iterable<Authorities> authoritieslist) {
    	for (Authorities a : authoritieslist){
    		this.authoritieslist.add(a);
    	}
    }
 
    public List<Authorities> getAuthorities() {
        return authoritieslist;
    }
 
    public void setAuthorities(List<Authorities> authoritieslist) {
        this.authoritieslist = authoritieslist;
    } 
}