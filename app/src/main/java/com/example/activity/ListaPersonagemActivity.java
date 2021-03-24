package com.example.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

        PersonagemDAO dao = new PersonagemDAO(); //Instanciando classe PErsonagem

        setTitle("Lista de Personagem");

        FloatingActionButton botaoNovoPersonagem = findViewById(R.id.fab_novo_personagem); //associando ao id do FloatingActionButton
        botaoNovoPersonagem.setOnClickListener(new View.OnClickListener() { // adiciona um evento ao FloatingActionButton para que ao clicar, vá para a tela do Formulario
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ListaPersonagemActivity.this, FormularioPersonagemActivity.class));
            }
        });

        //List<String> personagem = new ArrayList<>(Arrays.asList("Alex", "Ken", "Ryu"));

        ListView listaDePersonagens  = findViewById(R.id.lista_personagem);
        listaDePersonagens.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dao.todos()));
    }
}