package fr.iut_amiens.weatherapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import fr.iut_amiens.weatherapplication.openweathermap.ForecastResponse;

public class ListMeteoActivity extends AppCompatActivity implements GetForecastResponseListener{

    private MeteoAdapter nameAdapter;
    private String city = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_meteo);

        /***
         * nameAdapter : contient la liste des notes
         */


        GetForecastResponseTask getForecastResponseTask = new GetForecastResponseTask("Amiens");
        getForecastResponseTask.addListener(this);
        getForecastResponseTask.execute();

    }

    @Override
    public void getForecast(ForecastResponse forecastResponse) {
        RecyclerView recyclerView; //List of item
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new MeteoAdapter(this));
        nameAdapter = ((MeteoAdapter) recyclerView.getAdapter());
    }
}
