package com.cme.mb;

import java.util.Arrays;

public class Response {
    String[] output;

    public Response(String[] output) {
        this.output = output;
    }

    public String[] getOutput() {
        return output;
    }

    public void setOutput(String[] output) {
        this.output = output;
    }

    @Override
    public String toString() {
        return Arrays.toString(output);
    }
}
