package com.wenlong.hope.Information;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.firebase.client.Firebase;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.wenlong.hope.Calendar.GymCalendar;
import com.wenlong.hope.Confirmation.GymConfirmation;
import com.wenlong.hope.InputFilterMinMax;
import com.wenlong.hope.R;

public class GymInfo extends AppCompatActivity implements View.OnClickListener{

    private String dat,type,radioText,matrix;
    private static int count=0;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private Button group,single;
    private EditText editGroupAmount,matrixNum;
    private int GroupAmount,price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gym_info);

        group =  findViewById(R.id.gym_GroupBooking);
        group.setOnClickListener(this);
        single = findViewById(R.id.gym_SingleBooking);
        single.setOnClickListener(this);
        editGroupAmount = findViewById(R.id.gym_GroupAmount);
        editGroupAmount.setFilters(new InputFilter[] {new InputFilterMinMax("1","15")});
        matrixNum = findViewById(R.id.gym_matrix);

        // extract Bundle from calendarClass
        Intent in = getIntent();
        Bundle b = in.getExtras();
        if(b != null) {
            dat = b.getString("date");
        }

        Button submit = findViewById(R.id.gymSubmitInfo);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editTextName = findViewById(R.id.gym_input_name);
                EditText editTextEmail = findViewById(R.id.gym_input_email);
                EditText editTextPhone = findViewById(R.id.gym_input_phone);

                String name = editTextName.getText().toString();
                String email = editTextEmail.getText().toString();
                String phone = editTextPhone.getText().toString();

                // validate user input
                if(TextUtils.isEmpty(name)) {
                    editTextName.setError("Name cannot be empty");
                    return;
                }
                else if(TextUtils.isEmpty(email)) {
                    editTextEmail.setError("Email cannot be empty");
                    return;
                }
                else if(TextUtils.isEmpty(phone)) {
                    editTextPhone.setError("Phone cannot be empty");
                    return;
                }

                // radioButton
                radioGroup = findViewById(R.id.RadioGroup);
                int selectedID = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(selectedID);
                radioText = radioButton.getText().toString();

                radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        switch(checkedId) {
                            case R.id.radio_public:
                                matrixNum.setEnabled(false);
                                matrixNum.setInputType(InputType.TYPE_NULL);
                                matrixNum.setFocusableInTouchMode(false);
                                break;
                            default: matrixNum.setEnabled(true);
                        }
                    }
                });

                matrix = matrixNum.getText().toString();

                if(type.equals("Group Booking")) {
                    switch(radioText) {
                        case "Student" : price = 5 * GroupAmount; break;
                        case "Staff" : price = 7 * GroupAmount; break;
                        case "Public" : price = 10 * GroupAmount; break ;
                    }
                }else if(type.equals("Single Booking")) {
                    switch(radioText) {
                        case "Student" : price = 5; break;
                        case "Staff" : price = 7; break;
                        case "Public" : price = 10; break;
                    }
                }

                Bundle c = new Bundle(); // store bundle for name,phone,email
                c.putString("date",dat);
                c.putString("name",name);
                c.putString("email",email);
                c.putString("phone",phone);
                c.putString("type",type);
                c.putString("status",radioText);
                c.putInt("people",GroupAmount);
                c.putInt("price",price);
                c.putString("matrix",matrix);

                Intent intent = new Intent(getApplicationContext(), GymConfirmation.class);
                intent.putExtras(c);
                startActivity(intent);
            }
        });
    }
    public void onClick(View v) {
        if(v.getId() == R.id.gym_SingleBooking) {
            group.setBackgroundColor(Color.TRANSPARENT);
            single.setBackgroundColor(Color.CYAN);
            editGroupAmount.setEnabled(false);
            Button singleText = (Button) v;
            type = singleText.getText().toString();
        }

        if(v.getId() ==  R.id.gym_GroupBooking) {
            editGroupAmount.setEnabled(true);
            single.setBackgroundColor(Color.TRANSPARENT);
            group.setBackgroundColor(Color.CYAN);
            String a = editGroupAmount.getText().toString().trim();
            try {
                GroupAmount = Integer.parseInt(a);
            }catch (NumberFormatException ex) {
                GroupAmount = 1;
                Log.i("Group", a.toString());
            }
            Button groupText = (Button) v;
            type = groupText.getText().toString();
        }
    }
}

