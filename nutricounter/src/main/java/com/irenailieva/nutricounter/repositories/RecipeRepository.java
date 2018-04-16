package com.irenailieva.nutricounter.repositories;

import com.irenailieva.nutricounter.entities.Recipe;
import com.irenailieva.nutricounter.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    @Query("SELECT r FROM Recipe AS r WHERE r.user = :user AND LOWER(r.name) LIKE CONCAT('%', LOWER(:name), '%')")
    List<Recipe> findAllByUserAndName(@Param("user") User user, @Param("name") String name);

    List<Recipe> findAllByUser(User user);

    int countAllByUser(User user);
}
