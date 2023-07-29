package com.example.tiendaelec.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.tiendaelec.R;
import com.example.tiendaelec.activities.LoginActivity;
import com.example.tiendaelec.models.UserModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import de.hdodenhof.circleimageview.CircleImageView;

public class PerfilFragment extends Fragment {

    CircleImageView profileImg;
    EditText name, email, number, address;
    Button update;

    FirebaseStorage storage;
    FirebaseAuth auth;
    FirebaseDatabase database;
    Button cerrarSesion;
    private ActivityResultLauncher<String> mGetContentLauncher;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_perfil, container, false);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        storage = FirebaseStorage.getInstance();

        profileImg = root.findViewById(R.id.profile_img);
        name = root.findViewById(R.id.profile_name);
        email = root.findViewById(R.id.profile_email);
        number = root.findViewById(R.id.profile_number);
        address = root.findViewById(R.id.profile_address);
        update = root.findViewById(R.id.update);

        database.getReference().child("Users").child(FirebaseAuth.getInstance().getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        UserModel userModel = snapshot.getValue(UserModel.class);

                        Glide.with(getContext()).load(userModel.getProfileImg()).into(profileImg);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

        String userId = FirebaseAuth.getInstance().getUid();
        if (userId != null) {
            DatabaseReference userRef = database.getReference().child("Users").child(userId);
            userRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        UserModel userModel = snapshot.getValue(UserModel.class);

                        // Mostrar la información del usuario en los campos
                        name.setText(userModel.getName());
                        email.setText(userModel.getEmail());
                        number.setText(userModel.getNumber());
                        address.setText(userModel.getAddress());
                        Glide.with(getContext()).load(userModel.getProfileImg()).into(profileImg);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
        mGetContentLauncher = registerForActivityResult(new ActivityResultContracts.GetContent(),
                new ActivityResultCallback<Uri>() {
                    @Override
                    public void onActivityResult(Uri result) {
                        if (result != null) {
                            profileImg.setImageURI(result);

                            final StorageReference reference = storage.getReference().child("profile_picture")
                                    .child(FirebaseAuth.getInstance().getUid());

                            reference.putFile(result).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                    Toast.makeText(getContext(), "Subido", Toast.LENGTH_SHORT).show();

                                    reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                        @Override
                                        public void onSuccess(Uri uri) {

                                            database.getReference().child("Users").child(FirebaseAuth.getInstance().getUid())
                                                    .child("profileImg").setValue(uri.toString());
                                            Toast.makeText(getContext(), "Imagen de perfil cargada", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                            });
                        }
                    }
                });

        profileImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGetContentLauncher.launch("image/*");
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateUserProfile();
            }
        });

        cerrarSesion = root.findViewById(R.id.cerrar_sesion);
        cerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOutUser();
            }
        });

        return root;
    }

    private void updateUserProfile() {
        // Obtener los valores ingresados por el usuario
        String newName = name.getText().toString().trim();
        String newEmail = email.getText().toString().trim();
        String newNumber = number.getText().toString().trim();
        String newAddress = address.getText().toString().trim();

        // Validar que los campos no estén vacíos
        if (newName.isEmpty() || newEmail.isEmpty() || newNumber.isEmpty() || newAddress.isEmpty()) {
            Toast.makeText(getContext(), "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        // Actualizar la información del usuario en la base de datos de Firebase
        String userId = FirebaseAuth.getInstance().getUid();
        if (userId != null) {
            DatabaseReference userRef = database.getReference().child("Users").child(userId);
            userRef.child("name").setValue(newName);
            userRef.child("email").setValue(newEmail);
            userRef.child("number").setValue(newNumber);
            userRef.child("address").setValue(newAddress).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Toast.makeText(getContext(), "Perfil actualizado correctamente", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
    private void signOutUser() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(getContext(), LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
