package tn.weinsure1.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.weinsure1.entities.Contract;
import tn.weinsure1.entities.Offer;
import tn.weinsure1.entities.User;
import tn.weinsure1.repository.UserRepository;
import tn.weinsure1.service.IOfferService;
import tn.weinsure1.service.IUserService;

@RestController
public class OfferRestController {
	
	@Autowired
	IOfferService io;
	UserRepository userRepository;
	IUserService iu;

	
	@PostMapping("/addOffer")
	@ResponseBody	
	public Offer addOffer(@RequestBody Offer o) {
	return io.addOffer(o);}
	
	@GetMapping("/retrieveAllOffers")
	@ResponseBody
	public List<Offer> getOffer() {
	 List<Offer> o = io.retrieveAllOffers();
	 return o;
	 }
	
	@PostMapping("/affOfferUser/{idUser}/{idOffer}")
	  public void affecterUseraOffer(@PathVariable("idUser")Long iduser,@PathVariable("idOffer")Long idoffer) {
	  io.affecterUserOffer(iduser, idoffer);
	  }
	
	@GetMapping("/olduser")
	@ResponseBody
	public Long OldUser() {
	 Long o = io.Old_User1();
	 return o;
	 }
	
	@GetMapping("/pack3")
	@ResponseBody
	public List<Long> pack3() {
	 List<Long> o = io.pack_3();
	 return o;
	 }
	
	@GetMapping("/pack2")
	@ResponseBody
	public List<Long> pack2() {
	 List<Long> o = io.pack_2();
	 return o;
	 }
	
	@GetMapping("/pack1")
	@ResponseBody
	public List<Long> pack1() {
	 List<Long> o = io.pack_1();
	 return o;
	 }
	
	@GetMapping("/test")
	@ResponseBody
	public Long test2() {
	 Long o = io.test1();
	 return o;
	 }
	
	@GetMapping("/topnum")
	@ResponseBody
	public Long topnum() {
	 Long o = io.topnum();
	 return o;
	 }
	
	@GetMapping("/topusers")
	@ResponseBody
	public List<Long> topusers() {
	 List<Long> o = io.Top_Users(io.topnum());
	 return o;
	 }
	
	@GetMapping("/top3")
	@ResponseBody
	public List<String> top3() {
	 List<String> o = io.Top3();
	 return o;
	 }
	
	@GetMapping("/top4")
	@ResponseBody
	public int[] top4() {
	 int[] o = io.Top4();
	 return o;
	 }
	
	@GetMapping("/fidele1")
	@ResponseBody
	public List<String> fidele1() {
	 List<String> o = io.fidele1();
	 return o;
	 }
	
	@GetMapping("/fidele2")
	@ResponseBody
	public float[] fidele2() {
	 float[] o = io.fidele2();
	 return o;
	 }
	
	/*@PutMapping("/fidelite")
	@ResponseBody
		public User UpdateUser() {
		User u = userRepository.findById((long) 6).get();
		u.setPointsF((float) 200);
			return userRepository.save(u);}*/
	
	@PostMapping("/affecter/{idUser}/{idOffer}")
	@ResponseBody
	public void affuo(@PathVariable("idUser")Long idu,@PathVariable("idOffer")Long ido) {
	 io.affectation(idu, ido);
	 }
	
	@PutMapping("/fidele/{somme}/{idu}")
	@ResponseBody
	public void fidelite(@PathVariable("somme")float som,@PathVariable("idu")Long id) {
	 io.Fidele(som, id);
	 }
	
	@PutMapping("/remise/{somme}/{idu}")
	@ResponseBody
	public void remise(@PathVariable("somme")float som,@PathVariable("idu")Long id) {
	 io.remise1(1-som, io.basprix1(id));
	 }
	
	// these 5 are for testing remise
	@PutMapping("/remise1/{somme}/{idc}")
	@ResponseBody
	public void remise1(@PathVariable("somme")float som,@PathVariable("idc")Long id) {
	 io.remise1(1-som, id);
	 }
	@GetMapping("/bas/{idu}")
	@ResponseBody
	public Long bas(@PathVariable("idu")Long id) {
		 Long o = io.basprix1(id);
		 return o;
	 }
	@GetMapping("/basp1/{idu}")
	@ResponseBody
	public Long basp1(@PathVariable("idu")Long id) {
		 Long o = io.basprixp1(id);
		 return o;
	 }
	@GetMapping("/basp2/{idu}")
	@ResponseBody
	public Long basp2(@PathVariable("idu")Long id) {
		 Long o = io.basprixp2(id);
		 return o;
	 }
	@GetMapping("/basp3/{idu}")
	@ResponseBody
	public Long basp3(@PathVariable("idu")Long id) {
		 Long o = io.basprixp3(id);
		 return o;
	 }
	
	@PostMapping("/affectationAuto")
	@ResponseBody	
	public void affectationauto1(@RequestBody Offer o) {
	io.affectationauto(o);}
	
	@PutMapping("/desaffecter/{idUser}/{idOffer}")
	@ResponseBody
	public void desaffuo(@PathVariable("idUser")Long idu,@PathVariable("idOffer")Long ido) {
	 io.desaffectation(idu, ido);
	 }
	
	@DeleteMapping("/deleteoffer/{idOffer}")
	@ResponseBody
	public void delete(@PathVariable("idOffer")Long ido) {
		 io.deleteOffer(ido);
		 }

	@PutMapping("/updateoffer")
	@ResponseBody	
	public Offer Update(@RequestBody Offer o) {
	return io.updateOffer(o);}
	
	@GetMapping("/simulateur/{credit}/{mois}")
	@ResponseBody	
	public String sim(@PathVariable("credit")Long cred,@PathVariable("mois")int m) {
	return io.simulateur(cred, m);}
	
	@GetMapping("/cptendettement/{salaire}/{ppm}")
	@ResponseBody	
	public String cpt(@PathVariable("salaire")double sal,@PathVariable("ppm")Long p) {
	return io.cptendettement(sal, p);}
	
	@GetMapping("/annuite/{montant}/{interet}/{duree}")
	@ResponseBody	
	public String annuite(@PathVariable("montant")double m,
			@PathVariable("interet")float i, @PathVariable("duree")int d) {
	return io.annuiteconstante(m, i, d);}
}

