package rushi.bhandari.n01464259;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ShapesFragmenty#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShapesFragmenty extends Fragment {
    Switch firebaseSwitch;
    FirebaseDatabase database;
    DatabaseReference myRef;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ShapesFragmenty() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ShapesFragmenty.
     */
    // TODO: Rename and change types and number of parameters
    public static ShapesFragmenty newInstance() {
        ShapesFragmenty fragment = new ShapesFragmenty();
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
        View view = inflater.inflate(R.layout.fragment_shapes_fragmenty, container, false);

        AutoCompleteTextView autoCompleteTextView = view.findViewById(R.id.rushiShapesFragmentIdAutoCompleteView);

        String[] autoCompleteTextEmailOptions = getResources().getStringArray(R.array.rushiAutoCompleteEmailArray);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,autoCompleteTextEmailOptions);

         database = FirebaseDatabase.getInstance("https://rushiassign5-default-rtdb.firebaseio.com/");
         myRef = database.getReference("Emails:");

        firebaseSwitch = view.findViewById(R.id.rushiShapesFragmentIdFireDataBaseStorageEnableDisable);
        firebaseSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(firebaseSwitch.isChecked() == true){
                    Toast.makeText(getContext(),"Data storing On...",Toast.LENGTH_LONG).show();
                    String autoTextData = autoCompleteTextView.getText().toString();
                    myRef.setValue(autoTextData);
                    autoCompleteTextView.setText("");
                }else if(firebaseSwitch.isChecked() == false){
                    myRef = database.getReference("Emails");
                    myRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String value = snapshot.getValue(String.class);
                            TextView textViewTDisplay = view.findViewById(R.id.rushiShapesFragmentIdFireDataBaseStorageDataDisplay);
                            textViewTDisplay.setText(value);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
                MobileAds.initialize(getContext(), new OnInitializationCompleteListener() {
                    @Override
                    public void onInitializationComplete(@NonNull InitializationStatus initializationStatus) {

                    }

                });

             

            }
        });





        autoCompleteTextView.setThreshold(1);
        autoCompleteTextView.setAdapter(adapter);




        return view;
    }
}