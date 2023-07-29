package com.example.tiendaelec.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ButtonBarLayout;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.tiendaelec.R;
import com.example.tiendaelec.models.ViewAllModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class DetallesActivity extends AppCompatActivity {

    TextView quantity;
    int totalQuantity = 1;
    int totalPrecio = 0;
    ImageView detailedImg;
    TextView price, rating, descripcion;
    Button addToCart;
    ImageView addItem, removeItem;

    Toolbar toolbar;

    FirebaseFirestore firestore;
    FirebaseAuth auth;

    ViewAllModel viewAllModel = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles);

        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        final Object object = getIntent().getSerializableExtra("detail");
        if(object instanceof ViewAllModel){
            viewAllModel = (ViewAllModel) object;
        }

        quantity = findViewById(R.id.quantity);
        detailedImg = findViewById(R.id.detailed_img);
        addItem = findViewById(R.id.add_item);
        removeItem = findViewById(R.id.remove_item);

        price = findViewById(R.id.detailed_precio);
        rating = findViewById(R.id.detailed_rating);
        descripcion = findViewById(R.id.detailed_des);

        if (viewAllModel != null){
            Glide.with(getApplicationContext()).load(viewAllModel.getImg_url()).into(detailedImg);
            rating.setText(viewAllModel.getRating());
            descripcion.setText(viewAllModel.getDescripcion());
            price.setText("Precio : "+viewAllModel.getPrice()+" soles");

            totalPrecio = viewAllModel.getPrice() * totalQuantity;

            if(viewAllModel.getType().equals("laptop")){
                price.setText("Precio: "+viewAllModel.getPrice()+" soles");
                totalPrecio = viewAllModel.getPrice() * totalQuantity;
            }
            if(viewAllModel.getType().equals("televisor")){
                price.setText("Precio: "+viewAllModel.getPrice()+" soles");
                totalPrecio = viewAllModel.getPrice() * totalQuantity;
            }

        }

        addToCart = findViewById(R.id.add_to_cart);
        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addedToCart();
            }
        });

        addItem.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(totalQuantity < 10){
                    totalQuantity++;
                    quantity.setText(String.valueOf(totalQuantity));
                    totalPrecio = viewAllModel.getPrice() * totalQuantity;

                }
            }
        });
        removeItem.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(totalQuantity > 1){
                    totalQuantity--;
                    quantity.setText(String.valueOf(totalQuantity));
                    totalPrecio = viewAllModel.getPrice() * totalQuantity;

                }
            }
        });

    }

    private void addedToCart() {
        String saveCurrentDate, saveCurrentTime;
        Calendar calForDate = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("MM dd, yyyy");
        saveCurrentDate = currentDate.format(calForDate.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calForDate.getTime());

        final HashMap<String, Object> cartMap = new HashMap<>();

        cartMap.put("productName", viewAllModel.getName());
        cartMap.put("productPrice", price.getText().toString());
        cartMap.put("currentDate", saveCurrentDate);
        cartMap.put("currentTime", saveCurrentTime);
        cartMap.put("totalQuantity", quantity.getText().toString() );
        cartMap.put("totalPrecio", totalPrecio);

        firestore.collection("CurrentUser").document(auth.getCurrentUser().getUid())
                .collection("AddToCart").add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        Toast.makeText(DetallesActivity.this, "Agregado al carrito", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });

    }
}