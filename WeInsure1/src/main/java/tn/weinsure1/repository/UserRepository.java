package tn.weinsure1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.weinsure1.entities.User;

@Repository
public interface UserRepository  extends CrudRepository <User, Long >
{
	/*@Query(value="SELECT u from user u where u.Role=: Insured")
	List<User> findUserByRoleInsured();
	@Query(value="SELECT u from user u where u.Role=: Insurer")
	List<User> findUserByRoleInsurer();*/
}

