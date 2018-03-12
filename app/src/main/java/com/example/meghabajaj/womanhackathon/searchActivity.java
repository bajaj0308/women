package com.example.meghabajaj.womanhackathon;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class searchActivity extends AppCompatActivity {
    EditText ed1, ed2, ed3,ed4,ed5,ed6;
    //CardView CardView1;
    Button b1,btnBack;
    ProgressBar progressBar;
    //Used to check for authentication of user
    FirebaseAuth auth;
//create a new node named Details
    DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Details");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        auth = FirebaseAuth.getInstance();
        //These are the views i.e. the layouts in which textfrom the user is to be entered for the detail form
        progressBar = (ProgressBar) findViewById(R.id.search_progress);
        ed1 = (EditText) findViewById(R.id.name_search);
        ed2 = (EditText) findViewById(R.id.phone_search);
        ed3 = (EditText) findViewById(R.id.street_address);
        ed4 = (EditText) findViewById(R.id.state_address);
        ed5 = (EditText) findViewById(R.id.pincode_address);
        ed6 = (EditText) findViewById(R.id.type);
        b1 = (Button) findViewById(R.id.add_details_button);
        btnBack = (Button) findViewById(R.id.back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                String id = ref.push().getKey();
                String name = ed1.getText().toString().trim();
                String phone = ed2.getText().toString().trim();
                String street = ed3.getText().toString().trim();
                String state = ed4.getText().toString().trim();
                String pincode = ed5.getText().toString().trim();
                String type = ed6.getText().toString().trim();
                if (TextUtils.isEmpty(name)) {
                    ed1.setError(getString(R.string.invalid_name));
                    return;
                }
                if (TextUtils.isEmpty(phone)) {
                    ed2.setError(getString(R.string.invalid_phone));
                    return;
                }
                if (TextUtils.isEmpty(state)) {
                    ed4.setError(getString(R.string.invalid_state));
                    return;
                }
                if (TextUtils.isEmpty(pincode)) {
                    ed5.setError(getString(R.string.invalid_pincode));
                    return;
                }
                if (TextUtils.isEmpty(type) || !(type.equals("DOCTOR") || type.equals("HOSPITAL"))) {
                    ed6.setError(getString(R.string.invalid_type));
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                doctor_details detail;
                if (TextUtils.isEmpty(street)) {
                    detail = new doctor_details(name, phone, state, pincode, type);
                } else {
                    detail = new doctor_details(name, phone, street, state, pincode, type);
                }

                //This is for making a child node of details with the data entered by the user
                // and id is randomly generated which will act as a primary key and
                // set value sets the value of all the fields in the detail class.

                ref.child(id).setValue(detail);
                Toast.makeText(getApplicationContext(), "added", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(searchActivity.this, searchActivity.class));
                finish();


            }
        });

    }
}
