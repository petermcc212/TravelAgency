package main.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import main.externalapi.model.myip.MyIP;
import main.externalapi.model.openweathermap.OpenWeatherMap;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

@Service
public class ExternalApIsServiceImpl implements ExternalAPIsService{

    private static final String OPENWEATHERMAP_API_KEY = "19022b49b6b1ddb5238d5af3a75e81c8";

    private static final String OPENWEATHERMAP_ADDRESS = "https://api.openweathermap.org/data/2.5/";
    private static final String IPAPI_ADDRESS = "https://ipapi.co/";
    private static final String MYIP_ADDRESS = "https://api.myip.com";

    private static final String LOCALHOST_IP_V4 = "127.0.0.1";
    private static final String LOCALHOST_IP_V6 = "0:0:0:0:0:0:0:1";


    @Override
    public OpenWeatherMap getData(HttpServletRequest request) {

        String ip = getIpAddress(request);
        String city = getCity(ip);
        OpenWeatherMap openWeatherMap = getWeatherForCity(city);
        return openWeatherMap;
    }


    private String getIpAddress(HttpServletRequest request)
    {
        String ip = request.getRemoteAddr();
        if(ip.equals(LOCALHOST_IP_V4) || ip.equals(LOCALHOST_IP_V6))
        {
            RestTemplate restTemplate = new RestTemplate();
            String result = restTemplate.getForObject(MYIP_ADDRESS, String.class);
            ObjectMapper mapper = new ObjectMapper();
            MyIP myIP = new MyIP();
            try {
                myIP = mapper.readValue(result, MyIP.class);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            ip = myIP.getIp();
        }
        return ip;
    }


    private String getCity(String ip)
    {
        RestTemplate restTemplate = new RestTemplate();
        String city = restTemplate.getForObject(IPAPI_ADDRESS + ip + "/city", String.class);
        return city;
    }

    private OpenWeatherMap getWeatherForCity(String city){
        StringBuilder url = new StringBuilder();
        url.append(OPENWEATHERMAP_ADDRESS).append("weather?q=").append("city").append("&units=metric").append("&appid=").append(OPENWEATHERMAP_API_KEY);
        RestTemplate restTemplate = new RestTemplate();
        OpenWeatherMap openWeatherMap = new OpenWeatherMap();

        String response = restTemplate.getForObject(url.toString(), String.class);
        ObjectMapper mapper = new ObjectMapper();
        try {
            openWeatherMap = mapper.readValue(response, OpenWeatherMap.class);
        } catch (JsonProcessingException | HttpClientErrorException e) {
            return null;
        }


        return openWeatherMap;

    }

}
