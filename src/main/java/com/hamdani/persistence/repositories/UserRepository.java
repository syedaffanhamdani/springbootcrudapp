package com.hamdani.persistence.repositories;

import com.hamdani.persistence.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

  List<User> findByFirstName(String firstName);


}

