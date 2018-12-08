/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplexlang.fileio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import simplexlang.parser.Parser;

/**
 *
 * @author MajickTek
 */
public class FileIO {

    public static void readFile(String filename) throws FileNotFoundException, IOException {
        // Open this file.
        BufferedReader reader = new BufferedReader(new FileReader(
                filename));

        // Read lines from file.
        while (true) {
            String line = reader.readLine();
            if (line == null) {
                break;
            }
            // Split line on comma.
            //String[] parts = line.split("==>");
            //for (String part : parts) {
            //System.out.println(part);
            //}
            //System.out.println();
            Parser.parseLine(line);
        }

        reader.close();
    }

    public static void createTestFile(String filename) throws UnsupportedEncodingException, FileNotFoundException, IOException {
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(filename), "utf-8"))) {

            String program = "setvar=>x,hello\n" // make sure to add newlines otherwise it gets confused
                    + "print=>test is complete\n"
                    + "getvar=>x\n"
                    + "debug=>listVarMap\n"
                    + "getsysprop=>os.name\ngetsysprop=>os.version\n"
                    + "getsysprop=>all\n"
                    + "eval=>1+2\n"
                    + "setvar=>y,0\n"
                    + "mathvar=>y,2+2\n"
                    + "getvar=>y\n"
                    ;
            writer.write(program);
            writer.close();
        }
    }

    public static void createTextFile(String filename, String data) throws UnsupportedEncodingException, FileNotFoundException, IOException {
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(filename), "utf-8"))) {
            writer.write(data);
            writer.close();
        }
    }
}
