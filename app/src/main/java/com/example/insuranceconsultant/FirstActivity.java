package com.example.insuranceconsultant;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class FirstActivity extends AppCompatActivity {

    private CardView cvClients;
    private CardView cvTeam;
    private ImageView imMetlife;
    private ImageView imStarlife;
    private ImageView imCients;
    private ImageView imTeam;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        cvClients = findViewById(R.id.cvClients);
        cvTeam = findViewById(R.id.cvTeam);
        imMetlife = findViewById(R.id.imMetlife);
        imStarlife = findViewById(R.id.imStarlife);
        imCients = findViewById(R.id.imCients);
        imTeam = findViewById(R.id.imTeam);

        cvClients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ClientsActivity.class);
                startActivity(intent);
            }
        });


        Glide.with(getApplicationContext())
                .load("https://www.homepro.com/hs-fs/hubfs/HomePro%20Website%20Images/Icons/Life%20insurance.png?width=480&name=Life%20insurance.png")
                .into(imCients);

        Glide.with(getApplicationContext())
                .load("https://www.metlife.ua/content/dam/metlifecom/ua/icons/Pictogram_Hands_Heart_01.L.png")
                .into(imMetlife);

        Glide.with(getApplicationContext())
                .load("https://starlife1.com/static/img/misc/stlogo.png")
                .into(imTeam);


        imMetlife.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                Uri uri = Uri.parse("https://www.metlife.ua/");
                intent.setData(uri);
                startActivity(intent);
            }
        });

        imStarlife.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                Uri uri = Uri.parse("http://cabinet.starlife1.com:2000/starlife_zvit/");
                intent.setData(uri);
                startActivity(intent);
            }
        });


    }
}