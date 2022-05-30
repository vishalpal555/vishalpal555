package com.vishalpal555.myapplication101;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class ActivityMedia extends AppCompatActivity {
    public String fromMillisecondToStandard(long milliseconds){
        int hours = (int) (milliseconds / (1000 * 60 * 60));
        int minutes = (int) (milliseconds % (1000 * 60 * 60)) / (1000 * 60);
        int seconds = (int) ((milliseconds % (1000 * 60 * 60)) % (1000 * 60) / 1000);
        if(hours == 0){
            return String.valueOf(minutes) +":" +String.valueOf(seconds);
        }
        return String.valueOf(hours) +" : " +String.valueOf(minutes) +":" +String.valueOf(seconds);
    }
    private ImageButton playPauseButton;
    private MediaPlayer mediaPlayer = new MediaPlayer();
    private SeekBar seekBar;
    private Switch switchLoop;
    private TextView textViewProgressDuration;
    private TextView textViewMaxDuration;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media);

        playPauseButton = findViewById(R.id.playPauseButton);
        seekBar = findViewById(R.id.seekBar);
        switchLoop = findViewById(R.id.switchLoop);
        textViewMaxDuration = findViewById(R.id.textViewMaxDuration);
        textViewProgressDuration = findViewById(R.id.textViewProgressDuration);

        //Offline music
        //mediaPlayer = MediaPlayer.create(ActivityMedia.this, R.raw.wateve_voc);

        try {
            mediaPlayer.setDataSource("https://paglasongs.com/files/download/id/2094");
            mediaPlayer.prepareAsync();
        }
        catch (IOException e){
            Toast.makeText(ActivityMedia.this, "Music not loaded", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                seekBar.setProgress(0);
                seekBar.setMax(mediaPlayer.getDuration());
                textViewMaxDuration.setText(fromMillisecondToStandard(mediaPlayer.getDuration()));
                new Timer().scheduleAtFixedRate(new TimerTask() {
                    @Override
                    public void run()
                    {
                        seekBar.setProgress(mediaPlayer.getCurrentPosition());
                    }
                },0,1);
                seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int duration, boolean fromUser) {
                        textViewProgressDuration.setText(fromMillisecondToStandard(mediaPlayer.getCurrentPosition()));
                        if(fromUser){
                            mediaPlayer.seekTo(duration);
                        }
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                });
                playPauseButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(mediaPlayer.isPlaying()){
                            mediaPlayer.pause();
                            playPauseButton.setImageResource(R.drawable.play);

                        }
                        else{
                            mediaPlayer.start();
                            playPauseButton.setImageResource(R.drawable.pause);;
                        }
                    }
                });
            }
        });

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {

                playPauseButton.setImageResource(R.drawable.play);
            }
        });

    }
}
