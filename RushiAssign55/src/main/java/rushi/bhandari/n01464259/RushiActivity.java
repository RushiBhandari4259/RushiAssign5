package rushi.bhandari.n01464259;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.rushi_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.rushi_id_menu_location:
                Toast.makeText(getApplicationContext(), "Lon  " + " Lat ", Toast.LENGTH_SHORT).show();
//                client = LocationServices.getFusedLocationProviderClient(this);
//
//                if (ActivityCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//                    ActivityCompat.requestPermissions(RushiActivity.this, new String[]{ACCESS_FINE_LOCATION}, 1);
//                }
//                client.getLastLocation().addOnSuccessListener(RushiActivity.this, new OnSuccessListener<Location>() {
//                    @Override
//                    public void onSuccess(Location location) {
//                        if (location != null) {
//                            Toast.makeText(getApplicationContext(), location.toString(), Toast.LENGTH_LONG).show();
//
//                        }
//                    }
//
//
//                });
//                double latitude = location.getLatitude();
//                double longitude= location.getLongitude();
//                Log.d("loacation", String.valueOf(latitude));
//
            case R.id.rushi_id_menu_sms:
                sendSMSMessage();
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.SEND_SMS)) {
                    } else {
                        ActivityCompat.requestPermissions(this,
                                new String[]{Manifest.permission.SEND_SMS},
                                0);
                    }
                }

        }
        return false;
    }
    protected void sendSMSMessage() {
        String phoneNo = "4378551228";
        String message = "Yoo";

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.SEND_SMS)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.SEND_SMS},
                        0);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        String phone = "4378551228";
        String message = "Yoo";
        switch (requestCode) {
            case 0: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(phone, null, message, null, null);
                    Toast.makeText(getApplicationContext(),
                            "Message Sent", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Failed!", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    //Transaction for the fragment to open
    private void startTransactionFragment(Fragment fragment) {
        if (!fragment.isVisible()) {
            getSupportFragmentManager().beginTransaction().replace(R.id.rushiFrameLayout, fragment).commit();
        }

    }
}