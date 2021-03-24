package tn.weinsure1.restController;

import java.util.List; 
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import tn.weinsure1.entities.sinister;
import tn.weinsure1.entities.sinisterstatus;
import tn.weinsure1.service.IsinisterService;

@RestController
public class sinisterrestController {
	@Autowired
	IsinisterService sr;
	// http://localhost:8000/SpringMVC/servlet/retrieve-All-Sinistres 
		 @GetMapping("/retrieve-All-Sinistres")
		 @ResponseBody
		 public List<sinister> getSinister() {
		 List<sinister> s = sr.retrieveAllSinistres();
		 return s;
		 } 
			// http://localhost:8000/SpringMVC/servlet/getSinisterBystatus/
		 @GetMapping("/getSinisterBystatus/{status}")
		 @ResponseBody
		 public List<sinister> getsinisterbystat(@PathVariable(value = "status") sinisterstatus status) {
		 List<sinister> s = sr.findByStatus(status);
		 return s;
		 } 
		 /*
		//*http://localhost:8087/SpringMVC/servlet/retrieve-sinistre/{sinistre-id}
		  @GetMapping("/sinister-user/{sinister-id}")
		  @ResponseBody
		  public sinister retrieveUser(@PathVariable("sinistre-id") Long sinisterid) {
			 
		  return sr.retrieveSinistre(sinisterid.toString());
		  }
		  */

		 // Ajouter sinister : http://localhost:8000/SpringMVC/servlet/add-sinistre
		  @PostMapping("/add-sinistre")
		  @ResponseBody
		  public sinister addSinister(@RequestBody sinister s) {
		  return sr.addSinistre(s);
		  
		  }
		  /*
		// http://localhost:8081/SpringMVC/servlet/remove-user/{user-id}
		   @DeleteMapping("/remove-user/{user-id}")
		   @ResponseBody
		   public void removeUser(@PathVariable("user-id") user userId) {
		   userService.deleteUser(userId);
		   }
		  
		   // http://localhost:8081/SpringMVC/servlet/modify-user
		   @PutMapping("/modify-user")
		    @ResponseBody
		    public user modifyUser(@RequestBody user user){
		    return userService.updateUser(user);
		   }
*/
}
