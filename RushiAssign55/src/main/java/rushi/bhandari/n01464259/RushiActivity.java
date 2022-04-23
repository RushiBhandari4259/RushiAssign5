package rushi.bhandari.n01464259;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;

public class RushiActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
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

        this.configureToolBar();
        this.configureDrawerLayout();
        this.configurationNavigationView();

        //Open Home fragement
        Fragment fragment = HomeFragment.newInstance();
        getSupportFragmentManager().beginTransaction().replace(R.id.rushiFrameLayout, fragment).commit();




    }
    private void configureToolBar() {
        this.toolbar = (Toolbar) findViewById(R.id.activity_main_toolbar);
        setSupportActionBar(toolbar);
    }

    private void configureDrawerLayout() {
        this.drawerLayout = (DrawerLayout) findViewById(R.id.activity_main_drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navi_open,  R.string.navi_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    private void configurationNavigationView() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.activity_main_nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            //Opening home fragment
            case R.id.rushiHomeFramentId:
                if (this.homeFragment == null) this.homeFragment = HomeFragment.newInstance();
                this.startTransactionFragment(this.homeFragment);
                break;
            //Opening download fragment
            case R.id.rushiDownloadFragmentId:
                if (this.downloadFragment == null)
                    this.downloadFragment = DownloadFragment.newInstance();
                this.startTransactionFragment(this.downloadFragment);
                break;
//Opening fragment weather
            case R.id.rushiWeatherFragmentId:
                if (this.weatherFragment == null) this.weatherFragment = WeatherFragment.newInstance();
                this.startTransactionFragment(this.weatherFragment);
                break;
//Opening fragment shapes
            case R.id.rushiShapesFragmentId:
                if (this.shapesFragment == null)
                    this.shapesFragment = ShapesFragmenty.newInstance();
                this.startTransactionFragment(this.shapesFragment);
                break;
//Opening fragment files
            case R.id.rushiFilesFragmentId:
                if (this.filesFragment == null)
                    this.filesFragment = FilesFragment.newInstance();
                this.startTransactionFragment(this.filesFragment);
                break;
            //Opening fragment Settings
            case R.id.rushiSettingsFragmentId:
                if (this.settingsFragment == null)
                    this.settingsFragment = SettingsFragment.newInstance();
                this.startTransactionFragment(this.settingsFragment);
                break;

            default:
                break;
        }
        this.drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    //Transaction for the fragment to open
    private void startTransactionFragment(Fragment fragment) {
        if (!fragment.isVisible()) {
            getSupportFragmentManager().beginTransaction().replace(R.id.rushiFrameLayout, fragment).commit();
        }

    }
}