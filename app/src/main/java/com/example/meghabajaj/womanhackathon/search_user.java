package com.example.meghabajaj.womanhackathon;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class search_user extends AppCompatActivity {
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
    EditText search_edit_text;
    TextView tx1;
    RecyclerView recyclerview;
    ArrayList<String> Type;
    ArrayList<String> Name;
    ArrayList<String> Phone;
    ArrayList<String> Address;
    searchAdapter searchadapter;

    //MaterialSearchView searchView;
    @Override
    public boolean onPrepareOptionsMenu(Menu menu)
    {
        MenuItem add_details = menu.findItem(R.id.add_details);
        MenuItem add_question_answer=menu.findItem(R.id.add_question_answer);
        //For debugging Purpose
        Log.v("USER",user.getUid());
        Log.v("USER ",add_details.isVisible()+" ");
        //condition that a user can add details only if he is admin
        if(user.getUid().equals("cggZm6U41cQkTmhNfr3wnKpp5Mj1") || user.getUid().equals("KeokiktH8Pc6wtnHYmGlrkDTX2I2") ||user.getUid().equals("WjO08uj5VEY31cpdP9I95CS6DB32")){
            add_details.setVisible(true);
            add_question_answer.setVisible(true);
            Log.v("USER ",add_details.isVisible()+" ");
        }
        return true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_user);
        search_edit_text=(EditText)findViewById(R.id.search_edit_text);
        recyclerview=(RecyclerView)findViewById(R.id.recycler_view);
//        tx1=(TextView)findViewById(R.id.search_not_found);
        Name=new ArrayList<>();
        Type=new ArrayList<>();
        Phone=new ArrayList<>();
        Address=new ArrayList<>();
        recyclerview.setHasFixedSize(true);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.VERTICAL));
        search_edit_text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!s.toString().isEmpty()){
                    setAdapter(s.toString());
                }
                else{
                    Name.clear();
                    Type.clear();
                    Phone.clear();
                    Address.clear();
                    recyclerview.removeAllViews();

                }
            }
        });
//        Toolbar toolbar=(Toolbar)findViewById(R.id.tool_bar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setTitle("Search...");
//        toolbar.setTitleTextColor(Color.parseColor("FFFFFF"));
//        searchView=(MaterialSearchView)findViewById(R.id.search_view);
    }

    private void setAdapter(final String s) {

        ref.child("Details").addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Name.clear();
                Type.clear();
                Phone.clear();
                Address.clear();
                recyclerview.removeAllViews();
                int count=0;
                for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                    String uid=snapshot.getKey();
                    String name=snapshot.child("name").getValue(String.class);
                    String phone=snapshot.child("phone").getValue(String.class);
                    String pincode=snapshot.child("pincode").getValue(String.class);
                    String street=snapshot.child("street").getValue(String.class);
                    String state=snapshot.child("state").getValue(String.class);
                    String type=snapshot.child("type").getValue(String.class);
                    String address=street+"\n"+state+"\n"+pincode+"\n";
                    if(pincode.contains(s)){
                        Name.add(name);
                        Type.add(type);
                        Address.add(address);
                        Phone.add(phone);
                        count++;

                    }else if(name.toLowerCase().contains(s.toLowerCase())){
                        Name.add(name);
                        Type.add(type);
                        Address.add(address);
                        Phone.add(phone);
                        count++;
                    }
                    if(count==15){
                        break;
                    }

                }

                if(count==0)
                    Toast.makeText(search_user.this,"No matches found",Toast.LENGTH_SHORT).show();
                searchadapter=new searchAdapter(search_user.this,Type,Name,Phone,Address);
                recyclerview.setAdapter(searchadapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_main_actions,menu);
        Log.v("user",user.getUid());
//        final MenuItem searchItem = menu.findItem(R.id.action_search);
//        SearchView searchView = (android.widget.SearchView) searchItem.getActionView();
//        // Detect SearchView icon clicks
//        searchView.setOnSearchClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                setItemsVisibility(menu, searchItem, false);
//            }
//        });
//        // Detect SearchView close
//        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
//            @Override
//            public boolean onClose() {
//                setItemsVisibility(menu, searchItem, true);
//                return false;
//            }
//        });
//        MenuItem item=(MenuItem)findViewById(R.id.action_search);
//
//        searchView.setMenuItem(item);
//        // Associate searchable configuration with the SearchView

        return super.onCreateOptionsMenu(menu);
    }
//    private void setItemsVisibility(Menu menu, MenuItem exception, boolean visible) {
//        for (int i=0; i<menu.size(); ++i) {
//            MenuItem item = menu.getItem(i);
//            if (item != exception) item.setVisible(visible);
//        }
//    }

    /**
     * On selecting action bar icons
     * */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Take appropriate action for each action item click
        switch (item.getItemId()) {
            case R.id.action_search:
                // search action
                return true;

            case R.id.action_refresh:
                // refresh
                return true;
            case R.id.sign_out:
                // help action
                return true;
            case R.id.add_details:
                startActivity(new Intent(search_user.this, searchActivity.class));
                // check for updates action
                return true;
            case R.id.add_question_answer:
                startActivity(new Intent(search_user.this,questionAnswer.class));
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
