package com.irenailieva.nutricounter.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "recipes")
@PrimaryKeyJoinColumn(name = "id")
@NoArgsConstructor
@Getter
@Setter
public class Recipe extends Edible {

    @OneToMany(mappedBy = "recipe", targetEntity = Ingredient.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Ingredient> ingredients;

    @Basic
    private String recipeImageUrl;

    public String getIngredientsAsString() {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < ingredients.size(); i++) {
            builder.append(ingredients.get(i).getEdible().getName())
                    .append(" - ")
                    .append(ingredients.get(i).getGrams())
                    .append("g");

            if (i < ingredients.size() - 1) {
                builder.append(System.lineSeparator());
            }
        }
        return builder.toString();
    }
}
