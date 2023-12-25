package com.example.lr15.services;

import com.example.lr15.specifications.DishSpecifications;
import com.example.lr15.entities.Dish;
import com.example.lr15.repositories.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishService {
    private final DishRepository repository;

    @Autowired
    public DishService(DishRepository repository) {
        this.repository = repository;
    }

    public Dish getById(Integer id) {
        Dish organization = repository.findById(id).orElse(null);
        if(organization == null) throw new UsernameNotFoundException("");
        return organization;
    }

    public Page<Dish> getAllDish(Pageable pageable) {
        return repository.findAll(pageable);
    }
    public Page<Dish> getAllDish(String name, String ingredients, Integer energyFrom, Integer energyTo, Pageable pageable) {
        Specification<Dish> specification = Specification
                .where(DishSpecifications.hasName(name))
                .and(DishSpecifications.hasIngredients(ingredients))
                .and(DishSpecifications.hasEnergyFrom(energyFrom))
                .and(DishSpecifications.hasEnergyTo(energyTo));
        return repository.findAll(specification, pageable);
    }
    public List<Dish> getTopDish() {
        Pageable topPageable = PageRequest.of(0, 3, Sort.by(Sort.Order.desc("views")));
        Page<Dish> topDishPage = repository.findAll(topPageable);
        return topDishPage.getContent();
    }
    public void add(Dish dish) {
        dish.setViews(0);
        repository.save(dish);
    }

    public void delete(Dish dish) {
        repository.delete(dish);
    }

    public void update(Dish exist, Dish updated) {
        if (!updated.getName().isBlank()) exist.setName(updated.getName());
        if (!updated.getIngredients().isBlank()) exist.setIngredients(updated.getIngredients());
        if (updated.getProteins() != 0.0) exist.setProteins(updated.getProteins());
        if (updated.getFats() != 0.0) exist.setFats(updated.getFats());
        if (updated.getCarbohydrates() != 0.0) exist.setCarbohydrates(updated.getCarbohydrates());
        if (updated.getEnergy_value() != null) exist.setEnergy_value(updated.getEnergy_value());
        repository.save(exist);
    }
    public void incViews(Dish dish){
        dish.setViews(dish.getViews() + 1);
        repository.save(dish);
    }
}
