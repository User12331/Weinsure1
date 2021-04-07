package tn.weinsure1.restController;
import java.util.List;    
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.weinsure1.entities.User;
import tn.weinsure1.entities.sinister;
import tn.weinsure1.entities.sinisterstatus;
import tn.weinsure1.service.ITableMortaliteService;
import tn.weinsure1.service.IsinisterService;
import tn.weinsure1.service.TableMortaliteService;
@RestController
public class sinisterrestController {
	@Autowired
	IsinisterService sr;
	@Autowired
	ITableMortaliteService ts;
	private static final Logger L= LogManager.getLogger(TableMortaliteService.class);
	// http://localhost:8000/SpringMVC/servlet/retrieve-All-Sinistres 
		 @GetMapping("/retrieve-All-Sinistres")
		 @ResponseBody
		 public List<sinister> getSinister() {
		 List<sinister> s = sr.retrieveAllSinistres();
		 return s;
		 } 
			// http://localhost:8000/SpringMVC/servlet/getSinisterBystatus/
		 @GetMapping("/getSinisterBystatus/{id}")
		 @ResponseBody
		 public List<sinister> getsinisterbystat(@PathVariable(value = "id") Long id) {
		 List<sinister> s = sr.findbyuserid(id);
		 return s;
		 } 
		 @GetMapping("/getSinisterBystatuss/{id}")
		 @ResponseBody
		 public int getduration(@PathVariable(value = "id") Long id) {
		 int s = sr.findcontractdurationBysinister(id);
		 return s;
		 } 
		 // Ajouter sinister : http://localhost:8000/SpringMVC/servlet/add-sinistre
		  @PostMapping("/add-sinistre/{id}")
		  @ResponseBody
		  public sinister addSinister(@RequestBody sinister s, @PathVariable Long id) {
		  return sr.addSinistre(s,id);
		  
		  }
		  @GetMapping("/calcul/{capital}/{ageClient}/{AgeMax}/{taux}")
		  @ResponseBody
		  public float calcul(@PathVariable("capital") float capital , @PathVariable("ageClient") int ageClient, @PathVariable("AgeMax") int AgeMax, @PathVariable("taux") double taux){
				int k;
				float prime = 0;
				for (k =0; k < AgeMax - ageClient; k++) {
					float dxk= ts.findByDecesDx(ageClient+k); 	
					L.info("DX " + dxk) ;
					float lx = ts.findBySurvivantsLx(ageClient);
					 double v = Math.pow( 1/ (1+taux) ,  k + (1/2)  );			 
					prime = (float) (capital * v) *  ( dxk / lx) ;	
			}

				L.info("PRIME+++++++++ =" + prime) ;
				return prime;
			}
		  //http://localhost:8000/SpringMVC/servlet/aff-sinistre/10/10
		  @PutMapping(value = "/aff-sinistre/{idSin}/{idUser}") 
		  public void affecterEmployeADepartement(@PathVariable("idSin")Long idSin, @PathVariable("idUser")Long idUser) {
		  sr.affecterSinisterUser(idSin, idUser);
		  }
		  @GetMapping("/creditsimul/{taux}/{idU}/{idC}")
		  @ResponseBody
		  public float creditsimul(@PathVariable("taux") double taux , @PathVariable("idU") Long idU, @PathVariable("idC") Long idC){
				float k = 0 ;
				k = (float) sr.CreditSimulator(taux, idU, idC) ; 
				return k ;
			  
			}
		     @PutMapping("/CheckStatus")
			 public void checkStatus() {
			 sr.CheckStatus();
			 } 
		  
		  
		  
		  
				}

