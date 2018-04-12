package com.irenailieva.nutricounter.repositories;

import com.irenailieva.nutricounter.entities.DiaryEntry;
import com.irenailieva.nutricounter.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface DiaryEntryRepository extends JpaRepository<DiaryEntry, Long> {
    List<DiaryEntry> findAllByUserAndDate(User user, Date date);

    @Query("SELECT di.date FROM DiaryEntry AS di")
    List<Date> findAllDates();
}
