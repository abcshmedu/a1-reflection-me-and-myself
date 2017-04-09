/* (C) 2017, O. Hauser, ohauser@hm.edu
 * Munich University of Applied Sciences, Department 07, Computer Science
 * Java 1.8.0_121, Linux x86_64 4.4.0-66-generic
 * Dell (Intel Core i7-5500U CPU @ 2.40GHz, 4 cores, 8000 MByte RAM)
 **/

package edu.hm.cs.swa.demo;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * A annotation class to declare renderable fields and methods.
 * @author Oliver Hauser, ohauser@hm.edu
 * @since 09.04.2017
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface RenderMe {

    String with() default "";
}
