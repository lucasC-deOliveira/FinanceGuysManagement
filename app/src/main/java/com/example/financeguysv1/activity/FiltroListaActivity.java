package com.example.financeguysv1.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.financeguysv1.R;
import com.example.financeguysv1.model.Despesa;
import com.google.android.material.textfield.TextInputLayout;

public class FiltroListaActivity extends AppCompatActivity {

    private Spinner spinner;
    private TextInputLayout textDescricao, textValor, textData;
    private Button buttonListar;
    private TextView textErro;
    private int tipo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtro_lista);

        spinner = (Spinner) findViewById(R.id.tipo);

        textDescricao = findViewById(R.id.textInputDescricao);
        textValor = findViewById(R.id.textInputValor);
        textData = findViewById(R.id.textInputData);
        textErro = findViewById(R.id.textViewErrorFiltro);
        buttonListar = findViewById(R.id.buttonListar);


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
                tipo = 0;
            }
        });

        buttonListar.setOnClickListener(
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

                        int count = 0;

                        if(!descricao.equals("")){
                            count++;
                        }
                        if(!data.equals("")){
                            count++;
                        }
                        if(valor > 0){
                            count++;
                        }
                        if (tipo != 0) {
                            count++;
                        }

                        if(count == 1){
                            Despesa despesa = new Despesa(UID,usuarioUID,descricao,valor,data,tipo);


                            Intent intent = new Intent(getApplicationContext(), ListaDespesasActivity.class);
                            intent.putExtra("despesa",  despesa);

                            textErro.setText("");
                            startActivity(intent);
                            finish();
                        }
                        else{
                            textErro.setText("Escolha Apenas Um Filtro!");
                        }


                    }
                }

        );
    }
}