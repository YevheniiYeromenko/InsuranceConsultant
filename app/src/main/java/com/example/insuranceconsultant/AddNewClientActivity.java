package com.example.insuranceconsultant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.HashMap;
import java.util.Map;

public class AddNewClientActivity extends BaseActivity {

    private EditText etAddClientPolis;
    private EditText etAddClientName;
    private EditText etAddClientBirth;
    private EditText etAddClientTel;
    private EditText etAddClientAddr;
    private TextView bAddNewClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_client);

        etAddClientPolis = findViewById(R.id.etAddClientPolis);
        etAddClientName = findViewById(R.id.etAddClientName);
        etAddClientBirth = findViewById(R.id.etAddClientBirth);
        etAddClientTel = findViewById(R.id.etAddClientTel);
        etAddClientAddr = findViewById(R.id.etAddClientAddr);
        bAddNewClient = findViewById(R.id.bAddNewClient);

        //final String login = mySharedPreferences.getString("Login", "FAULT");

        bAddNewClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String clientPolis = etAddClientPolis.getText().toString();
                String clientName = etAddClientName.getText().toString();
                String clientBirth = etAddClientBirth.getText().toString();
                String clientTel = etAddClientTel.getText().toString();
                String clientAddr = etAddClientAddr.getText().toString();

                Map<String, String> map = new HashMap<>();
                map.put("numPolis", clientPolis);
                map.put("name", clientName);
                map.put("dateBirth", clientBirth);
                map.put("telephoneNum", clientTel);
                map.put("address", clientAddr);
                map.put("consultantNum", mySharedPreferences.getString("Login", "FAULT"));

                db.collection("Clients").document(clientPolis)
                        .set(map)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(getApplicationContext(), "Добавлено", Toast.LENGTH_SHORT).show();
                            }
                        });

                finish();

            }
        });
    }
}