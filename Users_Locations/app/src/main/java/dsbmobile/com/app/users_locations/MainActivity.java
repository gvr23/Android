package dsbmobile.com.app.users_locations;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    //1.
    LocationManager locationManager;
    //2.
    LocationListener locationListener;

    //6. What to do when they accept or not
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        //If both of these are true, we have permission
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                //minimum time and the minimum distance that will lead us to get an update
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //3. In order to get the location of the user, we start by
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        //4. Then we will create a listener to listen for changes in location
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
            //when the location is changed
                Log.i("location", location.toString());
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
                //when the location service is either enabled or disabled
            }

            @Override
            public void onProviderEnabled(String provider) {
            //When the location services are enabled or disabled by the users
            }

            @Override
            public void onProviderDisabled(String provider) {
                //When the location services are enabled or disabled by the users
            }
        };

        //If device is running SDK < 23
//        if (Build.VERSION.SDK_INT < 23)

        //5. check to see if we don't have permission
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            //ask for it
            //and the integer is the number that we can check that this request the one that was made
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }else{
            //we have permssion
            //minimum time and the minimum distance that will lead us to get an update
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        }
    }
}
