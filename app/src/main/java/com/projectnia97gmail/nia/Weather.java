package com.projectnia97gmail.nia;

import android.os.AsyncTask;
import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.util.Locale;

import static com.projectnia97gmail.nia.MainActivity.txtchat;
import static com.projectnia97gmail.nia.MainActivity.txtuser;

public class Weather {

    private static final String OPEN_WEATHER_MAP_URL =
            "http://api.openweathermap.org/data/2.5/weather?lat=%s&lon=%s&units=metric";
    private static final String OPEN_WEATHER_MAP_API ="80716b91c8706a2dcd22db204dc63ca8";


    Weather(double lattitude,double longitude){
        String lat=Double.toString(lattitude);
        String lon=Double.toString(longitude);
       new placeIdTask().execute(lat,lon);
    }
  /*  public interface AsyncResponse{

        void processFinish(String output1, String output2, String output3, String output4, String output5, String output6, String output7, String output8);
    } */


    public static class placeIdTask extends AsyncTask<String, Void, JSONObject> {
      //  public AsyncResponse delegate = null;//Call back interface

      /*  public placeIdTask(AsyncResponse asyncResponse) {
            delegate = asyncResponse;//Assigning call back interfacethrough constructor
        } */

        @Override
        protected JSONObject doInBackground(String... params) {
            JSONObject jsonWeather = null;
            try {
                jsonWeather = getWeatherJSON(params[0], params[1]);
            } catch (Exception e) {
                Log.d("Error", "Cannot process JSON results", e);
            }


            return jsonWeather;

        }

        @Override
        protected void onPostExecute(JSONObject json) {
            try {
                if(json != null) {
                    JSONObject details = json.getJSONArray("weather").getJSONObject(0);
                    JSONObject main = json.getJSONObject("main");
                    DateFormat df = DateFormat.getDateTimeInstance();
                    String city = json.getString("name");
                    String description = details.getString("description").toUpperCase(Locale.US);
                    String temperature = String.format("%.2f", main.getDouble("temp"))+ "°C";
                    String humidity = main.getString("humidity") + "%";
                    txtchat.append(" \n Today's weather detailes are: \n"+"Temperature: "+temperature+" "+description+"\n Humidity: "+humidity +"\n");
                }
            } catch (JSONException e) {
              //  Log.e(LOG_TAG, "Cannot process JSON results", e);
            }


            super.onPostExecute(json);
        }
    }
    public static JSONObject getWeatherJSON(String lat, String lon){
        try {
            URL url = new URL(String.format(OPEN_WEATHER_MAP_URL, lat, lon));
            HttpURLConnection connection =
                    (HttpURLConnection)url.openConnection();

            connection.addRequestProperty("x-api-key", OPEN_WEATHER_MAP_API);

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));

            StringBuffer json = new StringBuffer(1024);
            String tmp="";
            while((tmp=reader.readLine())!=null)
                json.append(tmp).append("\n");
            reader.close();

            JSONObject data = new JSONObject(json.toString());

            // This value will be 404 if the request was not
            // successful
            if(data.getInt("cod") != 200){
                return null;
            }

            return data;
        }catch(Exception e){
            return null;
        }
    }
}
