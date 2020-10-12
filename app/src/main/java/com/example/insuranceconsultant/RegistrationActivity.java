package com.example.insuranceconsultant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

//import com.google.firebase.firestore.FirebaseFirestore;

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
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        etLoginRegistration = findViewById(R.id.etLoginRegistration);
        etPasswordRegistration = findViewById(R.id.etPasswordRegistration);
        etPasswordRegistrationRepeat = findViewById(R.id.etPasswordRegistrationRepeat);
        bRegistration = findViewById(R.id.bRegistration);

        db = FirebaseFirestore.getInstance();

        final Map<String, Object> consultantInfo = new HashMap<>();
//        user.put("first", "Ada");
//        user.put("last", "Lovelace");
//        user.put("born", 1815);

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

                    startActivity(new Intent(getApplicationContext(), FirstActivity.class));
                    finish();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Repeat password is not correct!", Toast.LENGTH_SHORT).show();
                }
            }
        });



// Add a new document with a generated ID
/*        db.collection("users")
                .add(consultantInfo)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.wtf("TAG" , "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.wtf("TAG", "Error adding document", e);
                    }
                });*/
    }
}