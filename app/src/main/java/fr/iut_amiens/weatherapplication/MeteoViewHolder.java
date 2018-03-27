package fr.iut_amiens.weatherapplication;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import fr.iut_amiens.weatherapplication.openweathermap.ForecastResponse;

/***
 * Created by omer on 27/03/18.
 */
public class MeteoViewHolder extends RecyclerView.ViewHolder {

    private ForecastResponse.Forecast forcast;

    private TextView date;
    private TextView temps;
    private TextView temps_description;
    private TextView temperature;
    private ImageView imageView;

    public MeteoViewHolder(View itemView) {
        super(itemView);

        date = itemView.findViewById(R.id.date);
        temps = itemView.findViewById(R.id.temps);
        temps_description = itemView.findViewById(R.id.tempsDescription);
        temperature = itemView.findViewById(R.id.temperature);
        imageView = itemView.findViewById(R.id.imageView);
    }

    /***
     * Modifie la vue
     * @param forecast : donnée
     */
    public void bind(ForecastResponse.Forecast forecast) {
        this.forcast = forecast;
        ForecastResponse.Weather weather = forecast.getWeather().get(0);

        setText(date, forecast.getDatetime().toString());
        setText(temps, weather.getMain());
        setText(temps_description, weather.getDescription());
        setText(temperature, forecast.getMain().getTemp() + " C°");

        //Chargement de l'image
        Picasso.with(itemView.getContext()).load(weather.getIcon()).into(imageView);
        Log.d("MeteoViewHolder", forecast.getWeather().toString());
    }

    /***
     * Set value on textView and check null value
     * @param champs
     * @param value
     */
    public void setText(TextView champs, String value) {
        try {
            champs.setText(value);
        } catch (Exception exception) {
            Log.e("Champs", exception.getMessage());
        }
    }
}
