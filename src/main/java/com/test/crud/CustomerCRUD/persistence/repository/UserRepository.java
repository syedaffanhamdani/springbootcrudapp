package com.test.crud.CustomerCRUD.persistence.repository;

import com.test.crud.CustomerCRUD.persistence.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findByFirstName(String firstName);



}

