package tn.weinsure1;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
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


@RunWith(SpringRunner.class)
@SpringBootTest
public class ContractsTest {

	@Autowired 
	IContractService ic;
	/*
	@Test		//Add Contract
	public void TestAddContract() throws ParseException    {
		SimpleDateFormat dateFormat = new SimpleDateFormat ("yyyy-MM-dd");
		Date d = dateFormat.parse("2020-01-01");
		Date d2 = new Date(); 
		Contract c = new Contract (d2,d,5,230,ContractType.Personne,null);
		Contract ContractAdded = ic.AddContract(c);
		Assert.assertEquals(c.getIdcontract(), ContractAdded.getIdcontract());
	} 
	
	@Test		//Update Contract
	public void TestUpdateContract() throws ParseException    {
		SimpleDateFormat dateFormat = new SimpleDateFormat ("yyyy-MM-dd");
		Date d = new Date();
		Contract cnt = new Contract (2L, d,d,3,125,ContractType.Dommages, null );
		Contract ContractUpdated = ic.UpdateContract(cnt);
		Assert.assertEquals(cnt.getIdcontract(), ContractUpdated.getIdcontract());
	}
	
	@Test		//Delete Contract 
	public void TestDeleteContract() {
				ic.DeleteContract("7"); 
		}

	@Test		//Show all contracts
	public void TestRetriveAllContracts() {
		List<Contract> contracts = ic.RetrieveAllContracts() ;
		System.out.println(contracts);
	}
	
	@Test		//Show contract 
	public void TestRetriveContract() {
		Contract contract = ic.RetrieveContract("1");
		System.out.println(contract);
	}
	*/
	@Test
	public void testfindByYear() {

		List<Contract> cnt = ic.findByDurationGreater(2) ;
		System.out.println(cnt);
	} 
	
}
