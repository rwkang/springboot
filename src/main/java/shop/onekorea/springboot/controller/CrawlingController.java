/**
 * 날씨 크롤링
 * https://oranthy.tistory.com/m/313
 *
 * Weather API key
 * https://home.openweathermap.org/api_keys
 * 1fbfbf78015e00c092f0f0fcab9ff2da
 * 7eb63b98988614f92d303c1ca1db3330
 *
 *
 * @author by rwkang on 2023.09.28
 */

package shop.onekorea.springboot.controller;

//import ch.qos.logback.core.model.Model;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import shop.onekorea.springboot.dto.FormDto;
import shop.onekorea.springboot.entity.Weather;
import shop.onekorea.springboot.service.WeatherService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import static com.fasterxml.jackson.databind.type.LogicalType.DateTime;
import static java.awt.SystemColor.text;
import static shop.onekorea.springboot.SpringbootApplication.getInfo;

//@RestController
@Controller
@RequiredArgsConstructor
@RequestMapping("/create")
public class CrawlingController {

    private final WeatherService weatherService;

    @GetMapping("/form")
    public String showForm(HttpServletRequest request, Model model) {
        FormDto formDto = new FormDto();
        formDto.setName("weather");
        LocalDateTime toDay = LocalDateTime.now();
        formDto.setToday(toDay);

        model.addAttribute("formDto", formDto);
//        model.addAttribute("form", new FormDto());

        System.err.println(getInfo());
        System.err.println(getInfo() + "formDto.toString(): " + formDto.toString());

        return "weather";
    }

//    @PostMapping("/form")
//    public String form(@ModelAttribute("form") FormDto formDto) {
//        return "";
////        return "/create/weather";
//    }

    @PostMapping("/weather")
    public String createWeather(@RequestParam FormDto formDto, @RequestBody String body) {
//    public String createWeather(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date, @RequestBody String text) {
//    void createWeather(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date, @RequestBody String text) {
//    void createWeather(@ModelAttribute FormDto formDto) {
//        System.err.println(getInfo() + ", date: " + date + ", text: " + text);
        System.err.println(getInfo());
        System.err.println(getInfo() + ", formDto: " + formDto.toString());

        LocalDateTime dateTime = formDto.getToday();
        LocalDate date = dateTime.toLocalDate();
        String text = formDto.getName();

        System.err.println(getInfo() + ", dateTime: " + dateTime);
//        System.err.println(getInfo() + ", date: " + date);
        System.err.println(getInfo() + ", text: " + text);

        try {
            weatherService.createWeather(date, text);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return "/crawling/weather";

    }


//    @GetMapping("/weather")
//    public String getWeather(Model model) throws Exception {
//        List<Weather> weatherList = weatherService.getWeatherData();
//        model.addAttribute("weatherList", weatherList);
//
//        return "weatherList";
//    }

}
