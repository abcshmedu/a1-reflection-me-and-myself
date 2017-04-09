package edu.hm.cs.swa.demo;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * A class which renders fields with the annotation @
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
     * method to render the fields
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
                if (!field.isAccessible())
                    field.setAccessible(true);

                final Object fieldValue;
                final String withClassString = field.getAnnotation(RenderMe.class).with();

                //is annotation value given?
                if (!withClassString.equals("")){

                    //get render method of the given class
                    Class withClass = Class.forName(withClassString);
                    Object instanceOfWithClass = withClass.newInstance();
                    Method method = withClass.getMethod("render",Object.class);

                    //set method to public
                    if(!method.isAccessible()){
                        method.setAccessible(true);
                    }

                    //use render method on the field value
                    fieldValue = method.invoke(instanceOfWithClass,field.get(toRender));
                }

                else
                    //print the field value with toString()
                    fieldValue = field.get(toRender).toString();

                output.append(String.format("%s (Type %s): %s\n", field.getName(), field.getType().getCanonicalName(),fieldValue));
            }
        }
        return output.toString();
    }


    public static void main(String... args) throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, ClassNotFoundException {
        System.out.println(new Renderer(new SomeClass(5)).render());
    }

}
