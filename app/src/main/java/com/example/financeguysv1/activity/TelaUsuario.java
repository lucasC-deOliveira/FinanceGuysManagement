package com.example.financeguysv1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import com.example.financeguysv1.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class TelaUsuario extends AppCompatActivity {
private TextView txtEmail;
private TextView txtUsuario ;
private Button btnSair;
private String usuariopr ;
FirebaseFirestore fs = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_usuario);
        componestes();
        btnSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(TelaUsuario.this,TelaInicial.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        usuariopr = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DocumentReference dp = fs.collection("Usuarios").document(usuariopr);
        dp.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                if(value != null){}
                    txtUsuario.setText(value.getString("Nome"));
                    txtEmail.setText(value.getString("E-mail"));

             }
        }); }

    private void componestes(){
        txtUsuario = findViewById(R.id.idNomeDoUsuario);
        txtEmail = findViewById(R.id.idEmailDoUsuario);
        btnSair = (Button)findViewById(R.id.idSair);
    }




}