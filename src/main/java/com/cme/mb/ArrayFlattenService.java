package com.cme.mb;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;

import java.util.Arrays;

public class ArrayFlattenService {

    public static Response handle(Request request, Context context) throws IllegalArgumentException {
        LambdaLogger logger = context.getLogger();
        logger.log(String.format("[DEBUG] starting with request object [%s]", request));
        String input = request.getInput();
        char[] arr = input.toCharArray();

        int braceCount = 0;
        StringBuffer stringBuffer = new StringBuffer();

        for (int i = 0; i < arr.length; i++) {
            char c = arr[i];
            switch (c) {
                case '[':
                    braceCount++;
                    break;
                case ']':
                    braceCount--;
                    break;
                case ' ':
                    break;
                default:
                    if ((c <= 57 && c >= 48) || c == ',')
                        stringBuffer.append(c);
            }
        }

        if (braceCount != 0) {
            throw new IllegalArgumentException("[PARSE_ERROR] Unable to get input as " + request);
        }

        String[] output = new String[0];

        if (!stringBuffer.toString().isEmpty()) {
            output = stringBuffer.toString().split(",");
        }
        logger.log(Arrays.toString(output));
        return new Response(output);
    }
}
