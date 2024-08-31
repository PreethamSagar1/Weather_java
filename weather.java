import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class WeatherApp {
    private static final String API_KEY = "7910e8948c3bd9c3edb144ea0c847893";
    private static final String BASE_URL = "http://api.openweathermap.org/data/2.5/weather?q=";

    public static void main(String[] args) {
        String cityName = "Hyderabad"; // Updated city name
        String urlString = BASE_URL + cityName + "&appid=" + API_KEY + "&units=metric";
        
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();
            
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            
            in.close();
            connection.disconnect();
            
            // Parse JSON response
            JSONObject jsonResponse = new JSONObject(content.toString());
            JSONObject main = jsonResponse.getJSONObject("main");
            double temperature = main.getDouble("temp");
            int humidity = main.getInt("humidity");
            
            JSONObject weather = jsonResponse.getJSONArray("weather").getJSONObject(0);
            String weatherDescription = weather.getString("description");
            
            System.out.println("City: " + cityName);
            System.out.println("Temperature: " + temperature + "°C");
            System.out.println("Humidity: " + humidity + "%");
            System.out.println("Weather: " + weatherDescription);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}