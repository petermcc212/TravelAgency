package main.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import main.model.Tour;

@Controller
public class HomeController {

//	private List<Tour> tours = new ArrayList<>();

	@RequestMapping("/")
	public String getHome() {
		return "home";
	}
	

	
}
