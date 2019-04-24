package com.wenlong.hope.Information;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuthSettings;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.wenlong.hope.Confirmation.HydroConfirmation;
import com.wenlong.hope.InputFilterMinMax;
import com.wenlong.hope.R;

public class HydroInfo extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference myRef;
    private Button submit;
    private String dat,radioText,matrix;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private EditText editGroupAmount,matrixNum;
    private int price,GroupAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hydro_info);

        editGroupAmount = findViewById(R.id.hydro_GroupAmount);
        editGroupAmount.setFilters(new InputFilter[]{new InputFilterMinMax("1","15")});
        matrixNum = findViewById(R.id.hydro_matrix);

        // extract Bundle from calendarClass
        Intent in = getIntent();
        Bundle b = in.getExtras();
        if(b != null) {
            dat = b.getString("date");
        }
        database = FirebaseDatabase.getInstance();

        submit = findViewById(R.id.hydroSubmitInfo);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText editTextName = findViewById(R.id.hydro_input_name);
                EditText editTextEmail = findViewById(R.id.hydro_input_email);
                EditText editTextPhone = findViewById(R.id.hydro_input_phone);

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

                switch(radioText) {
                    case "Student" : price = 5 * GroupAmount; break;
                    case "Staff" : price = 7 * GroupAmount; break;
                    case "Public" : price = 10 * GroupAmount; break ;
                }

                Bundle c = new Bundle();
                c.putString("date",dat);
                c.putString("name",name);
                c.putString("email",email);
                c.putString("phone",phone);
                c.putString("status",radioText);
                c.putInt("people",GroupAmount);
                c.putInt("price",price);
                c.putString("matrix",matrix);

                Intent intent = new Intent(getApplicationContext(),HydroConfirmation.class);
                intent.putExtras(c);
                startActivity(intent);
            }
        });
    }
}

