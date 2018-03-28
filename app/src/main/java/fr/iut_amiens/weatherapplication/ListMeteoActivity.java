package fr.iut_amiens.weatherapplication;

import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import java.util.List;

import fr.iut_amiens.weatherapplication.openweathermap.ForecastResponse;

public class ListMeteoActivity extends AppCompatActivity implements GetForecastResponseListener{

    private MeteoAdapter nameAdapter;
    private String city = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_meteo);

        /***
         * Récupération du nom de la ville
         */
        Bundle b = getIntent().getExtras();
        if (null != b) {
            city = b.getString("city");
            this.setTitle(city);
        }

        /***
         * Lancement de l'AsyncTask
         */
        GetForecastResponseTask getForecastResponseTask = new GetForecastResponseTask(city);
        getForecastResponseTask.addListener(this);
        getForecastResponseTask.execute();

    }

    /**
     * Fonction du listener GetForcastResponseListener appelé automatiquement par GetForcastResponseTask
     *
     * @param forecastResponse
     */
    @Override
    public void getForecast(ForecastResponse forecastResponse) {

        List<ForecastResponse.Forecast> forecasts = forecastResponse.getList();

        /***
         * nameAdapter : contient la liste des notes
         */
        RecyclerView recyclerView; //List of item
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new MeteoAdapter(this, forecasts));
        nameAdapter = ((MeteoAdapter) recyclerView.getAdapter());

        Log.d("ListMeteoActivity", forecastResponse.getCity().toString());
    }

    /***
     * Création du menu
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.back_menu, menu);

        return true;
    }

    /***
     * Selection d'un bouton du menu
     * @param item Selected item
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.back){
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        super.onPause();
        this.finish();
    }
}
