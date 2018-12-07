/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplexlang.parser;

import simplexlang.variables.VariableEngine;

/**
 *
 * @author MajickTek
 */
public class Parser {

    private static String[] cmd;
    public static boolean debugEnabled = false;

    public static void parseLine(String s) {
        /*
        syntax:
        <op1> => <op2>
        pseudocode:
        if text equals op1 then do something with op2
         */ Keywords kwd = null;
        String delimeterregex = kwd.OPERATOR.toString();
        cmd = s.split(delimeterregex); //operator between command and parameter
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
        if (debugEnabled == true) {
            doDebug(s);
        } else {
            if (left.equalsIgnoreCase(kwd.DEBUG.toString())) {
                System.err.println("DEBUG not enabled, ignoring: " + right);
            }
        }

    }

    private static void printTree() { // for debug purposes
        for (int i = 0; i < cmd.length; i++) {
            System.out.println("\n" + cmd[i] + "\n");
        }
    }

    private static void doDebug(String s) {
        Keywords kwd = null;
        String delimeterregex = kwd.OPERATOR.toString();
        cmd = s.split(delimeterregex); //operator between command and parameter
        String left = cmd[0];//command
        String right = cmd[1];//parameter
        if (left.equalsIgnoreCase(kwd.DEBUG.toString())) {
            if (right.equalsIgnoreCase("listVarMap")) {
                VariableEngine.showDebugDisplay();
            }

        }
    }
}
