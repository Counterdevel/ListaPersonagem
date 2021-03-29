package com.example.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.listapersonagem.R;
import com.example.listapersonagem.dao.PersonagemDAO;

public class FormularioPersonagemActivity extends AppCompatActivity {

    private EditText campoNome;
    private EditText campoAltura;
    private EditText campoNascimento;

    private final PersonagemDAO dao = new PersonagemDAO(); //Instancia a classe PersonagemDAO para o FormularioPersonagem

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_personagem);

        setTitle("Adicionar Personagem"); //Muda o titulo do FormularioPersonagem

        campoNome = findViewById(R.id.editText_nome);
        campoAltura = findViewById(R.id.editText_altura);           //Econtra o EditText a partir da id declarada no xml
        campoNascimento = findViewById(R.id.editText_nascimento);

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

                //startActivity(new Intent(FormularioPersonagemActivity.this, ListaPersonagemActivity.class)); //Transaciona aa tela do formulario para a lista

                //Toast.makeText(FormularioPersonagemActivity.this, personagemSalvo.getNome() + " - " + personagemSalvo.getAltura() + " - " + personagemSalvo.getNascimento(), Toast.LENGTH_SHORT).show();

                //new Personagem(nome, altura, nascimento); //Instancia no personagem

                personagemSalvo.setNome(nome);
                personagemSalvo.setAltura(altura);
                personagemSalvo.setNascimento(nascimento);
                dao.edita(personagemSalvo);

                //Toast.makeText(FormularioPersonagemActivity.this, "Funcionando", Toast.LENGTH_SHORT).show();
            }
        });

        Intent dados = getIntent();
        Personagem personagem = (Personagem) dados.getSerializableExtra("personagem");
        campoNome.setText(personagem.getNome());
        campoAltura.setText(personagem.getAltura());
        campoNascimento.setText(personagem.getNascimento());
    }
}