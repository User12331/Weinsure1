package tn.weinsure1.service;

import java.util.List; 

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.weinsure1.entities.sinister;
import tn.weinsure1.repository.sinisterRepository;




@Service
public class sinisterServiceImpl implements IsinisterService {

	@Autowired     
	sinisterRepository sinistreRepository ;
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
	
	
	

}
