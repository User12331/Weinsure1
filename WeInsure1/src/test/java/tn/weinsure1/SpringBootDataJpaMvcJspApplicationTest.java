package tn.weinsure1;


import java.text.ParseException; 
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Test; 
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import tn.weinsure1.entities.sinister;
import tn.weinsure1.entities.sinisterstatus;
import tn.weinsure1.entities.typeSinister;
import tn.weinsure1.service.IsinisterService;

import org.junit.Assert;


@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootDataJpaMvcJspApplicationTest {
	@Autowired 
	IsinisterService is;

	@Test
	public void testRetrieveAllUsers() {
		List<sinister> sinistres = is.retrieveAllSinistres() ;
		//Assert.assertEquals(1, sinistres.size()); }
	}
	@Test
	public void testAddSinistre() throws ParseException    {
		SimpleDateFormat dateFormat = new SimpleDateFormat ("yyyy-MM-dd");
		Date d = dateFormat.parse("2018-02-02");
		sinister s = new sinister (typeSinister.vie, "My first sinistre",d,sinisterstatus.encours , null );
		sinister sinistreAdded = is.addSinistre(s);
		Assert.assertEquals(s.getIdSinistre(), sinistreAdded.getIdSinistre());

	} 


	@Test
	public void testUpdateSinistre() throws ParseException    {
		SimpleDateFormat dateFormat = new SimpleDateFormat ("yyyy-MM-dd");
		Date d = dateFormat.parse("2018-02-02");
		sinister s = new sinister (2L, typeSinister.vie, "My first sinistre MODIFIED AGAIN",d, sinisterstatus.encours, null );
		sinister sinistreUpdated = is.updateSinistre(s);
		Assert.assertEquals(s.getDescription(), sinistreUpdated.getDescription());

	} 


	@Test
	public void testRetrieveSinistre() {
		sinister sinistre = is.retrieveSinistre("5"); 
		//Assert.assertEquals(5L,sinistre.getIdSinistre().longValue());
	} 

	@Test
	public void testDeleteSinistre() {

		is.deleteSinistre("1");
	}
	
	
}
    
    