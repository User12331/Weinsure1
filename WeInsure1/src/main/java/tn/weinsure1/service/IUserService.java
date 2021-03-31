package tn.weinsure1.service;

import java.util.List;

import tn.weinsure1.entities.Role;
import tn.weinsure1.entities.User;
import tn.weinsure1.entities.sinister;
import tn.weinsure1.entities.sinisterstatus;

public interface IUserService {

	List<User> RetrieveAllUsers(); 
	
	 User AddUser(User u);
	 void DeleteUser(String id);
	 User UpdateUser(User u);
	 User RetrieveUser(String id);
	/* List<User> findByRole(Role ro);
	  List<User> findByDescription(String name);
	  List<User> findByYear(String year) ;
	  List<User> findByAny(String any);

	 */

	
}



