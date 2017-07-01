package Citacoes;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import org.jbibtex.*;

public class Main {

    public static void main(String[] args) throws FileNotFoundException, IOException, ParseException, InterruptedException {
        String base = "/home/kevin/Documentos/Facul/Engenharia de Software II/Projeto 1/jabref/Especificacoes_p2/scimagojr.csv";
        String Bib = "/home/kevin/Documentos/Facul/Engenharia de Software II/Projeto 1/jabref/Especificacoes_p2/referencias2.bib";
        TrataBib b = new TrataBib(base, Bib);

        /*String Comando = "/home/kevin/miniconda3/bin/python scholar.py -a \"Jeff Yan and Alan Blackwell and Ross Anderson and Alasdair Grant\" -c 1";
        Process p = Runtime.getRuntime().exec(Comando);
        BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        while ((line = in.readLine()) != null) {
            System.out.println(line);
            line = line.trim();
            if (line.contains("Citations")){
                String[] spliter = line.split(" ");
                if (spliter.length == 2){
                    System.out.println("a -> "+spliter[1]);
                }
            }
        }
        in.close();*/
    }

}
