package com.vogella.android.locationapi.maps;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import org.w3c.dom.Document;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    static final LatLng HAMBURG = new LatLng(53.558, 9.927);
    static final LatLng KIEL = new LatLng(52.551, 9.993);
    private GMapV2Direction md;
    private GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        LatLng sourcePosition = new LatLng(-6.33438, 106.74316);
//        LatLng destPosition = new LatLng(-70.15721, 506.21572);
//        md = new GMapV2Direction();
//        map = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
//        Document doc = md.getDocument(sourcePosition, destPosition, GMapV2Direction.MODE_DRIVING);
//        ArrayList<LatLng> directionPoint = md.getDirection(doc);
//        PolylineOptions rectLine = new PolylineOptions().width(3).color(Color.RED);
//        for (int i = 0; i < directionPoint.size(); i++) {
//            rectLine.add(directionPoint.get(i));
//        }
//        Polyline polylin = map.addPolyline(rectLine);
        map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
        Marker hamburg = map.addMarker(new MarkerOptions().position(HAMBURG).title("Hamburg"));
        Marker kiel = map.addMarker(new MarkerOptions().position(KIEL).title("Kiel").snippet("Kiel is cool").icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher)));

        // Move the camera instantly to hamburg with a zoom of 15.
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(HAMBURG, 15));

        // Zoom in, animating the camera.
        map.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);
    }
}
