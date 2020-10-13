package com.example.insuranceconsultant;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class FirstActivity extends AppCompatActivity {

    private CardView cvClients;
    private CardView cvTeam;
    private ImageView imMetlife;
    private ImageView imStarlife;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        cvClients = findViewById(R.id.cvClients);
        cvTeam = findViewById(R.id.cvTeam);
        imMetlife = findViewById(R.id.imMetlife);
        imStarlife = findViewById(R.id.imStarlife);

        cvClients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ClientsActivity.class);
                startActivity(intent);
            }
        });
    }
}