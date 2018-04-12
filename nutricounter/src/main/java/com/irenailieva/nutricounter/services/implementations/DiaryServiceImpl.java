package com.irenailieva.nutricounter.services.implementations;

import com.irenailieva.nutricounter.entities.DiaryEntry;
import com.irenailieva.nutricounter.entities.Edible;
import com.irenailieva.nutricounter.entities.User;
import com.irenailieva.nutricounter.models.create.DiaryEntryCreateModel;
import com.irenailieva.nutricounter.repositories.DiaryEntryRepository;
import com.irenailieva.nutricounter.services.interfaces.DiaryService;
import com.irenailieva.nutricounter.services.interfaces.FoodService;
import com.irenailieva.nutricounter.services.interfaces.UserService;
import com.irenailieva.nutricounter.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class DiaryServiceImpl implements DiaryService {

    private DiaryEntryRepository diaryEntryRepository;

    private UserService userService;
    private FoodService foodService;

    @Autowired
    public DiaryServiceImpl(DiaryEntryRepository diaryEntryRepository, UserService userService, FoodService foodService) {
        this.diaryEntryRepository = diaryEntryRepository;
        this.userService = userService;
        this.foodService = foodService;
    }

    @Override
    public DiaryEntry addDiaryEntry(DiaryEntryCreateModel diaryEntryCreateModel) {

        User user = this.userService.findByUsername(diaryEntryCreateModel.getUsername());
        Date dateObject = DateUtil.convertToDate(diaryEntryCreateModel.getDate());
        Edible edible = this.foodService.findFoodById(diaryEntryCreateModel.getEdibleId());

        DiaryEntry newDiaryEntry = new DiaryEntry();
        newDiaryEntry.setUser(user);
        newDiaryEntry.setDate(dateObject);
        newDiaryEntry.setEdible(edible);
        newDiaryEntry.setGrams(Math.round(diaryEntryCreateModel.getGrams() * 100.0) / 100.0);

        this.diaryEntryRepository.saveAndFlush(newDiaryEntry);
        return newDiaryEntry;
    }

    @Override
    public void deleteDiaryEntry(long entryId) {
        this.diaryEntryRepository.deleteById(entryId);
    }

    @Override
    public List<DiaryEntry> findEntriesOfCurrentDate(String username) {
        User user = this.userService.findByUsername(username);
        Date today = DateUtil.getToday();
        List<DiaryEntry> diaryEntries = this.diaryEntryRepository.findAllByUserAndDate(user, today);
        return diaryEntries;
    }

    @Override
    public List<Edible> findFoodsByUsernameAndFoodName(String username, String foodName) {
        return this.foodService.findFoodsByUserAndName(username, foodName);
    }
}
