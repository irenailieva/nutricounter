package com.irenailieva.nutricounter.controllers;

import com.irenailieva.nutricounter.controllers.base.BaseController;
import com.irenailieva.nutricounter.entities.DailyIntake;
import com.irenailieva.nutricounter.entities.User;
import com.irenailieva.nutricounter.models.service.DiaryEntryServiceModel;
import com.irenailieva.nutricounter.services.interfaces.DailyIntakeService;
import com.irenailieva.nutricounter.services.interfaces.DiaryService;
import com.irenailieva.nutricounter.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/diary")
public class DiaryController extends BaseController {

    private final DiaryService diaryService;
    private final UserService userService;
    private final DailyIntakeService dailyIntakeService;

    @Autowired
    public DiaryController(DiaryService diaryService, UserService userService, DailyIntakeService dailyIntakeService) {
        this.diaryService = diaryService;
        this.userService = userService;
        this.dailyIntakeService = dailyIntakeService;
    }

    @GetMapping("")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView diary(Principal principal) {

        User user = this.userService.findByUsername(principal.getName());
        DailyIntake dailyIntake = this.dailyIntakeService.findIntakeByUser(user);

        return super.view("diary")
        .addObject("username", principal.getName())
        .addObject("dailyIntake", dailyIntake);
    }

    @PostMapping("/save-day")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView saveDay(@RequestParam("completionPercentage") int completionPercentage, Principal principal, RedirectAttributes redirectAttributes) {
        this.diaryService.addDiaryEntry(completionPercentage, principal.getName());
        redirectAttributes.addFlashAttribute("displayDiaryEntryAddedSuccess", true);
        return super.redirect("/diary");
    }

    @GetMapping("/history")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView getDiaryHistory(Principal principal) {
        List<DiaryEntryServiceModel> diaryEntryHistory = this.diaryService.getDiaryHistory(principal.getName());
        return super.view("users/diary-history", "diaryEntryHistory", diaryEntryHistory);
    }

}
