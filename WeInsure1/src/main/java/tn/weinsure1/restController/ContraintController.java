package tn.weinsure1.restController;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import net.minidev.json.JSONObject;
import tn.weinsure1.entities.Contract;
import tn.weinsure1.entities.Contraint;
import tn.weinsure1.entities.User;
import tn.weinsure1.service.IContraintService;

@RestController
public class ContraintController {
	
	@Autowired
	IContraintService ics;
	
	@PostMapping("/addContraint")
	@ResponseBody	
	public Contraint addContraint(@RequestBody Contraint c) {
	return ics.AddContraint(c);
	}
	
	@GetMapping("/retrieveAllContraints")
	@ResponseBody
	public List<Contraint> getContraint() {
	 List<Contraint> c = ics.RetrieveAllContraints();
	 return c;
	 }
	
	@DeleteMapping("/deleteContraints/{idcontract}")
	@ResponseBody
	public void removeUser(@PathVariable("idcontract") String idc) {
		ics.DeleteContraint(idc);
	}
	
	@PutMapping("/updateContraint")
	@ResponseBody	
	public String updateContraint(@RequestBody Contraint c) {
		return ics.UpdateContraint(c);
	}
	
	@GetMapping("/filtreType/{type}")
	@ResponseBody
	public List<Contraint> contraintFiltre(@PathVariable("type") String type){
		
		if( type.equals("feedback"))
			return ics.contraintFiltreFeedback();
		
		return ics.contraintFiltreReclmation();
	}	
	
	@GetMapping("/filtreState/{state}")
	@ResponseBody
	public List<Contraint> contraintFiltreState(@PathVariable("state") int state){
		
		return ics.contraintFiltreState(state);
	}	
	
	@PutMapping("/repondre")
	@ResponseBody	
	public String repondreContraint(@RequestBody Map<String, String> json) {
		return ics.repondreContraint(json.get("id") , json.get("rep"))	;	
	}
	
	@GetMapping("/filtreDates")
	@ResponseBody
	public List<Contraint> contraintFiltreState(@RequestBody Map<String, String> json){
		return ics.getAllBetweenDates(LocalDate.parse(json.get("start")), LocalDate.parse(json.get("end")) );
	}
	
	@GetMapping("/stat")
	@ResponseBody
	public  List<Map<String, BigInteger>> contraintFiltreState(){
		
		//JSONObject jj = new JSONObject(ics.statistiqueDate());
		System.out.println(ics.statistiqueDate().size());
		return ics.statistiqueDate();
		
	}

}
