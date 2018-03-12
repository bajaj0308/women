package com.example.meghabajaj.womanhackathon;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

//import android.support.v7.widget.CardView;

public class Sign_Up_Activity extends AppCompatActivity {


    EditText ed1, ed2, ed3;
    TextView tx1;
    //CardView CardView1;
    Button b1;
    ProgressBar progressBar;
    FirebaseAuth auth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign__up_);
        auth = FirebaseAuth.getInstance();
        progressBar = (ProgressBar) findViewById(R.id.sign_up_progress);
        ed2=(EditText)findViewById(R.id.sign_up_password);
        ed3=(EditText)findViewById(R.id.sign_up_confirm_password);
        ed1=(EditText)findViewById(R.id.email_sign_up);
        b1=(Button)findViewById(R.id.email_sign_up_button);
        tx1=(TextView)findViewById(R.id.log_in);
//        CardView1=(CardView)findViewById(R.id.CardView1);



        b1.setOnClickListener(new View.OnClickListener()
        {
            @Override

            public void onClick(View v)
            {
              final String email = ed1.getText().toString().trim();
                String password = ed2.getText().toString().trim();
                if (TextUtils.isEmpty(email)) {
                    ed1.setError(getString(R.string.invalid_email));
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    ed2.setError(getString(R.string.no_password));
                    return;
                }

                if (password.length() < 6) {
                    ed2.setError(getString(R.string.minimum_password));
                    return;
                }

                if(!(ed2.getText().toString().equals( ed3.getText().toString()))){

                    Toast.makeText(getApplicationContext(), "Passwords do not match!", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);
                //create user
                auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(Sign_Up_Activity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                //  Toast.makeText(SignUp.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                if (!task.isSuccessful()) {
                                    Toast.makeText(Sign_Up_Activity.this, "Authentication failed." + task.getException(),
                                            Toast.LENGTH_SHORT).show();
                                } else {

                                    sendVerificationEmail();
                                    startActivity(new Intent(Sign_Up_Activity.this, MainActivity.class));
                                    finish();
                                }
                            }
                        });

            }


        });
        tx1.setOnClickListener(new View.OnClickListener(){

            @Override
            public  void onClick(View view){

                startActivity(new Intent(Sign_Up_Activity.this, MainActivity.class));
            }
        });
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu2) {
//        getMenuInflater().inflate(R.menu.menu2,menu2);
//        return true;
//    }
//
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId())
//        {
//            case R.id.about_us:
//            {
//                Intent intent = new Intent(SignUp.this,AboutUs.class);
//
//                startActivity(intent);
//                return true;
//            }
//            case R.id.feedback: {
//
//                Intent intent = new Intent(SignUp.this,Feedback.class);
//
//                startActivity(intent);
//                return true;
//            }
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//
//    }
//    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }

    private void sendVerificationEmail()
    {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        user.sendEmailVerification()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Kindly verify your email by clicking on the link sent at registered email.", Toast.LENGTH_LONG).show();
                            FirebaseAuth.getInstance().signOut();
                            // startActivity(new Intent(SignUp.this, MainActivity.class));
                            finish();
                        }
                        else
                        {
                            // email not sent, so display message and restart the activity or do whatever you wish to do

                            //restart this activity
                            overridePendingTransition(0, 0);
                            finish();
                            overridePendingTransition(0, 0);
                            startActivity(getIntent());

                        }
                    }
                });
    }
}

