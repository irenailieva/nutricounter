package com.irenailieva.nutricounter.models.view;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class RecipeViewModel {
    private long id;
    private String name;
    private String ingredients;
    private String recipeImageUrl;
}
