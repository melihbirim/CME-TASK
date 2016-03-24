package com.cme.mb.parser;

import java.io.IOException;
import java.io.InputStream;

public class Statement implements Node {
    Key key = new Key();
    Array array;

    public Statement(InputStream input) throws IOException, ParseException, IllegalTokenException {
        int c;
        while ((c = input.read()) != -1) {
            if (Token.isEmpty(c)) ;
            else if (Token.isChar(c)) {
                if (!key.isFull()) {
                    key.append((char) c);
                } else {
                    throw new ParseException("There should not be any other character other than [input:] but got [" + c + "]");
                }
            } else {
                array = new Array(true, input);
                return;
            }
        }
    }

    @Override
    public String value() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("{\"output\": [");
        buffer.append(array);
        buffer.append("]}");
        return buffer.toString();
    }

    @Override
    public String toString() {
        return value();
    }
}
