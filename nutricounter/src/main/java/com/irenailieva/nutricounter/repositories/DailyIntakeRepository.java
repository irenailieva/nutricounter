package com.irenailieva.nutricounter.repositories;

import com.irenailieva.nutricounter.entities.DailyIntake;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DailyIntakeRepository extends JpaRepository<DailyIntake, Long> {
    DailyIntake findByUserId(long userId);
}
