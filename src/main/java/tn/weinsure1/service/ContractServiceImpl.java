package tn.weinsure1.service;



import java.time.LocalDate;
import java.util.List;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.weinsure1.entities.Contract;
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
	public float CapitalVieUnique (float C,long userid, int duree){
		float prime =0;
		double taux = 0;
		Date date = UserRepository.findById(userid).get().getBirthdate();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		int BDay = calendar.get(Calendar.YEAR);
		L.info(BDay);
		LocalDate now = LocalDate.now();
		int ageClient = now.getYear()-BDay;
		L.info("AGe:"+ageClient);
		if((ageClient > 18) &&( ageClient<=35) )
			{taux = 13;
			L.info("Taux ="+taux);
			}
		else if ((ageClient >35) &&( ageClient<=50) )
			{taux = 19;
			L.info("Taux ="+taux);
			}
		else if ((ageClient >50) &&( ageClient<60) )
			{taux = 27;
			L.info("Taux ="+taux);
			}
		else {L.info("Erreur: Le client n'est pas Ã©ligible Ã  ce type de contrat");
		return 0;
		}
		float lxn = tr.findBySurvivantsLx(ageClient+duree);
		L.info("lxn=" + lxn);
		float lx = tr.findBySurvivantsLx(ageClient);
		double v = Math.pow( 1/ (1+(taux/100)) ,duree);
		prime = (float) (C*(lxn)/lx * v);
		L.info("PRIME =" + prime) ;
		return prime;
	}
	@Override
	public float PrimeVieUnique(float prime,long userid, int duree){
		float C =0;
		double taux = 0;
		Date date = UserRepository.findById(userid).get().getBirthdate();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		int BDay = calendar.get(Calendar.YEAR);
		L.info(BDay);
		LocalDate now = LocalDate.now();
		int ageClient = now.getYear()-BDay;
		L.info("AGe:"+ageClient);
		if((ageClient > 18) &&( ageClient<=35) )
			{taux = 13;
			L.info("Taux ="+taux);
			}
		else if ((ageClient >35) &&( ageClient<=50) )
			{taux = 19;
			L.info("Taux ="+taux);
			}
		else if ((ageClient >50) &&( ageClient<60) )
			{taux = 27;
			L.info("Taux ="+taux);
			}
		else {L.info("Erreur: Le client n'est pas Ã©ligible Ã  ce type de contrat");
		return 0;
		}
		float lxn = tr.findBySurvivantsLx(ageClient+duree);
		L.info("lxn=" + lxn);
		float lx = tr.findBySurvivantsLx(ageClient);
		double v = Math.pow( 1/ (1+(taux/100)) ,duree);
		C = (float) (prime/((lxn)/lx * v));
		L.info("Capital =" + C) ;
		return C;
	}
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
	public double RITP(double prime,long userid){
		float somme1 = 0,somme2=0;
		double taux = 0;
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(UserRepository.findById(userid).get().getBirthdate());
		int BDay = calendar.get(Calendar.YEAR);
		LocalDate now = LocalDate.now();
		int ageClient = now.getYear()-BDay;
		if((ageClient > 18) &&( ageClient<=35) )
		{taux = 13;
		L.info("Taux ="+taux);
		}
	else if ((ageClient >35) &&( ageClient<=50) )
		{taux = 19;
		L.info("Taux ="+taux);
		}
	else if ((ageClient >50) &&( ageClient<60) )
		{taux = 27;
		L.info("Taux ="+taux);
		}
	else {
		L.info("Client non Ã©ligible");
		return 0;
	}
		for (int i=0;i<60-ageClient-1;i++)
		{
			float lxn = tr.findBySurvivantsLx(ageClient+i);
			float lx = tr.findBySurvivantsLx(ageClient);
			double v = Math.pow( 1/ (1+(taux/100)) ,i);
			somme1=(float) (somme1+((lxn/lx)*v));
		}
		double side1= prime*somme1;
		for (int i=60-ageClient-1;i<105-ageClient;i++)
		{
			float lxn = tr.findBySurvivantsLx(ageClient+i);
			float lx = tr.findBySurvivantsLx(ageClient);
			double v = Math.pow( 1/ (1+(taux/100)) ,i);
			somme2=(float) (somme2+((lxn/lx)*v));
		}
		return side1/somme2;
	}
	public double RITC(double capital,long userid){
		float somme1 = 0,somme2=0;
		double taux = 0;
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(UserRepository.findById(userid).get().getBirthdate());
		int BDay = calendar.get(Calendar.YEAR);
		LocalDate now = LocalDate.now();
		int ageClient = now.getYear()-BDay;
		if((ageClient > 18) &&( ageClient<=35) )
		{taux = 13;
		L.info("Taux ="+taux);
		}
	else if ((ageClient >35) &&( ageClient<=50) )
		{taux = 19;
		L.info("Taux ="+taux);
		}
	else if ((ageClient >50) &&( ageClient<60) )
		{taux = 27;
		L.info("Taux ="+taux);
		}
	else {
		L.info("Client non Ã©ligible");
		return 0;
	}
		for (int i=0;i<60-ageClient-1;i++)
		{
			float lxn = tr.findBySurvivantsLx(ageClient+i);
			float lx = tr.findBySurvivantsLx(ageClient);
			double v = Math.pow( 1/ (1+(taux/100)) ,i);
			somme1=(float) (somme1+((lxn/lx)*v));
		}
		
		for (int i=60-ageClient-1;i<tr.findAgeMax()-ageClient;i++)
		{
			float lxn = tr.findBySurvivantsLx(ageClient+i);
			float lx = tr.findBySurvivantsLx(ageClient);
			double v = Math.pow( 1/ (1+(taux/100)) ,i);
			somme2=(float) (somme2+((lxn/lx)*v));
		}
		double side2= capital*somme2;
		return side2/somme1;
	}
	public void MAJContractPrice(float price,long cntid){
		Contract cnt = ContractRepository.findById(cntid).get();
		cnt.setPrice(price);
		ContractRepository.save(cnt);
	}

}