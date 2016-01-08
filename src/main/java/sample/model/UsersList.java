package sample.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UsersList {
	
	@XmlElement(name="userslist")
	private List<Users> userslist = new ArrayList<Users>();
	
	public UsersList() {}
	 
    public UsersList(Iterable<Users> userslist) {
    	for (Users a : userslist){
    		this.userslist.add(a);
    	}
    }
 
    public List<Users> getUsers() {
        return userslist;
    }
 
    public void setUsers(List<Users> userslist) {
        this.userslist = userslist;
    } 
}