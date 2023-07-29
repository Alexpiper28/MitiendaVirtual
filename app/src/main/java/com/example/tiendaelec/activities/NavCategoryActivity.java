package com.example.tiendaelec.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.tiendaelec.R;
import com.example.tiendaelec.adapters.NavCategoryDetallesAdapter;
import com.example.tiendaelec.models.NavCategoryDetallesModel;
import com.example.tiendaelec.models.RecommendedModel;
import com.example.tiendaelec.models.ViewAllModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class NavCategoryActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<NavCategoryDetallesModel> list;
    NavCategoryDetallesAdapter adapter;
    FirebaseFirestore db;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_category);

        db = FirebaseFirestore.getInstance();

        progressBar = findViewById(R.id.progressbar);
        progressBar.setVisibility(View.VISIBLE);

        String type = getIntent().getStringExtra("type");

        recyclerView = findViewById(R.id.nav_cat_det_rec);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();
        adapter = new NavCategoryDetallesAdapter(this, list);
        recyclerView.setVisibility(View.GONE);
        recyclerView.setAdapter(adapter);

        if (type!= null && type.equalsIgnoreCase("laptop")) {
            db.collection("NavCategoryDetalles").whereEqualTo("type", "laptop").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                        NavCategoryDetallesModel navCategoryDetallesModel = documentSnapshot.toObject(NavCategoryDetallesModel.class);
                        list.add(navCategoryDetallesModel);
                        adapter.notifyDataSetChanged();
                        progressBar.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);

                    }

                }
            });
        }
        if (type!= null && type.equalsIgnoreCase("tablet")) {
            db.collection("NavCategoryDetalles").whereEqualTo("type", "tablet").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                        NavCategoryDetallesModel navCategoryDetallesModel = documentSnapshot.toObject(NavCategoryDetallesModel.class);
                        list.add(navCategoryDetallesModel);
                        adapter.notifyDataSetChanged();
                        progressBar.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);

                    }

                }
            });
        }
        if (type!= null && type.equalsIgnoreCase("celular")) {
            db.collection("NavCategoryDetalles").whereEqualTo("type", "celular").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                        NavCategoryDetallesModel navCategoryDetallesModel = documentSnapshot.toObject(NavCategoryDetallesModel.class);
                        list.add(navCategoryDetallesModel);
                        adapter.notifyDataSetChanged();
                        progressBar.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);

                    }

                }
            });
        }
        if (type!= null && type.equalsIgnoreCase("computadora")) {
            db.collection("NavCategoryDetalles").whereEqualTo("type", "computadora").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                        NavCategoryDetallesModel navCategoryDetallesModel = documentSnapshot.toObject(NavCategoryDetallesModel.class);
                        list.add(navCategoryDetallesModel);
                        adapter.notifyDataSetChanged();
                        progressBar.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);

                    }

                }
            });
        }
        if (type!= null && type.equalsIgnoreCase("smartwatch")) {
            db.collection("NavCategoryDetalles").whereEqualTo("type", "smartwatch").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                        NavCategoryDetallesModel navCategoryDetallesModel = documentSnapshot.toObject(NavCategoryDetallesModel.class);
                        list.add(navCategoryDetallesModel);
                        adapter.notifyDataSetChanged();
                        progressBar.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);

                    }
                }
            });
        }
        if (type!= null && type.equalsIgnoreCase("refrigeradora")) {
            db.collection("NavCategoryDetalles").whereEqualTo("type", "refrigeradora").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                        NavCategoryDetallesModel navCategoryDetallesModel = documentSnapshot.toObject(NavCategoryDetallesModel.class);
                        list.add(navCategoryDetallesModel);
                        adapter.notifyDataSetChanged();
                        progressBar.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);

                    }

                }
            });
        }

    }
}