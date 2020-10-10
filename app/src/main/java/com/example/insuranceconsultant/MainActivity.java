package com.example.insuranceconsultant;

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

public class MainActivity extends AppCompatActivity {

    private EditText etLogin;
    private EditText etPassword;
    private TextView tvRegistration;
    private Button bLogin;
    private SharedPreferences sharedPreferences;

    public static final String APP_PREFERENCES = "com.example.insuranceconsultant";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etLogin = findViewById(R.id.etLogin);
        etPassword = findViewById(R.id.etPassword);
        tvRegistration = findViewById(R.id.tvRegistration);
        bLogin = findViewById(R.id.bLogin);

        sharedPreferences = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();

        Log.wtf("Shared", sharedPreferences.getString("Login", "Fault"));
        if (sharedPreferences.getString("Login", "Fault").equals("LoginOk")){
            Intent intent = new Intent(getApplicationContext(), FirstActivity.class);
            startActivity(intent);
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
                String numConsultant = etLogin.getText().toString();
                String password = etPassword.getText().toString();
                Log.wtf("LOGIN", numConsultant);
                Log.wtf("LOGIN", password);

                if (numConsultant.equals("525654")){
                    Log.wtf("IF", "Ok");
                    if (password.equals("50615664")){
                        Toast.makeText(MainActivity.this, "Correct", Toast.LENGTH_SHORT).show();

                        editor.putString("Login","LoginOk");
                        editor.apply();
                    }
                    else Toast.makeText(MainActivity.this, "wrong password", Toast.LENGTH_SHORT).show();
                }
                else Toast.makeText(MainActivity.this, "consultant is not exist", Toast.LENGTH_SHORT).show();
            }
        });
    }
}