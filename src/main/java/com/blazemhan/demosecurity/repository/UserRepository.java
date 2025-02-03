package com.blazemhan.demosecurity.repository;

import com.blazemhan.demosecurity.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {

    User findByUsername(String username);
}
