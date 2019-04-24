package com.wenlong.hope.Confirmation;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.wenlong.hope.MainActivity;
import com.wenlong.hope.MainActivityFragment.BookingFragment;
import com.wenlong.hope.R;

import org.w3c.dom.Text;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;


public class GymConfirmation extends AppCompatActivity {

    private String date, name, email, phone,status,type,matrix;
    private static int count;
    private int price,people;
    private String ID;
    FirebaseDatabase database;
    DatabaseReference myRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gym_confirmation);
        database = FirebaseDatabase.getInstance();

        Intent in = getIntent();
        Bundle b = in.getExtras();
        if (b != null) {
            date = b.getString("date");
            name = b.getString("name");
            email = b.getString("email");
            phone = b.getString("phone");
            status = b.getString("status");
            type = b.getString("type");
            people = b.getInt("people");
            price = b.getInt("price");
            matrix = b.getString("matrix");
        }

        TextView Tdate = findViewById(R.id.gym_date_confirm);
        TextView Tname = findViewById(R.id.gym_name_confirm);
        TextView Temail = findViewById(R.id.gym_email_confirm);
        TextView Tphone = findViewById(R.id.gym_phone_confirm);
        TextView Ttype = findViewById(R.id.gym_type_confirm);
        TextView Tstatus = findViewById(R.id.gym_status_confirm);
        TextView Tprice = findViewById(R.id.gym_price_confirm);
        TextView Tpeople = findViewById(R.id.gym_people_confirm);
        TextView Tmatrix = findViewById(R.id.gym_matrix_confirm);

        // set all info bring forward from previous class
        Tdate.setText(date);
        Tname.setText(name);
        Temail.setText(email);
        Tphone.setText(phone);
        if(matrix == null) {
            Tmatrix.setText("-");
        }else Tmatrix.setText(matrix);
        Ttype.setText(type);
        Tstatus.setText(status);
        Tprice.setText(String.valueOf(price));
        Tpeople.setText(String.valueOf(people));



        // return to homepage and save data into database
        Button btn = findViewById(R.id.btn_gym_confirmation);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                ID = String.format(Locale.US, "%03d", count); // concetente ID with leading zero of width = 3
//               database.getReference(date).child("GYM").push().setValue(ID);
                myRef = database.getReference(date).child("GYM").child(name);
                myRef.child("count").setValue(count);
                myRef.child("Name").setValue(name);
                myRef.child("Email").setValue(email);
                myRef.child("Phone").setValue(phone);
                myRef.child("Type").setValue(type);
                myRef.child("Status").setValue(status);
                myRef.child("Matrix").setValue(matrix);
                myRef.child("Price").setValue(price);
                myRef.child("People").setValue(people);

                //Alert Dialog to prompt user booking successful
                AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(GymConfirmation.this, R.style.AlertDialog);
                builder.setTitle("Thanks for Booking!");
                builder.setMessage("Here is Your Booking Details!\n\nBooking ID:   " + ID + "\nFacilities : LAB FITNESS" +
                        "\nName :   " + name + "\nEmail :   " + email + "\nPhone :   " + phone + "\nMatrix No : " + matrix +
                        "\nType Of Booking: "+ type +  "\nStatus : " + status + "\nPeople : "+ people + "\nPrice : "+ price +
                        "\n\nThanks for Booking!\nA Confirmation Email had been sent and Kindly display the email upon registration");
                builder.setCancelable(true);
                builder.setNeutralButton("Return To Homepage", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent in = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(in);
                    }
                });
                builder.show();

                // get Max value from database




                //send email to user
                Log.i("SendMailActivity","Send Button Clicked");
                String fromEmail = "skynight1996@gmail.com";
                String fromPassword = "skynight";
                String toEmails = email;
                List<String>toEmailList = Arrays.asList(toEmails.split("\\s*,\\s*"));
                Log.i("SendMailActivity","To List:" + toEmailList);
                String emailSubject = "New Booking From SITC   "+date;
                String emailBody = "Here is Your Booking Details!<br><b>Booking ID:   " + ID + "</b><br>Facilities : LAB FITNESS" +
                        "</b><br>\nName :   " + name + "\n<br>Email :   " + email + "\n<br>Phone :   " + phone + "<br>Matrix No : " + matrix +
                        "\n<br>Type Of Booking: "+ type + "\n<br>Status: " + status + "\n<br>Price: "+ price +
                        "\n\n <br><b>Thanks for Booking!\n***<br>***A Confirmation Email had been sent and kindly display the email upon registration***</b> ";
                new SendMailTask(GymConfirmation.this).execute(fromEmail,fromPassword,toEmailList,emailSubject,emailBody);
                }
        });
    }
}





