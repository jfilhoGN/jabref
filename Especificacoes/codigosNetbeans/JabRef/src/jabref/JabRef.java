/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jabref;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author kevin
 */
public class JabRef {

    public static void main(String[] args) throws IOException, InterruptedException {

        //String diretorio = "/home/kevin/Documentos/Facul/Engenharia de Software II/Projeto 1/jabref/Especificacoes/Artigos_JabRef/";
        TrataArquivos ta = new TrataArquivos();

        String nomeTitulo = "{Besouro}: A framework for exploring compliance rules in automatic {TDD} behavior assessment.pdf";
        nomeTitulo = nomeTitulo.toLowerCase().trim();

        ta.searchDirectory(new File("/home/kevin/"), nomeTitulo);

        String diretorio = ta.firstDiretorio();
        File f = new File (diretorio);
        ta.openFile(f);
        /*for (String x : ta.getResult()) {
            System.out.println(x);
        }*/

        /*int count = ta.getResult().size();
        if (count == 0) {
            System.out.println("\nNo result found!");
        } else {
            System.out.println("\nFound " + count + " result!\n");
            for (String matched : ta.getResult()) {
                System.out.println("Found : " + matched);
            }
        }

        
        if (ta.findNameFile(nomeTitulo, listFiles) == true) {
            System.out.println("tem pelo nome do arquivo");
            File file = ta.getFileByName(nomeTitulo, listFiles);
            System.out.println(file.getName());
            ta.openFile(file);
            //ta.openAllFiles(listFiles);
            
        } else {
            System.out.println("buscar pelo titulo...");

        }*/
    }

}
