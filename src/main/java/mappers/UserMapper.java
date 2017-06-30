package mappers;

import models.User;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by Guady on 6/28/2017.
 */
public class UserMapper implements Mapper<User> {

    @Override
    public User map(List<String> data) {
        User user= new User();
        user.setUserId(Integer.parseInt(data.get(0)));
        user.setOrgId(Integer.parseInt(data.get(1)));
        user.setNumFiles(Integer.parseInt(data.get(2)));
        user.setNumBytes(Integer.parseInt(data.get(3)));
        return user;
    }

    @Override
    public User map(List<String> headers, List<String> data) {
        User user = new User();
        for(int i =0; i<headers.size();i++)
        {
            String header = headers.get(i);
            try {
                Field field = user.getClass().getDeclaredField(header);
                String setterName = "set"+header.substring(0, 1).toUpperCase() + header.substring(1);
                Method setter = user.getClass().getMethod(setterName, field.getType());
                setter.invoke(user, toObjectFromString(field.getType(),data.get(i)));
            } catch (NoSuchMethodException e) {
                System.err.println("Could not set value for: " +header);
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e) {
                System.err.println("Could not find a field corresponding to " +header);
                e.printStackTrace();
            }
        }

        return user;
    }
}
