package lt.kestutismozeris.weatherforecast.models.services;

import lt.kestutismozeris.weatherforecast.models.Utils;
import lt.kestutismozeris.weatherforecast.models.WeatherModel;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WeatherService {

    private static WeatherService ourInstance = new WeatherService();

    private WeatherService() {
    }

    public static WeatherService getInstance() {
        return ourInstance;
    }

    public WeatherModel getWeather(String city) {

        String websiteResponse = Utils.readWebsiteContent("http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=52a68fc2733794ec8e5f76eccf3b2480");
        String description = null;
        int temperature;
        int pressure;
        int humidity;
        int clouds;
        String date = null;

        JSONObject root = new JSONObject(websiteResponse);

        JSONArray weatherObject = root.getJSONArray("weather");

        for (int i = 0; i < weatherObject.length(); i++) {
            JSONObject elementInArray = weatherObject.getJSONObject(i);
            description = elementInArray.getString("description");
        }

        JSONObject main = root.getJSONObject("main");
        temperature = (int) main.getFloat("temp") - 273;
        pressure = main.getInt("pressure");
        humidity = main.getInt("humidity");

        JSONObject mainOb = root.getJSONObject("clouds");
        clouds = mainOb.getInt("all");


        WeatherModel weatherModel = new WeatherModel();
        weatherModel.setCity(city);
        weatherModel.setDate(date);
        weatherModel.setClouds(clouds);
        weatherModel.setHumidity(humidity);
        weatherModel.setPressure(pressure);
        weatherModel.setTemperature(temperature);
        weatherModel.setWeatherComment(description);

        return weatherModel;
    }


    public List<WeatherModel> getWeatherForFive(String city) {
        String websiteResponse = Utils.readWebsiteContent("http://api.openweathermap.org/data/2.5/forecast?q=" + city + "&appid=52a68fc2733794ec8e5f76eccf3b2480");
        String description = null;
        int temperature = 0;
        float pressure = 0;
        int humidity = 0;
        int clouds = 0;
        String date = null;


        List<WeatherModel> weatherList = new ArrayList<>();

        JSONObject root = new JSONObject(websiteResponse);

        JSONArray weatherObject = root.getJSONArray("list");

        for (int i = 0; i < weatherObject.length(); i++) {
            JSONObject arrayElement = weatherObject.getJSONObject(i);
            JSONObject main = arrayElement.getJSONObject("main");
            temperature = (int) main.getFloat("temp") - 273;
            pressure = main.getFloat("pressure");
            humidity = main.getInt("humidity");
            clouds = arrayElement.getJSONObject("clouds").getInt("all");
            description = arrayElement.getJSONArray("weather").getJSONObject(0).getString("description");

            date = arrayElement.getString("dt_txt");

            if (date.contains("06:00:00") || date.contains("09:00:00") || date.contains("12:00:00")
                    || date.contains("15:00:00") || date.contains("18:00:00") || date.contains("21:00:00")
                    || date.contains("00:00:00")) {

                weatherList.add(new WeatherModel.Builder(city)
                        .setDate(date.substring(0, 10) + " (" + date.substring(10, date.length() - 3) + ")")
                        .setTemperature(temperature)
                        .setPressure((int) pressure / 10)
                        .setHumidity(humidity)
                        .setClouds(clouds)
                        .setWeatherComment(description)
                        .build());
            }
        }
        return weatherList;
    }
}