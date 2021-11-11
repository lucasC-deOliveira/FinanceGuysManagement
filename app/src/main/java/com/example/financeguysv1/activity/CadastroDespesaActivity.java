package com.example.financeguysv1.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.financeguysv1.R;
import com.example.financeguysv1.model.Despesa;
import com.google.android.material.textfield.TextInputLayout;

import java.util.UUID;

public class CadastroDespesaActivity extends AppCompatActivity {
    private Spinner spinner;
    private TextInputLayout textDescricao, textValor, textData;
    private Button buttonEnviar;
    private int tipo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_despesa);

        spinner = (Spinner) findViewById(R.id.tipo);

        textDescricao = findViewById(R.id.textInputDescricao);
        textValor = findViewById(R.id.textInputValor);
        textData = findViewById(R.id.textInputData);
        buttonEnviar = findViewById(R.id.buttonEnviar);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getApplicationContext(),
                R.array.tipoDespesasArray, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tipo = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                tipo = 1;
            }
        });



        buttonEnviar.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String descricao = textDescricao.getEditText().getText().toString();
                        double valor;
                        if(!textValor.getEditText().getText().toString().equals("") ){
                            valor = Double.parseDouble(textValor.getEditText().getText().toString());

                        }
                        else{
                            valor = 0;
                        }
                        String data = textData.getEditText().getText().toString();
                        String UID = java.util.UUID.randomUUID().toString();
                        String usuarioUID=MainActivity.usuario.getUid();

                        Despesa despesa = new Despesa(UID,usuarioUID,descricao,valor,data,tipo);

                        MainActivity.myRef.child("Despesa").child(UID).setValue(despesa);

                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                       // intent.putExtra("despesa",  despesa);


                        startActivity(intent);
                    }
                }

        );


    }
}