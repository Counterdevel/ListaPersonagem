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

        Personagem personagemEscolhido = null;  //para identificar o id do personagem que o usuario deseja fazer a modificação
        for (Personagem p: personagens){
            if(p.getId() == personagem.getId()){
                personagemEscolhido = p;
            }
        }
        if(personagemEscolhido != null){
            int posicaoDoPersonagem = personagens.indexOf(personagemEscolhido);
            personagens.set(posicaoDoPersonagem, personagem);
        }

    }

    public List<Personagem> todos() { return new ArrayList<>(personagens); } //busca as informações salvas
}
