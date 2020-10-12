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

public class RegistrationActivity extends AppCompatActivity {

    private EditText etLoginRegistration;
    private EditText etPasswordRegistration;
    private EditText etPasswordRegistrationRepeat;
    private Button bRegistration;
    private FirebaseFirestore db;
    private SharedPreferences sharedPreferences;

    public static final String APP_PREFERENCES = "com.example.insuranceconsultant";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        etLoginRegistration = findViewById(R.id.etLoginRegistration);
        etPasswordRegistration = findViewById(R.id.etPasswordRegistration);
        etPasswordRegistrationRepeat = findViewById(R.id.etPasswordRegistrationRepeat);
        bRegistration = findViewById(R.id.bRegistration);

        db = FirebaseFirestore.getInstance();
        sharedPreferences = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();

        final Map<String, Object> consultantInfo = new HashMap<>();

        bRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String login = etLoginRegistration.getText().toString();
                String password = etPasswordRegistration.getText().toString();
                String passwordRepeat = etPasswordRegistrationRepeat.getText().toString();

                if (password.equals(passwordRepeat)){
                    consultantInfo.put("password", password);
                    db.collection("Consultants").document(login)
                            .set(consultantInfo)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
                                }
                            });

                    editor.putString("Login", login);
                    editor.apply();
                    startActivity(new Intent(getApplicationContext(), FirstActivity.class));
                    finish();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Repeat password is not correct!", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}