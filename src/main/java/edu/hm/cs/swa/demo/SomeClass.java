package edu.hm.cs.swa.demo;
import java.util.*;

/**
 * A basic test class.
 */
public class SomeClass {

    private static final int NUMBERS = 123456789;
    private static final int THREE = 3;

    //fields
    @RenderMe private int foo;
    @RenderMe(with = "edu.hm.cs.swa.demo.ArrayRenderer")private int[] array = {1, 2, THREE, };
    @RenderMe private Date date = new Date(NUMBERS);

    /**
     * Constructor.
     * @param foo int value for foo
     */
    public SomeClass(int foo) {
        this.foo = foo;
    }
}