package com.kenforum.repository;

import com.kenforum.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {

    List<User> findByEmail(String email);

    List<User> findByEmailAndPassword(String email, String password);

}
