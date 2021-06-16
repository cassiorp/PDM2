package com.example.calculadora;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class ModeloDeLinha extends RecyclerView.ViewHolder {

    public int indiceValor;
    public TextView valor1;
    public TextView valor2;
    public TextView operacao;
    public TextView resultado;
    public ImageButton imageButtonExcluir;
    public ImageButton imageButtonRepetir;

    public ModeloDeLinha(View itemView) {
        super(itemView);
        valor1 = (TextView) itemView.findViewById(R.id.valor1);
        valor2 = itemView.findViewById(R.id.valor2);
        operacao = itemView.findViewById(R.id.operacao);
        resultado = itemView.findViewById(R.id.resultado);
        imageButtonExcluir = itemView.findViewById(R.id.delete);
        imageButtonRepetir = itemView.findViewById(R.id.edit);
        indiceValor = -1;
    }

    public void atualizaValorExibido(int indiceValor){
        this.indiceValor = indiceValor;
    }

}