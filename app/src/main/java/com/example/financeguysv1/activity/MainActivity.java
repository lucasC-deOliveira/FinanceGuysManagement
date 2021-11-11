package com.example.financeguysv1.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.financeguysv1.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    public static  FirebaseDatabase dataBase;
    public static DatabaseReference myRef;
    public static FirebaseAuth usuario;

    private Button inserirDespesa, listaDespesa, perfil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iniciarFirebase();
        inserirDespesa = findViewById(R.id.buttonInserirDespesa);
        listaDespesa = findViewById(R.id.buttonLista);
        perfil = findViewById(R.id.buttonPerfil);
        usuario =  FirebaseAuth.getInstance();




        if(usuario.getCurrentUser()==null){
            Intent intent = new Intent(getApplicationContext(), TelaInicial.class);
            startActivity(intent);
            finish();
        }

        perfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                perfilUsuario();
            }
        });


        inserirDespesa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CadastroDespesaActivity.class);
                startActivity(intent);
            }
        });

        listaDespesa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), FiltroListaActivity.class);
                startActivity(intent);
            }
        });




    }
    private void perfilUsuario() {
        Intent intent = new Intent(getApplicationContext(),TelaUsuario.class);
        startActivity(intent);

    }

    private void iniciarFirebase(){
        dataBase = FirebaseDatabase.getInstance();
        myRef = dataBase.getReference();
    }
}