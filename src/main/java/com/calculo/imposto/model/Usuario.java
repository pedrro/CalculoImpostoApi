package com.calculo.imposto.model;


import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    public UUID id;
    public String nome;
    public double salario, inss, segVida, vr, vt, custoTotal;

    public Usuario(String nome, Double salario) {
        this.nome = nome;
        this.salario = salario;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    public void setInss(Double salario) {
        this.inss = salario * 0.11;
    }

    public void setSegVida(Double salario) {
        this.segVida = salario * 0.20;
    }
    public void setVr(Double salario) {
        this.vr = salario * 0.13;
    }
    public void setVt(Double salario) {
        this.vt = salario * 0.06;
    }

    public void setCustoTotal(){
        this.custoTotal = getSalario() + getCustoTotal() + getInss() + getSegVida() + getVr() + getVt();
    }
}
