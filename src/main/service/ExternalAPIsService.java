package main.service;

import main.externalapi.model.openweathermap.OpenWeatherMap;

import javax.servlet.http.HttpServletRequest;

public interface ExternalAPIsService {

    public OpenWeatherMap getData(HttpServletRequest request);

}
