package com.cme.mb;

public class Request {
    public String input;

    public String getInput() {
        if(input == null) input = "";
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    @Override
    public String toString() {
        return "Request{ INPUT='" + input + "}";
    }
}