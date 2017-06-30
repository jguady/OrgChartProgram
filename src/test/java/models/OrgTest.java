package models;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Guady on 6/28/2017.
 */
public class OrgTest {

    List<Org> testTree;
    User tom;
    User sally;
    User sam;

    @Before
    public void setUp() throws Exception {
        OrgCollection.getInstance().clear();
        testTree = TestOrgTreeFactory.get().createTree("OrderTestTree");

        tom = new User();
        tom.setNumFiles(100);
        tom.setNumBytes(1024);

        sally = new User();
        sally.setNumBytes(2048);
        sally.setNumFiles(50);

        sam = new User();
        sam.setNumFiles(405);
        sam.setNumBytes(512);

    }

    @Test
    public void getTotalNumUsers() throws Exception {
        //Add users
        Org orgE = testTree.get(3);
        orgE.addUser(tom);

        Org orgC = testTree.get(4);
        orgC.addUser(sally);


        assertEquals(1,orgE.getTotalNumUsers());
        assertEquals(1,orgC.getTotalNumUsers());

        assertEquals(2,testTree.get(2).getTotalNumUsers());
        assertEquals(2,testTree.get(0).getTotalNumUsers());

        Org orgA = testTree.get(0);
        orgA.addUser(sam);

        assertEquals(1,orgE.getTotalNumUsers());
        assertEquals(1,orgC.getTotalNumUsers());

        assertEquals(2,testTree.get(2).getTotalNumUsers());
        assertEquals(3,testTree.get(0).getTotalNumUsers());


    }

    @Test
    public void getTotalNumFiles() throws Exception {

        Org orgE = testTree.get(3);
        orgE.addUser(tom);

        Org orgC = testTree.get(4);
        orgC.addUser(sally);

        assertEquals(100,orgE.getTotalNumFiles());
        assertEquals(50,orgC.getTotalNumFiles());

        assertEquals(150,testTree.get(2).getTotalNumFiles());
        assertEquals(150,testTree.get(0).getTotalNumFiles());

        Org orgA = testTree.get(0);
        orgA.addUser(sam);

        assertEquals(100,orgE.getTotalNumFiles());
        assertEquals(50,orgC.getTotalNumFiles());

        assertEquals(150,testTree.get(2).getTotalNumFiles());
        assertEquals(555,testTree.get(0).getTotalNumFiles());


    }

    @Test
    public void getTotalNumBytes() throws Exception {
        Org orgE = testTree.get(3);
        orgE.addUser(tom);

        Org orgC = testTree.get(4);
        orgC.addUser(sally);

        assertEquals(1024,orgE.getTotalNumBytes());
        assertEquals(2048,orgC.getTotalNumBytes());

        assertEquals(3072,testTree.get(2).getTotalNumBytes());
        assertEquals(3072,testTree.get(0).getTotalNumBytes());

        Org orgA = testTree.get(0);
        orgA.addUser(sam);

        assertEquals(1024,orgE.getTotalNumBytes());
        assertEquals(2048,orgC.getTotalNumBytes());

        assertEquals(3072,testTree.get(2).getTotalNumBytes());
        assertEquals(3584,testTree.get(0).getTotalNumBytes());

    }

    @Test(expected = UnsupportedOperationException.class)
    public void addOrgCreatesCycle()
    {
        testTree = TestOrgTreeFactory.get().createTree("ComplexCycleTree");
    }
}