package tn.weinsure1.restController;
import java.text.ParseException;  
import java.util.List;    
import java.util.Optional;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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
import tn.weinsure1.repository.sinisterRepository;
import tn.weinsure1.service.ITableMortaliteService;
import tn.weinsure1.service.IUserService;
import tn.weinsure1.service.IsinisterService;
import tn.weinsure1.service.TableMortaliteService;
@RestController
public class sinisterrestController {
	      @Autowired
	      IsinisterService sr;
	      @Autowired
		  ITableMortaliteService ts;
	      @Autowired     
	  	sinisterRepository sinistreRepository ;
	      @Autowired
		  IUserService us;
	      private static final Logger L= LogManager.getLogger(TableMortaliteService.class);
		 @GetMapping("/retrieve-All-Sinistres")
		 @ResponseBody
		  public List<sinister> getSinister() {
		 List<sinister> s = sr.retrieveAllSinistres();
		 return s;
		 } 
		  @GetMapping("/retrieve-SinsitresByIdUser")
			 @ResponseBody
		  public List<sinister> SinByIdUser() {
				  Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			 List<sinister> s = sr.findbyuserid(((User)principal).getId());
			// System.out.println(""+ ((User)principal).getContraint().getIdcontraint());
			 return s;
			 } 
		  @GetMapping("/findByAny/{any}")
			 @ResponseBody
		  public List<sinister> findByANy(@PathVariable(value = "any") String any) {
				  
			 List<sinister> s = sr.findByAny(any);
			 return s;
			 }  
		  @GetMapping("/retrieveSinWithReclamation/{id}")
			 @ResponseBody
		  public List<sinister> getSinistersRecla(@PathVariable(value = "id") Long id) {
				  
			 List<sinister> s = sr.findSinisterDescriptionwithUR(id);
			 return s;
			 } 
			  @GetMapping("/userrr")
				 @ResponseBody
		  public String getetet() {
				 String s = sr.yallacurrent();
				 return s;
				 } 
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
		  @PostMapping("/add-sinistre")
		  @ResponseBody
		  public sinister addSinister(@RequestBody sinister s) {
			  Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			  s.setStatus(sinisterstatus.enAttente);			
		  return sr.addSinistre(s,((User)principal).getId());
		  
		  }
		  @GetMapping("/calculCVE/{idS}/{idC}")
		  @ResponseBody
		  public float calculCVE(@PathVariable("idS") Long idS, @PathVariable("idC") Long idC){
				float k = 0 ;
				k = (float) sr.CVE(idS, idC) ; 
				return k ;
			  
			}
		@GetMapping("/calculCD/{idU}/{idC}")
		@ResponseBody
		  public float CapitalCasDéces(@PathVariable("idU") Long idU, @PathVariable("idC") Long idC) throws ParseException{
		float k = 0 ;
		k = (float) sr.CapitalCasDéces(idU, idC) ; 
		return k ;
	  
	}
		@GetMapping("/calculCDP/{idS}/{idC}")
		@ResponseBody
		  public float CapitalDécesPeriodique(@PathVariable("idS") Long idS, @PathVariable("idC") Long idC) throws ParseException{
		float k = 0 ;
		k = (float) sr.CapitalDécesPeriodique(idS, idC) ; 
		sinister ss = sinistreRepository.findById(idS).get();
		return k ;
	  
	}
		@GetMapping("/calculTDE/{idS}/{idC}")
		@ResponseBody
		  public float TDEMPRUNTEUR(@PathVariable("idS") Long idS, @PathVariable("idC") Long idC) throws ParseException{
		float k = 0 ;
		k = (float) sr.TDEMPRUNTEUR(idS, idC) ; 
		return k ;
	  
	}
		  @PutMapping(value = "/aff-sinistre/{idSin}/{idUser}") 
		  public void affecterEmployeADepartement(@PathVariable("idSin")Long idSin, @PathVariable("idUser")Long idUser) {
		  sr.affecterSinisterUser(idSin, idUser);
		  }
		  @GetMapping("/creditsimul/{idU}/{idC}")
		  @ResponseBody
		  public float creditsimul(@PathVariable("idU") Long idU, @PathVariable("idC") Long idC){
				float k = 0 ;
				k = (float) sr.CreditSimulator( idU, idC) ; 
				return k ;
			  
			}
		     @PutMapping("/CheckStatus")
		  public void checkStatus() {
			 sr.CheckStatus();
			 } 
		     @PutMapping("/sendMail")
		  public void sendMail() {
			 sr.SendMail();
			 }
		    @PutMapping(value = "/modifyDescription/{idS}/{newdescription}") 
		 	@ResponseBody
	 	  public void mettreAjourDescriptionBysinIdId(@PathVariable("idS") Long idS, @PathVariable("newdescription") String disc) {
		 		sr.UpdateSinDescription(idS, disc);
		 		
		 	}
			@GetMapping("/AVG")
			@ResponseBody
			  public float CapitalCasDéces()  {
			float k = 0 ;
			k = (float) sr.GetSalaireMoyenUsersContractSinister() ; 
			return k ;
		  
		}
		  
				}

