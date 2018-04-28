package com.irenailieva.nutricounter.services.implementations;

import com.irenailieva.nutricounter.entities.DiaryEntry;
import com.irenailieva.nutricounter.entities.User;
import com.irenailieva.nutricounter.models.view.DiaryEntryViewModel;
import com.irenailieva.nutricounter.repositories.DiaryEntryRepository;
import com.irenailieva.nutricounter.services.interfaces.DiaryService;
import com.irenailieva.nutricounter.services.interfaces.UserService;
import com.irenailieva.nutricounter.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class DiaryServiceImpl implements DiaryService {

    private DiaryEntryRepository diaryEntryRepository;

    private UserService userService;

    @Autowired
    public DiaryServiceImpl(DiaryEntryRepository diaryEntryRepository, UserService userService) {
        this.diaryEntryRepository = diaryEntryRepository;
        this.userService = userService;
    }

    @Override
    public DiaryEntry addDiaryEntry(int completionPercentage, String username) {

        User user = this.userService.findByUsername(username);

        DiaryEntry newDiaryEntry = new DiaryEntry();
        newDiaryEntry.setUser(user);
        newDiaryEntry.setDate(DateUtil.getToday());
        newDiaryEntry.setCompletionPercentage(completionPercentage);

        this.diaryEntryRepository.saveAndFlush(newDiaryEntry);

        return newDiaryEntry;
    }

    @Override
    public List<DiaryEntryViewModel> getDiaryHistory(String username) {

        User user = this.userService.findByUsername(username);
        List<DiaryEntry> diaryEntries = this.diaryEntryRepository.findAllByUserOrderByIdDesc(user);
        List<DiaryEntryViewModel> diaryEntryViewModels = new ArrayList<>();
        for (DiaryEntry diaryEntry : diaryEntries) {
            DiaryEntryViewModel entryViewModel = new DiaryEntryViewModel();
            entryViewModel.setCompletionPercentage(diaryEntry.getCompletionPercentage());
            entryViewModel.setDate(DateUtil.getDateAsString(diaryEntry.getDate()));
            diaryEntryViewModels.add(entryViewModel);
        }

        return diaryEntryViewModels;
    }
}
