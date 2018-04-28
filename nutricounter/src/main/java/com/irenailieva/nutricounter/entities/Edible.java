package com.irenailieva.nutricounter.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "edibles")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
public abstract class Edible {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private double energy;

    @Column(nullable = false)
    private double water;

    @Column(nullable = false)
    private double carbohydrates;

    @Column(nullable = false)
    private double fat;

    @Column(nullable = false)
    private double protein;

    @Column(name = "vitamin_a", nullable = false)
    private double vitaminA;

    @Column(name = "vitamin_b6", nullable = false)
    private double vitaminB6;

    @Column(name = "vitamin_c", nullable = false)
    private double vitaminC;

    @Column(nullable = false)
    private double calcium;

    @Column(nullable = false)
    private double iron;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public Edible() {
        this.energy = 0;
        this.water = 0;
        this.carbohydrates = 0;
        this.fat = 0;
        this.protein = 0;
        this.vitaminA = 0;
        this.vitaminB6 = 0;
        this.vitaminC = 0;
        this.calcium = 0;
        this.iron = 0;
    }
}