package tn.weinsure1;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import tn.weinsure1.entities.Contract;
import tn.weinsure1.entities.ContractType;
import tn.weinsure1.service.IContractService;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ContractsTest {

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
	
	@Test		//Delete Contract from keyboard
	public void TestDeleteContract() {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		Long i = scanner.nextLong();
		ic.DeleteContract(Long.toString(i)); 
		}

	@Test		//Show all contracts
	public void TestRetriveAllContracts() {
		List<Contract> contracts = ic.RetrieveAllContracts() ;
		System.out.println(contracts);
	}
	
	@Test		//Show contract from keyboard
	public void TestRetriveContract() {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		Long i = scanner.nextLong();
		Contract contract = ic.RetrieveContract(Long.toString(i));
		System.out.println(contract);
	}
}
