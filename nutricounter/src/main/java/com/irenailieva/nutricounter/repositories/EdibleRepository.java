package com.irenailieva.nutricounter.repositories;

import com.irenailieva.nutricounter.entities.Edible;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EdibleRepository extends JpaRepository<Edible, Long> {
    List<Edible> findAllByName(String name);
}
