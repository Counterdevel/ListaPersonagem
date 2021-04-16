package com.example.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.listapersonagem.R;
import com.example.listapersonagem.dao.PersonagemDAO;

public class FormularioPersonagemActivity extends AppCompatActivity {

    private EditText campoNome;
    private EditText campoAltura;
    private EditText campoNascimento;
    private Personagem Personagem;
    private final PersonagemDAO dao = new PersonagemDAO(); //Instancia a classe PersonagemDAO para o FormularioPersonagem

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_personagem);

        setTitle("Adicionar Personagem"); //Muda o titulo do FormularioPersonagem

        InicializacaoCampos();

        configuraBotao();

        Intent dados = getIntent();
        if(dados.hasExtra("personagem")) {
            Personagem personagem = (Personagem) dados.getSerializableExtra("personagem");
            campoNome.setText(personagem.getNome());                                                //Pega as informações salvas no dao
            campoAltura.setText(personagem.getAltura());
            campoNascimento.setText(personagem.getNascimento());
        }
        else{
            Personagem = new Personagem();
        }
    }

    private void configuraBotao() {
        Button botaoSalvar = findViewById(R.id.button_salvar); //Econtra o button a partir da id declarada no xml
        botaoSalvar.setOnClickListener(new View.OnClickListener() { //Cria um evento de ações para o botão
            @Override
            public void onClick(View view) {

                String nome = campoNome.getText().toString();
                String altura = campoAltura.getText().toString();          //Converte os editText em strings
                String nascimento = campoNascimento.getText().toString();

                Personagem personagemSalvo = new Personagem(nome, altura, nascimento);
                dao.salva(personagemSalvo);   //metodo para armazenar as informações no PersonagemDAO
                finish();

                personagemSalvo.setNome(nome);
                personagemSalvo.setAltura(altura);
                personagemSalvo.setNascimento(nascimento);
                dao.edita(personagemSalvo);

            }
        });
    }

    private void InicializacaoCampos() {
        campoNome = findViewById(R.id.editText_nome);
        campoAltura = findViewById(R.id.editText_altura);           //Econtra o EditText a partir da id declarada no xml
        campoNascimento = findViewById(R.id.editText_nascimento);
    }
}