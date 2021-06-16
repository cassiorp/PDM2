package com.example.calculadora;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AdaptadorDoRecyclerView extends RecyclerView.Adapter<ModeloDeLinha> {
    // valores
    private final List<Calculo> list;

    private Context ativityEmExecucao;

    // eventos
    public EventoDeClickCustomizado<Calculo> eventoDeletarCalculo = null;
    public EventoDeClickCustomizado<Calculo> eventoRepetirCalculo = null;

    public AdaptadorDoRecyclerView(List<Calculo> valores) {
        list = new ArrayList<>();
        for (Calculo a : valores) {
            list.add(a);
        }
    }

    public List<Calculo> getList() {
        return list;
    }

    /**
     * Método executado sempre que a tela é executada
     * @return
     */
    @Override
    public ModeloDeLinha onCreateViewHolder(ViewGroup parent, int viewType) {
        ativityEmExecucao = parent.getContext();
        final ModeloDeLinha holder = new ModeloDeLinha(LayoutInflater.from(ativityEmExecucao)
                .inflate(R.layout.linha_recycler_view, parent, false));

        if(eventoDeletarCalculo != null){
            holder.imageButtonExcluir.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // pega o dado
                    Calculo dado = list.get(holder.indiceValor);
                    // executa o evento
                    eventoDeletarCalculo.onItemClick(dado);
                }
            });
        }

        if(eventoRepetirCalculo != null){
            holder.imageButtonRepetir.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // pega o dado
                    Calculo dado = list.get(holder.indiceValor);
                    // executa o evento
                    eventoRepetirCalculo.onItemClick(dado);
                }
            });
        }


        return holder;
    }
    @Override
    public void onBindViewHolder(ModeloDeLinha holder, int position) {
        // dado selecionado
        Calculo conteudoLinha = list.get(position);
        // exibe dados
        holder.valor1.setText(String.valueOf(conteudoLinha.getValor1()));
        holder.valor2.setText(String.valueOf(conteudoLinha.getValor2()));
        holder.operacao.setText(conteudoLinha.getOperacao());
        holder.resultado.setText(" = " + String.valueOf(conteudoLinha.getResultado()));
        // atualiza indice do item exibido
        holder.atualizaValorExibido(position);
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

}