package com.example.trung_000.myapplication;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MapsActivity extends AppCompatActivity implements GoogleMap.OnMapClickListener, GoogleMap.OnMapLongClickListener {
    GoogleMap mMap;
    ProgressDialog progressDialog;
    Button btnsearch, btnsearch1,btndirection,btnfindpath,btnok;
    EditText edtsearch,edtorigin,edtdestination;
    LinearLayout linearLayout;
    TextView txtDistance,txtDuration;
    String link="https://maps.googleapis.com/maps/api/directions/json?origin=";
    String links;
    String duration,distance;
    ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        android.support.v7.app.ActionBar ab=getSupportActionBar();
//        ab.setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_maps);
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Đang tải Map ...");
        progressDialog.setMessage("Xin Chờ...");
        progressDialog.setCancelable(true);
        progressDialog.show();
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.maps);
        mapFragment.getMapAsync(new OnMapReadyCallback() {

            @Override
            public void onMapReady(GoogleMap googleMap) {
                onMyMapReady(googleMap);
            }
        });
        txtDistance=(TextView)findViewById(R.id.txtDistance);
        txtDuration=(TextView)findViewById(R.id.txtDuration);
        linearLayout=(LinearLayout)findViewById(R.id.layoutfindpath);
        btnsearch = (Button) findViewById(R.id.btnseatch);
        btnsearch1 = (Button)findViewById(R.id.btnseatch1);
        edtsearch = (EditText)findViewById(R.id.edtsearch);
        btnsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnsearch.setVisibility(View.GONE);
                btnsearch1.setVisibility(View.VISIBLE);
                edtsearch.setVisibility(View.VISIBLE);

            }
        });
        btnsearch1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String location;
                location = edtsearch.getText().toString();
                List<Address> addresses = null;

                if (location == null || location.equals("")) {
                    Toast.makeText(MapsActivity.this, "Vui lòng nhập địa chỉ", Toast.LENGTH_SHORT).show();


                } else {
                    Toast.makeText(MapsActivity.this, location, Toast.LENGTH_SHORT).show();
                    Geocoder geocoder = new Geocoder(MapsActivity.this);
                    try {
                        addresses = geocoder.getFromLocationName(location, 1);
                        if (addresses.size() > 0) {
                            Address address = addresses.get(0);
                            LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
                            mMap.addMarker(new MarkerOptions().position(latLng).title(location));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 12));
                            mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
                            btnsearch.setVisibility(View.VISIBLE);
                            btnsearch1.setVisibility(View.GONE);
                            edtsearch.setVisibility(View.GONE);
                            edtsearch.setText("");

                        } else {
                            AlertDialog.Builder adb = new AlertDialog.Builder(MapsActivity.this);
                            adb.setTitle("Google Map");
                            adb.setMessage("Vui lòng cung cấp địa chỉ chính xác hơn");
                            adb.setPositiveButton("Close", null);
                            adb.show();
                            edtsearch.setText("");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                }

            }
        });
        btndirection=(Button)findViewById(R.id.btndirection);
        btndirection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayout.setVisibility(View.VISIBLE);
            }
        });
        btnfindpath=(Button)findViewById(R.id.btnfindpath);
        edtorigin=(EditText)findViewById(R.id.edtorigin);
        edtdestination=(EditText)findViewById(R.id.edtdestination);
        btnfindpath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String origin=edtorigin.getText().toString();
                String destination=edtdestination.getText().toString();
                if (origin.isEmpty()) {
                    Toast.makeText(MapsActivity.this, "Vui lòng nhập địa chỉ bắt đầu", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (destination.isEmpty()) {
                    Toast.makeText(MapsActivity.this, "Vùi lòng nhập địa chỉ điểm đến", Toast.LENGTH_SHORT).show();
                    return;
                }
                String originnew=origin.replace(" ","%20");
                String destinationnew=destination.replace(" ","%20");
                links="https://maps.googleapis.com/maps/api/directions/json?origin="+originnew+"&destination"+destinationnew+"keyAIzaSyAjKzfMSsLU_3ntEa39ZyNOcKV27i1KIGQ";
                Log.d("LinkApi",links);
//                GetData getData=new GetData();
//                getData.execute(links);





            }
        });


        btnok=(Button)findViewById(R.id.btnok);
        btnok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayout.setVisibility(View.GONE);
            }
        });

//

    }

    private void onMyMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {

            @Override
            public void onMapLoaded() {
                progressDialog.dismiss();
            }
        });
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);
        LatLng maker = new LatLng(21.0706251, 105.8540668);
        CameraPosition cameraPosition=CameraPosition.builder()
                .target(maker)
                .bearing(4.5f)
                .tilt(90)
                .zoom(15).build();
        CameraUpdate cameraUpdate=CameraUpdateFactory.newCameraPosition(cameraPosition);
        mMap.animateCamera(cameraUpdate,2000,null);
        mMap.addMarker(new MarkerOptions().title("Hello Google Maps").position(maker));
        mMap.setOnMapClickListener(this);
        mMap.setOnMapLongClickListener(this);
        mMap.setBuildingsEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.idmap_type_none:
                mMap.setMapType(GoogleMap.MAP_TYPE_NONE);
                Toast.makeText(MapsActivity.this,"Map Type None",Toast.LENGTH_SHORT).show();
                break;
            case R.id.idmap_type_normal:
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                Toast.makeText(MapsActivity.this,"Map Type Normal",Toast.LENGTH_SHORT).show();
                break;
            case R.id.idmap_type_satellife:
                mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                Toast.makeText(MapsActivity.this,"Map Type Satelite",Toast.LENGTH_SHORT).show();
                break;
            case R.id.idmap_type_terrain:
                mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                Toast.makeText(MapsActivity.this,"Map Type Terrain",Toast.LENGTH_SHORT).show();
                break;
            case R.id.idmap_type_hybrid:
                mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                Toast.makeText(MapsActivity.this,"Map Type Hybrid",Toast.LENGTH_SHORT).show();
                break;
            default:


        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.map_menu, menu);
        return true;
    }




    @Override
    public void onMapClick(LatLng latLng) {
        Toast.makeText(MapsActivity.this, "Địa điểm vừa click\n" + latLng.latitude + " : " + latLng.longitude,
                Toast.LENGTH_LONG).show();

    }

    @Override
    public void onMapLongClick(LatLng latLng) {
        Toast.makeText(MapsActivity.this,
                "Địa điểm vừa click\n" + latLng.latitude + " : " + latLng.longitude,
                Toast.LENGTH_LONG).show();
        MarkerOptions markerOptions =new MarkerOptions().position(latLng).title(latLng.toString());
        mMap.addMarker(markerOptions);

    }
}

