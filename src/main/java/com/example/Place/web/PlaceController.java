package com.example.Place.web;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.Place.domain.Category;
import com.example.Place.domain.CategoryRepository;
import com.example.Place.domain.Place;
import com.example.Place.domain.PlaceRepository;

@Controller
public class PlaceController {

    @Autowired
    private PlaceRepository placeRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @ModelAttribute("place")
    public Place place() {
        return new Place();
    }

    // List
    @GetMapping("/places")
    public String placeList(Model model) {
        List<Place> places = (List<Place>) placeRepository.findAll();
        model.addAttribute("places", places);
        return "places";
    }

    // Delete - Only accessible by users with "ADMIN" role
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/places/delete/{id}")
    public String deletePlace(@PathVariable("id") Long placeId, Model model) {
        placeRepository.deleteById(placeId);
        return "redirect:/places";
    }

    // ADD
    @GetMapping("/addplace")
    public String addPlaceForm(Model model) {
        model.addAttribute("place", new Place());
        model.addAttribute("categories", categoryRepository.findAll());
        return "addplace";
    }

    @PostMapping("/saveplace")
    public String savePlace(@ModelAttribute("place") Place place, @RequestParam("category.id") Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElse(null);
        if (category != null) {
            place.setCategory(category);
            placeRepository.save(place);
        }
        return "redirect:/places";
    }

    // Edit
    @GetMapping("/editplace/{id}")
    public String editPlaceForm(@PathVariable Long id, Model model) {
        Place place = placeRepository.findById(id).orElse(null);

        if (place == null) {
            return "redirect:/places";
        }

        model.addAttribute("place", place);
        model.addAttribute("categories", categoryRepository.findAll());

        return "editplace";
    }

    @PostMapping("/editplace/{id}")
    public String editPlace(@PathVariable Long id, @ModelAttribute("place") Place editedPlace, @RequestParam("category.id") Long categoryId) {
        Place place = placeRepository.findById(id).orElse(null);
        if (place != null) {
            Category category = categoryRepository.findById(categoryId).orElse(null);
            if (category != null) {
                place.setName(editedPlace.getName());
                place.setLocation(editedPlace.getLocation());
                place.setPriceLevel(editedPlace.getPriceLevel());
                place.setCategory(category);

                placeRepository.save(place);
            }
        }
        return "redirect:/places";
    }
}
