package com.wenlong.hope.Calendar;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.wenlong.hope.Information.GymInfo;
import com.wenlong.hope.Information.StrongmanInfo;
import com.wenlong.hope.R;

public class StrongmanCalendar extends AppCompatActivity {

    private static final String TAG = "CalendarActivity";
    private TextView text;
    private String date;
    FirebaseDatabase database;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_strongman_calendar);

        // set default date to current date
        CalendarView cal = new CalendarView(this);
        cal.setDate(System.currentTimeMillis(),false,true);

        // get date selected from calendarView
        text = findViewById(R.id.strongman_available);
        CalendarView calendarView = findViewById(R.id.strongmanCalendar);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
             @Override
             public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                 String m="";
                 month += 1;
                 String y = String.valueOf(year);
                 String d = String.valueOf(dayOfMonth);
                 switch(month) {
                     case 1: m = "JANUARY" ; break;
                     case 2: m = "FEBRUARY" ;  break;
                     case 3: m = "MARCH" ; break;
                     case 4: m = "APRIL" ; break;
                     case 5: m = "MAY" ; break;
                     case 6: m = "JUN" ; break;
                     case 7: m = "JULY" ; break;
                     case 8: m = "AUGUST" ; break;
                     case 9: m = "SEPTEMBER"; break;
                     case 10: m = "OCTOBER"; break;
                     case 11: m = "NOVEMBER" ; break;
                     case 12: m = "DECEMBER" ; break;
                 }
                 date = y  + "/" + m + "/" + d ;
                 Log.d(TAG, "onSelectedDayChange: date:" + date); // check date using logcat
                 text.setText(date);
             }
         });


        Button btn = findViewById(R.id.btn_strongmanCalendar);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle b = new Bundle();
                b.putString("date",date);
                Intent in = new Intent(getApplicationContext(),StrongmanInfo.class);
                in.putExtras(b);
                startActivity(in);
            }
        });

    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
