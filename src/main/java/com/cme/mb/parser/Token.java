package com.cme.mb.parser;

public class Token {
    public static final char LEFT_BRACKET = '{';
    public static final char RIGHT_BRACKET = '}';
    public static final char LEFT_SQ_BRACKET = '[';
    public static final char RIGHT_SQ_BRACKET = ']';
    public static final char COLON = ':';
    public static final char QUOT_MARK = '"';
    public static final char COMMA = ',';
    public static final char SPACE = ' ';

    public static boolean isDigit(int token) {
        return token >= 48 && token <= 57;
    }

    public static boolean isComma(int token) {
        return token == COMMA;
    }

    public static boolean isEmpty(int token) {
        return token == 32;
    }

    public static boolean isChar(int token) {
        return Character.isAlphabetic(token) || token == COLON || token == SPACE || token == QUOT_MARK ;
    }
}
