package com.example.listapersonagem.dao;

import com.example.activity.Personagem;

import java.util.ArrayList;
import java.util.List;

public class PersonagemDAO {

    private final static List<Personagem> personagens = new ArrayList<>();
    private static int contadorDeId = 1;

    public void salva(Personagem personagemSalvo) { //Meotod para salvar personagens

        personagemSalvo.setId(contadorDeId); //atribuindo uma id para o personagem salvo
        personagens.add(personagemSalvo);  //adicionando personagem a lista
        contadorDeId++; //aumento o numero de id para o proximo personagem

    }

    public void edita(Personagem personagem){ //metodo para fazeer alterações nos personagens

        Personagem personagemEncontrado = buscaPersonagemId(personagem);  //para identificar o id do personagem que o usuario deseja fazer a modificação
        if(personagemEncontrado != null){
            int posicaoDoPersonagem = personagens.indexOf(personagemEncontrado);
            personagens.set(posicaoDoPersonagem, personagem);
        }

    }

    private Personagem buscaPersonagemId(Personagem personagem) { // busca o id do personagem
        for (Personagem p: personagens){
            if(p.getId() == personagem.getId()){
                return p;
            }
        }
        return null;
    }

    public List<Personagem> todos() { return new ArrayList<>(personagens); } //busca as informações salvas

    public void remove(Personagem personagem) {  //Remove personagens da lista
        Personagem personagemDevolvido = buscaPersonagemId(personagem); //busca o id do personagem que deseja remover
        if(personagemDevolvido != null) { //Verifica se o personagem é diferente de nulo
            personagens.remove(personagemDevolvido);
        }
    }
}
