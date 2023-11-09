package br.org.unisales.appform.persistencia;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import br.org.unisales.appform.clinica.Profissional;

public class BaseDados {
    //repositorio de objetos para a entidade profissional
    public static ObjectRepository<Profissional> rProfissional;

    public static void init (String file) {
        //instancia do banco de dados
        Nitrite db = Nitrite.builder()
                .compressed()
                .filePath(file) //define o caminho do arquivo bd
                .openOrCreate("root", "salesiano");

        rProfissional = db.getRepository(Profissional.class);
    }
}
