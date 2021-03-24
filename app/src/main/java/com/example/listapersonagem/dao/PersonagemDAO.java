package com.example.listapersonagem.dao;

import com.example.activity.Personagem;

import java.util.ArrayList;
import java.util.List;

public class PersonagemDAO {

    private final static List<Personagem> personagens = new ArrayList<>();

    public void salva(Personagem personagemSalvo) {

        personagens.add(personagemSalvo);

    }

    public List<Personagem> todos() {
        return new ArrayList<>(personagens);
    }
}
