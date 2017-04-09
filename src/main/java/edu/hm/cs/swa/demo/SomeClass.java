package edu.hm.cs.swa.demo;
import java.util.*;

/**
 * A basic test class.
 */
public class SomeClass {

    //fields
    @RenderMe private int foo;
    @RenderMe(with="edu.hm.cs.swa.demo.ArrayRenderer") int[] array = {1, 2, 3, };
    @RenderMe private Date date = new Date(123456789);

    /**
     * Constructor.
     * @param foo
     */
    public SomeClass(int foo) {
        this.foo = foo;
    }
}