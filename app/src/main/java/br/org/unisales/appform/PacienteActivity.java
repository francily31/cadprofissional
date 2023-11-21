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

import br.org.unisales.appform.clinica.Paciente;
import br.org.unisales.appform.clinica.Profissional;
import br.org.unisales.appform.persistencia.BaseDados;

public class PacienteActivity extends AppCompatActivity {

//começo

    //modelo
    Paciente paciente = new Paciente();

    //componentes visuais
    EditText edtNomePaciente;
    EditText edtCPFPaciente;
    EditText edtTelefonePaciente;
    EditText edtEmailPaciente;

    ListView listaP;
    private Paciente profissional;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Paciente");
        setContentView(R.layout.activity_paciente);
        edtNomePaciente = findViewById(R.id.edtNomePaciente);
        edtCPFPaciente = findViewById(R.id.edtCPFPaciente);
        edtTelefonePaciente = findViewById(R.id.edtTelefonePaciente);
        edtEmailPaciente = findViewById(R.id.edtEmailPaciente);
        listaP = findViewById(R.id.listaP);
    }

    public void salvarPaciente(View view) {
        try {
            this.paciente.nome = "" + edtNomePaciente.getText();
            this.paciente.cpf = "" + edtCPFPaciente.getText();
            this.paciente.telefone = "" +  edtTelefonePaciente.getText();
            this.paciente.email = "" + edtEmailPaciente.getText();

            if (this.profissional.id == null) {
                BaseDados.rPaciente.insert(this.paciente);
            } else {
                BaseDados.rPaciente.update(this.paciente);
            }
        } catch (Exception ex) {
            new AlertDialog.Builder(this)
                    .setMessage(ex.getMessage())
                    .setPositiveButton(android.R.string.yes, null)
                    .show();
        }
        this.cancelarPaciente(view);
    }

    private void cancelarPaciente(View view) {
    }


    public void getListaPacientes() {
        List<Paciente> lista = BaseDados.rPaciente.find().toList();
        final ArrayAdapter<Paciente> arrayAdapter = new  ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lista);
        listaP.setAdapter(arrayAdapter);
        listaP.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                paciente = BaseDados.rPaciente.getById(arrayAdapter.getItem(i).id);
                edtCPFPaciente.setText("" + paciente.cpf);
                edtNomePaciente.setText(paciente.nome);
                edtNomePaciente.requestFocus();
                edtTelefonePaciente.setText("" + paciente.telefone);
                edtEmailPaciente.setText("" + paciente.email);

            }
        });
    }



    @Override
    protected void onResume() {
        super.onResume();
        getListaPacientes();
    }
    public void cancelarPacientes(View view) {
        this.paciente = new Paciente();
        edtNomePaciente.setText("");
        edtCPFPaciente.setText("");
        edtNomePaciente.requestFocus();
        edtTelefonePaciente.setText("");
        edtEmailPaciente.setText("");

    }


    public void excluirPaciente(View view) {
        if (this.paciente.id != null) {
            BaseDados.rPaciente.remove(this.paciente);
            this.cancelarPaciente(view);
        }
    }





}