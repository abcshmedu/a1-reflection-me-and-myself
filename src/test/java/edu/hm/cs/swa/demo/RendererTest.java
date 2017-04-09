/* (C) 2017, O. Hauser, ohauser@hm.edu
 * Munich University of Applied Sciences, Department 07, Computer Science
 * Java 1.8.0_121, Linux x86_64 4.4.0-66-generic
 * Dell (Intel Core i7-5500U CPU @ 2.40GHz, 4 cores, 8000 MByte RAM)
 **/

package edu.hm.cs.swa.demo;

import static org.junit.Assert.*;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.Arrays;
import java.util.Collection;

/**
 * A test class with a ParameterizedRunner.
 * @author Oliver Hauser, ohauser@hm.edu
 * @since 09.04.2017
 */
@RunWith(Parameterized.class)
public class RendererTest {

    //fields
    private Renderer output;
    private String expected;

    private static final int THREE = 3;
    private static final double FOUR = 4.0;
    private static final int FIVE = 5;


    /**
     * Constructor.
     * @param toRender the class to be rendered
     * @param expected the expected output
     */
    public RendererTest(Object toRender, String expected) {
        this.output = new Renderer(toRender);
        this.expected = expected;
    }

    /**
     * All possible parameters which should be tested.
     * @return Collection<Object[]> Arrays.asList(new Object[][])
     */
    @Parameterized.Parameters
    public static Collection<Object[]> generateData() {
        return Arrays.asList(new Object[][] {
            {new SomeClass(FIVE), "Instance of edu.hm.cs.swa.demo.SomeClass:\nfoo (Type int): 5\narray (Type int[]) [1, 2, 3, ]\ndate (Type java.util.Date): Fri Jan 02 11:17:36 CET 1970\n"},
            {new Class1(), "Instance of edu.hm.cs.swa.demo.RendererTest.Class1:\nTWO (Type int): 2\n"},
            {new Class2(), "Instance of edu.hm.cs.swa.demo.RendererTest.Class2:\none (Type java.lang.String): one\nTWO (Type double): 2.0\n"},
            {new Class3(), "Instance of edu.hm.cs.swa.demo.RendererTest.Class3:\none (Type int[]) [1, ]\nthree (Type char[]) [t, h, r, e, e, ]\n"},
            {new Class4(), "Instance of edu.hm.cs.swa.demo.RendererTest.Class4:\ntwo (Type int[]): [I@1764bce\nreturn3 (Type int[]) [1, 2, 3, ]\nreturn4 (Type double): 4.0\n"}
        });
    }

    /**
     * Test for all cases.
     * @throws Exception throw exception if test does not pass
     */
    @Test
    public void testRendering() throws Exception {
        assertEquals(output.render(), expected);
    }

    /**
     * Test class number 1.
     */
    private static class Class1 {
        private final int one = 1;
        @RenderMe private static final int TWO = 2;
    }

    /**
     * Test class number 2.
     */
    private static class Class2 {
        @RenderMe private final String one = "one";
        @RenderMe static final double TWO = 2.0;
    }

    /**
     * Test class number 3.
     */
    private static class Class3 {
        @RenderMe(with = "edu.hm.cs.swa.demo.ArrayRenderer") private final int[] one = new int[] {1};
        private long two = 2;
        @RenderMe(with = "edu.hm.cs.swa.demo.ArrayRenderer") private char[] three = new char[] {'t', 'h', 'r', 'e', 'e'};
    }

    /**
     * Test class number 4.
     */
    private static class Class4 {

        /**
         * returns 1.
         * @return int 1
         */
        private int return1() {
            return 1;
        }

        @RenderMe private int[] two = new int[] {1, 2};

        /**
         * Return 1,2,3.
         * @return int[] array
         */
        @RenderMe(with = "edu.hm.cs.swa.demo.ArrayRenderer")
        private int[] return3() {
            return new int[] {1, 2, THREE};
        }

        /**
         * return 4.0.
         * @return double 4.0
         */
        @RenderMe double return4() {
            return FOUR;
        }
    }


}