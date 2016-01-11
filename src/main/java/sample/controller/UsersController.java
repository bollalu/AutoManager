package sample.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import sample.model.UsersList;
import sample.MailMail;
import sample.StringUtils;
import sample.model.Authorities;
import sample.model.Users;
import sample.repo.AuthoritiesRepository;
import sample.repo.RuoloRepository;
import sample.repo.UsersRepository;

@Controller
public class UsersController {

	@Autowired
	protected UsersRepository usr;
	@Autowired
	protected AuthoritiesRepository aur;
	@Autowired
	protected RuoloRepository rur;
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/admin/userslist", method = RequestMethod.GET)
	public String userslist(Model model) {
        System.out.println("Lista Utenti -> GET");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String nomeUtente = auth.getName();
        log.info("User {}: Richiesta lista Utenti", nomeUtente);
		return "admin@userslist";
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/admin/users", method = RequestMethod.GET)
	public String users(@RequestParam(value = "id", required = true) String users,	Model model) {
        System.out.println("Users Edit -> GET");
        System.out.println(rur.findAll());
		Users user = usr.findOne(users);
		model.addAttribute("users", user);
		model.addAttribute("ruoli", rur.findAll());		
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String nomeUtente = auth.getName();
        log.info("User {}: Modifica Users-> {}", nomeUtente, users);
		return "admin@usersEditForm";
	}
	
	@RequestMapping(value = "/admin/users", method = RequestMethod.POST)
	public String users(@ModelAttribute Users users,
										@RequestParam(value="oldusn", required=true) String oldusn,
										@RequestParam(value="oldemail", required=true) String oldemail,										
										@RequestParam(value="resetpw", required=false) String resetpw,										
										@RequestParam(value="ruolo", required=true) String ruolo,
										Model model) {
        System.out.println("Users -> EDIT ->POST");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String nomeUtente = auth.getName();        
        try {
        		if (!oldusn.equals(users.getUsername()) && usr.exists(users.getUsername())){
        			throw new Exception();	
        		}    		
                System.out.println("->" + resetpw + "<-");                
        		if (null != resetpw){
        			String passReset;
        			//passReset="123456";
        			passReset=StringUtils.generateRandomPassword();
        			users.setPassword(passReset);
        	    	ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Mail.xml"); 
        	       	MailMail mm = (MailMail) context.getBean("mailMail");
        	        mm.sendMail("lisp65@gmail.com",
        	        		   	users.getEmail(),
        	        		   	"paolo.romani@furiere.ch",
        	        		   	"Automanager Info", 
        	        		   	"Informazione importante \n\n Nuovo codice : " + passReset);     			
        		}else{
        			users.setPasswordEncoded(usr.findOne(oldusn).getPassword());	
        		}
        		if (!oldusn.equals(users.getUsername())){
        	        aur.deleteByUsername(oldusn);
        	        usr.deleteByUsername(oldusn);	
        		}         		
        		usr.save(users);
	    		Authorities authorities = new Authorities();
    			authorities.setUsername(users.getUsername());
    			authorities.setAuthority(ruolo);
    			aur.save(authorities);	
		        log.info("User {}: Salvata Modifica Users-> {}", nomeUtente, users.getUsername());
	    		return "redirect:/admin/userslist";
			} catch (Exception e) {
				model.addAttribute("messaggio", "L'utente " + users.getUsername() + " é già presente");
				users.setUsername(oldusn);
				users.setEmail(oldemail);
				model.addAttribute("users", users);
				model.addAttribute("ruoli", rur.findAll());	
		        log.info("User {}: Non Salvata Modifica Utente (duplicato) Users-> {}", nomeUtente, users.getUsername());
				return "admin@usersEditForm";
        }
	}
	
	
	@RequestMapping(value = "/admin/users/new", method = RequestMethod.GET)
	public String users(Model model) {
        System.out.println("Users -> Nuovo -> GET");
		Users users = new Users();
		model.addAttribute("users", users);
		model.addAttribute("ruoli", rur.findAll());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String nomeUtente = auth.getName();
        log.info("User {}: Nuovo Utente", nomeUtente);	
		return "admin@usersNewForm";
	}
	
	@RequestMapping(value = "/admin/users/new", method = RequestMethod.POST)
	public String usersPOST(@ModelAttribute Users users, @RequestParam(value="ruolo", required=true) String ruolo, Model model) {
        System.out.println("Users -> Nuovo -> POST");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String nomeUtente = auth.getName();
        System.out.println(usr.exists(users.getUsername()));
        try {      			
        	 if (usr.exists(users.getUsername())) throw new Exception();
 				String passReset;
 				//passReset="123456";
 				passReset=StringUtils.generateRandomPassword();
 	            System.out.println("3"); 				
 				users.setPassword(passReset);
 				ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Mail.xml"); 
 	            System.out.println("3"); 				
 				MailMail mm = (MailMail) context.getBean("mailMail");
                System.out.println("4");   				
 				mm.sendMail("lisp65@gmail.com",
 	        		   	users.getEmail(),
 	        		   	"paolo.romani@furiere.ch",
 	        		   	"Automanager Info", 
 	        		   	"Informazione importante \n\n Nuovo codice : " + passReset); 
                System.out.println("5");    	        
        		usr.save(users);	
        		Authorities authorities = new Authorities();
				authorities.setUsername(users.getUsername());
				authorities.setAuthority(ruolo);
	            System.out.println("4");
			    aur.save(authorities);				
				log.info("User {}: Salvato Nuovo User-> {}", nomeUtente, users.getUsername());				
	    		return "redirect:/admin/userslist";
			} catch (Exception e) {
				model.addAttribute("messaggio", "L'utente " + users.getUsername() + " é già presente");
				log.info("User {}: Non salvato Nuovo Utente (duplicato) Utente-> {}", nomeUtente, users.getUsername());
				return "admin@usersNewForm";
			}
	}
	
	
	@RequestMapping(value = "/admin/users/remove", method = RequestMethod.GET)
	public String usersRemove(@RequestParam(value = "id", required = true) String username, Model model) {
        System.out.println("Users -> Remove -> GET");	
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String nomeUtente = auth.getName();
        aur.deleteByUsername(username);
        usr.delete(username);        
        log.info("User {}: Eliminato Utente-> {}", nomeUtente, username);
		return "redirect:/admin/userslist";
	}
	
	@RequestMapping(value = "/json/userslist", method = RequestMethod.GET)
	public @ResponseBody UsersList userslistJSON(Model model) {
		return new UsersList(usr.findAll());
	}
	
	@RequestMapping(value = "/json/userslist/search", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody UsersList usersListJSON(@RequestParam(value = "q", required = true) String username, Model model) {
		return new UsersList(usr.findUsersByUsername(username));
	}

}
