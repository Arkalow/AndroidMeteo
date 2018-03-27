package fr.iut_amiens.weatherapplication;

import java.util.List;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/***
 * Created by omer on 27/03/18.
 */
public class MeteoAdapter extends RecyclerView.Adapter<MeteoViewHolder> {

    private List<String> titles;
    private final LayoutInflater layoutInflater;

    /***
     * Constructeur de NameAdapter
     */
    public MeteoAdapter(Context context) {
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public MeteoViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Log.d("NameAdapter", "onCreateViewHolder");
        View view = layoutInflater.inflate(R.layout.list_meteo, viewGroup, false);
        return new MeteoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MeteoViewHolder myViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return titles.size();
    }

}