package com.cme.mb;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.cme.mb.parser.IllegalTokenException;
import com.cme.mb.parser.Input;
import com.cme.mb.parser.ParseException;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class RecursiveArrayFlattenService implements RequestStreamHandler {
    @Override
    public void handleRequest(InputStream inputStream, OutputStream outputStream, Context context) throws IOException {
        LambdaLogger logger = context.getLogger();
        logger.log("[DEBUG] starting with request INPUT stream");

        Input input = new Input();
        try {
            input.parse(inputStream);
            outputStream.write(input.value());
        } catch (ParseException | IllegalTokenException e) {
            e.printStackTrace();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeChars(String.format("{'error' : '%s'}", e.getMessage()));
        }
        logger.log("[DEBUG] Finished with request INPUT");
    }
}
