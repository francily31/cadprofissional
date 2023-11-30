package br.org.unisales.appform;

import androidx.appcompat.app.AppCompatActivity;

//importacao dos pacotes relacionado a manipulacao
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import br.org.unisales.appform.persistencia.BaseDados;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //chamada do metodo da classe pai
        super.onCreate(savedInstanceState);
        //definicao do layout associado a esta atividade
        setContentView(R.layout.activity_main);


        //iniciacao (init) do banco de dados com o caminho do arquivo
        BaseDados.init(getFilesDir().getPath() + "/baseClinica.db");
    }


    //metodo para abrir a tela correspondente quando o botao e clicado
    public void openTelaCadastro(View view) {
        startActivity(new Intent(this, CadastroActivity.class));
    }

    public void openTelaPaciente(View view) {
        startActivity(new Intent(this, PacienteActivity.class));
    }


}