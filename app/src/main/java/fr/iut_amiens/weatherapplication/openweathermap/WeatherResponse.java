package fr.iut_amiens.weatherapplication.openweathermap;

import android.net.Uri;

import com.google.gson.annotations.SerializedName;

import org.joda.time.DateTime;

import java.util.List;

public class WeatherResponse {

    /**
     * City geo location
     */
    private Coord coord;

    /**
     * Weather conditions
     */
    private List<Weather> weather;

    /**
     * Main information
     */
    private Main main;

    /**
     * Wind information
     */
    private Wind wind;

    /**
     * Clouds information
     */
    private Clouds clouds;

    /**
     * Rain information
     */
    private Rain rain;

    /**
     * Snow information
     */
    private Snow snow;

    /**
     * Time of data calculation, unix, UTC
     */
    private long dt;

    private Sys sys;

    /**
     * City ID
     */
    private long id;

    /**
     * City name
     */
    private String name;

    public WeatherResponse() {
    }

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
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

    public Rain getRain() {
        return rain;
    }

    public void setRain(Rain rain) {
        this.rain = rain;
    }

    public Snow getSnow() {
        return snow;
    }

    public void setSnow(Snow snow) {
        this.snow = snow;
    }

    public long getDt() {
        return dt;
    }

    public void setDt(long dt) {
        this.dt = dt;
    }

    public DateTime getDatetime() {
        return new DateTime(dt * 1000L);
    }

    public Sys getSys() {
        return sys;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
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

    @Override
    public String toString() {
        return "WeatherResponse{" +
                "coord=" + coord +
                ", weather=" + weather +
                ", main=" + main +
                ", wind=" + wind +
                ", clouds=" + clouds +
                ", rain=" + rain +
                ", snow=" + snow +
                ", dt=" + getDatetime() +
                ", sys=" + sys +
                ", id=" + id +
                ", name='" + name + '\'' +
                '}';
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

    public static class Rain {

        /**
         * Rain volume for the last 3 hours
         */
        @SerializedName("3h")
        private int threeHours;

        public Rain() {
        }

        public int getThreeHours() {
            return threeHours;
        }

        public void setThreeHours(int threeHours) {
            this.threeHours = threeHours;
        }

        @Override
        public String toString() {
            return "Rain{" +
                    "threeHours=" + threeHours +
                    '}';
        }
    }

    public static class Snow {

        /**
         * Snow volume for the last 3 hours
         */
        @SerializedName("3h")
        private int threeHours;

        public Snow() {
        }

        public int getThreeHours() {
            return threeHours;
        }

        public void setThreeHours(int threeHours) {
            this.threeHours = threeHours;
        }

        @Override
        public String toString() {
            return "Snow{" +
                    "threeHours=" + threeHours +
                    '}';
        }
    }

    public static class Sys {

        /**
         * Country code (GB, JP etc.)
         */
        private String country;

        /**
         * Sunrise time, unix, UTC
         */
        private long sunrise;

        /**
         * Sunset time, unix, UTC
         */
        private long sunset;

        public Sys() {
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public long getSunrise() {
            return sunrise;
        }

        public DateTime getSunriseDateTime() {
            return new DateTime(sunrise * 1000L);
        }

        public void setSunrise(long sunrise) {
            this.sunrise = sunrise;
        }

        public long getSunset() {
            return sunset;
        }

        public DateTime getSunsetDateTime() {
            return new DateTime(sunset * 1000L);
        }

        public void setSunset(long sunset) {
            this.sunset = sunset;
        }

        @Override
        public String toString() {
            return "Sys{" +
                    "country='" + country + '\'' +
                    ", sunrise=" + getSunriseDateTime() +
                    ", sunset=" + getSunsetDateTime() +
                    '}';
        }
    }
}
