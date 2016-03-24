package com.cme.mb.parser;

import java.io.IOException;
import java.io.InputStream;

public interface Node {
    //public void parse(InputStream input) throws IOException, ParseException;
    public Object value();
}
