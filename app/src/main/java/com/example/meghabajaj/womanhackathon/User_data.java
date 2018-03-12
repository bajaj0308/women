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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class User_data extends AppCompatActivity {

    DatabaseReference userref;
    EditText ed1, ed2;
    Button b1;
    String email;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_data);
        userref = FirebaseDatabase.getInstance().getReference("user");
        ed1=(EditText)findViewById(R.id.name_user);
        ed2=(EditText)findViewById(R.id.phone_user);
        progressBar = (ProgressBar) findViewById(R.id.datastorage_progressbar);
        b1=(Button)findViewById(R.id.user_data_storage_button);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {

            email = user.getEmail();

        }
        b1.setOnClickListener(new View.OnClickListener()
        {
            @Override

            public void onClick(View v)
            {
                String id = userref.push().getKey();
                String name=ed1.getText().toString().trim();
                String phone=ed2.getText().toString().trim();
                if (TextUtils.isEmpty(name)) {
                    ed1.setError(getString(R.string.invalid_name));
                    return;
                }
                if (TextUtils.isEmpty(phone)) {
                    ed2.setError(getString(R.string.invalid_phone));
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);

                User users=new User(name,phone,email);
                userref.child(id).setValue(users);
                Toast.makeText(getApplicationContext(),"added", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(User_data.this, Helpline_Activity.class));
                finish();


            }

        }


        );
    }
}
