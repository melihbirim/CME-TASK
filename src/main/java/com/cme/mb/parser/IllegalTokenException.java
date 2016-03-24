package com.cme.mb.parser;

public class IllegalTokenException extends Exception {

    public IllegalTokenException(String token, String should) {
        super(String.format("Illegal token detected as [%s] but should be [%s]", token, should));
    }
}
