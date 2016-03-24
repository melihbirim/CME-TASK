package com.cme.mb.parser;


public class Number implements Node {
    protected StringBuffer value = new StringBuffer();

    public void append(int c) {
        value.append((char)c);
    }

    public String toString() {
        return value.toString();
    }

    public Integer value() {
        return Integer.parseInt(value.toString());
    }

}
