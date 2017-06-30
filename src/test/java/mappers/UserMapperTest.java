package mappers;

import models.User;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Guady on 6/29/2017.
 */
public class UserMapperTest {
    @Test
    public void map() throws Exception {
        List<String> values = new ArrayList<>(Arrays.asList("1","1","2004","981646"));

        UserMapper mapper = new UserMapper();

        User actual = mapper.map(values);
        User expected = new User();

        expected.setUserId(1);
        expected.setOrgId(1);
        expected.setNumFiles(2004);
        expected.setNumBytes(981646);

        assertNotNull(actual);
        assertEquals(expected.getUserId(), actual.getUserId());
        assertEquals(expected.getOrgId(), actual.getOrgId());
        assertEquals(expected.getNumFiles(), actual.getNumFiles());
        assertEquals(expected.getNumBytes(), actual.getNumBytes());
    }

    @Test
    public void mapWithHeadersSuccess() throws Exception {
        List<String> headers = new ArrayList<>(Arrays.asList("userId","orgId","numFiles","numBytes"));
        List<String> values = new ArrayList<>(Arrays.asList("1","1","2004","981646"));

        UserMapper mapper = new UserMapper();

        User actual = mapper.map(headers,values);
        User expected = new User();

        expected.setUserId(1);
        expected.setOrgId(1);
        expected.setNumFiles(2004);
        expected.setNumBytes(981646);

        assertNotNull(actual);
        assertEquals(expected.getUserId(), actual.getUserId());
        assertEquals(expected.getOrgId(), actual.getOrgId());
        assertEquals(expected.getNumFiles(), actual.getNumFiles());
        assertEquals(expected.getNumBytes(), actual.getNumBytes());
    }

}