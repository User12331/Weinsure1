package tn.weinsure1.restController;



import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.weinsure1.entities.sinister;
import tn.weinsure1.service.IContractService;

@RestController
public class contractRestController {
/*
	@Autowired
	IContractService ic;
	
	 	// http://localhost:8000/SpringMVC/servlet/retrieveAllContracts 
		 @GetMapping("/retrieveAllContracts")
		 @ResponseBody
		 public List<Contract> getContract() {
		 List<Contract> c = ic.RetrieveAllContracts();
		 return c;
		 }
		 
		
		 // http://localhost:8000/SpringMVC/servlet/addContract 
		 @GetMapping("/addContract")
		 @ResponseBody	
		  public Contract addContract(Contract c) {
			 Scanner scanner = new Scanner(System.in);
			 SimpleDateFormat dateFormat = new SimpleDateFormat ("yyyy-MM-dd");
			 c.setDuration(scanner.nextInt());
			 c.setCreation_date(new Date());
			 c.setPrice(scanner.nextFloat());
			 return ic.AddContract(c);}
		 
		// http://localhost:8000/SpringMVC/servlet/findByDurationGreater/{Duration}
		@GetMapping("/findByDurationGreater/{Duration}")
		@ResponseBody
		 public List<Contract> findByDurationGreater(@PathVariable("Duration") int Duration){
				List<Contract> c = ic.findByDurationGreater(Duration);		 
				  return c;
				  }	
		
		//Delete contract
		// http://localhost:8000/SpringMVC/servlet/deletecontract/{idcontract}
		   @DeleteMapping("/deletecontract/{idcontract}")
		   @ResponseBody
		   public void removeUser(@PathVariable("idcontract") String idcontract) {
		   ic.DeleteContract(idcontract);
		   }
	*/
}