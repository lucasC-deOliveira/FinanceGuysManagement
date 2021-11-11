package com.example.financeguysv1.model;

import android.content.Intent;

import java.io.Serializable;

public class Despesa implements Serializable {
    private String UID;
    private String usuarioUID;
    private String descricao;
    private Double valor;
    private String data;
    private Integer tipo;

    public Despesa(String UID,String usuarioUID,String descricao, double valor, String data, int tipo) {
        this.descricao = descricao;
        this.valor = valor;
        this.data = data;
        this.tipo = tipo;
        this.UID = UID;
        this.usuarioUID = usuarioUID;
    }

    public Despesa(){}

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public void setUUID(String UID) {
        this.UID = UID;
    }

    public String getUsuarioUID() {
        return usuarioUID;
    }

    public void setUsuarioUID(String usuarioUID) {
        this.usuarioUID = usuarioUID;
    }

    @Override
    public String toString() {
        return "Despesa{" +
                "UID='" + UID + '\'' +
                ", usuarioUID='" + usuarioUID + '\'' +
                ", descricao='" + descricao + '\'' +
                ", valor=" + valor +
                ", data='" + data + '\'' +
                ", tipo=" + tipo +
                '}';
    }
}
