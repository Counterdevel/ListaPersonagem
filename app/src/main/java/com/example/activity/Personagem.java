package com.example.activity;

import androidx.annotation.NonNull;

import java.io.Serializable;

import javax.xml.namespace.QName;

public class Personagem implements Serializable {

    private String nome;
    private String altura;      //criando variaveis.
    private String nascimento;
    private int id = 0;

    public Personagem(String nome, String altura, String nascimento) {

        this.nome = nome;
        this.altura = altura;       //Atribuindo valor as variaveis
        this.nascimento = nascimento;
    }

    public Personagem() {

    }

    @NonNull
    @Override
    public String toString() { //retorna o nome salvo na aplicação

        return nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAltura() {
        return altura;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }   //Convertendo os valores em strings

    public String getNascimento() {
        return nascimento;
    }

    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }


    public void setId(int id) {
        this.id = id;
    }   //Ids para identificar em qual nome da lista estamos clicando

    public int getId() {
        return id;
    }

    public boolean idValido() {
        return id > 0;
    }
}
