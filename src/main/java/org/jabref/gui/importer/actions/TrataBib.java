package org.jabref.gui.importer.actions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;
import java.util.Scanner;

public class TrataBib {

    private File fileBib;
    private File fileBaseSci;

    public TrataBib(String bib) throws FileNotFoundException, IOException, InterruptedException {
        String base = "/home/kevin/Documentos/Facul/Engenharia de Software II/Projeto 1/jabref/Especificacoes_p2/scimagojr.csv";
        this.searchBase(base);
        this.searchBib(bib);
        this.addSRJ();
    }

    public File getFileBib() {
        return fileBib;
    }

    public void setFileBib(File fileBib) {
        this.fileBib = fileBib;
    }

    public File getFileBase() {
        return fileBaseSci;
    }

    public void setFileBase(File fileBaseSci) {
        this.fileBaseSci = fileBaseSci;
    }

    public void searchBib(String bib) {
        File arq = new File(bib);
        this.setFileBib(arq);
    }

    public void searchBase(String base) {
        File arq = new File(base);
        this.setFileBase(arq);
    }

    public String getCitations(String author) throws IOException, InterruptedException {
        String Comando = "/home/kevin/miniconda3/bin/python /home/kevin/Documentos/Facul/Engenharia de Software II/Projeto 1/jabref/Especificacoes_p2/scholar.py -a \""
                + author + "\" -c 1";
        Process p = Runtime.getRuntime().exec(Comando);
        BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        while ((line = in.readLine()) != null) {
            line = line.trim();
            System.out.println(line);
            if (line.contains("Citations")) {
                String[] spliter = line.split(" ");
                if (spliter.length == 2) {
                    return "  Citations = {" + spliter[1] + "},";
                }
            }
        }
        in.close();
        return "  Citations = {0},";
    }

    public String returnTuple(String journal) throws FileNotFoundException {
        Scanner scan = new Scanner(fileBaseSci);
        while (scan.hasNextLine()) {
            String getLine = scan.nextLine();
            if (getLine.contains(journal)) {
                String[] vet = getLine.split("\\|");
                if (vet[4].isEmpty()) {
                    return "  SJR = {0},";
                } else {
                    return "  SJR = {" + vet[4] + "},";
                }
            }
        }
        return "  SJR = {0},";
    }

    public void addSRJ() throws FileNotFoundException, IOException, InterruptedException {
        Scanner scan = new Scanner(fileBib);
        File novo = new File("1" + fileBib.getName());
        Writer writer = new FileWriter(novo);

        while (scan.hasNextLine()) {
            String linha = scan.nextLine();
            if (!linha.contains("=")) {
                writer.write(linha + "\n");
                writer.flush();
            } else {
                String[] spliter = linha.split("=");
                if (spliter[0].toLowerCase().contains("journal")) {
                    spliter[1] = spliter[1].trim();
                    if (spliter[1].charAt(spliter[1].length() - 1) == ',') {
                        String clean = spliter[1].substring(1, spliter[1].length() - 2);
                        String result = this.returnTuple(clean);
                        writer.write(result + "\n");
                        writer.flush();
                        writer.write(linha + "\n");
                        writer.flush();
                    } else if (spliter[1].charAt(spliter[1].length() - 1) == '}') {
                        String clean = spliter[1].substring(1, spliter[1].length() - 1);
                        String result = this.returnTuple(clean);
                        writer.write(result + "\n");
                        writer.flush();
                        writer.write(linha + "\n");
                        writer.flush();
                    }
                } else if (spliter[0].toLowerCase().contains("author")) {
                    spliter[1] = spliter[1].trim();
                    if (spliter[1].charAt(spliter[1].length() - 1) == ',') {
                        String clean = spliter[1].substring(1, spliter[1].length() - 2);
                        //System.out.println("author: " + clean);
                        String result = this.getCitations(clean);
                        writer.write(result + "\n");
                        writer.flush();
                        writer.write(linha + "\n");
                        writer.flush();
                    } else if (spliter[1].charAt(spliter[1].length() - 1) == '}') {
                        String clean = spliter[1].substring(1, spliter[1].length() - 1);
                        String result = this.returnTuple(clean);
                        writer.write(result + "\n");
                        writer.flush();
                        writer.write(linha + "\n");
                        writer.flush();
                    }
                } else {
                    writer.write(linha + "\n");
                    writer.flush();
                }
            }
        }
        writer.close();
    }

}
