package com.cme.mb.parser;

public class Key implements Node {
    public static final String INPUT = "input:";
    protected StringBuffer value = new StringBuffer();

    public void parse() throws IllegalTokenException {
        if (!INPUT.equals(value.toString())) throw new IllegalTokenException(value.toString(), INPUT);
    }

    public void append(char c) {
        if (c == Token.QUOT_MARK) ;
        else value.append(c);
    }

    public boolean isFull() {
        return value.capacity() <= INPUT.length();
    }

    public String value() {
        return value.toString();
    }

    public String toString() {
        return value();
    }
}
