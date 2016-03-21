package com.cme.mb;

import com.amazonaws.services.lambda.runtime.Context;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ArrayFlattenServiceTest {
    Logger logger = Logger.getLogger(ArrayFlattenService.class);

    @Test
    public void handle() throws Exception {
        Request request = mock(Request.class);
        Context context = mock(Context.class);

        when(context.getLogger()).thenReturn(new LamdaLogger());

        when(request.getInput()).thenReturn("[]");    // Mock implementation

        Response handle = ArrayFlattenService.handle(request, context);
        context.getLogger().log(handle.toString());
        Assert.assertEquals(handle.getOutput().length, 0);

        when(request.getInput()).thenReturn("[1,2,4]");    // Mock implementation

        handle = ArrayFlattenService.handle(request, context);
        Assert.assertEquals(handle.getOutput(), new String[]{"1","2","4"});


        when(request.getInput()).thenReturn("[1,2,4,[ 5,6 ]]");    // Mock implementation

        handle = ArrayFlattenService.handle(request, context);
        Assert.assertEquals(handle.getOutput(), new String[]{"1","2","4","5","6"});

        when(request.getInput()).thenReturn("[1,2,4,[ 5,6 ,[7,9]],[4455]]");    // Mock implementation

        handle = ArrayFlattenService.handle(request, context);
        Assert.assertEquals(handle.getOutput(), new String[]{"1","2","4","5","6","7","9","4455"});

        when(request.getInput()).thenReturn("[as]");    // Mock implementation

        handle = ArrayFlattenService.handle(request, context);
        Assert.assertEquals(handle.getOutput().length, 0);

        when(request.getInput()).thenReturn("[[3],[1,[34,2],[12,323,55,23],[2]]]");    // Mock implementation

        handle = ArrayFlattenService.handle(request, context);
        Assert.assertEquals(handle.getOutput(), new String[]{"3","1","34","2","12","323","55","23","2"});
    }

    class LamdaLogger implements com.amazonaws.services.lambda.runtime.LambdaLogger {

        Logger logger = Logger.getLogger(ArrayFlattenService.class);

        @Override
        public void log(String s) {
            logger.debug(s);
        }
    }
}