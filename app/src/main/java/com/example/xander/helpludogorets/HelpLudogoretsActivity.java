package com.example.xander.helpludogorets;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.VideoView;

import java.io.File;


public class HelpLudogoretsActivity extends Activity {
    private static VideoView videoView;
    private ImageButton prevButton;
    private ImageButton playPauseButton;
    private ImageButton nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_ludogorets);

        videoView = (VideoView) findViewById(R.id.videoView);
        prevButton = (ImageButton) findViewById(R.id.prevButton);
        playPauseButton = (ImageButton) findViewById(R.id.playButton);
        nextButton = (ImageButton) findViewById(R.id.nextButton);
        File video = new File(Environment.getExternalStorageDirectory(), "a.mp4");
        prevButton.setOnClickListener(clickPrev());
        playPauseButton.setOnClickListener(clickPlay());
        nextButton.setOnClickListener(clickNext());

        videoView.setVideoURI(Uri.fromFile(video));
        pausePlayVideoAction();

    }

    public void pausePlayVideoAction (){
        if (!videoView.isPlaying()){
            videoView.start();
            playPauseButton.setImageResource(R.drawable.pause);
        } else {
            videoView.pause();
            playPauseButton.setImageResource(R.drawable.play);
        }
    }

    public View.OnClickListener clickPlay (){
        View.OnClickListener playClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               pausePlayVideoAction();
            }
        };
        return playClickListener;
    }

    public View.OnClickListener clickNext (){
        View.OnClickListener playClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoView.pause();
                int currentFrame = videoView.getCurrentPosition();
                videoView.seekTo(currentFrame + 3000);
            }
        };
        return playClickListener;
    }

    public View.OnClickListener clickPrev(){
        View.OnClickListener playClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoView.pause();
                int currentFrame = videoView.getCurrentPosition();
                videoView.seekTo(currentFrame - 3000);
            }
        };
        return playClickListener;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.help_ludogorets, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
