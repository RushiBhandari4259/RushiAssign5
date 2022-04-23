package rushi.bhandari.n01464259;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FilesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FilesFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FilesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment FilesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FilesFragment newInstance() {
        FilesFragment fragment = new FilesFragment();
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
        View view = inflater.inflate(R.layout.fragment_files, container, false);
        Button GetContentButton = view.findViewById(R.id.rushiFilesFragmentIdGetContentButton);
        String filename="rushi.txt";
        TextView contentView = view.findViewById(R.id.rushiFilesFragmentIdFileContentDisplay);

        GetContentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    FileInputStream fin = getActivity().openFileInput(filename);
                    int a;
                    StringBuilder temp = new StringBuilder();
                    while((a = fin.read())!= -1){
                        temp.append((char)a);

                    }
                    contentView.setText(temp.toString());
                    fin.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        Button deleteButton = view.findViewById(R.id.rushiFilesFragmentIdDeleteButton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                File fileToDelete = new File("/data/data/n01464259.bhandari.rushi/files/rushi.txt");
                fileToDelete.delete();
                contentView.setText("File deleted");

            }
        });




        return view;

    }
}