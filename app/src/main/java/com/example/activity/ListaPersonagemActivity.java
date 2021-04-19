package com.example.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.listapersonagem.R;
import com.example.listapersonagem.dao.PersonagemDAO;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import static com.example.activity.ConstantesActivities.CHAVE_PERSONAGEM;

public class ListaPersonagemActivity extends AppCompatActivity {

    public static final String Titulo = "Lista de Personagem"; //Cosntantes para titulos
    public static final String TITULO_APPBAR = Titulo;
    private final PersonagemDAO dao = new PersonagemDAO(); //Instanciando classe PersonagemDAO

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle(TITULO_APPBAR);
        configuraFabNovoPersonagem();

    }

    private void configuraFabNovoPersonagem() {
        FloatingActionButton botaoNovoPersonagem = findViewById(R.id.fab_novo_personagem); //associando ao id do FloatingActionButton
        botaoNovoPersonagem.setOnClickListener(new View.OnClickListener() { // adiciona um evento ao FloatingActionButton para que ao clicar, vá para a tela do Formulario
            @Override
            public void onClick(View v) {
                abreFormularioSalva();
            }
        });
    }

    private void abreFormularioSalva() {
        startActivity(new Intent(this, FormularioPersonagemActivity.class));
    }

    @Override
    protected void onResume() { //Persiste os dados para a classe dao
        super.onResume();
        configuraLista();
    }

    private void configuraLista() {
        ListView listaDePersonagens  = findViewById(R.id.lista_personagem);
        final List<Personagem> personagens = dao.todos();
        listaDePersonagens(listaDePersonagens, personagens);
        configuraItemPorClique(listaDePersonagens);
    }

    private void configuraItemPorClique(ListView listaDePersonagens) {
        listaDePersonagens.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicao, long id) { //Metodo para selecionar o personagem
                Personagem personagemEscolhido = (Personagem) adapterView.getItemAtPosition(posicao);

                abreFormularioModoEditar(personagemEscolhido);

            }
        });
    }

    private void abreFormularioModoEditar(Personagem personagem) {
        Intent vaiParaFormulario = new Intent(ListaPersonagemActivity.this, FormularioPersonagemActivity.class); //Joga as informações para o formulário
        vaiParaFormulario.putExtra(CHAVE_PERSONAGEM, personagem);

        startActivity(vaiParaFormulario);
    }

    private void listaDePersonagens(ListView listaDePersonagens, List<Personagem> personagens) {
        listaDePersonagens.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, personagens));
    }
}