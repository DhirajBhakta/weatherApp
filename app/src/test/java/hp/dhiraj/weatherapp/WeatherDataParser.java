package hp.dhiraj.weatherapp;

import org.json.*;

/**
 * Created by root on 28/6/16.
 */
public class WeatherDataParser {


    /**
     * Given a string of the form returned by the api call:
     * http://api.openweathermap.org/data/2.5/forecast/daily?q=94043&mode=json&units=metric&cnt=7
     * retrieve the maximum temperature for the day indicated by dayIndex
     * (Note: 0-indexed, so 0 would refer to the first day).
     */
    public static double getMaxTemperatureForDay(String weatherJsonStr, int dayIndex)
            throws JSONException {
       JSONObject weather = new JSONObject(weatherJsonStr);
       JSONArray days = weather.getJSONArray("list");
       JSONObject day = days.getJSONObject(dayIndex);
       JSONObject temperatureInfo = day.getJSONObject("temp");
        return  temperatureInfo.getDouble("max");


    }


}
