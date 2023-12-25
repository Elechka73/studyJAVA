package com.example.lr15.entities;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "dish")
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "ingredients")
    private String ingredients;

    @Column(name = "proteins")
    private Double proteins;

    @Column(name = "fats")
    private Double fats ;

    @Column(name = "carbohydrates")
    private Double carbohydrates;

    @Column(name = "energy_value")
    private Integer energy_value;

    @Column(name = "views")
    private Integer views;
}
