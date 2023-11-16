package br.org.unisales.appform.clinica;

import org.dizitart.no2.IndexType;
import org.dizitart.no2.NitriteId;
import org.dizitart.no2.objects.Id;
import org.dizitart.no2.objects.Index;
import org.dizitart.no2.objects.Indices;

import java.util.Objects;

@Indices(value = {
        //verifica se há crp igual
        @Index(value = "crp", type = IndexType.Unique)
})
public class Profissional {
    //componentes da entidade
    @Id
    public NitriteId id;//gera automatico
    public String nome;
    public Long crp;
    public String formacao;
    public String especializacao;
    public String nasct;
    public String cpf;


    @Override
    public String toString() {

        return this.nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Profissional cadastros = (Profissional) o;
        return Objects.equals(id, cadastros.id);
    }

}
