package tn.weinsure1.repository;

import org.springframework.data.repository.CrudRepository;

import tn.weinsure1.entities.User;

public interface UserRepository  extends CrudRepository <User, Long >
{

}
