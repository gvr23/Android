package dsbmobile.com.app.google_maps_demo;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
//fragmentActivity is a generic type of activity that allows us t create any sort of activity
//the OnMapReadyCallback allows us to do something when the map is ready
public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    // to refer to the map itself
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        //gets that map in the background and we can run any other ode if we want
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    //this is the code to decide where the map is centered
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // to set the map to have satellite view as well
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        // Add a marker in Sydney and move the camera
        LatLng dsbMobile = new LatLng(-12.090086, -77.0188311);
        //changes the icon and the color for the marker used for the map
        mMap.addMarker(new MarkerOptions().position(dsbMobile).title("Marker in DSB Mobile").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
        //move the view of the map to that location
        //newLatLnZoom starts the app zoomed in, and it varies from 0 - 20 being complete zoomed in
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(dsbMobile, 15));
    }
}
