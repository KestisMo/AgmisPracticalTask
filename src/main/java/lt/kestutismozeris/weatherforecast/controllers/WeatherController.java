package lt.kestutismozeris.weatherforecast.controllers;

import lt.kestutismozeris.weatherforecast.models.services.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class WeatherController {

    private final WeatherService weatherService;

    @Autowired
    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/weather")
    public String getWeather() {
        return "weather";
    }

    @PostMapping("/weather")
    public String postWeather(@RequestParam("city") String city,
                              Model model) {
        WeatherService weatherService = WeatherService.getInstance();

        try {
            model.addAttribute("cityName", weatherService.getWeather(city));
            model.addAttribute("cityWeather", weatherService.getWeatherForFive(city));
        } catch (Exception e) {
            model.addAttribute("wrongCity", "Enter city name!");
            return "weather";
        }

        return "weather";

    }
}
