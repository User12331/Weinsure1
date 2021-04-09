package tn.weinsure1.restController;



import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.weinsure1.entities.Contract;
import tn.weinsure1.entities.sinister;
import tn.weinsure1.service.IContractService;

@RestController
public class contractRestController {

	@Autowired
	IContractService ic;
	
		@GetMapping("/retrieveAllContracts")
		@ResponseBody
		public List<Contract> getContract() {
		 List<Contract> c = ic.RetrieveAllContracts();
		 return c;
		 }
		
		@PostMapping("/addContract")
		@ResponseBody	
		public Contract addContract(@RequestBody Contract c) {
			//c.setCreation_date(new Date());
			//c.setPrice((float) ic.RITP(500, 1));
		return ic.AddContract(c);}
		@GetMapping("/findByDurationGreater/{Duration}")
		@ResponseBody
		public List<Contract> findByDurationGreater(@PathVariable("Duration") int Duration){
				List<Contract> c = ic.findByDurationGreater(Duration);		 
				  return c;
				  }	
		@DeleteMapping("/deletecontract/{idcontract}")
		@ResponseBody
		public void removeUser(@PathVariable("idcontract") String idcontract) {
		   ic.DeleteContract(idcontract);
		   }
		@GetMapping("/RITP/{idu}/{p}")
		@ResponseBody  
		public double contractRITP(@PathVariable("idu")long idu,@PathVariable("p")double p)
		{
			return ic.RITP(p, idu);
		}
		@GetMapping("/RITC/{idu}/{c}")
		@ResponseBody  
		public double contractRITC(@PathVariable("idu")long idu,@PathVariable("c")double c)
		{
			return ic.RITC(c, idu);
		}
		@GetMapping("/CVU/{C}/{idu}/{duree}")
		@ResponseBody 
		public float CapitalVieUnique (@PathVariable("c")float C,@PathVariable("idu")long userid,@PathVariable("duree") int duree){
			return ic.CapitalVieUnique(C, userid, duree);
			
		}
		@GetMapping("/PVU/{prime}/{idu}/{duree}")
		@ResponseBody 
		public float PrimeVieUnique(@PathVariable("p")float prime,@PathVariable("idu")long userid, @PathVariable("duree")int duree){
			return ic.PrimeVieUnique(prime, userid, duree);
		}
		@PutMapping("/ContractToUser/{idcnt}/{iduser}")
		@ResponseBody
		public void ContractToUser(@PathVariable("iduser")long iduser,@PathVariable("idcnt")long idcnt)
		{
		ic.ContractToUser(idcnt, iduser);
		}

}