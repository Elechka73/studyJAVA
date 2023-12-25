package com.example.lr15.repositories;

import com.example.lr15.entities.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;



@Repository
public interface DishRepository extends JpaRepository<Dish,Integer>, JpaSpecificationExecutor<Dish> {
}
