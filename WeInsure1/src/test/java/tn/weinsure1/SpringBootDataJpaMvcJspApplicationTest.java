package tn.weinsure1;


import java.text.ParseException; 
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.junit.Test; 
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import tn.weinsure1.entities.Contract;
import tn.weinsure1.entities.ContractType;
import tn.weinsure1.entities.sinister;
import tn.weinsure1.entities.sinisterstatus;
import tn.weinsure1.entities.typeSinister;
import tn.weinsure1.service.IContractService;
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
	
	@Autowired 
	IContractService ic;
	
	@Test		//Add Contract
	public void TestAddContract() throws ParseException    {
		SimpleDateFormat dateFormat = new SimpleDateFormat ("yyyy-MM-dd");
		Date d = dateFormat.parse("2020-01-01");
		Contract c = new Contract (d,d,5,250,ContractType.Personne,null);
		Contract ContractAdded = ic.AddContract(c);
		Assert.assertEquals(c.getIdcontract(), ContractAdded.getIdcontract());
	} 
	
	@Test		//Update Contract
	public void TestUpdateContract() throws ParseException    {
		SimpleDateFormat dateFormat = new SimpleDateFormat ("yyyy-MM-dd");
		Date d = dateFormat.parse("2018-02-02");
		Contract c = new Contract (d,d,25,235,ContractType.Domamges,null);
		Contract ContractUpdated = ic.UpdateContract(c);
		Assert.assertEquals(c.getType(), ContractUpdated.getType());
	}
	
	@Test		//Delete Contract
	public void TestDeleteContract() {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		Long i = scanner.nextLong();
		ic.DeleteContract(Long.toString(i)); //Delete from keyboard
	}
	
	
}
    
    