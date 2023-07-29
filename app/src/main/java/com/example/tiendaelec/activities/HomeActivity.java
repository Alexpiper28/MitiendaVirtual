package com.example.tiendaelec.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.example.tiendaelec.R;
import com.example.tiendaelec.ui.InicioFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {

    /*ProgressBar progressBar;
    FirebaseAuth auth;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        /*auth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressbar);
        progressBar.setVisibility(View.GONE);

        if (auth.getCurrentUser() != null){
            //progressBar.setVisibility(View.VISIBLE);
            startActivity(new Intent(HomeActivity.this, InicioFragment.class));
            Toast.makeText(this, "por favor espera ya te estás conectando", Toast.LENGTH_SHORT).show();
            setupNavegacion();
            finish();
        }*/
        setupNavegacion();
    }

    public void Login(View view) {
        startActivity(new Intent(HomeActivity.this, LoginActivity.class));
    }

    public void registration(View view) {
        startActivity(new Intent(HomeActivity.this, RegistrationActivity.class));
    }
    private void setupNavegacion(){
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_hostfragment);
        NavigationUI.setupWithNavController(
                bottomNavigationView,
                navHostFragment.getNavController()
        );
    }
}