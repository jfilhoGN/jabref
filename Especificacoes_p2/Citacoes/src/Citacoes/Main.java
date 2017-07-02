package Citacoes;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.jbibtex.*;

public class Main {

    public static void main(String[] args) throws FileNotFoundException, IOException, ParseException, InterruptedException {
        String base = "/home/kevin/Documentos/Facul/Engenharia de Software II/Projeto 1/jabref/Especificacoes_p2/scimagojr.csv";
        String Bib = "/home/kevin/Documentos/Facul/Engenharia de Software II/Projeto 1/jabref/Especificacoes_p2/referencias_mojo.bib";
        TrataBib b = new TrataBib(base, Bib);

    }

}
