package com.cme.mb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by melih on 21.03.2016.
 */
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
