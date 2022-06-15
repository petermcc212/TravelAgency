package main.controller;

import main.externalapi.model.openweathermap.OpenWeatherMap;
import main.service.ExternalAPIsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController {

	@Autowired
	private ExternalAPIsService externalAPIsService;

	@RequestMapping("/")
	public String getHome(HttpServletRequest request, Model model) {
		OpenWeatherMap openWeatherMap = externalAPIsService.getData(request);
		model.addAttribute("openWeatherMap", openWeatherMap);
		return "home";

	}
	
}
