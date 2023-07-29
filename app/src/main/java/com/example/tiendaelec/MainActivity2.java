package com.example.tiendaelec;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tiendaelec.activities.HomeActivity;
import com.example.tiendaelec.activities.LoginActivity;
import com.example.tiendaelec.activities.RegistrationActivity;
import com.example.tiendaelec.ui.InicioFragment;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity2 extends AppCompatActivity {

    ProgressBar progressBar;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        auth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressbar);
        progressBar.setVisibility(View.GONE);
        if (auth.getCurrentUser() != null){

            Toast.makeText(this, "por favor espera ya te estás conectando", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(MainActivity2.this, HomeActivity.class));
        }

    }

    public void Login(View view) {
        progressBar.setVisibility(View.VISIBLE);
        startActivity(new Intent(MainActivity2.this, LoginActivity.class));
    }

    public void registration(View view) {
        progressBar.setVisibility(View.VISIBLE);
        startActivity(new Intent(MainActivity2.this, RegistrationActivity.class));
    }

}