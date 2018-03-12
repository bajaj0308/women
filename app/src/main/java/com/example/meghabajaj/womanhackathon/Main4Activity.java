package com.example.meghabajaj.womanhackathon;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

public class Main4Activity extends AppCompatActivity {
    private VideoView videoView;
    private MediaController mediaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
    }

    public void play(View view) {
        videoView = (VideoView)findViewById(R.id.video);
        ImageView img=(ImageView)findViewById(R.id.img);
        img.setVisibility(ImageView.INVISIBLE);

        Uri videoUri = Uri.parse("android.resource://com.example.meghabajaj.womanhackathon" + "/" + R.raw.kja);

        videoView.setVideoURI(videoUri);

        mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);
        videoView.requestFocus();
        videoView.start();
    }

}
