package com.example.meghabajaj.womanhackathon;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
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

public class MainActivity extends AppCompatActivity {

    //    CardView c;
    int count=0;
    EditText ed1, ed2;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseAuth mAuth;
    TextView tx, tx2,tx3;
    FirebaseAuth mauth;
    ProgressBar progressBar;
    Button b1;
    public static final String TAG ="user" ;

    //SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mauth = FirebaseAuth.getInstance();
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            public static final String TAG ="user" ;

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }

        };

        progressBar = (ProgressBar) findViewById(R.id.login_progress);


//        c = (CardView) findViewById(R.id.CardView1);
        ed1 = (EditText) findViewById(R.id.email);
        ed2 = (EditText) findViewById(R.id.password);
        b1 = (Button) findViewById(R.id.email_sign_in_button);
        tx3=(TextView)findViewById(R.id.sign_up);
        //tx = (TextView) findViewById(R.id.textView1);Login word
         tx2 = (TextView) findViewById(R.id.forgot_password);
        mauth = FirebaseAuth.getInstance();


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = ed1.getText().toString();
                final String password = ed2.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                //authenticate user

                mauth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                progressBar.setVisibility(View.GONE);
                                if (!task.isSuccessful()) {
                                    // there was an error
                                    if (password.length() < 6) {
                                        ed2.setError(getString(R.string.minimum_password));
                                    } else {
                                        Toast.makeText(MainActivity.this, getString(R.string.does_not_match), Toast.LENGTH_LONG).show();
                                    }
                                } else {

                                    checkIfEmailVerified();
                                }
                            }
                        });


            }
        });
        tx2.setOnClickListener(new View.OnClickListener(){

            @Override
            public  void onClick(View view){

                startActivity(new Intent(MainActivity.this, ForgetPasswordActivity.class));
            }
        });
        tx3.setOnClickListener(new View.OnClickListener(){

            @Override
            public  void onClick(View view){

                startActivity(new Intent(MainActivity.this, Sign_Up_Activity.class));
            }
        });
    }

    private void checkIfEmailVerified() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user.isEmailVerified()) {
            // user is verified, so you can finish this activity or send user to activity which you want
            finish();
            Toast.makeText(MainActivity.this, "Successfully logged in", Toast.LENGTH_SHORT).show();
            // sp.edit().putBoolean("logged",true).apply();
//            FirebaseDatabase database = FirebaseDatabase.getInstance();
//            DatabaseReference ref = database.getInstance().getReference("user").child(user.getUid());
//            Log.v(TAG," Did not enter in the loop"+ref+"  ");
//            ref.addListenerForSingleValueEvent(new ValueEventListener() {
//
//
//                @Override
//                public void onDataChange(DataSnapshot dataSnapshot) {
//                   // final User tempProfile = new User(); //this is my user_class Class
//                     count= (int)dataSnapshot.getChildrenCount();
//                    Log.v(TAG,"Count is: "+count);
//                    if(count>1){
//                        Log.v(TAG," Did not enter in the loop"+count+" ");
//                        count=1;
//                    }
//                    Log.v(TAG," Did not enter in the loop"+count+" ");
//
//                }
//
//                @Override
//                public void onCancelled(DatabaseError databaseError) {
//                }
//            });
//            if(count==1){
//                Intent intent = new Intent(MainActivity.this, Helpline_Activity.class);
//                startActivity(intent);
//            }
//            else{
//                Intent intent = new Intent(MainActivity.this, User_data.class);
//                startActivity(intent);
//            }
            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(intent);
        }
        else
        {
            // email is not verified, so just prompt the message to the user and restart this activity.
            // NOTE: don't forget to log out the user.
            Toast.makeText(MainActivity.this, "Kindly verify your email first!!", Toast.LENGTH_SHORT).show();

            //restart this activity

        }
    }

}

