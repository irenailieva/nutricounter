package com.irenailieva.nutricounter.repositories;

import com.irenailieva.nutricounter.entities.GlobalFood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GlobalFoodRepository extends JpaRepository<GlobalFood, Long> {

    @Query("SELECT gf FROM GlobalFood AS gf WHERE LOWER(gf.name) LIKE CONCAT('%', LOWER(:name), '%')")
    List<GlobalFood> findAllByName(@Param("name") String name);
}
