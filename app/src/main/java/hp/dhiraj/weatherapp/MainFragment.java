package hp.dhiraj.weatherapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by root on 26/6/16.
 */
public class MainFragment extends Fragment {

    ArrayAdapter<String> arrayAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.forecast_listview_fragment,container,false);

        String[] forecastArray = {
                "Today - Sunny -88/63",
                "Tomorrow - Rainy - 77/90",
                "Wednesday - Rainy - 88/09",
                "Thursday - Windy - 8/90",
                "Friday - Cloudy - 9/00",
                "Today - Sunny -88/63",
                "Tomorrow - Rainy - 77/90",
                "Wednesday - Rainy - 88/09",
                "Thursday - Windy - 8/90",
                "Friday - Cloudy - 9/00",
                "Today - Sunny -88/63",
                "Tomorrow - Rainy - 77/90",
                "Wednesday - Rainy - 88/09",
                "Thursday - Windy - 8/90",
                "Friday - Cloudy - 9/00"
        };

        List<String> forecastList=new ArrayList<String>(Arrays.asList(forecastArray));

        arrayAdapter = new ArrayAdapter<String>(getContext(),R.layout.textview_for_listview,R.id.textview,forecastList);

       ListView LV = (ListView)v.findViewById(R.id.forecastListView);
       LV.setAdapter(arrayAdapter);

        return v;
    }
}
