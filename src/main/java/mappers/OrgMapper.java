package mappers;

import models.Org;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by Guady on 6/28/2017.
 */
public class OrgMapper implements Mapper<Org> {
    @Override
    public Org map(List<String> data) {
       Org org = new Org();
       org.setOrgId(Integer.parseInt(data.get(0)));
       try {org.setParentOrgId(Integer.parseInt(data.get(1)));} catch( NumberFormatException ex) {}
       org.setName(data.get(2));
       return org;
    }

    @Override
    public Org map(List<String> headers, List<String> data) {
        Org org = new Org();
        for(int i =0; i<headers.size();i++)
        {
            String header = headers.get(i);
            try {
                Field field = org.getClass().getDeclaredField(header);
                String setterName = "set"+header.substring(0, 1).toUpperCase() + header.substring(1);
                Method setter = org.getClass().getMethod(setterName, field.getType());
                setter.invoke(org, toObjectFromString(field.getType(), data.get(i)));
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
            } catch (NumberFormatException ex) {}
        }

        return org;
    }
}
