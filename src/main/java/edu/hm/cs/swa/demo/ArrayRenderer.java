package edu.hm.cs.swa.demo;

import java.lang.reflect.Array;

/**
 * Created by oliver on 09.04.17.
 */
public class ArrayRenderer {

    public static String render(Object inputField){

        String arrayValues = "[";
        final int length = Array.getLength(inputField);

        for (int i = 0; i < length; i++) {
            arrayValues += Array.get(inputField, i);
            arrayValues += ", ";
        }

        return arrayValues+"]";
    }
}
