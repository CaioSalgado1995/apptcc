package com.example.clsalgado.tccws.modelo;

public class Usuario {

    private int matricula;
    private String senha;
    private int contador;

    public Usuario(){}

    public Usuario(int matricula, String senha) {
        this.matricula = matricula;
        this.senha = senha;
    }

    public Usuario(int matricula, String senha, int contador) {
        this.matricula = matricula;
        this.senha = senha;
        this.contador = contador;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getContador() { return contador; }

    public void setContador(int contador) { this.contador = contador; }
}
