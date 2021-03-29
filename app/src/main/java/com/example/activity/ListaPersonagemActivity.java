package com.example.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.listapersonagem.R;
import com.example.listapersonagem.dao.PersonagemDAO;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListaPersonagemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Lista de Personagem");

        FloatingActionButton botaoNovoPersonagem = findViewById(R.id.fab_novo_personagem); //associando ao id do FloatingActionButton
        botaoNovoPersonagem.setOnClickListener(new View.OnClickListener() { // adiciona um evento ao FloatingActionButton para que ao clicar, vá para a tela do Formulario
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ListaPersonagemActivity.this, FormularioPersonagemActivity.class));
            }
        });

        //List<String> personagem = new ArrayList<>(Arrays.asList("Alex", "Ken", "Ryu"));
    }

    @Override
    protected void onResume() { //Persiste os dados
        super.onResume();

        PersonagemDAO dao = new PersonagemDAO(); //Instanciando classe PErsonagem

        ListView listaDePersonagens  = findViewById(R.id.lista_personagem);
        List<Personagem> personagens = dao.todos();
        listaDePersonagens.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dao.todos()));
        listaDePersonagens.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicao, long id) {
                Personagem personagemEscolhido = personagens.get(posicao);
                //Log.i("personagem", "" + personagemEscolhido);

                Intent vaiParaFormulario = new Intent(ListaPersonagemActivity.this, FormularioPersonagemActivity.class);

                startActivity(vaiParaFormulario);
            }
        });
    }
}