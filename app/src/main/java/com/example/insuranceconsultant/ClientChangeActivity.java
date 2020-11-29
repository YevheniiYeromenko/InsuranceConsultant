package com.example.insuranceconsultant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.HashMap;
import java.util.Map;

public class ClientChangeActivity extends BaseActivity {

    private ClientInfo client;
    private EditText etChangeClientPolis;
    private EditText etChangeClientName;
    private EditText etChangeClientBirth;
    private EditText etChangeClientTel;
    private EditText etChangeClientAddr;
    private TextView bChangeClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_change);

        etChangeClientPolis = findViewById(R.id.etChangeClientPolis);
        etChangeClientName = findViewById(R.id.etChangeClientName);
        etChangeClientBirth = findViewById(R.id.etChangeClientBirth);
        etChangeClientTel = findViewById(R.id.etChangeClientTel);
        etChangeClientAddr = findViewById(R.id.etChangeClientAddr);
        bChangeClient = findViewById(R.id.bChangeClient);

        if (getIntent().hasExtra("CLIENT")) {
            client = getIntent().getParcelableExtra("CLIENT");

            etChangeClientPolis.setText(client.getNumPolis());
            etChangeClientName.setText(client.getName());
            etChangeClientBirth.setText(client.getDateBirth());
            etChangeClientTel.setText(client.getTelephoneNum());
            etChangeClientAddr.setText(client.getAddress());
        }

        bChangeClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String clientPolis = etChangeClientPolis.getText().toString();
                String clientName = etChangeClientName.getText().toString();
                String clientBirth = etChangeClientBirth.getText().toString();
                String clientTel = etChangeClientTel.getText().toString();
                String clientAddr = etChangeClientAddr.getText().toString();

                Map<String, String> map = new HashMap<>();
                map.put("numPolis", clientPolis);
                map.put("name", clientName);
                map.put("dateBirth", clientBirth);
                map.put("telephoneNum", clientTel);
                map.put("address", clientAddr);
                map.put("consultantNum", mySharedPreferences.getString("Login", "FAULT"));

                db.collection("Clients").document(client.getNumPolis())
                        .set(map)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Log.d("TAG", "DocumentSnapshot successfully written!");
                                finish();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w("TAG", "Error writing document", e);
                            }
                        });
            }
        });

    }
}