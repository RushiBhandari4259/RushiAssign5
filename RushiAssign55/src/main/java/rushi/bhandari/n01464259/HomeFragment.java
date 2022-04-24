//Rushi Bhandari N01464259 RNB
package rushi.bhandari.n01464259;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextClock;
import android.widget.TextView;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
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
        View view = inflater.inflate(R.layout.fragment_home, container, false);


        Date c = Calendar.getInstance().getTime();
        //Displaying date
        TextView dateDisplay= view.findViewById(R.id.rushiHomeFramentIdDateShow);
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
        String formattedDate = df.format(c);
        dateDisplay.setText(formattedDate);

        //Displaying time
        TextClock timeDisplay = view.findViewById(R.id.rushiHomeFramentIdTimeDisplay);
        SimpleDateFormat tf = new SimpleDateFormat("HH:mm:ss",Locale.getDefault());
        String formattedTime = tf.format(c);

        //Files
        String filename = "rushi.txt";
        Button saveButton = view.findViewById(R.id.rushiHomeFramentIdSaveButton);
        EditText userInput = view.findViewById(R.id.rushiHomeFramentIdEditText);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    FileOutputStream fos = getActivity().openFileOutput(filename, Context.MODE_APPEND);
                    String data = userInput.getText().toString();
                    fos.write(data.getBytes());
                    fos.flush();
                    fos.close();



                } catch (IOException e) {
                    e.printStackTrace();
                }
                userInput.setText("");
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        //Changing Clock Format
        SharedPreferences dateFormatSharePref = getActivity().getPreferences(Context.MODE_PRIVATE);
        int formatCode = dateFormatSharePref.getInt("Format", 200);
        Date c = Calendar.getInstance().getTime();
        TextClock timeDisplay = view.findViewById(R.id.rushiHomeFramentIdTimeDisplay);
        if(formatCode == 1){

        }else if (formatCode ==2 ){

        }

        //changing Background color
        SharedPreferences sharedpref = getActivity().getPreferences(Context.MODE_PRIVATE);
        int BackgroundColor = sharedpref.getInt("Color",222);
        if(BackgroundColor ==1){
            getView().setBackgroundColor(Color.RED);
        }else if(BackgroundColor == 2){
            getView().setBackgroundColor(Color.BLUE);

        }else if(BackgroundColor == 3){
            getView().setBackgroundColor(Color.CYAN);
        }
    }
}