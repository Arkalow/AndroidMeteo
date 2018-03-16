package fr.iut_amiens.weatherapplication.openweathermap;

import android.net.Uri;

import com.google.gson.annotations.SerializedName;

import org.joda.time.DateTime;

import java.util.List;

public class ForecastResponse {

    private City city;

    /**
     * Number of lines returned by this API call
     */
    private int cnt;

    private List<Forecast> list;

    public ForecastResponse() {
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public List<Forecast> getList() {
        return list;
    }

    public void setList(List<Forecast> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "ForecastResponse{" +
                "city=" + city +
                ", cnt=" + cnt +
                ", list=" + list +
                '}';
    }

    public static class City {

        /**
         * City ID
         */
        private long id;

        /**
         * City name
         */
        private String name;

        /**
         * City geo location
         */
        private Coord coord;

        /**
         * Country code (GB, JP etc.)
         */
        private String country;

        public City() {
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Coord getCoord() {
            return coord;
        }

        public void setCoord(Coord coord) {
            this.coord = coord;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        @Override
        public String toString() {
            return "City{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", coord=" + coord +
                    ", country='" + country + '\'' +
                    '}';
        }
    }

    public static class Coord {

        /**
         * City geo location, longitude
         */
        private double lon;

        /**
         * City geo location, latitude
         */
        private double lat;

        public Coord() {
        }

        public double getLon() {
            return lon;
        }

        public void setLon(double lon) {
            this.lon = lon;
        }

        public double getLat() {
            return lat;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }

        @Override
        public String toString() {
            return "Coord{" +
                    "lon=" + lon +
                    ", lat=" + lat +
                    '}';
        }
    }

    public static class Forecast {

        /**
         * Main information
         */
        private Main main;

        private List<Weather> weather;

        /**
         * Wind information
         */
        private Wind wind;

        /**
         * Clouds information
         */
        private Clouds clouds;

        /**
         * Time of data calculation, unix, UTC
         */
        private long dt;

        public Forecast() {
        }

        public Main getMain() {
            return main;
        }

        public void setMain(Main main) {
            this.main = main;
        }

        public List<Weather> getWeather() {
            return weather;
        }

        public void setWeather(List<Weather> weather) {
            this.weather = weather;
        }

        public Wind getWind() {
            return wind;
        }

        public void setWind(Wind wind) {
            this.wind = wind;
        }

        public Clouds getClouds() {
            return clouds;
        }

        public void setClouds(Clouds clouds) {
            this.clouds = clouds;
        }

        public long getDt() {
            return dt;
        }

        public DateTime getDatetime() {
            return new DateTime(dt * 1000L);
        }

        public void setDt(long dt) {
            this.dt = dt;
        }

        @Override
        public String toString() {
            return "Forecast{" +
                    "main=" + main +
                    ", weather=" + weather +
                    ", wind=" + wind +
                    ", clouds=" + clouds +
                    ", dt=" + getDatetime() +
                    '}';
        }
    }

    public static class Weather {

        /**
         * Weather condition id
         */
        private int id;

        /**
         * Group of weather parameters (Rain, Snow, Extreme etc.)
         */
        private String main;

        /**
         * Weather condition within the group
         */
        private String description;

        /**
         * Weather icon id
         */
        private String icon;

        public Weather() {
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getMain() {
            return main;
        }

        public void setMain(String main) {
            this.main = main;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public Uri getIconUri() {
            return Uri.parse(String.format("http://openweathermap.org/img/w/%s.png", icon));
        }

        @Override
        public String toString() {
            return "Weather{" +
                    "id=" + id +
                    ", main='" + main + '\'' +
                    ", description='" + description + '\'' +
                    ", icon='" + getIconUri() + '\'' +
                    '}';
        }
    }

    public static class Main {

        /**
         * Temperature. Unit Default: Kelvin, Metric: Celsius, Imperial: Fahrenheit.
         */
        private double temp;

        /**
         * Atmospheric pressure (on the sea level, if there is no sea_level or grnd_level data), hPa
         */
        private double pressure;

        /**
         * Humidity, %
         */
        private int humidity;

        /**
         * Minimum temperature at the moment. This is deviation from current temp that is possible for large cities and megalopolises geographically expanded (use these parameter optionally). Unit Default: Kelvin, Metric: Celsius, Imperial: Fahrenheit.
         */
        @SerializedName("temp_min")
        private double tempMin;

        /**
         * Maximum temperature at the moment. This is deviation from current temp that is possible for large cities and megalopolises geographically expanded (use these parameter optionally). Unit Default: Kelvin, Metric: Celsius, Imperial: Fahrenheit.
         */
        @SerializedName("temp_max")
        private double tempMax;

        /**
         * Atmospheric pressure on the sea level, hPa
         */
        @SerializedName("sea_level")
        private double seaLevel;

        /**
         * Atmospheric pressure on the ground level, hPa
         */
        @SerializedName("grnd_level")
        private double groundLevel;

        public Main() {
        }

        public double getTemp() {
            return temp;
        }

        public void setTemp(double temp) {
            this.temp = temp;
        }

        public double getPressure() {
            return pressure;
        }

        public void setPressure(double pressure) {
            this.pressure = pressure;
        }

        public int getHumidity() {
            return humidity;
        }

        public void setHumidity(int humidity) {
            this.humidity = humidity;
        }

        public double getTempMin() {
            return tempMin;
        }

        public void setTempMin(double tempMin) {
            this.tempMin = tempMin;
        }

        public double getTempMax() {
            return tempMax;
        }

        public void setTempMax(double tempMax) {
            this.tempMax = tempMax;
        }

        public double getSeaLevel() {
            return seaLevel;
        }

        public void setSeaLevel(double seaLevel) {
            this.seaLevel = seaLevel;
        }

        public double getGroundLevel() {
            return groundLevel;
        }

        public void setGroundLevel(double groundLevel) {
            this.groundLevel = groundLevel;
        }

        @Override
        public String toString() {
            return "Main{" +
                    "temp=" + temp +
                    ", pressure=" + pressure +
                    ", humidity=" + humidity +
                    ", tempMin=" + tempMin +
                    ", tempMax=" + tempMax +
                    ", seaLevel=" + seaLevel +
                    ", groundLevel=" + groundLevel +
                    '}';
        }
    }

    public static class Wind {

        /**
         * Wind speed. Unit Default: meter/sec, Metric: meter/sec, Imperial: miles/hour.
         */
        private double speed;

        /**
         * Wind direction, degrees (meteorological)
         */
        private double deg;

        public Wind() {
        }

        public double getSpeed() {
            return speed;
        }

        public void setSpeed(double speed) {
            this.speed = speed;
        }

        public double getDeg() {
            return deg;
        }

        public void setDeg(double deg) {
            this.deg = deg;
        }

        @Override
        public String toString() {
            return "Wind{" +
                    "speed=" + speed +
                    ", deg=" + deg +
                    '}';
        }
    }

    public static class Clouds {

        /**
         * Cloudiness, %
         */
        private int all;

        public Clouds() {
        }

        public int getAll() {
            return all;
        }

        public void setAll(int all) {
            this.all = all;
        }

        @Override
        public String toString() {
            return "Clouds{" +
                    "all=" + all +
                    '}';
        }
    }
}
