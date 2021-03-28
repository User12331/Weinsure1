package tn.weinsure1.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.weinsure1.entities.Contract;
import tn.weinsure1.service.IContractService;

@RestController
public class contractRestController {

	@Autowired
	IContractService ic;
	
	 	// http://localhost:8000/SpringMVC/servlet/retrieveAllContracts 
		 @GetMapping("/retrieveAllContracts")
		 @ResponseBody
		 public List<Contract> getContract() {
		 List<Contract> c = ic.RetrieveAllContracts();
		 return c;
		 }
	 
}
