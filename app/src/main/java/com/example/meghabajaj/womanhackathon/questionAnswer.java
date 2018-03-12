package com.example.meghabajaj.womanhackathon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class questionAnswer extends AppCompatActivity {

    EditText ed1, ed2;
    //CardView CardView1;
    Button b1,btnBack;
    ProgressBar progressBar;
    //Used to check for authentication of user
    FirebaseAuth auth;
    //create a new node named Details
    DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Discussion");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_answer);
        auth = FirebaseAuth.getInstance();
        //These are the views i.e. the layouts in which textfrom the user is to be entered for the detail form
        progressBar = (ProgressBar) findViewById(R.id.search_progress);
        ed1 = (EditText) findViewById(R.id.question);
        ed2 = (EditText) findViewById(R.id.answer);
        b1 = (Button) findViewById(R.id.add_question_answer_button);
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
                String question = ed1.getText().toString().trim();
                String answer = ed2.getText().toString().trim();
                if (TextUtils.isEmpty(question)) {
                    ed1.setError("Question required");
                    return;
                }
                if (TextUtils.isEmpty(answer)) {
                    ed2.setError("Answer Required");
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                Question_Answer discuss;
                    discuss = new Question_Answer(question,answer);


                //This is for making a child node of details with the data entered by the user
                // and id is randomly generated which will act as a primary key and
                // set value sets the value of all the fields in the detail class.

                ref.child(id).setValue(discuss);
                Toast.makeText(getApplicationContext(), "added", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(questionAnswer.this, searchActivity.class));
                finish();


            }
        });

    }
}
