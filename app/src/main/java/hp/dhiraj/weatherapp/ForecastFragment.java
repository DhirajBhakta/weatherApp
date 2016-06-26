package hp.dhiraj.weatherapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.NetworkOnMainThreadException;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by root on 26/6/16.
 */
public class ForecastFragment extends Fragment {

    ArrayAdapter<String> arrayAdapter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
      inflater.inflate(R.menu.main,menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if(id == R.id.action_refresh)
        {
            new FetchWeatherTask().execute("hi");
        }

        return super.onOptionsItemSelected(item);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {

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


class FetchWeatherTask extends AsyncTask
{
    public final String LOG_TAG = FetchWeatherTask.class.getSimpleName();

    @Override
    protected Object doInBackground(Object[] params) {


        // These two need to be declared outside the try/catch
        // so that they can be closed in the finally block.
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        // Will contain the raw JSON response as a string.
        String forecastJsonStr = null;

        try {
            // Construct the URL for the OpenWeatherMap query
            // Possible parameters are avaiable at OWM's forecast API page, at
            // http://openweathermap.org/API#forecast
            URL url = new URL("http://api.openweathermap.org/data/2.5/forecast/daily?q=90210&mode=json&units=metric&cnt=7&APPID=8780b95cdbab0e8fb5fd63c28041ba49");

            // Create the request to OpenWeatherMap, and open the connection
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // Read the input stream into a String
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                // Nothing to do.
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                // But it does make debugging a *lot* easier if you print out the completed
                // buffer for debugging.
                buffer.append(line + "\n");
            }

            if (buffer.length() == 0) {
                // Stream was empty.  No point in parsing.
                return null;
            }
            forecastJsonStr = buffer.toString();
            Log.e(LOG_TAG,forecastJsonStr);
        } catch (IOException e) {
            Log.e(LOG_TAG, "error", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e(LOG_TAG, "Error closing stream", e);
                }
            }
        }
     return null;
    }


}
