package com.pablodev.imageprocessing.repositories;

import com.pablodev.imageprocessing.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {

    Optional<User> findByUsername(String username);

}
