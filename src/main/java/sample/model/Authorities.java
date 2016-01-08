package sample.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "authorities")
@Entity
@Table(name = "authorities")
public class Authorities {
	
 @Id
// @Column(name = "username", unique = true, nullable = false, length = 45)
 private String username;

// @Column(name = "authority", nullable = false, length = 45)
 private String authority;


public String getUsername() {
  return username;
 }

 public void setUsername(String username) {
  this.username = username;
 }

 public String getAuthority() {
  return authority;
 }

 public void setAuthority(String authority) {
  this.authority = authority;
 }
 
 @Override
 public String toString() {
	 return "Authoriies [username=" + username + ", Authority=" + authority + "]";
 } 
}
