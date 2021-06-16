package com.example.calculadora;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText input1, input2;
    private Spinner spinner;
    private Button button;
    private RecyclerView recyclerView;
    private List<Calculo> calculos;
    private ArrayAdapter<String> adapterSpinner;
    private AdaptadorDoRecyclerView adaptadorDoRecyclerView;
    private int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        input1 = findViewById(R.id.input1);
        input2 = findViewById(R.id.input2);
        spinner = findViewById(R.id.spinner);
        button = findViewById(R.id.button);
         recyclerView = findViewById(R.id.recyclerView);

        this.calculos = new ArrayList<>();

        adapterSpinner = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                new String[]{"+", "-", "x", "/"}
        );
        spinner.setAdapter(adapterSpinner);


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        // Valores e Adapter
        adaptadorDoRecyclerView = new AdaptadorDoRecyclerView(calculos);
        // vincilar adaptador e RecyclerView
        recyclerView.setAdapter(adaptadorDoRecyclerView);

        adaptadorDoRecyclerView.getList().addAll(calculos);

        adaptadorDoRecyclerView.eventoDeletarCalculo = new EventoDeClickCustomizado<Calculo>() {
            @Override
            public void onItemClick(Calculo item) {
                adaptadorDoRecyclerView.getList().remove(item);
                adaptadorDoRecyclerView.notifyDataSetChanged();
            }
        };

        adaptadorDoRecyclerView.eventoRepetirCalculo = new EventoDeClickCustomizado<Calculo>() {
            @Override
            public void onItemClick(Calculo item) {
                // EDIÇÃO NO BANCO DE DADOS
                input1.setText(String.valueOf(item.getValor1()));
                input2.setText(String.valueOf(item.getValor2()));
                position = adapterSpinner.getPosition(item.getOperacao());
                spinner.setSelection(position);
            }
        };
    }

    public void doCalculo(View v) {
        System.out.println("do Calculo");
        String v1 = input1.getText().toString();
        String v2 = input2.getText().toString();
        String op = (String) spinner.getSelectedItem();

        if (v1.isEmpty()) input1.setError("Campo obrigatorio");
        else if (v2.isEmpty()) input2.setError("Campo obrigatorio");

        else {

            Double resultado = calcular(Double.parseDouble(v1), Double.parseDouble(v2), op);

            Calculo calculo = new Calculo(
                    Double.parseDouble(v1),
                    Double.parseDouble(v2),
                    op,
                    resultado
            );
            adaptadorDoRecyclerView.getList().add(calculo);
            adaptadorDoRecyclerView.notifyDataSetChanged();
        }


    }

    public Double calcular(Double valor1, Double valor2, String op) {
        if (op == "+") {
            return valor1 + valor2;
        }
        if (op == "-") {
            return valor1 - valor2;
        }
        if (op == "x") {
            return valor1 * valor2;
        }
        if (op == "/") {
            return valor1 / valor2;
        }
        return null;
//    }
    }
}