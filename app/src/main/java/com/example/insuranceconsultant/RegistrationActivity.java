package com.example.insuranceconsultant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegistrationActivity extends BaseActivity {

    private EditText etLoginRegistration;
    private EditText etNameRegistration;
    private EditText etScoreRegistrationRepeat;
    private EditText etTrainerNameRegistration;
    private EditText etLevelRegistration;
    private EditText etPasswordRegistration;
    private EditText etPasswordRegistrationRepeat;
    private Button bRegistration;
    private ConsultantInfo consultant;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        etLoginRegistration = findViewById(R.id.etLoginRegistration);
        etNameRegistration = findViewById(R.id.etNameRegistration);
        etScoreRegistrationRepeat = findViewById(R.id.etScoreRegistration);
        etTrainerNameRegistration = findViewById(R.id.etTrainerNumberRegistration);
        etLevelRegistration = findViewById(R.id.etLevelRegistration);
        etPasswordRegistration = findViewById(R.id.etPasswordRegistration);
        etPasswordRegistrationRepeat = findViewById(R.id.etPasswordRegistrationRepeat);
        bRegistration = findViewById(R.id.bRegistration);


        final SharedPreferences.Editor editor = mySharedPreferences.edit();
        final Map<String, Object> consultantInfoMap = new HashMap<>();

        bRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String login = etLoginRegistration.getText().toString();
                String password = etPasswordRegistration.getText().toString();
                String passwordRepeat = etPasswordRegistrationRepeat.getText().toString();
                String name = etNameRegistration.getText().toString();
                String score = etScoreRegistrationRepeat.getText().toString();
                String trainerNumber = etTrainerNameRegistration.getText().toString();
                Integer level = Integer.parseInt(etLevelRegistration.getText().toString());

                if (password.equals(passwordRepeat)){
                    consultant = new ConsultantInfo(login, name, score, trainerNumber, password, level);
                    consultantInfoMap.put("number", consultant.getNumber());
                    consultantInfoMap.put("name", consultant.getName());
                    consultantInfoMap.put("score", consultant.getScore());
                    consultantInfoMap.put("trainerNumber", consultant.getTrainerNumber());
                    consultantInfoMap.put("level", consultant.getLevel());
                    consultantInfoMap.put("password", consultant.getPassword());

                    db.collection("Consultants").document(consultant.getNumber())
                            .set(consultantInfoMap)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
                                }
                            });

                    editor.putString("Login", login);
                    editor.apply();
                    Intent intent = new Intent(getApplicationContext(), FirstActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Repeat password is not correct!", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}