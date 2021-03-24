package tn.weinsure1.service;

import java.util.List;

import tn.weinsure1.entities.sinister;
import tn.weinsure1.entities.sinisterstatus;

public interface IsinisterService {

	 List<sinister> retrieveAllSinistres(); 
	 sinister addSinistre(sinister s);
	 void deleteSinistre(String id);
	 sinister updateSinistre(sinister s);
	 sinister retrieveSinistre(String id);
	 List<sinister> findByStatus(sinisterstatus sins);
	  List<sinister> findByDescription(String name);
	  List<sinister> findByYear(String year) ;
	  List<sinister> findByAny(String any);
	  List<sinister> findSinisterByStatusRejected()  ;
	  void CheckStatus() ; 
	  float calcul(float capital , int ageClient, int AgeMax, double taux) ; 
	  void SendMail() ;
}
