package fr.iut_amiens.weatherapplication;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

/***
 * Created by omer on 27/03/18.
 */
public class MeteoViewHolder extends RecyclerView.ViewHolder {

    private TextView textView;
    private String data;

    public MeteoViewHolder(View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.item);
    }

    /***
     * Modifie la vue
     * @param data : donnée
     */
    public void bind(String data) {
        this.data = data;
        textView.setText(data);
        Log.d("MeteoViewHolder", textView.getText().toString());
    }

    public void recycle() {
        textView.setText("");
    }
}
