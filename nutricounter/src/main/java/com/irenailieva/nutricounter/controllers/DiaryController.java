package com.irenailieva.nutricounter.controllers;

import com.irenailieva.nutricounter.controllers.base.BaseController;
import com.irenailieva.nutricounter.entities.DiaryEntry;
import com.irenailieva.nutricounter.entities.Edible;
import com.irenailieva.nutricounter.models.create.DiaryEntryCreateModel;
import com.irenailieva.nutricounter.services.interfaces.DiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/diary")
public class DiaryController extends BaseController {

    private DiaryService diaryService;

    @Autowired
    public DiaryController(DiaryService diaryService) {
        this.diaryService = diaryService;
    }

    @GetMapping("")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView diary(Principal principal) {
        List<DiaryEntry> diaryEntries = this.diaryService.findEntriesOfCurrentDate(principal.getName());
        return super.view("diary")
        .addObject("username", principal.getName())
        .addObject("diaryEntries", diaryEntries);
    }

    @GetMapping("/{foodName}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView diaryFindFoods(Principal principal, @PathVariable String foodName) {
        List<Edible> foundFoods = this.diaryService.findFoodsByUsernameAndFoodName(principal.getName(), foodName);
        return super.view("diary")
        .addObject("username", principal.getName())
        .addObject("diaryEntries", foundFoods);
    }

    @PostMapping("/add-entry")
    public ResponseEntity<DiaryEntry> addEntry(DiaryEntryCreateModel diaryEntryCreateModel) {
        DiaryEntry diaryEntry = this.diaryService.addDiaryEntry(diaryEntryCreateModel);
        ResponseEntity<DiaryEntry> responseEntity = new ResponseEntity<>(diaryEntry, HttpStatus.OK);
        return responseEntity;
    }

    @PostMapping("/delete-entry")
    public void deleteEntry(long entryId) {
        this.diaryService.deleteDiaryEntry(entryId);
    }
}
