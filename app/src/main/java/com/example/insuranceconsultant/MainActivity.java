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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class MainActivity extends AppCompatActivity {

    private EditText etLogin;
    private EditText etPassword;
    private TextView tvRegistration;
    private Button bLogin;
    private SharedPreferences sharedPreferences;

    FirebaseFirestore db;

    public static final String APP_PREFERENCES = "com.example.insuranceconsultant";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etLogin = findViewById(R.id.etLogin);
        etPassword = findViewById(R.id.etPassword);
        tvRegistration = findViewById(R.id.tvRegistration);
        bLogin = findViewById(R.id.bLogin);

        db = FirebaseFirestore.getInstance();

        sharedPreferences = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();

        Log.wtf("Shared", sharedPreferences.getString("Login", "Fault"));
        if (sharedPreferences.getString("Login", "Fault").equals("LoginOk")){
            Intent intent = new Intent(getApplicationContext(), FirstActivity.class);
            startActivity(intent);
            finish();
        }

        tvRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });

        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String numConsultant = etLogin.getText().toString();
                final String password = etPassword.getText().toString();

                db.collection("Consultants")
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()){
                                    for (QueryDocumentSnapshot document :
                                            task.getResult()) {
                                        if (document.getId().toString().equals(numConsultant)){
                                            if (document.getData().get("password").toString().equals(password)){
                                                editor.putString("Login","LoginOk");
                                                editor.apply();
                                                startActivity(new Intent(getApplicationContext(), FirstActivity.class));
                                                finish();
                                            }
                                            else Toast.makeText(getApplicationContext(), "Неверный пароль", Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(getApplicationContext(), "Консультант не зарегистрирован", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }
                            }
                        });

//                if (numConsultant.equals("525654")){
//                    Log.wtf("IF", "Ok");
//                    if (password.equals("50615664")){
//                        Toast.makeText(MainActivity.this, "Correct", Toast.LENGTH_SHORT).show();
//
//                        editor.putString("Login","LoginOk");
//                        editor.apply();
//                    }
//                    else Toast.makeText(MainActivity.this, "wrong password", Toast.LENGTH_SHORT).show();
//                }
//                else Toast.makeText(MainActivity.this, "consultant is not exist", Toast.LENGTH_SHORT).show();
            }
        });
    }
}