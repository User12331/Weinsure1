package tn.weinsure1.service;

import java.text.ParseException;
import java.util.List;

import org.springframework.data.repository.query.Param;

import tn.weinsure1.entities.Contract;
import tn.weinsure1.entities.User;
import tn.weinsure1.entities.sinister;
import tn.weinsure1.entities.sinisterstatus;

public interface IsinisterService {

	 List<sinister> retrieveAllSinistres(); 
	 sinister addSinistre(sinister s , Long id);
	 void deleteSinistre(String id);
	 sinister updateSinistre(sinister s);
	 sinister retrieveSinistre(String id);
	 List<sinister> findByStatus(sinisterstatus sins);
	 List<sinister> findByDescription(String name);
	 List<sinister> findByYear(String year) ;
	 List<sinister> findByAny(String any);
	 List<sinister> findSinisterByStatusRejected()  ;
	 void CheckStatus() ; 
	 float CVE(double taux  , User u , Contract c , double AgeMax) ; 
	 void SendMail() ;
	 List<sinister> findbyuserid(Long id);
	 int findcontractdurationBysinister(Long id2 );
	 float CapitalCasDéces(double taux , User u , Contract c) throws ParseException ;
	 float CapitalDécesPeriodique(double taux  , User u , Contract c  ) throws ParseException ;
	 float TDEMPRUNTEUR(double taux  , User u , Contract c  ) throws ParseException ;
	 void affecterUserSinister(Long SinId , Long UserId) ; 
	 void affecterSinisterUser(Long SinId, Long userId);
	 String findSinisterDescriptionwithUR( Long id);
	 double CreditSimulator( double taux , Long idu, Long idc) ;


}
