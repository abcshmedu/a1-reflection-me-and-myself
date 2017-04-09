/* (C) 2017, O. Hauser, ohauser@hm.edu
 * Munich University of Applied Sciences, Department 07, Computer Science
 * Java 1.8.0_121, Linux x86_64 4.4.0-66-generic
 * Dell (Intel Core i7-5500U CPU @ 2.40GHz, 4 cores, 8000 MByte RAM)
 **/

package edu.hm.cs.swa.demo;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * A class which renders fields with the annotation @RenderMe.
 * @author Oliver Hauser, ohauser@hm.edu
 * @since 09.04.2017
 */
public class Renderer {

    //fields
    private final Object toRender;

    /**
     * Constructor
     * @param object
     */
    public Renderer(Object object){
        this.toRender = object;
    }

    /**
     * Renders fields and methods of a class.
     * Checks for all fields and methods with @RenderMe
     * annotation and renders them with the pattern:
     * name (Type type): value\n .
     * @return output string
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public String render() throws IllegalAccessException, InstantiationException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException {

        //output buffer
        final StringBuffer output = new StringBuffer();
        output.append("Instance of "+ toRender.getClass().getCanonicalName() +":\n");

        //get fields of toRender class
        Field[] fields = toRender.getClass().getDeclaredFields();

        //loop through fields
        for(Field field: fields) {

            //check if field has annotation @RenderMe
            if(field.isAnnotationPresent(RenderMe.class)){

                //change the field to public
                if (!field.isAccessible()) {
                    field.setAccessible(true);
                }
                final Object fieldValue;
                final String withClassString = field.getAnnotation(RenderMe.class).with();

                //is annotation value given?
                if (!withClassString.equals("")){

                    //get method of class given in with annotation
                    Method method = callClassOfWithAnnotation(withClassString);

                    //use render method on the field value
                    fieldValue = method.invoke(Class.forName(withClassString).newInstance(),field.get(toRender));
                }

                else
                    //print the field value with toString()
                    fieldValue = ": "+field.get(toRender).toString();

                output.append(String.format("%s (Type %s)%s\n", field.getName(), field.getType().getCanonicalName(), fieldValue));
            }
        }

        //get methods of toRender class
        Method[] methods = toRender.getClass().getDeclaredMethods();

        //loop through methods
        for(Method method : methods){

            //check if method has annotation @RenderMe and no return type void and no parameters
            if(method.isAnnotationPresent(RenderMe.class) && !method.getReturnType().equals(Void.TYPE) && method.getParameterCount() == 0){

                //set method to public
                if(!method.isAccessible()) {
                    method.setAccessible(true);
                }
                final Object methodValue;
                final String withClassString = method.getAnnotation(RenderMe.class).with();

                //is annotation value given?
                if (!withClassString.equals("")) {

                    //get method of class given in with annotation
                    Method thatMethod = callClassOfWithAnnotation(withClassString);

                    //use that method on the return value of this class
                    methodValue = thatMethod.invoke(Class.forName(withClassString).newInstance(), method.invoke(toRender));

                }
                else {
                    //get return value of method
                    methodValue = ": " + method.invoke(toRender);
                }
                output.append(String.format("%s (Type %s)%s\n", method.getName(), method.getReturnType().getCanonicalName(), methodValue));
            }
        }
        return output.toString();
    }

    /**
     * returns the method of the class given in the
     * with value of the @RenderMe annoation.
     * @param withClassString string of the with value
     * @return Method the method of the class
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws NoSuchMethodException
     */
    private Method callClassOfWithAnnotation(String withClassString) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException {

        //get render method of the given class
        final Class withClass = Class.forName(withClassString);
        final Method method = withClass.getMethod("render",Object.class);

        //set method to public
        if(!method.isAccessible()){
            method.setAccessible(true);
        }

        return method;
    }
}
