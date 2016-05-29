package com.example.trung_000.myapplication;


import android.Manifest;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
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


/**
 * A simple {@link Fragment} subclass.
 */
public class MapsFragment extends Fragment implements OnMapReadyCallback,GoogleMap.OnMapClickListener,GoogleMap.OnMapLongClickListener {
    GoogleMap mMap;
    Button btnsearch, btnsearch1,btndirection,btnfindpath,btnok;
    EditText edtsearch,edtorigin,edtdestination;
    ProgressDialog myProgress;
    LinearLayout linearLayout;
    TextView txtDistance,txtDuration;
    String link="https://maps.googleapis.com/maps/api/directions/json?origin=";
    String links;
    String duration,distance;
    ProgressDialog pDialog;

    public MapsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_maps, container, false);
        txtDistance=(TextView)v.findViewById(R.id.txtDistance);
        txtDuration=(TextView)v.findViewById(R.id.txtDuration);
        linearLayout=(LinearLayout)v.findViewById(R.id.layoutfindpath);
        myProgress = new ProgressDialog(MapsFragment.this.getActivity());
        myProgress.setTitle("Đang tải Map ...");
        myProgress.setMessage("Vui lòng chờ...");
        myProgress.setCancelable(true);
        myProgress.show();
        setHasOptionsMenu(true);
        btnsearch = (Button) v.findViewById(R.id.btnseatch);
        btnsearch1 = (Button) v.findViewById(R.id.btnseatch1);
        edtsearch = (EditText) v.findViewById(R.id.edtsearch);
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
                    Toast.makeText(MapsFragment.this.getActivity(), "Vui lòng nhập địa chỉ", Toast.LENGTH_SHORT).show();


                } else {
                    Toast.makeText(MapsFragment.this.getActivity(), location, Toast.LENGTH_SHORT).show();
                    Geocoder geocoder = new Geocoder(MapsFragment.this.getActivity());
                    try {
                        addresses = geocoder.getFromLocationName(location, 1);
                        if (addresses.size() > 0) {
                            Address address = addresses.get(0);
                            LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
                            mMap.addMarker(new MarkerOptions().position(latLng).title(location));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 12));
                            mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
//                            MapOverlay mapOverlay = new MapOverlay();
//                            List<Overlay> listOfOverlays = mapView.getOverlays();
//                            listOfOverlays.clear();
//                            listOfOverlays.add(mapOverlay);
//
//                            mapView.invalidate();
                            btnsearch.setVisibility(View.VISIBLE);
                            btnsearch1.setVisibility(View.GONE);
                            edtsearch.setVisibility(View.GONE);
                            edtsearch.setText("");

                        } else {
                            AlertDialog.Builder adb = new AlertDialog.Builder(MapsFragment.this.getActivity());
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
        btndirection=(Button)v.findViewById(R.id.btndirection);
        btndirection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayout.setVisibility(View.VISIBLE);
            }
        });
        btnfindpath=(Button)v.findViewById(R.id.btnfindpath);
        edtorigin=(EditText)v.findViewById(R.id.edtorigin);
        edtdestination=(EditText)v.findViewById(R.id.edtdestination);
        btnfindpath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String origin=edtorigin.getText().toString();
                String destination=edtdestination.getText().toString();
                if (origin.isEmpty()) {
                    Toast.makeText(MapsFragment.this.getActivity(), "Vui lòng nhập địa chỉ bắt đầu", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (destination.isEmpty()) {
                    Toast.makeText(MapsFragment.this.getActivity(), "Vùi lòng nhập địa chỉ điểm đến", Toast.LENGTH_SHORT).show();
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


        btnok=(Button)v.findViewById(R.id.btnok);
        btnok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayout.setVisibility(View.GONE);
            }
        });

//
        // Inflate the layout for this fragment
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.maps);
        mapFragment.getMapAsync(this);


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng maker = new LatLng(21.0706251, 105.8540668);
        CameraPosition cameraPosition=CameraPosition.builder()
                .target(maker)
                .bearing(4.5f)
                .tilt(90)
                .zoom(15).build();
        CameraUpdate cameraUpdate=CameraUpdateFactory.newCameraPosition(cameraPosition);
        googleMap.animateCamera(cameraUpdate,2000,null);

        googleMap.addMarker(new MarkerOptions().title("Hello Google Maps").position(maker));
        mMap = googleMap;
        mMap.setOnMapClickListener(this);
        mMap.setOnMapLongClickListener(this);
        mMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
            @Override
            public void onMapLoaded() {
                myProgress.dismiss();



            }
        });
        if (ContextCompat.checkSelfPermission(MapsFragment.this.getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        } else {
            Toast.makeText(MapsFragment.this.getActivity(),"Vui lòng bật GPS",Toast.LENGTH_SHORT).show();
        }
        mMap.setBuildingsEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(true);


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.idmap_type_none:
                mMap.setMapType(GoogleMap.MAP_TYPE_NONE);
                Toast.makeText(MapsFragment.this.getActivity(),"Map Type None",Toast.LENGTH_SHORT).show();
                break;
            case R.id.idmap_type_normal:
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                Toast.makeText(MapsFragment.this.getActivity(),"Map Type Normal",Toast.LENGTH_SHORT).show();
                break;
            case R.id.idmap_type_satellife:
                mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                Toast.makeText(MapsFragment.this.getActivity(),"Map Type Satelite",Toast.LENGTH_SHORT).show();
                break;
            case R.id.idmap_type_terrain:
                mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                Toast.makeText(MapsFragment.this.getActivity(),"Map Type Terrain",Toast.LENGTH_SHORT).show();
                break;
            case R.id.idmap_type_hybrid:
                mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                Toast.makeText(MapsFragment.this.getActivity(),"Map Type Hybrid",Toast.LENGTH_SHORT).show();
                break;

        }
        return false;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.back,menu);
        super.onCreateOptionsMenu(menu, inflater);

    }


    @Override
    public void onMapClick(LatLng latLng) {
        Toast.makeText(MapsFragment.this.getActivity(), "onMapClick:\n" + latLng.latitude + " : " + latLng.longitude,
                Toast.LENGTH_LONG).show();

    }

    @Override
    public void onMapLongClick(LatLng latLng) {
        Toast.makeText(MapsFragment.this.getActivity(),
                "onMapLongClick:\n" + latLng.latitude + " : " + latLng.longitude,
                Toast.LENGTH_LONG).show();

        //Add marker on LongClick position
        MarkerOptions markerOptions =
                new MarkerOptions().position(latLng).title(latLng.toString());
        mMap.addMarker(markerOptions);

    }
//    public class GetData extends AsyncTask<String,Void,String>{
//        StringBuilder dulieu ;
//        @Override
//        protected void onPreExecute() {
////            pDialog = new ProgressDialog(MapsFragment.this.getActivity());
////            pDialog.setMessage("Please wait...");
////            pDialog.setCancelable(false);
////            pDialog.show();
//            super.onPreExecute();
//        }
//
//        @Override
//        protected String doInBackground(String... params) {
//
//            try {
//                URL url=new URL(params[0]);
//                HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
//                httpURLConnection.connect();
//                InputStream inputStream=httpURLConnection.getInputStream();
//                InputStreamReader reader=new InputStreamReader(inputStream);
//                BufferedReader bufferedReader=new BufferedReader(reader);
//                String dong="";
//                dulieu=new StringBuilder();
//
//                while ((dong=bufferedReader.readLine())!=null){
//                    dulieu.append(dong);
//                }
//                bufferedReader.close();
//                reader.close();
//                inputStream.close();
//                httpURLConnection.disconnect();
//                Log.d("dulieu",dulieu.toString());
//
//
//            }
//
//            catch (MalformedURLException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
////            return dulieu.toString();
//            try {
//                JSONObject object=new JSONObject(dulieu.toString());
//                JSONArray routes=object.getJSONArray("routes");
//                for (int i=0;i<routes.length();i++){
//                    JSONObject oroutes=routes.getJSONObject(i);
//                    JSONArray legs=oroutes.getJSONArray("legs");
//                    for(int j=0;j<legs.length();j++){
//                        JSONObject olegs=legs.getJSONObject(j);
//                        JSONObject odistance=olegs.getJSONObject("distance");
//                        distance=odistance.getString("text");
//                        Log.d("Distance",distance);
//                        JSONObject oduration=olegs.getJSONObject("duration");
//                        duration=oduration.getString("text");
//                        Log.d("Duration",duration);
//                    }
//
//                }
//
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//           return dulieu.toString();
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
////            if (pDialog.isShowing())
////                pDialog.dismiss();
//            txtDistance.setText(distance);
//            txtDuration.setText(duration);
//            super.onPostExecute(s);
//        }
//    }



}
