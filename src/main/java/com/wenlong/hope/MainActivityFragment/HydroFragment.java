package com.wenlong.hope.MainActivityFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.wenlong.hope.Calendar.HydroCalendar;
import com.wenlong.hope.R;

public class HydroFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_hydro,container,false);

        Button btn1 = root.findViewById(R.id.btn_HydroBooking);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), HydroCalendar.class);
                startActivity(intent);
            }
        });
        return root;
    }

    // redirect to BookingFragment (main menu) when pressed back
    @Override
    public void onResume() {
        super.onResume();
        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                    FragmentTransaction back = getFragmentManager().beginTransaction();
                    back.replace(R.id.fragment_container, new BookingFragment());
                    back.commit();
                    return true;
                }
                return false;
            }
        });
    }
}
