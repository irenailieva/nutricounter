package com.irenailieva.nutricounter.services.interfaces;

import com.irenailieva.nutricounter.entities.DiaryEntry;
import com.irenailieva.nutricounter.models.view.DiaryEntryViewModel;

import java.util.List;

public interface DiaryService {

    DiaryEntry addDiaryEntry(int completionPercentage, String username);

    List<DiaryEntryViewModel> getDiaryHistory(String username);
}
