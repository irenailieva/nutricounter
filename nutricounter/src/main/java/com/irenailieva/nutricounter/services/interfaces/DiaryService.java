package com.irenailieva.nutricounter.services.interfaces;

import com.irenailieva.nutricounter.entities.DiaryEntry;
import com.irenailieva.nutricounter.entities.Edible;
import com.irenailieva.nutricounter.models.create.DiaryEntryCreateModel;

import java.util.List;

public interface DiaryService {

    DiaryEntry addDiaryEntry(DiaryEntryCreateModel diaryEntryCreateModel);
    void deleteDiaryEntry(long diaryId);

    List<DiaryEntry> findEntriesOfCurrentDate(String username);
    List<Edible> findFoodsByUsernameAndFoodName(String username, String foodName);
}
