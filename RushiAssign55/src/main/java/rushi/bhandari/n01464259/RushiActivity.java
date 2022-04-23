package rushi.bhandari.n01464259;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.widget.Toolbar;

public class RushiActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private Fragment homeFragment;
    private Fragment downloadFragment;
    private Fragment weatherFragment;
    private Fragment shapesFragment;
    private Fragment filesFragment;
    private Fragment settingsFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




    }
}