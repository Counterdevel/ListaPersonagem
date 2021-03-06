package com.example.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.listapersonagem.R;
import com.example.listapersonagem.dao.PersonagemDAO;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import static com.example.activity.ConstantesActivities.CHAVE_PERSONAGEM;

public class ListaPersonagemActivity extends AppCompatActivity {

    public static final String Titulo = "Lista de Personagem"; //Cosntantes para titulos
    public static final String TITULO_APPBAR = Titulo;
    private final PersonagemDAO dao = new PersonagemDAO(); //Instanciando classe PersonagemDAO
    private ArrayAdapter<Personagem> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle(TITULO_APPBAR);
        configuraFabNovoPersonagem();
        configuraLista();

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
        atualizaPersonagem();
    }

    private void atualizaPersonagem() {
        adapter.clear();                 //Limpa os dados e preenche com os dados salvos novamente
        adapter.addAll(dao.todos());
    }

    private void remove (Personagem personagem){ //Remove o personagem salvo no PersonagemDAO
        dao.remove(personagem);
        adapter.remove(personagem);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) { //Adiciona no menu a palavra "Remover" ao segurar o click
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.activity_lista_personagens_menu, menu); //busca o Menu
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        configuraMenu(item);
        return super.onContextItemSelected(item);
    }

    private void configuraMenu(@NonNull MenuItem item) { //Metodo com condicões para remover ou não personagem
        int itemId = item.getItemId();
        if(itemId == R.id.activity_lista_personagens_menu_remover) { //Pega o id do menu

            new AlertDialog.Builder(this)
                    .setTitle("Removendo Personagem")
                    .setMessage("Tem certeza que deseja remover?")
                    .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                            Personagem personagemEscolhido = adapter.getItem(menuInfo.position);                                      //Metodo para encontrar e remover o personagem
                            remove(personagemEscolhido);
                        }
                    })
                    .setNegativeButton("Não", null)
                    .show();
        }
    }

    private void configuraLista() {
        ListView listaDePersonagens  = findViewById(R.id.lista_personagem);
        configuraAdapter(listaDePersonagens);
        configuraItemPorClique(listaDePersonagens);
        registerForContextMenu(listaDePersonagens);
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

    private void configuraAdapter(ListView listaDePersonagens) {
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        listaDePersonagens.setAdapter(adapter);
    }
}