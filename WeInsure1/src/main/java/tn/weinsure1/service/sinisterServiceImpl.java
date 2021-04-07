package tn.weinsure1.service;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import tn.weinsure1.entities.Contract;
import tn.weinsure1.entities.SendEmailService;
import tn.weinsure1.entities.SinisterMotif;
import tn.weinsure1.entities.User;
import tn.weinsure1.entities.sinister;
import tn.weinsure1.entities.sinisterstatus;
import tn.weinsure1.repository.ContractRepository;
import tn.weinsure1.repository.TableMortalitéRepository;
import tn.weinsure1.repository.UserRepository;
import tn.weinsure1.repository.sinisterRepository;

@Service
public class sinisterServiceImpl implements IsinisterService {
	
	
	@Autowired
	private SendEmailService sendEmailService;
	
	File file = new File("C:\\Users\\Bourguiba\\Desktop\\11.jpg");

	@Autowired     
	sinisterRepository sinistreRepository ;
	@Autowired
	TableMortalitéRepository tr ;
	@Autowired
	UserRepository ur ;
	@Autowired
	ContractRepository cr ;
	private static final Logger L = LogManager.getLogger(sinisterServiceImpl.class);
	
	@Override
	public sinister addSinistre(sinister s , Long id ) {
		User u=ur.findById(id).get();
		s.setUser(u);
		sinistreRepository.save(s);
		
		return s;
	
	}
	@Override
	public void deleteSinistre(String id) {
		sinistreRepository.deleteById(Long.parseLong(id));
		
	}
	@Override
	public sinister updateSinistre(sinister s) {
		sinister sinistreAdded = sinistreRepository.save(s);
		return sinistreAdded;
	}
	
	@Override
	public sinister retrieveSinistre(String id) {
		L.info("in retrieveSinistre id = " + id);
		sinister s = sinistreRepository.findById(Long.parseLong(id)).get();
		L.info("sinistre returned = : " + s);
		return s;
			}
	
	
	@Override
	public List<sinister> retrieveAllSinistres() {
		List<sinister> sinistres = (List<sinister>) sinistreRepository.findAll(); 
		for(sinister sin : sinistres)
		{
			L.info("sinistre +++ :" + sin);
		}
					
		return sinistres;
	}	
	
	@Override 
	public List<sinister> findByStatus(sinisterstatus sins) {
		return sinistreRepository.findSinisterByStatus(sins);
		
	}
	
	@Override
	public List<sinister> findByDescription(String name) {

		List<sinister> sins = sinistreRepository.findByDescription(name);
		L.info("sinister +++ :" + sins) ;
		return sins;
	} 

	@Override
	public List<sinister> findByYear(String year) {
		List<sinister> sins = sinistreRepository.findByYear(year);
		L.info("sinistre +++ :" + sins) ;
		return sins;
	}
	@Override
	public List<sinister> findByAny(String any) {
		List<sinister> sins = sinistreRepository.findByAny(any);
		L.info("sinistre +++ :" + sins) ;
		return sins;
	}
	
	
	@Override
	public List<sinister> findSinisterByStatusRejected( ) {
		List<sinister> sins = sinistreRepository.findSinisterByStatusRejected();
		L.info("sinistre +++ :" + sins) ;
		return sins;
	}
	
	@Override
	public void SendMail() {
		try {
		List<sinister> sinstreRej = sinistreRepository.findSinisterByStatusRejected();

		//SimpleMailMessage mail = new SimpleMailMessage();
		for(int i=0;i< sinstreRej.size();i++)
		{   // String email = sinstreRej.get(i).getClient().getEmail();
			//nom=  String email = sinstreRej.get(i).getClient().getNom();
			String motif =sinstreRej.get(i).getMotifStatus().toString(); 
			String date =sinstreRej.get(i).getDateOccurence().toString(); 
			
			sendEmailService.sendEmail("bourguibaahmed06@gmail.com", "Sinistre rejeté" , "Cher cleint monsieur M, on vous informe que votre demande de remboursement"
					+ "de sinistre, envoyée à la date "+date+ " , a été rejetée car cette demande " + motif + ". Merci pour votre compréhension. ", file);	
		} 
		} 
		catch (Exception e)
		{System.out.println(e.getMessage());}
		}
	
	@Override
	public void CheckStatus() {
		List<sinister> sinsenattente = sinistreRepository.findSinisterByStatusRejected();
		Calendar currentdate = Calendar.getInstance(); 
		Date d = currentdate.getTime();  
		
		currentdate.add(Calendar.DAY_OF_MONTH,-5);
		Date d1= currentdate.getTime();
	
	
		sinisterstatus status=sinisterstatus.rejected;
		sinisterstatus status2=sinisterstatus.encours;
		

		for(int i=0;i<sinsenattente.size();i++)
		{ // L.info("date OCC:" + sinsenattente.get(i).getDateOccurence()) ;
		
		
			if (sinsenattente.get(i).getDateOccurence().compareTo(d1) < 0)
			{
				 L.info("BOUCLE 1:");
				 SinisterMotif motif=SinisterMotif.depasse5jours;
				 sinsenattente.get(i).setStatus(status);
				 sinsenattente.get(i).setMotifStatus(motif);
				 sinistreRepository.save(sinsenattente.get(i));
				 L.info("new sin +++ :" + sinsenattente.get(i)) ;

			}
			else if (sinsenattente.get(i).getDocuments() == null)
			{
				L.info("boucle 2") ;
				SinisterMotif motif=SinisterMotif.Pasdedocuments;
				sinsenattente.get(i).setStatus(status);
				sinsenattente.get(i).setMotifStatus(motif);
				 sinistreRepository.save(sinsenattente.get(i));
				 L.info("new sin 2 +++ :" + sinsenattente.get(i));
			} 
			else {
				sinsenattente.get(i).setStatus(status2);
				sinistreRepository.save(sinsenattente.get(i));
			}

		} 

	}	
	
	
	@Override
	public float CVE(double taux  , User u , Contract c , double AgeMax){
		int k;
		float prime = 0 ; 
		float cd = 0  ;
		LocalDate bdate = u.getBirthdate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate localDate = LocalDate.now();
		int years = localDate.getYear()-bdate.getYear();
		for (k =0; k < AgeMax - years; k++) {
			float dxk= tr.findByDecesDx(years+k); 	
			L.info("DX " + dxk) ;
			float lx = tr.findBySurvivantsLx(years);
			 double v = Math.pow( 1/ (1+taux) ,  k + (1/2)  );			 
			prime = (float) (v) *  ( dxk / lx) ;	
	}
		cd= c.getPrice() / prime ; 
		L.info("PRIME+++++++++ =" + cd) ;
		return cd;
	}
	
	public List<sinister> findbyuserid(Long id) {
		List<sinister> sins = sinistreRepository.findbyuserid(id);
		L.info("sinister +++ :" + sins) ;
		return sins;
	}
	public int findcontractdurationBysinister(Long id2) {
		int k = sinistreRepository.findcontractdurationBysinister(id2);
		L.info("sinister +++ :" + k) ;
		return k;
	}
	
	@Override
	public float CapitalCasDéces(double taux  , User u , Contract c  ) throws ParseException {
		int k;
		float prime = 0;
		LocalDate bdate = u.getBirthdate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate localDate = LocalDate.now();
		int years = localDate.getYear()-bdate.getYear();
		float cd = 0; 
		int s = findcontractdurationBysinister(u.getIdUser()) ; 
		for (k =0; k < s-1; k++) {
			float dxk= tr.findByDecesDx(years+k); 	
			L.info("DX " + dxk) ;
			float lx = tr.findBySurvivantsLx(years);
			 double v = Math.pow( 1/ (1+taux) ,  k + (1/2)  );			 
			prime = (float) (v) *  ( dxk / lx) ;	
								 }
		 cd = c.getPrice() / prime ; 
		L.info("PRIME+++++++++ =" + cd) ;
		return cd;
	}
	
	
	
	
	@Override
	public float CapitalDécesPeriodique(double taux  , User u , Contract c  ) throws ParseException {
		int k1,k2;
		float prime1 = 0 , prime = 0;
		float cd = 0 ;
		LocalDate bdate = u.getBirthdate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate localDate = LocalDate.now();
		int years = localDate.getYear()-bdate.getYear();
		int s = findcontractdurationBysinister(u.getIdUser()) ; 
		for (k1 =0; k1 < s-1; k1++) {
			float lxk= tr.findBySurvivantsLx(years+k1); 	
			L.info("DX " + lxk) ;
			float lx = tr.findBySurvivantsLx(years);
			 double v = Math.pow( 1/ (1+taux) ,  k1   );	
			 double p = c.getPrice() * (( ( Math.pow((1 + taux) , (years + 1)) - 1 ) / years ) ) ; 
				L.info("DX " + p) ;
			prime1 = (float) (p * v) *  ( lxk / lx) ;	
	}

		for (k2 =0; k2 < s-1; k2++) {
			float lxk= tr.findByDecesDx(years+k2); 	
			L.info("DX " + lxk) ;
			float lx = tr.findBySurvivantsLx(years);
			 double v = Math.pow( 1/ (1+taux) ,  k2 +(1/2)   );	
			// double p = c.getPrice() * (( ( Math.pow((1 + taux) , (years + 1)) - 1 ) / years ) - 1) ; 
			prime = (float) (v) *  ( lxk / lx) ;	
	}
		L.info("PRIME+++++++++ =" + prime) ;
		L.info("PRIME1+++++++++ =" + prime1) ;
		cd = prime1 / prime ;
		
		L.info("PRIME+++++++++ =" + cd) ;
		return cd;
	}
	
	
	public float TDEMPRUNTEUR(double taux  , User u , Contract c  ) {
	
		int k;
		float prime = 0;
		LocalDate bdate = u.getBirthdate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate localDate = LocalDate.now();
		int years = localDate.getYear()-bdate.getYear();
		float crd = 0 , crd1 = 0 , tde = 0; 
		int s = findcontractdurationBysinister(u.getIdUser()) ; 
		for (k =0; k < ((s-1)*12) ; k++) {
			 double v = Math.pow((1+(taux/12)) ,  s*12 );
			 double l = Math.pow( 1/ (1+(taux/12)) ,  s*12 - 1 );
			prime = ((float) (v) * c.getPrice()) - (float)(v) ;	
			crd = (float) (prime / l) ; 
			crd1 = (float) (crd / Math.pow( (1+taux) , k )) ; 
			L.info("crd1+++++++++ =" + crd1) ;
			float lxk= tr.findProbaByAgeClient(years); 
			L.info("lxk+++++++++ =" + lxk) ;
			tde = crd1*lxk ; 
									      }
		L.info("PRIME+++++++++ =" + tde) ;
		return tde;
		
	}
	public void affecterUserSinister(Long SinId , Long UserId){
		User u  = ur.findById(UserId).get();
	    sinister s =  sinistreRepository.findById(SinId).get();
	    s.setUser(u);
	    sinistreRepository.save(s);	
	}
	@Transactional	
	public void affecterSinisterUser(Long SinId, Long userId) {
		User u = ur.findById(userId).get();
		sinister s = sinistreRepository.findById(SinId).get();

		if(s.getUser() == null){

			List<User> urs = new ArrayList<>();
			urs.add(u);
			s.setUser(u);
		}else{

			s.setUser(u);

		}

	}
	public String findSinisterDescriptionwithUR( Long id)
	{ 
		String k = sinistreRepository.findSinisterDescriptionwithUR(id);
		L.info("description +++ :" + k) ;
		return k;
		
	}
	public double CreditSimulator( double taux , Long idu, Long idc) {
		
		
		User u= ur.findById(idu).get();
		Contract c=cr.findById(idc).get();
		//Long id=(Long)session.getAttribute("name");	 
		int dur=c.getDuration();
		double Montant= u.getSalary()*0.8;
		double a = Math.pow((1+taux),-dur);
		double mfy=Montant*(taux/(1-a));
		return mfy ; 
	
	}
	
	
	
	
	
	
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


