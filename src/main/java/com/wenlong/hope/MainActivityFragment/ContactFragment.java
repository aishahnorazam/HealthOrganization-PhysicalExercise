package com.wenlong.hope.MainActivityFragment;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.wenlong.hope.Manifest;
import com.wenlong.hope.R;



public class ContactFragment extends Fragment implements OnMapReadyCallback {

    private TextView hPhone, rPhone;
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE_HADAFI = 1234;
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE_ROBIN = 5678;

    GoogleMap mGoogleMap;
    MapView mMapView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_contact, container, false);

        mMapView = root.findViewById(R.id.mapView);
        if (mMapView != null) {
            mMapView.onCreate(savedInstanceState);
            mMapView.getMapAsync(this);
            mMapView.onResume();

        }


        hPhone = root.findViewById(R.id.hadafi_phone);
        rPhone = root.findViewById(R.id.robin_phone);

        hPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(getActivity().getApplicationContext(),
                        android.Manifest.permission.CALL_PHONE)
                        != PackageManager.PERMISSION_GRANTED) {

                    ActivityCompat.requestPermissions(getActivity(),
                            new String[]{android.Manifest.permission.CALL_PHONE},
                            MY_PERMISSIONS_REQUEST_CALL_PHONE_HADAFI);
                } else {
                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    Toast.makeText(getActivity(), "Calling DR HADAFI...", Toast.LENGTH_SHORT).show();
                    callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    callIntent.setData(Uri.parse("tel: " + "0125015090"));
                    getActivity().startActivity(callIntent);
                }
            }
        });

        rPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(getActivity().getApplicationContext(),
                        android.Manifest.permission.CALL_PHONE)
                        != PackageManager.PERMISSION_GRANTED) {

                    ActivityCompat.requestPermissions(getActivity(),
                            new String[]{android.Manifest.permission.CALL_PHONE},
                            MY_PERMISSIONS_REQUEST_CALL_PHONE_ROBIN);
                } else {
                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    Toast.makeText(getActivity(), "Calling MR ROBIN...", Toast.LENGTH_SHORT).show();
                    callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    callIntent.setData(Uri.parse("tel: " + "01112716790"));
                    getActivity().startActivity(callIntent);
                }
            }
        });


        return root;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        MapsInitializer.initialize(getActivity().getApplicationContext());
        mGoogleMap = googleMap;
        LatLng SITC = new LatLng(1.544331, 103.632692);
        googleMap.addMarker(new MarkerOptions().position(SITC).title("Sport Innovation Technology Center").snippet("Your Fitness Start Here"));
        CameraPosition position = CameraPosition.builder().target(SITC).zoom(16).bearing(0).tilt(45).build();
        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(position));
    }
}
