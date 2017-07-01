package Citacoes;

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
    private File fileBase;

    public TrataBib(String base, String bib) throws IOException {
        this.searchBase(base);
        this.searchBib(bib);
        this.addSRJ();
        //this.addCitations();
    }

    public File getFileBib() {
        return fileBib;
    }

    public void setFileBib(File fileBib) {
        this.fileBib = fileBib;
    }

    public File getFileBase() {
        return fileBase;
    }

    public void setFileBase(File fileBase) {
        this.fileBase = fileBase;
    }

    public void searchBib(String bib) {
        File arq = new File(bib);
        this.setFileBib(arq);
    }

    public void searchBase(String base) {
        File arq = new File(base);
        this.setFileBase(arq);
    }

    public String getCitations(String title) throws IOException {
        String Comando = "/home/kevin/miniconda3/bin/python script_crawling.py " + title;
        Process p = Runtime.getRuntime().exec(Comando);
        BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
        if (in.readLine().isEmpty()) {
            return "  Citations                 = {0},";
        }
        return "  Citations                 = {" + in.readLine() + "},";
    }

    public void addCitations() throws IOException {

        Scanner scan = new Scanner("a.bib");
        File novo = new File("b.bib");
        Writer writer = new FileWriter(novo);

        while (scan.hasNextLine()) {
            String linha = scan.nextLine();
            System.out.println(linha);
            if (!linha.contains("=")) {
                writer.write(linha + "\n");
                writer.flush();
            } /*else {
                String[] spliter = linha.split("=");
                if (spliter[0].toLowerCase().contains("title")) {
                    spliter[1] = spliter[1].trim();
                    if (spliter[1].charAt(spliter[1].length() - 1) == ',') {
                        String clean = spliter[1].substring(1, spliter[1].length() - 2);
                        String result = this.getCitations(clean);
                        writer.write(result + "\n");
                        writer.flush();
                        writer.write(linha + "\n");
                        writer.flush();
                    } else if (spliter[1].charAt(spliter[1].length() - 1) == '}') {
                        String clean = spliter[1].substring(1, spliter[1].length() - 1);
                        String result = this.getCitations(clean);
                        writer.write(result + "\n");
                        writer.flush();
                        writer.write(linha + "\n");
                        writer.flush();
                    }
                } else {
                    writer.write(linha + "\n");
                    writer.flush();
                }
            }*/
        }

        writer.close();
    }

    public String returnTuple(String journal) throws FileNotFoundException {
        Scanner scan = new Scanner(fileBase);
        while (scan.hasNextLine()) {
            String getLine = scan.nextLine();
            if (getLine.contains(journal)) {
                String[] vet = getLine.split("\\|");
                if (vet[4].isEmpty()) {
                    return "  SJR                      = {0},";
                } else {
                    return "  SJR                      = {" + vet[4] + "},";
                }
            }
        }
        return "  SJR                      = {0},";
    }

    public void addSRJ() throws FileNotFoundException, IOException {
        Scanner scan = new Scanner(fileBib);
        File novo = new File("a.bib");
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
                } else {
                    writer.write(linha + "\n");
                    writer.flush();
                }
            }
        }

        writer.close();
    }

}
