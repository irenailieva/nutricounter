package com.irenailieva.nutricounter.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "recipes")
@PrimaryKeyJoinColumn(name = "id")
public class Recipe extends Edible {

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "recipes_ingredients",
            joinColumns = @JoinColumn(name = "recipe_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id", referencedColumnName = "id"))
    List<Ingredient> ingredients;
}
