package fr.iut_amiens.weatherapplication.openweathermap;

import java.io.IOException;

import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherManager {

    private WeatherClient client;

    public WeatherManager() {
        client = new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(WeatherClient.class);
    }

    /**
     * Get current weather by city name
     *
     * @param cityName Name of the city you're looking for
     * @return weather current condition
     * @throws IOException if network error
     */
    public WeatherResponse findWeatherByCityName(String cityName) throws IOException {
        Response<WeatherResponse> response = client.findWeatherByCityName(cityName).execute();

        if (response.code() != 200) {
            throw new IOException("Unexpected response " + response.code() + ": " + response.message());
        }

        return response.body();
    }

    /**
     * Get forecast weather by city name
     *
     * @param cityName Name of the city you're looking for
     * @return weather forecast condition
     * @throws IOException if network error
     */
    public ForecastResponse findForecastByCityName(String cityName) throws IOException {
        Response<ForecastResponse> response = client.findForecastByCityName(cityName).execute();

        if (response.code() != 200) {
            throw new IOException("Unexpected response " + response.code() + ": " + response.message());
        }

        return response.body();
    }

    /**
     * Get current weather by city location
     *
     * @param latitude  Latitude of the city you're looking for
     * @param longitude Longitude of the city you're looking for
     * @return weather current condition
     * @throws IOException if network error
     */
    public WeatherResponse findWeatherByGeographicCoordinates(double latitude, double longitude) throws IOException {
        Response<WeatherResponse> response = client.findWeatherByGeographicCoordinates(latitude, longitude).execute();

        if (response.code() != 200) {
            throw new IOException("Unexpected response " + response.code() + ": " + response.message());
        }

        return response.body();
    }

    /**
     * Get forecast weather by city location
     *
     * @param latitude  Latitude of the city you're looking for
     * @param longitude Longitude of the city you're looking for
     * @return weather forecast condition
     * @throws IOException if network error
     */
    public ForecastResponse findForecastByGeographicCoordinates(double latitude, double longitude) throws IOException {
        Response<ForecastResponse> response = client.findForecastByGeographicCoordinates(latitude, longitude).execute();

        if (response.code() != 200) {
            throw new IOException("Unexpected response " + response.code() + ": " + response.message());
        }

        return response.body();
    }
}
