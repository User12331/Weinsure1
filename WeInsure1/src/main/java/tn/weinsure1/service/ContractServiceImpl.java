package tn.weinsure1.service;



import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import tn.weinsure1.entities.Contract;
import tn.weinsure1.entities.ContractType;
import tn.weinsure1.entities.User;
import tn.weinsure1.repository.TableMortalitéRepository;
import tn.weinsure1.repository.UserRepository;
import tn.weinsure1.repository.ContractRepository;

@Service
public class ContractServiceImpl implements IContractService {
	@Autowired
	ContractRepository ContractRepository;
	@Autowired
	UserRepository UserRepository;
	@Autowired
	TableMortalitéRepository tr ;

	private static final Logger L= LogManager.getLogger(ContractServiceImpl.class);
	
	@Override
	public List<Contract> RetrieveAllContracts(){
		List<Contract> cnts = (List<Contract>) ContractRepository.findAll();
		for (Contract cnt : cnts){
			L.info("user +++ :" + cnt);
		}
		return cnts;
	}
	
	@Override
	public Contract AddContract(Contract c) {
		Contract cnt = null;
		cnt = ContractRepository.save(c);
		return cnt;
	}
	
	@Override
	public void DeleteContract(String id) {
		ContractRepository.deleteById(Long.parseLong(id));
	}
	
	@Override
	public Contract UpdateContract(Contract c) {
		Contract ContractUpdated = ContractRepository.save(c);
		return ContractUpdated;
	}	
	
	@Override
	public Contract RetrieveContract(String id) {
		L.info("in RetrieveContract id = " + id);
		Contract c = ContractRepository.findById(Long.parseLong(id)).get();
		L.info("Contract returned = : " + c);
		return c;	
	}
	
		@Override
	public List<Contract> findByDurationGreater(int year) {
		List<Contract> cnt = ContractRepository.findByDurationGreater(year);
		L.info("Contract +++ :" + cnt) ;
		return cnt;
	}
		
	@Override
	public int CountCntracts(){
		return ContractRepository.CountCntracts();
	}
	
	@Override
	public void DeleteContractsByUserId(long id){
		List<Contract> cnt = ContractRepository.RetrieveContractsByUserId(id)	;
		ContractRepository.deleteAll(cnt);
	}
	
	@Override
	public void ContractToUser(long cntID, long userID){
	Contract cnt = ContractRepository.findById(cntID).get();
	User user = UserRepository.findById(userID).get();
	cnt.setUser(user);
	ContractRepository.save(cnt);
	}
	
	@Override
	public float TotalPricing() {
		float total =0;
		total = ContractRepository.TotalPricing();
		return total;
	}
	
	@Override
	public List<Contract> RetrieveContractsByUserId(long id) {
		List<Contract> cnt = ContractRepository.RetrieveContractsByUserId(id);
		L.info("contract +++ :" + cnt) ;
		return cnt;
	}
	
	@Override
	public void MAJContractPrice(float price,long cntid){
		Contract cnt = ContractRepository.findById(cntid).get();
		cnt.setPrice(price);
		ContractRepository.save(cnt);
	}
	
	@Override
	public void MAJContractDuration(int duration,long cntid){
		Contract cnt = ContractRepository.findById(cntid).get();
		int d =cnt.getDuration();
		cnt.setDuration(duration);
		LocalDate exp = (new Date()).toInstant()
			      .atZone(ZoneId.systemDefault())
			      .toLocalDate().plusYears(duration);
		cnt.setExpiration_date(Date.from(exp.atStartOfDay(ZoneId.systemDefault()).toInstant()));
		cnt.setPrice(cnt.getPrice()*duration/d);
		ContractRepository.save(cnt);
	}
	
	@Override
	public float CapitalVieUnique (float C,long userid, int duree){
		float prime =0;
		double taux = 0;
		Date date = UserRepository.findById(userid).get().getBirthdate();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		int BDay = calendar.get(Calendar.YEAR);
		LocalDate now = LocalDate.now();
		int ageClient = now.getYear()-BDay;
		if((ageClient > 18) &&( ageClient<=35) )
		taux = 0.13;
		else if ((ageClient >35) &&( ageClient<=50) )
		taux = 0.19;
		else if ((ageClient >50) &&( ageClient<60) )
		taux = 0.27;
		else {L.info("Erreur: Le client n'est pas eligible a  ce type de contrat");
		return 0;
		}
		float lxn = tr.findBySurvivantsLx(ageClient+duree);
		float lx = tr.findBySurvivantsLx(ageClient);
		double v = Math.pow( 1/ (1+taux) ,duree);
		prime = (float) (C*(lxn/lx) * v);
		if (prime > (0.5*UserRepository.findById(userid).get().getSalary()))
				{L.info("Le salaire du client n'est pas suffisant pour ce montant!");
				return 0;
				}
		Contract c = new Contract();
		c.setCreation_date(new Date());
		c.setDuration(duree);
		LocalDate exp = (new Date()).toInstant()
			      .atZone(ZoneId.systemDefault())
			      .toLocalDate().plusYears(duree);
		c.setExpiration_date(Date.from(exp.atStartOfDay(ZoneId.systemDefault()).toInstant()));
		c.setType(ContractType.Vie);
		c.setRate(taux);
		c.setPrice((float) (prime*duree));
		User user = UserRepository.findById(userid).get();
		c.setUser(user);
		ContractRepository.save(c);
		return prime;
	}
	
	@Override
	public float PrimeVieUnique(float prime,long userid, int duree){
		if (prime > (0.5*UserRepository.findById(userid).get().getSalary()))
		{L.info("Le salaire du client n'est pas suffisant pour ce montant!");
		return 0;
		}
		float C =0;
		double taux = 0;
		Date date = UserRepository.findById(userid).get().getBirthdate();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		int BDay = calendar.get(Calendar.YEAR);
		LocalDate now = LocalDate.now();
		int ageClient = now.getYear()-BDay;
		if((ageClient > 18) &&( ageClient<=35) )
		taux = 0.13;
		else if ((ageClient >35) &&( ageClient<=50) )
		taux = 0.19;
		else if ((ageClient >50) &&( ageClient<60) )
		taux = 0.27;
		else {L.info("Erreur: Le client n'est pas eligible a  ce type de contrat");
		return 0;
		}
		float lxn = tr.findBySurvivantsLx(ageClient+duree);
		float lx = tr.findBySurvivantsLx(ageClient);
		double v = Math.pow( 1/ (1+taux) ,duree);
		C = (float) (prime/((lxn/lx) * v));
		Contract c = new Contract();
		c.setCreation_date(new Date());
		c.setDuration(duree);
		LocalDate exp = (new Date()).toInstant()
			      .atZone(ZoneId.systemDefault())
			      .toLocalDate().plusYears(duree);
		c.setExpiration_date(Date.from(exp.atStartOfDay(ZoneId.systemDefault()).toInstant()));
		c.setType(ContractType.Vie);
		c.setRate(taux);
		c.setPrice((float) (prime*duree));
		User user = UserRepository.findById(userid).get();
		c.setUser(user);
		ContractRepository.save(c);
		return C;
	}
	
	@Override
	public double RITP(double prime,long userid){
		float somme1 = 0,somme2=0;
		double taux = 0;
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(UserRepository.findById(userid).get().getBirthdate());
		int BDay = calendar.get(Calendar.YEAR);
		LocalDate now = LocalDate.now();
		int ageClient = now.getYear()-BDay;
		if((ageClient > 18) &&( ageClient<=35) )
		{taux = 0.13;
		L.info("Taux ="+taux);
		}
	else if ((ageClient >35) &&( ageClient<=50) )
		{taux = 0.19;
		L.info("Taux ="+taux);
		}
	else if ((ageClient >50) &&( ageClient<60) )
		{taux = 0.27;
		L.info("Taux ="+taux);
		}
	else {
		L.info("Erreur: Le client n'est pas eligible a  ce type de contrat");
		return 0;
	}
		for (int i=0;i<60-ageClient-1;i++)
		{
			float lxn = tr.findBySurvivantsLx(ageClient+i);
			float lx = tr.findBySurvivantsLx(ageClient);
			double v = Math.pow( 1/ (1+taux) ,i);
			somme1=(float) (somme1+((lxn/lx)*v));
		}
		double side1= prime*somme1;
		for (int i=60-ageClient-1;i<tr.findAgeMax()-ageClient;i++)
		{
			float lxn = tr.findBySurvivantsLx(ageClient+i);
			float lx = tr.findBySurvivantsLx(ageClient);
			double v = Math.pow( 1/ (1+taux) ,i);
			somme2=(float) (somme2+((lxn/lx)*v));
		}
		Contract c = new Contract();
		c.setCreation_date(new Date());
		c.setDuration(tr.findAgeMax()-ageClient);
		LocalDate exp = (new Date()).toInstant()
			      .atZone(ZoneId.systemDefault())
			      .toLocalDate().plusYears(tr.findAgeMax()-ageClient);
		c.setExpiration_date(Date.from(exp.atStartOfDay(ZoneId.systemDefault()).toInstant()));
		c.setType(ContractType.Rente);
		c.setRate(taux);
		c.setPrice((float) (prime*(60-ageClient)));
		User user = UserRepository.findById(userid).get();
		c.setUser(user);
		ContractRepository.save(c);
		return side1/somme2;
	}
	
	@Override
	public double RITC(double capital,long userid){
		float somme1 = 0,somme2=0;
		double taux = 0;
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(UserRepository.findById(userid).get().getBirthdate());
		int BDay = calendar.get(Calendar.YEAR);
		LocalDate now = LocalDate.now();
		int ageClient = now.getYear()-BDay;
		if((ageClient > 18) &&( ageClient<=35) )
		{taux = 0.13;
		L.info("Taux ="+taux);
		}
	else if ((ageClient >35) &&( ageClient<=50) )
		{taux = 0.19;
		L.info("Taux ="+taux);
		}
	else if ((ageClient >50) &&( ageClient<60) )
		{taux = 0.27;
		L.info("Taux ="+taux);
		}
	else {
		L.info("Erreur: Le client n'est pas eligible a  ce type de contrat");
		return 0;
	}
		for (int i=0;i<60-ageClient-1;i++)
		{
			float lxn = tr.findBySurvivantsLx(ageClient+i);
			float lx = tr.findBySurvivantsLx(ageClient);
			double v = Math.pow( 1/ (1+taux) ,i);
			somme1=(float) (somme1+((lxn/lx)*v));
		}
		
		for (int i=60-ageClient-1;i<tr.findAgeMax()-ageClient;i++)
		{
			float lxn = tr.findBySurvivantsLx(ageClient+i);
			float lx = tr.findBySurvivantsLx(ageClient);
			double v = Math.pow( 1/ (1+taux) ,i);
			somme2=(float) (somme2+((lxn/lx)*v));
		}
		double side2= capital*somme2;
		Contract c = new Contract();
		c.setCreation_date(new Date());
		c.setDuration(tr.findAgeMax()-ageClient);
		LocalDate exp = (new Date()).toInstant()
			      .atZone(ZoneId.systemDefault())
			      .toLocalDate().plusYears(tr.findAgeMax()-ageClient);
		c.setExpiration_date(Date.from(exp.atStartOfDay(ZoneId.systemDefault()).toInstant()));
		c.setType(ContractType.Rente);
		c.setRate(taux);
		c.setPrice((float) (side2/somme1)*(60-ageClient));
		User user = UserRepository.findById(userid).get();
		c.setUser(user);
		ContractRepository.save(c);
		return side2/somme1;
	}
	
	@Override
	public float CapitalMixte(double prime,long userid,int n){
		float somme1 = 0,somme2=0;
		double taux = 0;
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(UserRepository.findById(userid).get().getBirthdate());
		int BDay = calendar.get(Calendar.YEAR);
		LocalDate now = LocalDate.now();
		int ageClient = now.getYear()-BDay;
		if((ageClient > 18) &&( ageClient<=35) )
		taux = 0.13;
		else if ((ageClient >35) &&( ageClient<=50) )
			taux = 0.19;
		else if ((ageClient >50) &&( ageClient<60) )
			taux = 0.27;
		else {
		L.info("Erreur: Le client n'est pas eligible a  ce type de contrat");
		return 0;
		}
		for (int i=0;i<60-ageClient-1;i++)
		{
			float lxn = tr.findBySurvivantsLx(ageClient+i);
			float lx = tr.findBySurvivantsLx(ageClient);
			double v = Math.pow( 1/ (1+taux) ,i);
			somme1=(float) (somme1+((lxn/lx)*v));
		}
		float side1=(float) (somme1*prime);
		for(int i=0;i<n;i++)
		{
			float dxk=tr.findByDecesDx(ageClient+i)	;
			float lx=tr.findBySurvivantsLx(ageClient);
			double v = Math.pow( 1/ (1+taux) ,i+(1/2));
			somme2 = (float) (somme2+((dxk*v)/lx));
		}
		Contract c = new Contract();
		c.setCreation_date(new Date());
		c.setDuration(tr.findAgeMax()-ageClient);
		LocalDate exp = (new Date()).toInstant()
			      .atZone(ZoneId.systemDefault())
			      .toLocalDate().plusYears(tr.findAgeMax()-ageClient);
		c.setExpiration_date(Date.from(exp.atStartOfDay(ZoneId.systemDefault()).toInstant()));
		c.setType(ContractType.Mixte);
		c.setRate(taux);
		c.setPrice((float) (side1/(somme2+((Math.pow( 1/ (1+taux) ,n))*tr.findBySurvivantsLx(ageClient+n))/tr.findBySurvivantsLx(ageClient))));
		User user = UserRepository.findById(userid).get();
		c.setUser(user);
		ContractRepository.save(c);
		return (float) (side1/(somme2+((Math.pow( 1/ (1+taux) ,n))*tr.findBySurvivantsLx(ageClient+n))/tr.findBySurvivantsLx(ageClient)));
	}
	
}