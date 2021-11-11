package com.example.financeguysv1.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.financeguysv1.R;
import com.example.financeguysv1.RecyclerItemClickListener;
import com.example.financeguysv1.adapter.Adapter;
import com.example.financeguysv1.model.Despesa;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class ListaDespesasActivity extends AppCompatActivity {
private List<Despesa> despesas = new ArrayList<>();
private RecyclerView recyclerView;
private Adapter adapter = new Adapter(despesas);;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_despesas);

       Bundle dados = getIntent().getExtras();
       Despesa despesaFiltro = (Despesa) dados.getSerializable("despesa");

       if(despesaFiltro != null){
           for(Despesa x : despesas){
               despesas.remove(x);
           }

           pesquisarDespesa(despesaFiltro, adapter);
       }








        recyclerView = findViewById(R.id.recyclerView);



        //deve se passar um gerenciador de layout
        RecyclerView.LayoutManager layoutManager =  new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        //definir tamanho fixo
        recyclerView.setHasFixedSize(true);

        //definir um divisor de item
        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));

        //passar o adaptador
        recyclerView.setAdapter(adapter);


        // adicionar evento de click
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getApplicationContext(),
                        recyclerView,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                Despesa despesa = despesas.get(position);
                                Toast.makeText(
                                        getApplicationContext(), "Item Pressionado: " + despesa.getDescricao(), Toast.LENGTH_SHORT
                                ).show();

                            }

                            @Override
                            public void onLongItemClick(View view, int position) {
                                Despesa despesa = despesas.get(position);
                                Toast.makeText(
                                        getApplicationContext(), "Clique Longo: " + despesa.getDescricao(), Toast.LENGTH_SHORT
                                ).show();
                            }

                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            }
                        })
        );



    }

    private void pesquisarDespesa(Despesa despesa,Adapter adapter){
        Query query;

        query= MainActivity.myRef.child("Despesa").orderByChild("usuarioUID").equalTo(despesa.getUsuarioUID());





        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                    for (DataSnapshot obj : snapshot.getChildren()) {
                        Despesa despesaRecuperada = obj.getValue(Despesa.class);
                        if(filter(despesa,despesaRecuperada)) {
                            despesas.add(despesaRecuperada);
                            adapter.notifyDataSetChanged();
                        }

                    }
                }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }



    private boolean filter(Despesa despesa, Despesa despesaResultado){
        boolean result;
        if(!despesa.getDescricao().equals("")){
            if(despesa.getDescricao().equals(despesaResultado.getDescricao())){
                return result = true;
            }
            else{
                return result = false;
            }
        }

        if(!despesa.getData().equals("")){
            if(despesa.getData().equals(despesaResultado.getData())){
                return result = true;
            }
            else{
                return result = false;
            }
        }

        if(despesa.getTipo() != 0){
            if(despesa.getTipo()== despesaResultado.getTipo()){
                return result = true;
            }
            else{
                return  result = false;
            }
        }
        if(despesa.getValor() !=0){
            if(despesa.getValor()==despesaResultado.getValor()){
                return result = true;
            }
            else {
                return result = false;
            }
        }

        else{
            return result = false;
        }


    }


    /*@Override
    protected void onResume(){
        super.onResume();
        pesquisarDespesa( despes, adapter);
    }*/
}