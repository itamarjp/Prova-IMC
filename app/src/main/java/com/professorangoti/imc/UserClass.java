package com.professorangoti.imc;


public class UserClass {

    private Double peso;
    private Double altura;

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Double getAltura() {
        return altura;
    }

    public void setAltura(Double altura) {
        this.altura = altura;
    }

    @Override
    public String toString() {
        return ("peso = "+ peso+ " altura = "+ altura);
    }

}




