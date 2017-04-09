/* (C) 2017, O. Hauser, ohauser@hm.edu
 * Munich University of Applied Sciences, Department 07, Computer Science
 * Java 1.8.0_121, Linux x86_64 4.4.0-66-generic
 * Dell (Intel Core i7-5500U CPU @ 2.40GHz, 4 cores, 8000 MByte RAM)
 **/

package edu.hm.cs.swa.demo;

import java.lang.reflect.Array;

/**
 * A renderer for arrays.
 * @author Oliver Hauser, ohauser@hm.edu
 * @since 09.04.2017
 */
public class ArrayRenderer {

    /**
     * Renders the values of an array.
     * Loops through all values of an array
     * and renders them splitted with commas and
     * in square brackets.
     * @param inputField field with array
     * @return output string
     */
    public static String render(Object inputField){

        String arrayValues = " [";

        for (int i = 0; i < Array.getLength(inputField); i++) {
            arrayValues += Array.get(inputField, i);
            arrayValues += ", ";
        }

        return arrayValues+"]";
    }
}
