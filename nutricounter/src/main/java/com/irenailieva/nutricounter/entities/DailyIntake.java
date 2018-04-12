package com.irenailieva.nutricounter.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "daily_intakes")
@NoArgsConstructor
@Getter
@Setter
public class DailyIntake {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

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
}
