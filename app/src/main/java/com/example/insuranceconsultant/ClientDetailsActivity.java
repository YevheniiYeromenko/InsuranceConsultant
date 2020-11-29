package com.example.insuranceconsultant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ClientDetailsActivity extends AppCompatActivity {

    private TextView tvClDetailPolis;
    private TextView tvClDetailName;
    private TextView tvClDetailBirth;
    private TextView tvClDetailTel;
    private TextView tvClDetailAddr;
    private ImageView imSendMessage;
    private ImageView imPhoneCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_details);

        tvClDetailPolis = findViewById(R.id.tvClDetailPolis);
        tvClDetailName = findViewById(R.id.tvClDetailName);
        tvClDetailBirth = findViewById(R.id.tvClDetailBirth);
        tvClDetailTel = findViewById(R.id.tvClDetailTel);
        tvClDetailAddr = findViewById(R.id.tvClDetailAddr);

        imSendMessage = findViewById(R.id.imSendMessage);
        imPhoneCall = findViewById(R.id.imPhoneCall);


        final ClientInfo client;
        client = getIntent().getParcelableExtra("CLIENT_INFO");

        tvClDetailPolis.setText(client.getNumPolis());
        tvClDetailName.setText(client.getName());
        tvClDetailBirth.setText(client.getDateBirth());
        tvClDetailTel.setText(client.getTelephoneNum());
        tvClDetailAddr.setText(client.getAddress());

        imPhoneCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialPhoneNumber(client.getTelephoneNum());
            }
        });

        imSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage(client.getNumPolis() + "\n" +
                            client.getName() + "\n" +
                            client.getDateBirth() + "\n" +
                            client.getTelephoneNum() + "\n" +
                            client.getAddress() + "\n");
            }
        });


    }

    private void dialPhoneNumber(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        if (intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        }
    }

    private void sendMessage(String message){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setData(Uri.parse("smsto:"));
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, message);
        if (intent.resolveActivity(getPackageManager()) != null){
            startActivity(Intent.createChooser(intent, "Chose"));
        }
    }


}