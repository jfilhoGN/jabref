package org.jabref.model.database;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class BibDatabaseVisibleTest {

    private BibDatabase bibd;
    private String bibzin;

    @Before
    public void init() throws IOException {
        this.bibd = new BibDatabase();

        File file1 = new File("/home/kevin/Downloads/bibTest.bib");
        FileInputStream fis = new FileInputStream(file1);
        byte[] data = new byte[(int) file1.length()];
        fis.read(data);
        fis.close();
        this.bibzin = new String(data, "UTF-8");
        System.out.println(this.bibzin);
    }

    @Test
    public void test() {
        Set<String> expected = new HashSet();
        expected.add("author");
        expected.add("title");

        Set<String> result = this.bibd.getAllVisibleFields();
        assertEquals(expected, result);

    }
}
