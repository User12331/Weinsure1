package tn.weinsure1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import tn.weinsure1.entities.User;
import tn.weinsure1.repository.UserRepository;

@Service
public class UserServiceImpl implements IUserService { 
	
	@Autowired
	UserRepository userRepository;
	
	private static final Logger L= LogManager.getLogger(UserServiceImpl.class);
	
	
	@Override
	public List<User> RetrieveAllUsers(){
		//List<User> users = (List<User>) userRepository.findAll();
		List<User>users= ( List <User>) userRepository.findAll();
	
		for (User user : users){
			L.info("user +++ :" + user);
		}
		return users;
	}
	
	
	
	
	
	@Override
	public User AddUser(User u) {
		// TODO Auto-generated method stub
		User us = userRepository.save(u);
		return us;
	}
	
	
	@Override
	public void DeleteUser(String id) {
	userRepository.deleteById(Long.parseLong(id));
	}


	@Override
	public User UpdateUser(User u) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public User RetrieveUser(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	}