package com.example.plaingaudio;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;
    ImageView playPauseIcon;
    SeekBar seekBar;
    AudioManager audioManager;



    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playPauseIcon = findViewById(R.id.imageViewPlayIcon);

        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.stuff);

        seekBar = findViewById(R.id.volumeSeekBar);
        seekBar.setMax(mediaPlayer.getDuration());
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser){
                    mediaPlayer.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                seekBar.setProgress(mediaPlayer.getCurrentPosition());
            }
        }, 0, 1000);
    ;}

    public void previous(View view) {
        mediaPlayer.pause();
        mediaPlayer.seekTo(0);
        playPauseIcon.setImageResource(R.drawable.ic_baseline_play_arrow_24);
    }

    public void next(View view) {
        mediaPlayer.pause();
        mediaPlayer.seekTo(mediaPlayer.getDuration());
        playPauseIcon.setImageResource(R.drawable.ic_baseline_play_arrow_24);
    }

    public void play(View view) {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            playPauseIcon.setImageResource(R.drawable.ic_baseline_play_arrow_24);
        } else{
            mediaPlayer.start();
            playPauseIcon.setImageResource(R.drawable.ic_baseline_pause_circle_filled_24);
        }
    }
}