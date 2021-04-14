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
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import tn.weinsure1.annotation.CurrentUser;
import tn.weinsure1.entities.Contract;
import tn.weinsure1.entities.ContraintType;
import tn.weinsure1.entities.SendEmailService;
import tn.weinsure1.entities.SinisterMotif;
import tn.weinsure1.entities.User;
import tn.weinsure1.entities.sinister;
import tn.weinsure1.entities.sinisterstatus;
import tn.weinsure1.entities.typeSinister;
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
		s.setStatus(sinisterstatus.enAttente);
		s.setDateOccurence(new Date());
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
	public List<sinister> findSinisterByStatusEnAttente( ) {
		List<sinister> sins = sinistreRepository.findSinisterByStatusEnAttente();
		L.info("sinistre +++ :" + sins) ;
		return sins;
	}
	
	@Override
	public void SendMail() {
		try {
		List<sinister> sinstreRej = sinistreRepository.findSinisterByStatusRejected();
		//SimpleMailMessage mail = new SimpleMailMessage();
		for(int i=0;i< sinstreRej.size();i++)
		{    String email = sinstreRej.get(i).getUser().getEmail();
			String nom = sinstreRej.get(i).getUser().getFirstName();
			String motif =sinstreRej.get(i).getMotifStatus().toString();
			String date =sinstreRej.get(i).getDateOccurence().toString();			
			sendEmailService.sendEmail(email, "Sinistre rejeté" , "Cher cleint monsieur " + nom +", on vous informe que votre demande de remboursement"
					+ "de sinistre, envoyée à la date "+date+ " , a été rejetée car cette demande " + motif + ". Merci pour votre compréhension. ", file);	
		} 
		} 
		catch (Exception e)
		{System.out.println(e.getMessage());}
		}
	
	@Override
	public void CheckStatus() {
		List<sinister> sinsenattente = sinistreRepository.findSinisterByStatusEnAttente();
		Calendar currentdate = Calendar.getInstance(); 
		Date d = currentdate.getTime();  
		
		currentdate.add(Calendar.DAY_OF_MONTH,-5);
		Date d1= currentdate.getTime();
	
	
		sinisterstatus status=sinisterstatus.rejected;
		sinisterstatus status2=sinisterstatus.encours;
		sinisterstatus status3=sinisterstatus.WaitingForReclamation;

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
				 SendMail();

			}
		/*	else if (sinsenattente.get(i).getDocuments() == null)
			{
				L.info("boucle 2") ;
				SinisterMotif motif=SinisterMotif.Pasdedocuments;
				sinsenattente.get(i).setStatus(status);
				sinsenattente.get(i).setMotifStatus(motif);
				 sinistreRepository.save(sinsenattente.get(i));
				 L.info("new sin 2 +++ :" + sinsenattente.get(i));
				 SendMail();
			}
			*/
			else if (sinsenattente.get(i).getUser().getContraint() != null &&  sinsenattente.get(i).getUser().getContraint().getType().equals(ContraintType.SinisterReclamation))
			{
				L.info("boucle 3") ;
				SinisterMotif motif=SinisterMotif.ReclamationSinistreEnCoursDeTraitement;
				sinsenattente.get(i).setStatus(status3);
				sinsenattente.get(i).setMotifStatus(motif);
				 sinistreRepository.save(sinsenattente.get(i));
				 L.info("new sin 2 +++ :" + sinsenattente.get(i));
				 SendMail();
			} 
			else {
				sinsenattente.get(i).setStatus(status2);
				sinistreRepository.save(sinsenattente.get(i));
			}

		} 

	}	
	
	
	@Override
	public float CVE( Long idS , Long idC  ){
		int k;
		float prime = 0 ; 
		float cd = 0  ;
		//CurrentUser.getId();
		sinister ss = sinistreRepository.findById(idS).get();
		User u =ss.getUser();
		Contract c = cr.findById(idC).get();
		int AgeMax = tr.findAgeMax();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(u.getBirthdate());
		int BDay = calendar.get(Calendar.YEAR);
		LocalDate now = LocalDate.now();
		int years = now.getYear()-BDay;
		double taux = c.getRate();
		if ( ss.getStatus().equals(sinisterstatus.rejected) )
		{
			L.info("Vous devez regler votre situation avec la plus proche agence" ) ;
			return 0 ;
		}
		else if ( ss.getStatus().equals(sinisterstatus.enAttente))
		{
			L.info("Veuillez patienter , Un de nos agent est entrain de regler votre situation" ) ;
			return 0 ;
		}
		else if (ss.getTypeSinistre().compareTo(typeSinister.VieEntiere) != 0)
		{
			L.info("Veuillez verifier votre Type de Sinistre" ) ;
			return 0 ;
		}
		for (k =0; k < AgeMax - years; k++) {
			float dxk= tr.findByDecesDx(years+k); 	
			L.info("DX " + dxk) ;
			float lx = tr.findBySurvivantsLx(years);
			//double v = Math.pow( (1/(1+taux)) ,  (k + (1/2))  );			 
			prime += (float) (Math.pow( (1/(1+taux)) ,  (k + (1/2))  )) * ( dxk / lx) ;	
			L.info("PRIMEeee+++++++++ =" + prime) ;
	}
		L.info("PRIME11eee+++++++++ =" + prime) ;
		cd= c.getPrice() / prime ; 
		L.info("PRIME+++++++++ =" + cd) ;
		L.info("prix cotnract+++++++++ =" + c.getPrice()) ;
		return cd;
	}
	
	public List<sinister> findbyuserid(Long id) {
		List<sinister> sins = sinistreRepository.findbyuserid(id);
		L.info("sinister +++ :" + sins) ;
		return sins;
	}
	public int findcontractdurationBysinister(Long id) {
		int k = sinistreRepository.findcontractdurationBysinister(id);
		L.info("sinister +++ :" + k) ;
		return k;
	}
	
	@Override
	public float CapitalCasDéces(Long idS , Long idC  ) throws ParseException {
		int k;
		float prime = 0 , dxk = 0 , lx = 0 ; ;
		sinister ss = sinistreRepository.findById(idS).get();
		User u =ss.getUser();
		Contract c = cr.findById(idC).get();
		double taux = c.getRate();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(u.getBirthdate());
		int BDay = calendar.get(Calendar.YEAR);
		LocalDate now = LocalDate.now();
		int years = now.getYear()-BDay;
		L.info("PRIME+++++++++ =" +years) ;	
		float cd = 0; 
		int s = findcontractdurationBysinister(u.getId()) ; 
		if ( ss.getStatus().equals(sinisterstatus.rejected) )
		{
			L.info("Vous devez regler votre situation avec la plus proche agence" ) ;
			return 0 ;
		}
		else if ( ss.getStatus().equals(sinisterstatus.enAttente))
		{
			L.info("Veuillez patienter , Un de nos agent est entrain de regler votre situation" ) ;
			return 0 ;
		}
		else if (ss.getTypeSinistre().compareTo(typeSinister.casDeces) != 0)
		{
			L.info("Veuillez verifier votre Type de Sinistre" ) ;
			return 0 ;
		}
		for (k =0; k < s-1; k++) {
			L.info("DX " + dxk) ;
			 lx = tr.findBySurvivantsLx(years);
			prime += Math.pow( 1/ (1+taux) ,  k + (1/2)  ) * ( tr.findByDecesDx(years+k) / lx) ;
			L.info("PRIME+++++++++ =" +prime) ;				 
			}
		 cd = c.getPrice() / prime ; 
		L.info("PRIME+++++++++ =" + cd) ;
		return cd;
	}
	
	
	
	
	@Override
	public float CapitalDécesPeriodique(Long idS , Long idC   ) throws ParseException {
		int k1,k2;
		float prime1 = 0 , prime = 0;
		float cd = 0 ;
		sinister ss = sinistreRepository.findById(idS).get();
		User u =ss.getUser();
		Contract c = cr.findById(idC).get();
		double taux = c.getRate();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(u.getBirthdate());
		int BDay = calendar.get(Calendar.YEAR);
		LocalDate now = LocalDate.now();
		int years = now.getYear()-BDay;
		int s = findcontractdurationBysinister(u.getId()) ; 
		if ( ss.getStatus().equals(sinisterstatus.rejected) )
		{
			L.info("Vous devez regler votre situation avec la plus proche agence" ) ;
			return 0 ;
		}
		else if ( ss.getStatus().equals(sinisterstatus.enAttente))
		{
			L.info("Veuillez patienter , Un de nos agent est entrain de regler votre situation" ) ;
			return 0 ;
		}
		else if (ss.getTypeSinistre().compareTo(typeSinister.casDecesperiodique) != 0)
		{
			L.info("Veuillez verifier votre Type de Sinistre" ) ;
			return 0 ;
		}
		for (k1 =0; k1 < (s-1)*12; k1++) {
			float lxk= tr.findBySurvivantsLx((years+k1)/12); 	
			L.info("DX " + lxk) ;
			float lx = tr.findBySurvivantsLx(years);
			  //p = c.getPrice() * (( ( Math.pow((1 + taux) , (years + 1)) - 1 ) / years ) ) ; 
				L.info("DX " + lx) ;
			prime1  += (float) (c.getPrice() * (( ( Math.pow((1 + taux) , (years + 1)) - 1 ) / years ) ) * Math.pow( 1/ (1+taux) ,  k1/12   )) *  ( lxk / lx) ;	
	}

		for (k2 =0; k2 < s-1; k2++) {
			float lxk= tr.findByDecesDx(years+k2); 	
			L.info("DX " + lxk) ;
			float lx = tr.findBySurvivantsLx(years);
			 //double v = Math.pow( 1/ (1+taux) ,  k2 +(1/2)  );	
			// double p = c.getPrice() * (( ( Math.pow((1 + taux) , (years + 1)) - 1 ) / years ) - 1) ; 
			prime += (float) (Math.pow( 1/ (1+taux) ,  k2 +(1/2)  )) *  ( lxk / lx) ;	
	}
		cd = prime1 / prime ;
		L.info("DX " + ss.getUser().getId()) ;
		ss.setReglemntation(cd);
		ss.setStatus(sinisterstatus.valide);
		sinistreRepository.save(ss);
		L.info("reg " + ss.getReglemntation()) ;

		return cd;
	}
	
	
	public float TDEMPRUNTEUR(Long idS , Long idC  ) {
		int k;
		float prime = 0;
		sinister ss = sinistreRepository.findById(idS).get();
		User u =ss.getUser();
		Contract c = cr.findById(idC).get();
		double taux = c.getRate();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(u.getBirthdate());
		int BDay = calendar.get(Calendar.YEAR);
		LocalDate now = LocalDate.now();
		int years = now.getYear()-BDay;
		float crd = 0 , crd1 = 0 , tde = 0; 
		int s = findcontractdurationBysinister(u.getId()) ; 
		if ( ss.getStatus().equals(sinisterstatus.rejected) )
		{
			L.info("Vous devez regler votre situation avec la plus proche agence" ) ;
			return 0 ;
		}
		else if ( ss.getStatus().equals(sinisterstatus.enAttente))
		{
			L.info("Veuillez patienter , Un de nos agent est entrain de regler votre situation" ) ;
			return 0 ;
		}
		else if (ss.getTypeSinistre().compareTo(typeSinister.TemporairedecesEmprunteur) != 0)
		{
			L.info("Veuillez verifier votre Type de Sinistre" ) ;
			return 0 ;
		}
		for (k =0; k < ((s-1)*12) ; k++) {
			 //double v = Math.pow((1+(taux/12)) ,  s*12 );
			//double l = Math.pow( 1/ (1+(taux/12)) ,  s*12 - 1 );
			//prime = ((float) (Math.pow((1+(taux/12)) ,  s*12 )) * c.getPrice()) - (float)(Math.pow((1+(taux/12)) ,  s*12 )) ;	
			//crd = (float) (((float) (Math.pow((1+(taux/12)) ,  s*12 )) * c.getPrice()) - (float)(Math.pow((1+(taux/12)) ,  s*12 )) / Math.pow( 1/ (1+(taux/12)) ,  s*12 - 1 )) ; 
			//crd1 = (float) ((float) (((float) (Math.pow((1+(taux/12)) ,  s*12 )) * c.getPrice()) - (float)(Math.pow((1+(taux/12)) ,  s*12 )) / Math.pow( 1/ (1+(taux/12)) ,  s*12 - 1 )) / Math.pow( (1+taux) , k )) ; 
			L.info("crd1+++++++++ =" + crd1) ;
			//float lxk= tr.findProbaByAgeClient(years+k); 
			//L.info("lxk+++++++++ =" + lxk) ;
			tde += (((float) ((float) (((float) (Math.pow((1+(taux/12)) ,  s*12 )) * c.getPrice()) - (float)(Math.pow((1+(taux/12)) ,  s*12 )) / Math.pow( 1/ (1+(taux/12)) ,  s*12 - 1 )) / Math.pow( (1+taux) , k )))* tr.findProbaByAgeClient(years+k)) ; 
									      }
		tde = tde /12 ; 
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
	public List<sinister> findSinisterDescriptionwithUR( Long id)
	{ 
		List<sinister> sins = sinistreRepository.findSinisterDescriptionwithUR(id);
		L.info("sinistre +++ :" + sins) ;
		return sins;
	}
	public double CreditSimulator( Long idu, Long idc) {
		
		User u= ur.findById(idu).get();
		Contract c=cr.findById(idc).get();
		double taux = c.getRate();
		//Long id=(Long)session.getAttribute("name");	 
		int dur=c.getDuration();
		double Montant= u.getSalary()* 0.8;
		double a = Math.pow((1+taux),-dur);
		double mfy=Montant*(taux/(1-a));
		return mfy ; 
	
	}
	public void UpdateSinDescription( Long idS , String description) {
		sinister s = sinistreRepository.findById(idS).get();
		s.setDescription(description);
		sinistreRepository.save(s);

	}
public String yallacurrent(){
	Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

	if (principal instanceof UserDetails) {
	  String username = ((UserDetails)principal).getUsername();
	} else {
	  String username = principal.toString();
	  return username ; 
	}
	
	return ((UserDetails)principal).getUsername() ; 
}

	
	
	
	
	
	
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


