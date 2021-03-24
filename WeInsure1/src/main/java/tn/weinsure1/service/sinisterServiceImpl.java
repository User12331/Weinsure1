package tn.weinsure1.service;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.List; 

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.weinsure1.entities.SendEmailService;
import tn.weinsure1.entities.SinisterMotif;
import tn.weinsure1.entities.sinister;
import tn.weinsure1.entities.sinisterstatus;
import tn.weinsure1.repository.TableMortalitéRepository;
import tn.weinsure1.repository.sinisterRepository;




@Service
public class sinisterServiceImpl implements IsinisterService {
	
	
	@Autowired
	private SendEmailService sendEmailService;
	
	File file = new File("C:\\Users\\Bourguiba\\Desktop\\11.jpg");

	@Autowired     
	sinisterRepository sinistreRepository ;
	TableMortalitéRepository tr ;
	private static final Logger L = LogManager.getLogger(sinisterServiceImpl.class);
	
	@Override
	public sinister addSinistre(sinister s) {
		sinister sinistreSaved = null;
		sinistreSaved = sinistreRepository.save(s);
		
		return sinistreSaved;
	
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
			
			sendEmailService.sendEmail("yahiabourguiba1997@gmail.com", "Sinistre rejeté" , "Cher cleint monsieur M, on vous informe que votre demande de remboursement"
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
		
		currentdate.add(Calendar.DAY_OF_MONTH, -5);
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
	public float calcul(float capital , int ageClient, int AgeMax, double taux){
		int k;
		float prime = 0;
		for (k =0; k < AgeMax - ageClient; k++) {
			float dxk= tr.findByDecesDx(ageClient+k); 	
			L.info("DX " + dxk) ;
			float lx = tr.findBySurvivantsLx(ageClient);
			 double v = Math.pow( 1/ (1+taux) ,  k + (1/2)  );
			 
			prime = (float) (capital * v) *  ( dxk / lx) ;
			
	}

		L.info("PRIME+++++++++ =" + prime) ;
		return prime;
	}
	
	
	
	
		 

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


