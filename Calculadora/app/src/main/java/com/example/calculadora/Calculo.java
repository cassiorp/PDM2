package com.example.calculadora;


public class Calculo {

    private Double valor1;
    private Double valor2;
    private String operacao;
    private Double resultado;

    public Calculo(Double valor1, Double valor2, String operacao, Double resultado) {
        this.valor1 = valor1;
        this.valor2 = valor2;
        this.operacao = operacao;
        this.resultado = resultado;
    }



    public Double getValor1() {
        return valor1;
    }

    public void setValor1(Double valor1) {
        this.valor1 = valor1;
    }

    public Double getValor2() {
        return valor2;
    }

    public void setValor2(Double valor2) {
        this.valor2 = valor2;
    }

    public Double getResultado() {
        return resultado;
    }

    public void setResultado(Double resultado) {
        this.resultado = resultado;
    }

    public String getOperacao() {
        return operacao;
    }

    public void setOperacao(String operacao) {
        this.operacao = operacao;
    }

}
