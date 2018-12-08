/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplexlang.parser;

/**
 *
 * @author MajickTek
 */
public enum Keywords { // helps me organize the main keywords, this is not defining any sort of grammar
    OPERATOR("=>"),    //this also helps me change these on the fly
    PRINT("print"),
    PARSE("parse"),
    SETVAR("setvar"),
    GETVAR("getvar"),
    DEBUG("debug"),
    NULL(">"),
    GETSYSTEMPROPERTY("getsysprop"),
    GETSYSTEMENV("getsysenv"),
    EVAL("eval"),
    MATHVAR("mathvar"),
    DOFILE("dofile"),
    TOGGLEDEBUG("toggledebug")
    ;

    private final String text;

    /**
     * @param text
     */
    Keywords(final String text) {
        this.text = text;
    }

    /* (non-Javadoc)
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return text;
    }
}