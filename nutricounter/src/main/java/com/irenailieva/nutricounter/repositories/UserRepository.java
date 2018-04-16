package com.irenailieva.nutricounter.repositories;

import com.irenailieva.nutricounter.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u.email FROM User u")
    List<String> findAllUserEmails();

    @Query("SELECT u.username FROM User u")
    List<String> findAllUsernames();

    @Query("SELECT u.username FROM User u WHERE u.username LIKE CONCAT('%', :username, '%')")
    List<String> findUsernames(@Param("username") String username);

    List<User> findAll();

    User findFirstById(long username);

    User findFirstByUsername(String username);
}
