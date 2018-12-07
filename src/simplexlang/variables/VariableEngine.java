/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplexlang.variables;

import java.util.HashMap;

/**
 *
 * @author MajickTek
 */
public class VariableEngine {

    private static HashMap<String, String> vars = new HashMap<>();

    public static void insert(String key, String value) {
        vars.put(key, value);
    }

    public static void clear() {
        vars.clear();
    }

    public static void removeKey(String key) {
        vars.remove(key);
    }

    public static void removeKeyValuePair(String key, String value) {
        vars.remove(key, value);
    }

    public static void replace(String key, String value) {
        vars.replace(key, value);
    }

    public static void replaceInsert(String key, String oldValue, String newValue) {
        vars.replace(key, oldValue, newValue);
    }

    public static String getKeyValue(String key) {
        return vars.get(key);
    }

    public static int getSize() {
        return vars.size();
    }

    public static void showDebugDisplay() { // list entire variable table for debug purposes
        /*Set set = vars.entrySet();
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry) iterator.next();
           System.out.print("|key: " + mentry.getKey() + "|value: ");
           System.out.println(mentry.getValue()+"|");
            
        }*/
       System.out.println(vars.toString()); // this is cleaner, smaller and nicer
    }
}
