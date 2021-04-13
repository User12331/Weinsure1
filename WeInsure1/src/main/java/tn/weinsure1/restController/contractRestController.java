package tn.weinsure1.restController;



import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.weinsure1.entities.Contract;
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
		
		@GetMapping("/RertiveContractByUserId/{id}")
		@ResponseBody
		public List<Contract> RetrieveContractsByUserId(@PathVariable("id")long id) {
			List<Contract> cnt = ic.RetrieveContractsByUserId(id);
			return cnt;
		}
		
		@DeleteMapping("/DeleteContractsByUserId/{id}")
		@ResponseBody
		public void DeleteContractsByUserID(@PathVariable("id")long id){
			ic.DeleteContractsByUserId(id);
			
		}
		
		@PostMapping("/addContract")
		@ResponseBody	
		public Contract addContract(@RequestBody Contract c) {
			//{	"duration":"5",	"expiration_date":"",	"price":"",	"type":"",	"rate":""}
			c.setCreation_date(new Date());
			c.setDocument(null);
			return ic.AddContract(c);}
		
		@GetMapping("/findByDurationGreater/{Duration}")
		@ResponseBody
		public List<Contract> findByDurationGreater(@PathVariable("Duration") int Duration){
				List<Contract> c = ic.findByDurationGreater(Duration);		 
				  return c;
				  }	
		
		@DeleteMapping("/deletecontract/{idcontract}")
		@ResponseBody
		public void removeContract(@PathVariable("idcontract") String idcontract) {
		   ic.DeleteContract(idcontract);
		   }
		
		@GetMapping(value="/CountContracts")
		@ResponseBody
		public int CountContracts(){
			return ic.CountCntracts();
			
		}

		@PutMapping(value = "/MAJcntprice/{cntid}/{price}") 
		@ResponseBody
		public void MAJContractPrice(@PathVariable("price")float price,@PathVariable("cntid")long cntid){
			ic.MAJContractPrice(price, cntid);
		}
		
		@PutMapping(value = "/MAJcntduration/{cntid}/{duration}") 
		@ResponseBody
	    public void MAJContractDuration(@PathVariable("duration")int duration, @PathVariable("cntid")long cntid){
			ic.MAJContractDuration(duration, cntid);
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
		public float CapitalVieUnique (@PathVariable("C")float C,@PathVariable("idu")long userid,@PathVariable("duree") int duree){
			return ic.CapitalVieUnique(C, userid, duree);
			
		}
		
		@GetMapping("/PVU/{prime}/{idu}/{duree}")
		@ResponseBody 
		public float PrimeVieUnique(@PathVariable("prime")float prime,@PathVariable("idu")long userid, @PathVariable("duree")int duree){
			return ic.PrimeVieUnique(prime, userid, duree);
		}
		
		@PutMapping("/ContractToUser/{idcnt}/{iduser}")
		@ResponseBody
		public void ContractToUser(@PathVariable("iduser")long iduser,@PathVariable("idcnt")long idcnt)
		{
		ic.ContractToUser(idcnt, iduser);
		}
				
		@GetMapping(value="/CapitalMixte/{prime}/{userid}/{duree}")
		@ResponseBody
		public float CapitalMixte(@PathVariable("prime")double prime,@PathVariable("userid")long userid,@PathVariable("duree")int n){
			return ic.CapitalMixte(prime, userid, n);
			}
		

}