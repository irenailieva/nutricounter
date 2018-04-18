package com.irenailieva.nutricounter.services.interfaces;

import com.irenailieva.nutricounter.models.service.DiaryEntryServiceModel;

import java.util.List;

public interface DiaryService {

    void addDiaryEntry(int completionPercentage, String username);

    List<DiaryEntryServiceModel> getDiaryHistory(String username);
}
