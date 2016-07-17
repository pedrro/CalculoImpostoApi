package com.calculo.imposto.model;


import org.apache.commons.math3.util.Precision;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Document(collection = "usuario")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    public String nome;
    public double salario;
    private double inss, segVida, vr, vt, custoTotal;

    public Usuario(String nome, Double salario) {
        this.nome = nome;
        this.salario = salario;
        setInss(salario);
        setSegVida(salario);
        setVr(salario);
        setVt(salario);
        setCustoTotal();
    }


    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    public void setInss(Double salario) {
        this.inss = Precision.round(salario * 0.11, 2);
    }

    public void setSegVida(Double salario) {
        this.segVida = Precision.round(salario * 0.20, 2);
    }
    public void setVr(Double salario) {
        this.vr = Precision.round(salario * 0.13, 2);
    }
    public void setVt(Double salario) {
        this.vt = Precision.round(salario * 0.06, 2);
    }

    public void setCustoTotal(){
        this.custoTotal = Precision.round(getSalario() + getCustoTotal() + getInss() + getSegVida() + getVr() + getVt(), 2);
    }
}
