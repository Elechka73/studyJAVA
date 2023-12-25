package com.example.lr15.specifications;

import com.example.lr15.entities.Dish;
import org.springframework.data.jpa.domain.Specification;


public class DishSpecifications {
    public static Specification<Dish> hasName(String name) {
        return ((root, query, criteriaBuilder) -> {
            if (name == null || name.isBlank()) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("name")), "%" + name.toLowerCase() + "%");
        });
    }

    public static Specification<Dish> hasIngredients(String ingredients) {
        return ((root, query, criteriaBuilder) -> {
            if (ingredients == null || ingredients.isBlank()) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("ingredients")), "%" + ingredients.toLowerCase() + "%");
        });
    }

    public static Specification<Dish> hasEnergyFrom(Integer energyFrom) {
        return (((root, query, criteriaBuilder) -> {
            if(energyFrom == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.greaterThanOrEqualTo(root.get("energy_value"), energyFrom);
        }));
    }
    public static Specification<Dish> hasEnergyTo(Integer energyTo) {
        return (((root, query, criteriaBuilder) -> {
            if(energyTo == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.lessThanOrEqualTo(root.get("energy_value"), energyTo);
        }));
    }
}
