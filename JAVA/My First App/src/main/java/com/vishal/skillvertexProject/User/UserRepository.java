package com.vishal.skillvertexProject.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



@Repository
public interface UserRepository extends JpaRepository<User, String> {

    @Query(value = "SELECT * FROM users where username = ?1", nativeQuery = true)
    List<User> findByEmail(String email);

    @Query(value = "SELECT CONCAT(first_name, ' ', last_name) FROM users where email = ?1", nativeQuery = true)
    String getNameByEmail(String email);
}
