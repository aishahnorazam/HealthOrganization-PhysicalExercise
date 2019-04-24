package com.wenlong.hope.MainActivityFragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.wenlong.hope.R;

public class BookingFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root=inflater.inflate(R.layout.fragment_booking,container,false);

        ImageView gym = root.findViewById(R.id.booking_gym);
        ImageView hydro = root.findViewById(R.id.booking_hydro);
        ImageView strongman =  root.findViewById(R.id.booking_strongman);

        gym.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction a = getFragmentManager().beginTransaction();
                a.replace(R.id.fragment_container,new GymFragment());
                a.commit();
            }
        });

        hydro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction b = getFragmentManager().beginTransaction();
                b.replace(R.id.fragment_container,new HydroFragment());
                b.commit();
            }
        });

        strongman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction c = getFragmentManager().beginTransaction();
                c.replace(R.id.fragment_container,new StrongmanFragment());
                c.commit();
            }
        });
        return root;


    }
}
