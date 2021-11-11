package com.example.financeguysv1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.financeguysv1.R;


public class TelaInicial extends AppCompatActivity {
     private Button txtBtnCadastra ;
     private Button txtBtnlogar ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_inicial_activity);

        txtBtnCadastra=findViewById(R.id.buttonCadastro);
        txtBtnlogar =findViewById(R.id.buttonLogin);

        txtBtnCadastra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CadastroActivity.class);
                startActivity(intent);
            }
        });


        txtBtnlogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
            }
        });
    }



}