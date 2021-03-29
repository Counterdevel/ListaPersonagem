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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_personagem);

        setTitle("Adicionar Personagem"); //Muda o titulo do FormularioPersonagem

        PersonagemDAO dao = new PersonagemDAO(); //Instancia a classe PersonagemDAO para o FormularioPersonagem

        EditText campoNome = findViewById(R.id.editText_nome);
        EditText campoAltura = findViewById(R.id.editText_altura);           //Econtra o EditText a partir da id declarada no xml
        EditText campoNascimento = findViewById(R.id.editText_nascimento);

        Button botaoSalvar = findViewById(R.id.button_salvar);
        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nome = campoNome.getText().toString();
                String altura = campoAltura.getText().toString();
                String nascimento = campoNascimento.getText().toString();

                Personagem personagemSalvo = new Personagem(nome, altura, nascimento);
                dao.salva(personagemSalvo);

                startActivity(new Intent(FormularioPersonagemActivity.this, ListaPersonagemActivity.class)); //Transaciona aa tela do formulario para a lista

                //Toast.makeText(FormularioPersonagemActivity.this, personagemSalvo.getNome() + " - " + personagemSalvo.getAltura() + " - " + personagemSalvo.getNascimento(), Toast.LENGTH_SHORT).show();

                new Personagem(nome, altura, nascimento);

                //Toast.makeText(FormularioPersonagemActivity.this, "Funcionando", Toast.LENGTH_SHORT).show();
            }
        });
    }
}