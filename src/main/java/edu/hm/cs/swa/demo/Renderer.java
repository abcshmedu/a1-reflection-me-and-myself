package edu.hm.cs.swa.demo;

import java.lang.reflect.Array;
import java.lang.reflect.Field;

/**
 * Created by oliver on 30.03.17.
 */

public class Renderer {

    private final Object target;

    public Renderer(Object object){
        this.target = object;
    }

    public String render() throws IllegalAccessException, InstantiationException {

        final StringBuffer output = new StringBuffer();
        output.append("Instance of "+ target.getClass().getCanonicalName() +":\n");

        Field[] fields = target.getClass().getDeclaredFields();
        for(Field field: fields) {
            if(field.isAnnotationPresent(RenderMe.class)){

                field.setAccessible(true);

                Object value = field.get(target.getClass().newInstance());

                if(field.getType().isArray()) {
                    Object array = field.get(target.getClass().newInstance());
                    int length = Array.getLength(array);
                    for (int i = 0; i < length; i++) {
                         value = Array.get(array, i);

                    }
                }


                output.append(String.format("%s (Type %s): %s\n",
                        field.getName(),
                        field.getType().getCanonicalName(),
                        value));
            }


        }
        return output.toString();
    }


    public static void main(String... args) throws InstantiationException, IllegalAccessException {
        System.out.println(new Renderer(new App()).render());
    }

}
