package fr.iut_amiens.weatherapplication;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import fr.iut_amiens.weatherapplication.openweathermap.ForecastResponse;

/***
 * Created by omer on 27/03/18.
 */
public class MeteoViewHolder extends RecyclerView.ViewHolder {

    private TextView textView;
    private ForecastResponse.Forecast forcast;

    public MeteoViewHolder(View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.item);
    }

    /***
     * Modifie la vue
     * @param forecast : donnée
     */
    public void bind(ForecastResponse.Forecast forecast) {
        this.forcast = forecast;
        textView.setText(forecast.toString());
        Log.d("MeteoViewHolder", textView.getText().toString());
    }

    public void recycle() {
        textView.setText("");
    }
}
