package com.cme.mb;

import com.amazonaws.services.lambda.runtime.Context;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by melih on 24.03.2016.
 */
public class RecursiveArrayFlattenServiceTest extends ArrayFlattenServiceTest {

    @Test
    public void handleRequest() throws Exception {
        Logger logger = Logger.getLogger(ArrayFlattenService.class);

        Request request = mock(Request.class);
        Context context = mock(Context.class);

        when(context.getLogger()).thenReturn(new LamdaLogger());

        String exampleString;
        InputStream stream;
        RecursiveArrayFlattenService rfs = new RecursiveArrayFlattenService();

        exampleString = "{\"input\":[]}";
        stream = new ByteArrayInputStream(exampleString.getBytes(StandardCharsets.UTF_8));
        rfs.handleRequest(stream, System.out, context);

        exampleString = "{\"input\": [1,2,3]}";
        stream = new ByteArrayInputStream(exampleString.getBytes(StandardCharsets.UTF_8));
        rfs.handleRequest(stream, System.out, context);

        exampleString = "{\"input\": [1,2,3,4]}";
        stream = new ByteArrayInputStream(exampleString.getBytes(StandardCharsets.UTF_8));
        rfs.handleRequest(stream, System.out, context);

        exampleString = "{\"input\" : [1, [2,3, [4]], [5], 6, [7, [8]]]}";
        stream = new ByteArrayInputStream(exampleString.getBytes(StandardCharsets.UTF_8));
        rfs.handleRequest(stream, System.out, context);

    }
}