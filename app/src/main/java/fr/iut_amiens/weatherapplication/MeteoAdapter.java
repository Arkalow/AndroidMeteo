package fr.iut_amiens.weatherapplication;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fr.iut_amiens.weatherapplication.openweathermap.ForecastResponse;

/***
 * Created by omer on 27/03/18.
 */
public class MeteoAdapter extends RecyclerView.Adapter<MeteoViewHolder> {

    private List<ForecastResponse.Forecast> forecasts;
    private final LayoutInflater layoutInflater;

    /***
     * Constructeur de NameAdapter
     */
    public MeteoAdapter(Context context, List<ForecastResponse.Forecast> forecasts) {
        layoutInflater = LayoutInflater.from(context);

        this.forecasts = forecasts;

        for (ForecastResponse.Forecast forecast : forecasts){
            notifyItemInserted(getItemCount());
        }
    }

    @Override
    public MeteoViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Log.d("NameAdapter", "onCreateViewHolder");
        View view = layoutInflater.inflate(R.layout.item_meteo, viewGroup, false);
        return new MeteoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MeteoViewHolder viewHolder, int position) {
        Log.d("MeteoAdapter", "onBindViewHolder");

        //On l'envoi au viewholder pour qu'il l'affiche
        viewHolder.bind(forecasts.get(position));
    }

    @Override
    public int getItemCount() {
        return forecasts.size();
    }

}