package fr.iut_amiens.weatherapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class ListMeteoActivity extends AppCompatActivity {

    private MeteoAdapter nameAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_meteo);

        /***
         * nameAdapter : contient la liste des notes
         */
        RecyclerView recyclerView; //List of item
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new MeteoAdapter(this));
        nameAdapter = ((MeteoAdapter) recyclerView.getAdapter());

    }
}
