package com.example.insuranceconsultant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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

public class MainActivity extends BaseActivity {

    private EditText etLogin;
    private EditText etPassword;
    private TextView tvRegistration;
    private Button bLogin;
   // private SharedPreferences sharedPreferences;

    //FirebaseFirestore db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etLogin = findViewById(R.id.etLogin);
        etPassword = findViewById(R.id.etPassword);
        tvRegistration = findViewById(R.id.tvRegistration);
        bLogin = findViewById(R.id.bLogin);

        //db = FirebaseFirestore.getInstance();

        //sharedPreferences = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = mySharedPreferences.edit();

        Log.wtf("Shared", mySharedPreferences.getString("Login", "Fault"));

        //boolean chek = chekLogin();
        chekLogin();

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
                                                editor.putString("Login",numConsultant);
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

            }
        });
    }

    private void chekLogin() {
        final String sharedLogin = mySharedPreferences.getString("Login", "Fault");
        //final boolean[] chekLogin = {false};
        if (hasConnection(getApplicationContext())) {
            db.collection("Consultants")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document :
                                        task.getResult()) {
                                    if (document.getId().equals(sharedLogin)) {
                                        Log.wtf("---CHEK---", "===OK===");
                                        startActivity(new Intent(getApplicationContext(), FirstActivity.class));
                                        finish();
                                    } else Log.wtf("---CHEK---", "===No OK===");
                                }
                            } else Log.wtf("FireStore", "FAULT RESULT");
                        }
                    });
        }
        else {
            Toast.makeText(getApplicationContext(), "No connection", Toast.LENGTH_SHORT).show();
            etLogin.setVisibility(View.GONE);
            etPassword.setVisibility(View.GONE);
            tvRegistration.setVisibility(View.GONE);
            bLogin.setVisibility(View.GONE);
        }
    }

    public static boolean hasConnection(final Context context)
    {
        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (wifiInfo != null && wifiInfo.isConnected())
        {
            return true;
        }
        wifiInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (wifiInfo != null && wifiInfo.isConnected())
        {
            return true;
        }
        wifiInfo = cm.getActiveNetworkInfo();
        if (wifiInfo != null && wifiInfo.isConnected())
        {
            return true;
        }
        return false;
    }
}