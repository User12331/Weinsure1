package tn.weinsure1;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import tn.weinsure1.entities.User;
import tn.weinsure1.entities.User.UserRole;
import tn.weinsure1.service.IUserService;
/*
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {

	@Autowired 
	IUserService iu;
	
	
	
	@Test		//Add Contract
	
	
	public void TestAddUser() throws ParseException    {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat ("yyyy-MM-dd");
		
		Date d = dateFormat.parse("1998-05-08");
		User u = new User ("chehaibi","amira",d,"hhhh","ggg",88888,15555,152, User.UserRole.Administrator);
		
		User UserAdded = iu.AddUser(u);
		Assert.assertEquals(u.getIdUser(), UserAdded.getIdUser());
	} 
}
*/