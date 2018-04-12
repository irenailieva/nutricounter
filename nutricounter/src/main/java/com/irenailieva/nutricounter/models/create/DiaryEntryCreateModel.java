package com.irenailieva.nutricounter.models.create;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class DiaryEntryCreateModel {
    private String username;
    private String date;
    private long edibleId;
    private double grams;
}
