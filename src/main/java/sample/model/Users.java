package sample.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "users")
@Entity
public class Users {

 @Id
 @Column(name = "username", unique = true, nullable = false, length = 45)
 private String username;

 @Column(name = "password", nullable = false, length = 60)
 private String password;

 @Column(name = "enabled", nullable = false)
 private boolean enabled;




public String getUsername() {
  return username;
 }

 public void setUsername(String username) {
  this.username = username;
 }

 public String getPassword() {
  return password;
 }

 public void setPassword(String password) {
  this.password = password;
 }

 public boolean isEnabled() {
  return enabled;
 }

 public void setEnabled(boolean enabled) {
  this.enabled = enabled;
 }


 @Override
 public String toString() {
	 return "User [username=" + username + ", Enable=" + enabled+ "]";
 }
}