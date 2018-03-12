package com.example.meghabajaj.womanhackathon;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class HomeActivity extends AppCompatActivity {





        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_home);
        }

        public void informationModule(View view) {
            Intent intent = new Intent(this, Main2Activity.class);
            startActivity(intent);
        }

        public void dietPlan(View view) {
            Intent intent = new Intent(this, Main3Activity.class);
            startActivity(intent);
        }

        public void playVideo(View view) {
            Intent intent = new Intent(this, Main4Activity.class);
            startActivity(intent);
        }

        public void vaccAppoint(View view) {
            Intent intent = new Intent(this, Main5Activity.class);
            startActivity(intent);
        }
        public void helpline(View view){
            startActivity(new Intent(this,search_user.class));
        }
        public void discussion(View view){
            startActivity(new Intent(this,Helpline_Activity.class));
        }


}
