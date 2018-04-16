package com.irenailieva.nutricounter.repositories;

import com.irenailieva.nutricounter.entities.CustomFood;
import com.irenailieva.nutricounter.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomFoodRepository extends JpaRepository<CustomFood, Long> {

    @Query("SELECT cf FROM CustomFood AS cf WHERE cf.user = :user AND LOWER(cf.name) LIKE CONCAT('%', LOWER(:name), '%')")
    List<CustomFood> findAllByUserAndName(@Param("user") User user, @Param("name") String name);

    List<CustomFood> findAllByUser(User user);

    int countAllByUser(User user);
}
