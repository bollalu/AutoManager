package sample.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@XmlRootElement(name = "users")
@Entity
@Table(name = "users")
public class Users {
 
	@Id 
	@Column(unique = true)	 
	 private String username;
	 private String password;
	 private boolean enabled = true;
	 private String email;
	 
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
	  PasswordEncoder encoder = (PasswordEncoder) new BCryptPasswordEncoder();
	  this.password = ((BCryptPasswordEncoder) encoder).encode(password);
	 }

	 public boolean isEnabled() {
	  return enabled;
	 }

	 public void setEnabled(boolean enabled) {
	  this.enabled = enabled;
	 }
	 
	 public String getEmail() {
		  return email;
	 }
	 
	 public void setEmail(String email) {
		  this.email = email;
	 } 


	 @Override
	 public String toString() {
		 return "User [username=" + username + ", Email=" + email + ", Enable=" + enabled + "]";
		 //return "User [username=" + username + ", Enable=" + enabled + "]";		 
	 }
	}