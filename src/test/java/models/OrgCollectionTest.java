package models;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static org.junit.Assert.*;

/**
 * Created by Guady on 6/27/2017.
 */
public class OrgCollectionTest {
    @Before
    public void initialize()
    {
        OrgCollection.getInstance().clear();
    }


    @Test
    public void getInstance() throws Exception {
        OrgCollection collection1 = OrgCollection.getInstance();
        OrgCollection collection2 = OrgCollection.getInstance();
        assertEquals(collection1,collection2);
    }

    @Test
    public void getOrg() throws Exception {
        OrgCollection collection = OrgCollection.getInstance();
        int id = new Random().nextInt();
        Org testOrg = new Org(id,"TestOrg");
        collection.addOrg(testOrg);

        Org actualOrg = collection.getOrg(id);

        assertEquals(testOrg.getOrgId(),actualOrg.getOrgId());
        assertEquals(testOrg.getOrgName(),actualOrg.getOrgName());
    }

    @Test
    public void addAndRemoveOrg() throws Exception {
        OrgCollection collection = OrgCollection.getInstance();
        List<Integer> ids = new ArrayList<>();
        for(int i=0;i<100;i++)
        {
            int id = new Random().nextInt();
            ids.add(id);
            Org testOrg = new Org(id,"TestOrg"+i);
            collection.addOrg(testOrg);
        }

        assertEquals(100, collection.size());

        for(Integer id: ids)
        {
            collection.removeOrg(id);
        }

        assertEquals(0, collection.size());
        assertTrue(collection.isEmpty());
    }
    @Test
    public void getAllRoots()
    {
        Org orgA = new Org(1, "Root", Optional.empty());
        Org orgB = new Org(2, "Child", Optional.of(1));
        Org orgC = new Org(3, "Root", Optional.empty());

        List<Org> expected = new ArrayList<>();
        expected.add(orgA);
        expected.add(orgC);

        OrgCollection.getInstance().addOrg(orgA);
        OrgCollection.getInstance().addOrg(orgB);
        OrgCollection.getInstance().addOrg(orgC);

        List<Org> actual = OrgCollection.getInstance().getAllRoots();

        assertEquals(expected.size(),actual.size());
        assertTrue(expected.containsAll(actual));


    }

//    public String resultMessage(Object expected, Object actual)
//    {
//        return "Assert Failed: Expected [" + expected + "] Actual [" + actual + "]";
//    }



}