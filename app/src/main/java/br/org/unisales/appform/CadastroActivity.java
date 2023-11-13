package br.org.unisales.appform;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

import br.org.unisales.appform.clinica.Profissional;
import br.org.unisales.appform.persistencia.BaseDados;


public class CadastroActivity extends AppCompatActivity {

    //modelo
    Profissional profissional = new Profissional();

    //componentes visuais
    EditText edtNomeProfissional;
    EditText edtCRPProfissional;
    EditText edtFormacaoProfissional;
    EditText edtNasctProfissional;

    ListView listaP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Cadastro");
        setContentView(R.layout.activity_cadastro);
        edtNomeProfissional = findViewById(R.id.edtNomeProfissional);
        edtCRPProfissional = findViewById(R.id.edtCRPProfissional);
        edtFormacaoProfissional = findViewById(R.id.edtFormacaoProfissional);
        edtNasctProfissional = findViewById(R.id.edtNasctProfissional);
        listaP = findViewById(R.id.listaP);
    }

    public void salvarProfissional(View view) {
        try {
            this.profissional.nome = "" + edtNomeProfissional.getText();
            this.profissional.crp = Long.parseLong("" + edtCRPProfissional.getText());
            this.profissional.formacao = "" + edtFormacaoProfissional.getText();
            this.profissional.nasct = "" + edtNasctProfissional.getText();
            if (this.profissional.id == null) {
                BaseDados.rProfissional.insert(this.profissional);
            } else {
                BaseDados.rProfissional.update(this.profissional);
            }
        } catch (Exception ex) {
            new AlertDialog.Builder(this)
                    .setMessage(ex.getMessage())
                    .setPositiveButton(android.R.string.yes, null)
                    .show();
        }
        this.cancelarProfissional(view);
    }

    public void getListaProfissionais() {
        List<Profissional> lista = BaseDados.rProfissional.find().toList();
        final ArrayAdapter<Profissional> arrayAdapter = new  ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lista);
        listaP.setAdapter(arrayAdapter);
        listaP.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                profissional = BaseDados.rProfissional.getById(arrayAdapter.getItem(i).id);
                edtCRPProfissional.setText("" + profissional.crp);
                edtNomeProfissional.setText(profissional.nome);
                edtNomeProfissional.requestFocus();
                edtFormacaoProfissional.setText("" + profissional.formacao);
                edtNasctProfissional.setText("" + profissional.nasct);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getListaProfissionais();
    }


    public void cancelarProfissional(View view) {
        this.profissional = new Profissional();
        edtNomeProfissional.setText("");
        edtCRPProfissional.setText("");
        edtNomeProfissional.requestFocus();
        edtFormacaoProfissional.setText("");
        edtNasctProfissional.setText("");
        getListaProfissionais();
    }


    public void excluirProfissional(View view) {
        if (this.profissional.id != null) {
            BaseDados.rProfissional.remove(this.profissional);
            this.cancelarProfissional(view);
        }
    }

}