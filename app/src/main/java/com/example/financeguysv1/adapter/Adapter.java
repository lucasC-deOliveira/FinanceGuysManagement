package com.example.financeguysv1.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.financeguysv1.R;
import com.example.financeguysv1.model.Despesa;


import java.util.List;

//o recyclerView recicla as visualizações ao rolar a tela

//passar viewholder
//mesmo ao rolar o scroll
public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    private List<Despesa> lista;

    public Adapter(List<Despesa> lista){
    this.lista = lista;
    }
    @NonNull
    @Override
    //metodo para criar as primeiras view
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //passar adapter de um layout de item para ser exibido
        //converter item xml para view
        View item_lista = LayoutInflater.from(parent.getContext()).inflate(R.layout.adpter_item_lista, parent, false);


        return new MyViewHolder(item_lista);
    }

    //metodo que exibe os itens
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //definindo objeto para recuperar os dados
        Despesa  despesa = lista.get(position);

        String string;
        int tipo = despesa.getTipo();



       switch(tipo){
           case 1:
               string = "Educação";
               holder.icon.setImageResource(R.drawable.ic_educacao);
               break;
           case 2:
               string = "Alimentação";
               holder.icon.setImageResource(R.drawable.ic_alimentacao);
               break;

           case 3:
               string = "Transporte";
               holder.icon.setImageResource(R.drawable.ic_transporte);
               break;
           case 4:
               string = "Roupas";
               holder.icon.setImageResource(R.drawable.ic_roupas);
               break;
           case 5:
               string = "Jogos";
               holder.icon.setImageResource(R.drawable.ic_jogos1);
               break;
           case 6:
               string = "Musica";
               holder.icon.setImageResource(R.drawable.ic_musica);
               break;
           case 7:
               string = "Lazer";
               holder.icon.setImageResource(R.drawable.ic_lazer);
               break;
           case 8:
               string = "Viagem";
               holder.icon.setImageResource(R.drawable.ic_viagem);
               break;

           case 9:
               string = "Saúde";
               holder.icon.setImageResource(R.drawable.ic_saude);
               break;
           default:
               string = "";
               break;
       }



        //definindo valores
        holder.descricao.setText(despesa.getDescricao());
        holder.tipo.setText(despesa.getTipo()+"");
        holder.tipo.setText(string);
        holder.data.setText(despesa.getData());
        holder.valor.setText("R$ "+despesa.getValor());
    }

    //metodo que define quantidade de itens que serão exibidos
    @Override
    public int getItemCount() {
        return lista.size();
    }

    //inner class
    public class MyViewHolder extends RecyclerView.ViewHolder{
        //criar atributos que vao ser exibidos
        TextView descricao;
        TextView data;
        TextView valor;
        TextView tipo;
        ImageView icon;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            //recuperar componentes de tela
            descricao= itemView.findViewById(R.id.textDescricao);
            tipo = itemView.findViewById(R.id.textTipo);
            data = itemView.findViewById(R.id.textData);
            valor = itemView.findViewById(R.id.textValor);
            icon = itemView.findViewById(R.id.imageViewIcon);
        }
    }
}
