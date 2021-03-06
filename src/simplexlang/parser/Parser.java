/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplexlang.parser;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import simplexlang.fileio.FileIO;
import simplexlang.variables.VariableEngine;

/**
 *
 * @author MajickTek
 */
public class Parser {

    private static String[] cmd;
    public static boolean debugEnabled = false;
    private static Keywords kwd = null;
    private static String delimiterregex = kwd.OPERATOR.toString();
    
    private static ScriptEngineManager mgr = new ScriptEngineManager();
    private static ScriptEngine engine = mgr.getEngineByName("JavaScript");
    
    
    public static void parseLine(String s) {
        /*
        syntax:
        <op1> => <op2>
        pseudocode:
        if text equals op1 then do something with op2
         */

        cmd = s.split(delimiterregex); //operator between command and parameter
        String left = cmd[0];//command
        String right = cmd[1];//parameter

        if (left.equalsIgnoreCase(kwd.PRINT.toString())) {
            System.out.println(right);
        }

        if (left.equalsIgnoreCase(kwd.PARSE.toString())) {
            printTree();
        } //debug, call with parse=>>

        if (right.equalsIgnoreCase(kwd.NULL.toString())) {
            System.out.println("NULL");
        }
        if (left.equalsIgnoreCase(kwd.SETVAR.toString())) {
            String[] parameter = right.split(",");
            VariableEngine.insert(parameter[0], parameter[1]);
        }
        if (left.equalsIgnoreCase(kwd.GETVAR.toString())) {
            System.out.println(VariableEngine.getKeyValue(right));
        }
        if (left.equalsIgnoreCase(kwd.EVAL.toString())) {
            System.out.println(doJSEval(right));
        }
        if(left.equalsIgnoreCase(kwd.MATHVAR.toString())) {
            String[] parameter = right.split(",");
            String test = doJSEval(parameter[1]);
            VariableEngine.insert(parameter[0], test);
        }
        if(left.equalsIgnoreCase(kwd.DOFILE.toString())) {// mostly for argument usage
            try {
                FileIO.readFile(right);
            } catch (IOException ex) {
                Logger.getLogger(Parser.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(left.equalsIgnoreCase(kwd.TOGGLEDEBUG.toString())) { // for arguments
            if(right.equalsIgnoreCase("true")) {
                debugEnabled = true;
            }
            if(right.equalsIgnoreCase("false")) {
                debugEnabled = false;
            }
        }
        if (debugEnabled == true) {
            doDebug(s);
            doGetSystemProperty(s);
        }

    }

    private static void printTree() { // for debug purposes
        for (int i = 0; i < cmd.length; i++) {
            System.out.println("\n" + cmd[i] + "\n");
        }
    }

    private static void doDebug(String s) {

        cmd = s.split(delimiterregex); //operator between command and parameter
        String left = cmd[0];//command
        String right = cmd[1];//parameter
        if (left.equalsIgnoreCase(kwd.DEBUG.toString())) {
            if (right.equalsIgnoreCase("help")) {
                System.out.println("listVarMap - List variables");
            }
            if (right.equalsIgnoreCase("listVarMap")) {
                VariableEngine.showDebugDisplay();
            }

        }

    }

    private static void doGetSystemProperty(String s) {

        cmd = s.split(delimiterregex); //operator between command and parameter
        String left = cmd[0];//command
        String right = cmd[1];//parameter
        if (left.equalsIgnoreCase(kwd.GETSYSTEMPROPERTY.toString())) {
            System.out.println(System.getProperty(right));
            if (right.equalsIgnoreCase("all")) {
                System.out.println(System.getProperties().toString());
            }
        }
        if (left.equalsIgnoreCase(kwd.GETSYSTEMENV.toString())) {
            System.out.println(System.getenv(right));
        }
    }

    private static String doJSEval(String input) {
        try {
            Object test = engine.eval(input);
            return test.toString();
        } catch (ScriptException ex) {
            Logger.getLogger(Parser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "NULL";
    }


}
