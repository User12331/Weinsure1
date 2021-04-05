package tn.weinsure1.service;

import java.util.List; 

import tn.weinsure1.entities.User;

public interface IUserService {

	List<User> RetrieveAllUsers(); 
	
	 User AddUser(User u);
	 void DeleteUser(String id);
	 User UpdateUser(User u);
	 
	 User RetrieveUser(String id);
	
}



