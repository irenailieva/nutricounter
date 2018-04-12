package com.irenailieva.nutricounter.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Basic;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

//@Entity
//@Table(name = "nutrients")
@NoArgsConstructor
@Getter
@Setter
public class Nutrient {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @Basic
    private String name;

    @Basic
    private String measuringUnit;
}
