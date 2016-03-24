package com.cme.mb.parser;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class Input {
    Node statement;

    boolean BEGIN = false;
    boolean END = false;

    public void parse(InputStream input) throws ParseException, IOException, IllegalTokenException {
        int c;
        while ((c = input.read()) != -1) {
            if (Token.LEFT_BRACKET == c) {
                if (BEGIN) throw new ParseException("Unable to parse input should wait for '}' to parse input");
                BEGIN = true;
            } else if (Token.RIGHT_BRACKET == c) {
                if (!BEGIN) throw new ParseException("Unable to parse input should wait for '}' to parse input");
                END = true;
            } else if (Token.isEmpty(c)) {
            } else {
                if (BEGIN) {
                    statement = new Statement(input);
                } else
                    throw new IllegalTokenException("" + c, "]");
            }
        }
    }

    public byte[] value(){
        return statement.value().toString().getBytes();
    }

    public void output(OutputStream outputStream) throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(statement.value());
    }
}
