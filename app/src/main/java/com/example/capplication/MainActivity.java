package com.example.capplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.util.Linkify;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.xml.transform.Source;

public class MainActivity extends AppCompatActivity {
    private int randNumber = 0;
    private int randNumberAudio = 0;
    private MediaPlayer mediaPlayer;

    private class AudioCap{
     Integer idAudio;
     public AudioCap(Integer idAudio){
         this.idAudio = idAudio;
     }

        public Integer getIdAudio() {
            return idAudio;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Map<Integer,CAP> map = new HashMap<>();
        final List<AudioCap> capTalk = new LinkedList<>();

        initMapwithCAP(map);
        initAudioCap(capTalk);


        TextView txt = findViewById(R.id.textView1);
        txt.setText("Made with \uD83E\uDDE1 per il Cap!");


        final Random rand = new Random();
        Button buttonToccami = findViewById(R.id.button);

        buttonToccami.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ImageView image = findViewById(R.id.imgView1);
                int tempNumber;
                do{
                    tempNumber = rand.nextInt(CAP.values().length);
                }while(tempNumber == randNumber);
                    randNumber = tempNumber;
                CAP chooseCap = map.get(randNumber);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    image.setForeground(getResources().getDrawable(chooseCap.getSource()));
                }

                Toast toast = Toast.makeText(getApplicationContext(),chooseCap.getDescription(),Toast.LENGTH_LONG);
                toast.setMargin(0,22);
                toast.show();
            }
        });
        buttonToccami.callOnClick();
        Button btnTalk = findViewById(R.id.btnTalk);

        btnTalk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int tempNumber;
                stopPlaying();
                do{
                    tempNumber = rand.nextInt(capTalk.size());
                }while(tempNumber == randNumberAudio);
                randNumberAudio = tempNumber;

                mediaPlayer = MediaPlayer.create(getBaseContext(),capTalk.get(randNumberAudio).getIdAudio());
                mediaPlayer.start(); // no need to call prepare(); create() does that for you


            }
        });

        Button btnCall = findViewById(R.id.btnCall);
        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent send = new Intent(Intent.ACTION_VIEW);
                String url = null;
                try {
                    url = "https://api.whatsapp.com/send?phone="+"+393318771454" + "&text=" + URLEncoder.encode("Mi vuoi sposare?","UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                send.setPackage("com.whatsapp");
                send.setData(Uri.parse(url));
                startActivity(send);
            }
        });

    }

  private void stopPlaying() {
        if(mediaPlayer != null){
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    private void initAudioCap(List<AudioCap> capTalk) {
        capTalk.add(new AudioCap(R.raw.audio1));
        capTalk.add(new AudioCap(R.raw.audio2));
        capTalk.add(new AudioCap(R.raw.audio3));
        capTalk.add(new AudioCap(R.raw.audio4));
        capTalk.add(new AudioCap(R.raw.audio5));
        capTalk.add(new AudioCap(R.raw.audio6));
        capTalk.add(new AudioCap(R.raw.audio7));
        capTalk.add(new AudioCap(R.raw.audio8));
    }

    private void initMapwithCAP(Map<Integer,CAP> map){
        map.put(0,CAP.CINESE);
        map.put(1,CAP.TAPPE);
        map.put(2,CAP.FAKETAXI);
        map.put(3,CAP.BRACCIO);
        map.put(4,CAP.BORGHETTI);
        map.put(5,CAP.CUSTODE);
        map.put(6,CAP.URLO);
    }
}
