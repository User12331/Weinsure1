package tn.weinsure1.restController;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.weinsure1.entities.User;
import tn.weinsure1.service.IUserService; 
@RestController

public class UserController {
	
	
	/*@Autowired
	IUserService userService;

	// http://localhost:8081/SpringMVC/servlet/retrieve-all-users
	@GetMapping("/retrieve-all-users")
	@ResponseBody
	public List<User> getUsers() {
	List<User> list = userService.RetrieveAllUsers();
	return list;
	}
	// http://localhost:8081/SpringMVC/servlet/retrieve-user/{user-id}
	@GetMapping("/retrieve-user/{user-id}")
	@ResponseBody
	public User retrieveUser(@PathVariable("user-id") String userId) {
	return userService.RetrieveUser(userId);
	}

	// http://localhost:8081/SpringMVC/servlet/add-user
	@PostMapping("/add-user")
	@ResponseBody
	public User addUser(@RequestBody User u) {
	User user = userService.AddUser(u);
	return user;
	}
	// http://localhost:8081/SpringMVC/servlet/remove-user/{user-id}
	@DeleteMapping("/remove-user/{user-id}")
	@ResponseBody
	public void removeUser(@PathVariable("user-id") String userId) {
	userService.DeleteUser(userId);
	}

	// http://localhost:8081/SpringMVC/servlet/modify-user
	@PutMapping("/modify-user")
	@ResponseBody
	public User modifyUser(@RequestBody User user) {
	return userService.UpdateUser(user);
	}*/
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	/*
//	
//  // ------------------------
//  // PUBLIC METHODS
//  // ------------------------
//  
//  /**
//   * /create  --> Create a new user and save it in the database.
//   * 
//   * @param email User's email
//   * @param name User's name
//   * @return A string describing if the user is successfully created or not.
//   */
//	
//	
//  @RequestMapping("/create")
//	User us = userRepository.save(u);
//  @ResponseBody
//  public User AddUser(User u) {
//    User user = null;
//    try {
//      user = new User
//    		  User us = serRepository.save(u);
//    }
//    catch (Exception ex) {
//      return "Error creating the user: " + ex.toString();
//    }
//    return "User succesfully created! (id = " + user.getId() + ")";
//  }
//  
//  /**
//   * /delete  --> Delete the user having the passed id.
//   * 
//   * @param id The id of the user to delete
//   * @return A string describing if the user is successfully deleted or not.
//   */
//  @RequestMapping("/delete")
//  @ResponseBody
//  public String delete(long id) {
//    try {
//      User user = new User(id);
//      userDao.delete(user);
//    }
//    catch (Exception ex) {
//      return "Error deleting the user: " + ex.toString();
//    }
//    return "User successfully deleted!";
//  }
//  
//  /**
//   * /get-by-email  --> Return the id for the user having the passed email.
//   * 
//   * @param email The email to search in the database.
//   * @return The user id or a message error if the user is not found.
//   */
//  @RequestMapping("/get-by-email")
//  @ResponseBody
//  public String getByEmail(String email) {
//    String userId;
//    try {
//      User user = userDao.findByEmail(email);
//      userId = String.valueOf(user.getId());
//    }
//    catch (Exception ex) {
//      return "User not found";
//    }
//    return "The user id is: " + userId;
//  }
//  
//  /**
//   * /update  --> Update the email and the name for the user in the database 
//   * having the passed id.
//   * 
//   * @param id The id for the user to update.
//   * @param email The new email.
//   * @param name The new name.
//   * @return A string describing if the user is successfully updated or not.
//   */
//  @RequestMapping("/update")
//  @ResponseBody
//  public String updateUser(long id, String email, String name) {
//    try {
//      User user = userDao.findOne(id);
//      user.setEmail(email);
//      user.setName(name);
//      userDao.save(user);
//    }
//    catch (Exception ex) {
//      return "Error updating the user: " + ex.toString();
//    }
//    return "User successfully updated!";
//  }
//
//  // ------------------------
//  // PRIVATE FIELDS
//  // ------------------------
//
//  @Autowired
//  private UserDao userDao;
//
//} // class UserControlle*/