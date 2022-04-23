package rushi.bhandari.n01464259;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DownloadFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DownloadFragment extends Fragment {

    View view = null;
    ProgressBar progressBar;
    ImageView imageView;
    ProgressDialog p;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DownloadFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment DownloadFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DownloadFragment newInstance() {
        DownloadFragment fragment = new DownloadFragment();
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
        View view = inflater.inflate(R.layout.fragment_download, container, false);

        //Creating listview options
        ListView listView = view.findViewById(R.id.rushiDownloadFragmentIdListView);

        imageView = view.findViewById(R.id.rushiDownloadFragmentIdImageViewDisplay);

        //Hiding progress bar
        progressBar = view.findViewById(R.id.rushiDownloadFragmentIdProgressBar);
        progressBar.setVisibility(View.INVISIBLE);

        //Creating options
        ArrayList<String> ImageOptions = new ArrayList<String>();
        ImageOptions.add("Genius");
        ImageOptions.add("Technology");
        ImageOptions.add("Motivational Quotes");

        //Setting array adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), R.layout.list_view,ImageOptions);
        listView.setAdapter(adapter);

        //OnClick event handler
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedItem = (String) adapterView.getItemAtPosition(i);
                if(i == 0){
                    AsyncTaskExample asyncTaskExample = new AsyncTaskExample();
                    asyncTaskExample.execute("https://pepnewz.com/wp-content/uploads/Quiz-WHich-Type-of-Creative-Genius-Are-You_FB.png");

                }else if(i == 1){
                    AsyncTaskExample asyncTaskExample = new AsyncTaskExample();
                    asyncTaskExample.execute("https://wallup.net/wp-content/uploads/2019/09/456778-computer-engineering-science-tech.jpg");

                }else if(i ==2){
                    AsyncTaskExample asyncTaskExample = new AsyncTaskExample();
                    asyncTaskExample.execute("https://th.bing.com/th/id/OIP.MFOuZDMwALLbREUdt8fofAHaD8?pid=ImgDet&rs=1");
                }
            }
        });
        return view;
    }
    //AsynTaskExample class to show image from internet
    private class AsyncTaskExample extends AsyncTask<String,String,Bitmap> {

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }
        @Override
        protected Bitmap doInBackground(String... strings) {
            Bitmap bitmapImage = null;

            try {
                Thread.sleep(5000);
                URL ImageUrl = new URL(strings[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) ImageUrl.openConnection();

                int responseCode = urlConnection.getResponseCode();
                if(responseCode == HttpURLConnection.HTTP_OK){
                    InputStream is = urlConnection.getInputStream();

                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inPreferredConfig = Bitmap.Config.RGB_565;
                    bitmapImage = BitmapFactory.decodeStream(is, null,options);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }


            return bitmapImage;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap){
            super.onPostExecute(bitmap);
            if(imageView!= null){
                progressBar.setVisibility(View.INVISIBLE);
                imageView.setImageBitmap(bitmap);
            }else {
                p.show();
            }

        }
    }

}