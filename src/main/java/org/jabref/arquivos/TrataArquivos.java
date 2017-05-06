package org.jabref.arquivos;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TrataArquivos {

    private String fileNameToSearch;
    private final List<String> result = new ArrayList<>();

    public String getFileNameToSearch() {
        return fileNameToSearch;
    }

    public void setFileNameToSearch(String fileNameToSearch) {
        this.fileNameToSearch = fileNameToSearch;
    }

    public List<String> getResult() {
        return result;
    }

    public void searchDirectory(File directory, String fileNameToSearch) {
        setFileNameToSearch(fileNameToSearch);
        if (directory.isDirectory()) {
            search(directory);
        } else {
            System.out.println(directory.getAbsoluteFile() + " is not a directory!");
        }

    }

    private void search(File file) {
        if (file.isDirectory()) {
            if (file.canRead()) {
                for (File temp : file.listFiles()) {
                    if (temp.isDirectory()) {
                        search(temp);
                    } else if (getFileNameToSearch().equals(temp.getName().toLowerCase().trim())) {
                        result.add(temp.getAbsoluteFile().toString());
                    }
                }

            } else {
                System.out.println(file.getAbsoluteFile() + "Permission Denied");
            }
        }

    }

    public String firstDiretorio() {
        return this.getResult().get(0);
    }

    public boolean findNameFile(String nameFile, File[] listFile) {
        for (File e : listFile) {
            if (e.getName().equals(nameFile)) {
                return true;
            }
        }
        return false;
    }

    public File getFileByName(String nameFile, File[] listFile) {
        for (File e : listFile) {
            if (e.getName().equals(nameFile)) {
                return e;
            }
        }
        return null;
    }

    public void printFiles(File[] listFile) {
        for (File e : listFile) {
            System.out.println(e.getName());
        }
    }

    public void openFile(File file) {
        if (Desktop.isDesktopSupported()) {
            try {
                Desktop.getDesktop().open(file);
            } catch (IOException ex) {
                // no application registered for PDFs
            }
        }
    }

    public void openAllFiles(File[] listFile) {
        if (Desktop.isDesktopSupported()) {
            try {
                for (File e : listFile) {
                    Desktop.getDesktop().open(e);
                }
            } catch (IOException ex) {
                // no application registered for PDFs
            }
        }
    }

}