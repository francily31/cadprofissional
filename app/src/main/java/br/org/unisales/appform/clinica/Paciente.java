package br.org.unisales.appform.clinica;


import org.dizitart.no2.IndexType;
import org.dizitart.no2.NitriteId;
import org.dizitart.no2.objects.Id;
import org.dizitart.no2.objects.Index;
import org.dizitart.no2.objects.Indices;

import java.util.Date;
import java.util.Objects;

@Indices(value = {
        @Index(value = "cpf", type = IndexType.Unique)
})

public class Paciente {
@Id
    public NitriteId id;
    public String nome;
    public String cpf;
    public String email;
    public String telefone;

    //public Date dtnascimento;

    //public Number telefone;

    //public String email;

    @Override

    public String toString(){

        return this.nome;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Paciente paciente = (Paciente) o;
        return Objects.equals(id, paciente.id);
    }




}
