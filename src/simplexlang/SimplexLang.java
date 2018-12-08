/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplexlang;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import simplexlang.fileio.FileIO;
import simplexlang.parser.Parser;

/**
 * A simple scripting language written entirely in pure java, no grammars
 * Current Syntax: There is only one current operator, "=>", the function call
 * operator. example: print=>hello variables are possible: setvar=>x,hello the
 * above code sets the variable x with the value "hello". All variables are
 * strings and have string values. To get a variable (and print it to the screen
 * as parameters are not supported): getvar=>x
 *
 * @author MajickTek
 */
public class SimplexLang {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Parser.debugEnabled = true;
        if (Parser.debugEnabled == true) {

            try {
                FileIO.createTestFile("test.src");
                FileIO.readFile("test.src");
            } catch (FileNotFoundException ex) {
                Logger.getLogger(SimplexLang.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(SimplexLang.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

}
