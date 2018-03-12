package com.example.meghabajaj.womanhackathon;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Helpline_Activity extends AppCompatActivity {

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
    RecyclerView recyclerview;
    ArrayList<String> Question;
    ArrayList<String> Answer;
    searchAdapterDiscussion searchadapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helpline);
        recyclerview=(RecyclerView)findViewById(R.id.recycler_view_discussion);
//        tx1=(TextView)findViewById(R.id.search_not_found);
        Question=new ArrayList<>();
        Answer=new ArrayList<>();
        recyclerview.setHasFixedSize(true);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.VERTICAL));
        setAdapter("");

    }
    private void setAdapter(final String s) {

        ref.child("Discussion").addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Question.clear();
                Answer.clear();
                recyclerview.removeAllViews();
                int count=0;
                for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                    String uid=snapshot.getKey();
                    String answer="Answer:\n"+snapshot.child("answer").getValue(String.class);
                    String question="Question:\n"+snapshot.child("question").getValue(String.class);
                        Question.add(question);
                        Answer.add(answer);
                        count++;

                }

                searchadapter=new searchAdapterDiscussion(Helpline_Activity.this,Question,Answer);
                recyclerview.setAdapter(searchadapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
