package shop.onekorea.springboot.service;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import shop.onekorea.springboot.entity.Weather;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Service
public class WeatherService {

    @Value("${openweathermap.key}")
    private String apiKey;

    public void createWeather(LocalDate localDate, String text) {
        StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[1];
        System.err.println(stackTraceElement.getClassName() + " : " + stackTraceElement.getLineNumber());

        // 날씨 데이터 가져오기
        String weatherData = getWeatherString();

        // 받아 온 날씨 JSON 파싱
        Map<String, Object> parsedWeather = parseWeather(weatherData);

        // 파싱된 데이터와 일기 값을 내 데이터베이스에 넣기
        Weather nowWeather = new Weather();
        nowWeather.setWeather(parsedWeather.get("main").toString());
        nowWeather.setIcon(parsedWeather.get("icon").toString());
        nowWeather.setTemperature((Double) parsedWeather.get("temp"));
        nowWeather.setText(parsedWeather.toString());;
        nowWeather.setCreatedAt(localDate);

        System.err.println(stackTraceElement.getClassName() + " : " + stackTraceElement.getLineNumber() + " : " + nowWeather);

    }

    private String getWeatherString() {
        String apiUrl = "https://api.openweathermap.org/data/2.5/weather?q=seoul&appid=" + apiKey;
        System.err.println("apiUrl=" + apiUrl);

        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection(); // apiUrl을 HttpURL 형식으로 연결.
            connection.setRequestMethod("GET"); // GET request
            int responseCode = connection.getResponseCode(); // 요청을 보낸 다음에 받아 온 응답 결과를 응답 코드(상태 코드)로 받아 올 수 있다.

            BufferedReader br;
            if (responseCode == 200) {
                br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            } else {
                br = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            }

            String inputLine;
            StringBuilder response = new StringBuilder(); // 결과 값을 response에 쌓는다.

            while ((inputLine = br.readLine()) != null) {
                System.err.println("inputLine: " + inputLine + ", \nbr.readLine: " + inputLine);
                response.append(inputLine);
            }
            br.close();
            return response.toString();
        } catch (Exception e) {
            return "failed to get response!!!";
        }

    }

    // temp(온도), main, icon을 map 형태로 반환해준다.
    private Map<String, Object> parseWeather(String jsonString) {

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject; // parsing 결과 값.

        try {
            jsonObject = (JSONObject) jsonParser.parse(jsonString); // 파싱 결과

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        Map<String, Object> resultMap = new HashMap<>();
        JSONObject mainData = (JSONObject) jsonObject.get("main"); // main data
        resultMap.put("temp", mainData.get("temp"));
        JSONArray weatherArray = (JSONArray) jsonObject.get("weather");
        JSONObject weatherData = (JSONObject) weatherArray.get(0);
        resultMap.put("main", weatherData.get("main"));
        resultMap.put("icon", weatherData.get("icon"));

        return resultMap;

    }

// 뉴스 크롤링
//    private static String WeatherUrl = "크롤링 할 URL";
//
//    @PostConstruct
//    public List<Weather> getWeatherData() throws Exception {
//        List<Weather> weatherList = new ArrayList<Weather>();
//        Document document = Jsoup.connect(WeatherUrl).get();
//        Elements element = document.select("select ul.type2 li");
//
//        for (Element content : element) {
//            Weather weather = Weather.builder()
//                    .image(content.select("a img").attr("abs:src")) // 이미지
//                    .subject(content.select("h4 a").text()) // 제목
//                    .url(content.select("a").attr("abs:href")) // 링크
//                    .build();
//            weatherList.add(weather);
//        }
//
//        return weatherList;
//    }

}
