package rushi.bhandari.n01464259;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.RequiresPermission;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class WeatherFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public WeatherFragment() {
        // Required empty public constructor
    }

    public static WeatherFragment newInstance() {
        WeatherFragment fragment = new WeatherFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_weather, container, false);

        //Creating lag and lat strings


        //Creating array of spinner options
        String[] spinnerOptions = {"Delhi","Toronto","London","New York","Capetown"};
        //Creating spinner
        Spinner spinner = view.findViewById(R.id.rushiWeatherFragmentIdSpinner);

        //Array adapter
        ArrayAdapter adapter = new ArrayAdapter(getContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,spinnerOptions);

        adapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        //Setting on click listener
        TextView spinneritem = view.findViewById(R.id.rushiWeatherFragmentIdWeatherContentDisplay);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String url =" https://api.openweathermap.org/data/2.5/weather?";
                String selectedItem = (String) adapterView.getItemAtPosition(i);
                String longitude;
                String latitude;
                if(i == 0){
                    longitude = "77.216721";
                    latitude = "28.644800";
                    url +="lat="+latitude;
                    url +="&lon="+longitude;
                    url+="&appid=6a5f6343ef93c37eb8ace299542b4fe0";
                    new ReadWeatherData().execute(url);


                }
                else if(i == 2){
                    longitude = "-79.347015";
                    latitude = "43.651070";
                    url +="lat="+latitude;
                    url +="&lon="+longitude;
                    url+="&appid=6a5f6343ef93c37eb8ace299542b4fe0";
                    Log.d("URL",url);
                    new ReadWeatherData().execute(url);

                }else if(i == 3){
                    longitude = "-0.118092";
                    latitude = "51.509865";
                    url +="lat="+latitude;
                    url +="&lon="+longitude;
                    url+="&appid=6a5f6343ef93c37eb8ace299542b4fe0";
                    new ReadWeatherData().execute(url);
                }else if(i == 4){
                    longitude = "-73.935242";
                    latitude = "40.730610";
                    url +="lat="+latitude;
                    url +="&lon="+longitude;
                    url+="&appid=6a5f6343ef93c37eb8ace299542b4fe0";
                    Log.d("URL",url);
                    new ReadWeatherData().execute(url);
                }else if(i == 5){
                    longitude = "18.459778";
                    latitude = "-34.270836";
                    url +="lat="+latitude;
                    url +="&lon="+longitude;
                    url+="&appid=6a5f6343ef93c37eb8ace299542b4fe0";
                    Log.d("URL",url);
                    new ReadWeatherData().execute(url);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }

        });






        return view;
    }
    private class ReadWeatherData extends AsyncTask<String,Void,String>{
        TextView txtDisplayWeather= getView().findViewById(R.id.rushiWeatherFragmentIdWeatherContentDisplay);
        @Override
        protected String doInBackground(String... urls) {
            URL url = null;
            HttpURLConnection httpURLConnection = null;
            StringBuilder bufferReader = null;
            try {
                url = new URL(urls[0]);
                bufferReader = new StringBuilder();
                httpURLConnection = (HttpURLConnection) url.openConnection();
                if(httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK){
                    InputStream content = new BufferedInputStream(httpURLConnection.getInputStream());
                    BufferedReader reader = new BufferedReader(new InputStreamReader(content));
                    String line;
                    while ((line = reader.readLine()) !=null){
                        bufferReader.append(line);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return bufferReader.toString();
        }
        @Override
        protected void onPostExecute(String result) {
            try {
                //JSONArray jsonArray = new JSONArray(result);
                //Uncomment the two rows below to parse weather data from OpenWeatherMap
                JSONObject weatherJson = new JSONObject(result);
                JSONArray dataArray1 = weatherJson.getJSONArray("weather");
                String strResults = "Weather\n";
                for (int i = 0; i < dataArray1.length(); i++) {
                    JSONObject jsonObject = dataArray1.getJSONObject(i);
                    strResults += "id: " + jsonObject.getString("id");
                    strResults += "\nmain: " + jsonObject.getString("main");
                    strResults += "\ndescription: " + jsonObject.getString("description");
                }
                //
                JSONObject dataObject = weatherJson.getJSONObject("main");
                strResults += "\ntemp: " + dataObject.getString("temp");
                strResults += "\nhumidity: " + dataObject.getString("humidity");
                strResults += "\ntemp_min: " + dataObject.getString("temp_min");
                strResults += "\ntemp_max: " + dataObject.getString("temp_max");
                //strResults+= "\nCountry: "+ dataObject.getString("country");

                txtDisplayWeather.setText(strResults);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}