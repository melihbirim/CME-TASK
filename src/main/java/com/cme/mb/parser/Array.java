package com.cme.mb.parser;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Array implements Node {
    List<Node> nodes;

    public Array(boolean begin, InputStream input) throws IOException, ParseException, IllegalTokenException {
        nodes = new ArrayList<>();
        parse(begin, input);
    }

    public void parse(boolean begin, InputStream input) throws IllegalTokenException, ParseException, IOException {
        Number number = null; // buffer
        int c;
        boolean lastArray = false;
        while ((c = input.read()) != -1) {
            if (!begin && Token.LEFT_SQ_BRACKET == c) {
                if (number != null) {
                    throw new ParseException("There should be either ',' or ']' or other than '[' ");
                }
                begin = true;
            } else if (Token.RIGHT_SQ_BRACKET == c) {
                if (!begin) throw new ParseException("There should either '[' or a 'digit' other than ']'");
                if (number != null)
                    nodes.add(number);
                return; // termination point
            } else {
                if (begin) {
                    if (Token.isEmpty(c)) {
                    } else if (Token.isDigit(c)) {
                        if (number == null) {
                            number = new Number();
                        }
                        number.append(c);
                    } else if (Token.isComma(c)) {
                        if (number == null && !lastArray) {
                            throw new ParseException("There should be either '[' or a 'digit' other than COMMA ");
                        } else if(!lastArray){
                            nodes.add(number);
                            number = null;
                        }
                        lastArray = false;
                    } else {
                        lastArray = true;
                        nodes.add(new Array(true, input));
                    }
                } else return;
            }
        }
    }

    public String value() {
        return nodes.stream()
                .map(i -> i.toString())
                .collect(Collectors.joining(", "));
    }

    @Override
    public String toString() {
        return value();
    }
}
