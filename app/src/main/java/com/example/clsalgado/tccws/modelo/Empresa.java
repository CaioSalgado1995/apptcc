package com.example.clsalgado.tccws.modelo;

public class Empresa {
    private String cnpj;

    private String nome;

    private boolean representanteLegal;

    public Empresa(String cnpj, String nome, boolean representanteLegal) {
        this.cnpj = cnpj;
        this.nome = nome;
        this.representanteLegal = representanteLegal;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNome() {
        return nome;
    }
}