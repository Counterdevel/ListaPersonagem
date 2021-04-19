package com.example.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.listapersonagem.R;
import com.example.listapersonagem.dao.PersonagemDAO;

import static com.example.activity.ConstantesActivities.CHAVE_PERSONAGEM;

public class FormularioPersonagemActivity extends AppCompatActivity {

    private static final String TITULO_APPBAR_EDITA_PERSONAGEM = "Editar Personagem";  //Constantes para os titulos
    private static final String TITULO_APPBAR_NOVO_PERSONAGEM = "Novo Personagem";
    private EditText campoNome;
    private EditText campoAltura;
    private EditText campoNascimento;
    private Personagem personagem;
    private final PersonagemDAO dao = new PersonagemDAO(); //Instancia a classe PersonagemDAO para o FormularioPersonagem

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_personagem);

        InicializacaoCampos();
        configuraBotaoSalvar();
        carregaPersonagem();
    }

    private void carregaPersonagem() {
        Intent dados = getIntent();
        if(dados.hasExtra(CHAVE_PERSONAGEM)) {
            setTitle(TITULO_APPBAR_EDITA_PERSONAGEM); //Muda o titulo do FormularioPersonagem
            personagem = (Personagem) dados.getSerializableExtra(CHAVE_PERSONAGEM);
            preencheCampos();
        }
        else{
            setTitle(TITULO_APPBAR_NOVO_PERSONAGEM); //Muda o titulo do FormularioPersonagem
            personagem = new Personagem();
        }
    }

    private void preencheCampos() {
        campoNome.setText(personagem.getNome());                                                //Pega as informações salvas no dao
        campoAltura.setText(personagem.getAltura());
        campoNascimento.setText(personagem.getNascimento());
    }

    private void configuraBotaoSalvar() {
        Button botaoSalvar = findViewById(R.id.button_salvar); //Econtra o button a partir da id declarada no xml
        botaoSalvar.setOnClickListener(new View.OnClickListener() { //Cria um evento de ações para o botão
            @Override
            public void onClick(View view) {
                finalizaFormulario();
            }
        });
    }

    private void finalizaFormulario() {
        preencheePersonagem();
        if(personagem.idValido()){
            dao.edita(personagem);
        } else {
            dao.salva(personagem);   //metodo para armazenar as informações no PersonagemDAO
        }
        finish();
    }

    private void InicializacaoCampos() {
        campoNome = findViewById(R.id.editText_nome);
        campoAltura = findViewById(R.id.editText_altura);           //Econtra o EditText a partir da id declarada no xml
        campoNascimento = findViewById(R.id.editText_nascimento);
    }


private void preencheePersonagem() {

    String nome = campoNome.getText().toString();
    String altura = campoAltura.getText().toString();          //Converte os editText em strings
    String nascimento = campoNascimento.getText().toString();

    personagem.setNome(nome);
    personagem.setAltura(altura);
    personagem.setNascimento(nascimento);
}}