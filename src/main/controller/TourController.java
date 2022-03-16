package main.controller;

import main.model.Tour;
import main.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class TourController {

    @Autowired
    private TourService tourService;

    @GetMapping("/addTour")
    public String showForm(Model model) {
        model.addAttribute("tour", new Tour());
        return "form";
    }

    @PostMapping("/processForm")
    public String showTourData(@Valid @ModelAttribute Tour tour, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "form";
        }
        tourService.saveOrUpdate(tour);
        return "redirect:showOffer";
    }

    @GetMapping("/showOffer")
    public String getTours(Model model) {
        List<Tour> tours = tourService.getAll();
        model.addAttribute("tours", tours);
        return "tours";
    }


    @GetMapping("/deleteTour/{id}")
    public String deleteTour(@PathVariable int id){
        Tour tour = tourService.getbyId(id);
        if (tour != null){
            tourService.delete(id);
        }
        return "redirect:/showOffer";
    }

    @GetMapping("/editTour/{id}")
    public String editTour(@PathVariable int id, Model model){
        Tour tour = tourService.getbyId(id);
        if(tour != null){
            model.addAttribute("tour", tour);
            return "form";
        }
        return "redirect:/showOffer";
    }

    @GetMapping("/addUserToTour/{id}/{userId}")
    public String addUserToTour(@PathVariable int id, @PathVariable int userId){
        tourService.addUserToTour(id, userId);

        return "redirect:/showOffer";
    }

}
