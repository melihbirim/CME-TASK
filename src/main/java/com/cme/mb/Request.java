package com.cme.mb;

public class Request {
    public String input;

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    @Override
    public String toString() {
        return "Request{ input='" + input + "}";
    }
}