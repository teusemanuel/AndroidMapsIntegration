package br.com.commons.androidmapsintegration;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.maps.android.clustering.ClusterManager;

import java.util.ArrayList;

import br.com.commons.androidmapsintegration.object.JobRenderer;
import br.com.commons.androidmapsintegration.object.MyItem;

public class DisctanceActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ClusterManager<MyItem> mClusterManager;
    private ArrayList<MyItem> jobs = new ArrayList<MyItem>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disctance);

        jobs.add(new MyItem("Mateus Emanuel", R.drawable.profile_ic, -18.642229, -48.176849));
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
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
    @Override
    /*public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        Bitmap imageResource = BitmapFactory.decodeResource(getResources(), R.drawable.profile_ic);
        Bitmap imageResized = Bitmap.createScaledBitmap(imageResource, 120, 120, false);


        // Add a marker in Sydney and move the camera
        LatLng mWork = new LatLng(-18.642229, -48.176849);
        mMap.addMarker(new MarkerOptions()
                .position(mWork)
                .icon(BitmapDescriptorFactory.fromBitmap(imageResized))
                        // Specifies the anchor to be at a particular point in the marker image.
                .anchor(0.5f, 1)
                .title("Marker in My Work"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mWork, 10));

    }*/

    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.getUiSettings().setMapToolbarEnabled(true);
        mClusterManager = new ClusterManager<MyItem>(this, mMap);
        mClusterManager.setRenderer(new JobRenderer(this, mMap, mClusterManager));
        mMap.setOnCameraChangeListener(mClusterManager);
        mMap.setOnMarkerClickListener(mClusterManager);

        //Assume that we already have arraylist of jobs


        for(final MyItem job: jobs){
            mClusterManager.addItem(job);
        }
        mClusterManager.cluster();

    }
}
