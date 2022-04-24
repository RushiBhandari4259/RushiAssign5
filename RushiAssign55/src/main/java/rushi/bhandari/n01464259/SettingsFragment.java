//Rushi Bhandari N01464259 RNB
package rushi.bhandari.n01464259;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Switch;

import java.time.Clock;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SettingsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingsFragment extends Fragment {
Switch pswitch;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SettingsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.

     * @return A new instance of fragment SettingsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SettingsFragment newInstance() {
        SettingsFragment fragment = new SettingsFragment();
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
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        //Setting the background of Home fragment
        SharedPreferences sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        RadioButton ColorRed = view.findViewById(R.id.rushiSettingsFragmentIdRadioButtonRed);
        RadioButton colorBlue = view.findViewById(R.id.rushiSettingsFragmentIdRadioButtonBlue);
        RadioButton colorCyan = view.findViewById(R.id.rushiSettingsFragmentIdRadioButtonCyan);

        SharedPreferences sharedPreferences2 = getActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor ClockFormatEditor = sharedPreferences.edit();
        RadioButton Format12Hr = view.findViewById(R.id.rushiSettingsFragmentIdRadioButtonT12);
        RadioButton Format24Hr = view.findViewById(R.id.rushiSettingsFragmentIdRadioButtonT24);

        //Temperature Unit
        SharedPreferences sharedPreferences3 = getActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor TemperatureUnitEditor = sharedPreferences.edit();
        RadioButton ButtonCelsius = view.findViewById(R.id.rushiSettingsFragmentIdRadioButtonCelsius);
        RadioButton ButtonFahrenheit = view.findViewById(R.id.rushiSettingsFragmentIdRadioButtonFahrenheit);

        Button ApplyButton = view.findViewById(R.id.rushiSettingsFragmentIdChangesApplyButton);
        ApplyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Changing Temperature unit
                if(ButtonCelsius.isChecked()){
                    TemperatureUnitEditor.putInt("Temp",1);
                    TemperatureUnitEditor.commit();
                }else if (ButtonFahrenheit.isChecked()){
                    TemperatureUnitEditor.putInt("Temp",2);
                    TemperatureUnitEditor.commit();
                }

                //Changing clock format
                if(Format12Hr.isChecked()){
                    ClockFormatEditor.putInt("Format",1);
                    ClockFormatEditor.commit();
                }else if(Format24Hr.isChecked()){
                    ClockFormatEditor.putInt("Format",2);
                    ClockFormatEditor.commit();
                }
                ClockFormatEditor.commit();

                //Sending background color info
                if(ColorRed.isChecked()){
                    editor.putInt("Color",1);
                    editor.commit();
                }else if(colorBlue.isChecked()){
                    editor.putInt("Color",2);
                    editor.commit();
                }else if(colorCyan.isChecked()){
                    editor.putInt("Color",3);
                    editor.commit();
                }
            }
        });
        editor.commit();


        pswitch = view.findViewById(R.id.switch1);
        pswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(pswitch.isChecked()){
                    getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                }
            }
        });
        return view;
    }

}