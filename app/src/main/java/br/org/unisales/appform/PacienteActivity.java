package br.org.unisales.appform;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import br.org.unisales.appform.clinica.Paciente;

public class PacienteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paciente);
    }
}