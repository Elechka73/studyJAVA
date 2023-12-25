package com.example.lr15.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.ui.Model;
import com.example.lr15.entities.Dish;
import com.example.lr15.services.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Controller
public class DishController {
    private DishService dishService;
    private UserDetailsService userDetailsService;

    @Autowired
    public void setDishService(DishService dishService) {
        this.dishService = dishService;
    }

    @GetMapping("")
    public String showDishList(Model model, @RequestParam(defaultValue = "0") int page) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<Dish> dishPage = dishService.getAllDish(pageable);
        model.addAttribute("dishs", dishPage.getContent());
        model.addAttribute("dish", new Dish());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", dishPage.getTotalPages());
        List<Dish> topDish = dishService.getTopDish();
        model.addAttribute("topDish", topDish);
        return "dishs";
    }

    @PostMapping("/dishs/addOrUpdate/add")
    public String addDish(@ModelAttribute(value = "dish") Dish dish) {
        dishService.add(dish);
        return "redirect:/";
    }

    @GetMapping("/dishs/addOrUpdate/add")
    public String test(Model model,
                       @RequestHeader(value = "Referer") String referer) {
        Page<Dish> dishPage = dishService.getAllDish(PageRequest.of(0, 5));
        model.addAttribute("dishs", dishPage.getContent());
        model.addAttribute("dish", new Dish());
        model.addAttribute("referer", referer);
        return "addOrUpdate";
    }

    @GetMapping("/dishs/addOrUpdate/edit/{id}")
    public String editDish(Model model, @PathVariable(value = "id") Integer id,
                                   @RequestHeader(value = "Referer") String referer) {
        Dish dish = dishService.getById(id);
        model.addAttribute("dish", dish);
        model.addAttribute("referer", referer);
        return "addOrUpdate";
    }

    @PostMapping("/dishs/addOrUpdate/edit")
    public String updateOrganization(@ModelAttribute(value = "dish") Dish updatedDish) {
        Dish dish = dishService.getById(updatedDish.getId());
        dishService.update(dish, updatedDish);
        return "redirect:/";
    }

    @GetMapping("/dishs/show/{id}")
    public String showOneOrganization(Model model, @PathVariable(value = "id") Integer id,
                                      @RequestHeader(value = "Referer") String referer) {
        Dish dish = dishService.getById(id);
        dishService.incViews(dish);
        model.addAttribute("dish", dish);
        model.addAttribute("referer", referer);
        return "dish-info";
    }

    @GetMapping("/dishs/delete/{id}")
    public String deleteOrganizations(@PathVariable(value = "id") Integer id) {
        Dish dish = dishService.getById(id);
        dishService.delete(dish);
        return "redirect:/";
    }

    @GetMapping("/dishs/filter")
    public String filterOrganizations(Model model,
                                      @RequestParam(value = "name", required = false) String name,
                                      @RequestParam(value = "ingredients", required = false) String ingredients,
                                      @RequestParam(value = "energyfrom", required = false) Integer energyfrom,
                                      @RequestParam(value = "energyto", required = false) Integer energyto,
                                      @RequestParam(defaultValue = "0") int page) {
        Dish dish = new Dish();

        Pageable pageable = PageRequest.of(page, 5);
        Page<Dish> dishPage = dishService.getAllDish(name, ingredients, energyfrom, energyto, pageable);

        model.addAttribute("dishs", dishPage.getContent());
        model.addAttribute("dish", dish);
        model.addAttribute("name", name);
        model.addAttribute("ingredients", ingredients);
        model.addAttribute("energyfrom", energyfrom);
        model.addAttribute("energyto", energyto);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", dishPage.getTotalPages());

        List<Dish> topDish = dishService.getTopDish();
        model.addAttribute("topDish", topDish);

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString("/dishs/filter");
        if (name != null && !name.isEmpty()) uriBuilder.queryParam("name", name);
        if (ingredients != null && !ingredients.isEmpty()) uriBuilder.queryParam("ingredients", ingredients);
        if (energyfrom != null) uriBuilder.queryParam("energyfrom", energyfrom);
        if (energyto != null) uriBuilder.queryParam("energyto", energyto);
        model.addAttribute("filterUrl", uriBuilder.build().toString());

        return "dishs";
    }

    @PostMapping("/authenticateTheUser")
    public String authenticateUser(@RequestParam("username") String username, @RequestParam("password") String password, Model model) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (userDetails != null) {
            String storedPassword = userDetails.getPassword();
            if (password.equals(storedPassword)) {
                model.addAttribute("username", username);
                return "redirect:/dishs";
            }
        }
        return "dishs";
    }
}