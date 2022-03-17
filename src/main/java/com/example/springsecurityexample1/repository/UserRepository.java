package com.example.springsecurityexample1.repository;

import com.example.springsecurityexample1.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.Email;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

  boolean existsByEmail(String email);

  Optional<User> findByEmail(String email);
}
