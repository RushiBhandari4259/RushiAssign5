package rushi.bhandari.n01464259;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ShapesFragmenty#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShapesFragmenty extends Fragment {

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

        FirebaseDatabase database = FirebaseDatabase.getInstance("https://rushiassign5-default-rtdb.firebaseio.com/");
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("Hello, World!");



        return view;
    }
}