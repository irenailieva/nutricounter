package com.irenailieva.nutricounter.repositories;

import com.irenailieva.nutricounter.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u.email FROM User u")
    List<String> findAllUserEmails();

    @Query("SELECT u.username FROM User u")
    List<String> findAllUsernames();

    List<User> findAll();

    User findFirstById(long username);

    User findFirstByUsername(String username);
}
