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
        
        try {
            Parser.debugEnabled = false;
            FileIO.createTestFile("test.src");
            FileIO.readFile("test.src");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SimplexLang.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SimplexLang.class.getName()).log(Level.SEVERE, null, ex);
        }
        /*
        planned features:
        graphics API for creating a JFrame with a Graphics2D canvas that you can draw to, and write text
        sound api (?)
        file api (?)
        the language is very simplistic and doesn't even really allow basic math so improvements will need to be made.
        However, the language only has one operator and a comma parameter, so addition would be like this:
        setvar=>x,2
        add=>x,2
        getvar=>x
        The output is 4 in this example. x is created with the initial value of 2, and then
        the add function, uh, adds 2 to x.
        then getvar returns 4 because 2+2 is 4.
         */

    }

}
