package models;


import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Created by Guady on 6/27/2017.
 */

public class OrgCollectionGetOrgTreeTest {
    @Before
    public void initialize()
    {
        OrgCollection.getInstance().clear();
    }

    @Test
    public void getOrgTreeTwoNodeTree()
    {
        OrgCollection collection = OrgCollection.getInstance();
        Org orgA = new Org(1, "Root", null);
        Org orgB = new Org(2, "Child", 1);
        Org orgC = new Org(3, "Child", 1);

        orgA.addChildOrg(orgC);
        orgA.addChildOrg(orgB);

        collection.addOrg(orgA);
        collection.addOrg(orgC);
        collection.addOrg(orgB);

        List<Org> actualTree = OrgCollection.getInstance().getOrgTree(1,true);
        List<Org> expectedTree = TestOrgTreeFactory.get().createTree("TWOCHILDTREE");
        assertEquals(expectedTree.size(), actualTree.size());

        //Check each org in the expected list is in the actual list
        ValidateTrees(actualTree, expectedTree);

    }

    @Test
    public void getOrgTreeOneChildPerNode()
    {
        OrgCollection collection = OrgCollection.getInstance();
        Org orgA = new Org(1, "Root", null);
        Org orgB = new Org(2, "Child", 1);
        Org orgC = new Org(3, "Child", 2);

        orgA.addChildOrg(orgC);
        orgA.addChildOrg(orgB);

        collection.addOrg(orgA);
        collection.addOrg(orgC);
        collection.addOrg(orgB);

        List<Org> actualTree = OrgCollection.getInstance().getOrgTree(1,true);
        List<Org> expectedTree = TestOrgTreeFactory.get().createTree("OneChildPerNodeTree");
        assertEquals(expectedTree.size(), actualTree.size());

        //Check each org in the expected list is in the actual list
        ValidateTrees(actualTree, expectedTree);

    }

    @Test
    public void getOrgTreeOneChildPerNodeExclusive()
    {
        OrgCollection collection = OrgCollection.getInstance();
        Org orgA = new Org(1, "Root", null);
        Org orgB = new Org(2, "Child", 1);
        Org orgC = new Org(3, "Child", 2);

        orgA.addChildOrg(orgC);
        orgA.addChildOrg(orgB);

        collection.addOrg(orgA);
        collection.addOrg(orgC);
        collection.addOrg(orgB);

        List<Org> actualTree = OrgCollection.getInstance().getOrgTree(1,false);
        List<Org> expectedTree = TestOrgTreeFactory.get().createTree("OneChildPerNodeTree");
        expectedTree.remove(0);
        assertEquals(expectedTree.size(), actualTree.size());

        //Check each org in the expected list is in the actual list
        ValidateTrees(actualTree, expectedTree);

    }

    @Test
    public void getOrgTreeOrder()
    {
        OrgCollection collection = OrgCollection.getInstance();
        Org orgA = new Org(1, "Root", null);
        Org orgB = new Org(2, "Child", 1);
        Org orgC = new Org(3, "Child", 2);
        Org orgD = new Org(4, "Child", 1);
        Org orgE = new Org(5, "Child", 3);

        // 1-> 2,4
        orgA.addChildOrg(orgB);
        orgA.addChildOrg(orgD);
        // 2->3
        orgB.addChildOrg(orgC);
        //3 ->5
        orgC.addChildOrg(orgE);


        collection.addOrg(orgA);
        collection.addOrg(orgC);
        collection.addOrg(orgB);
        collection.addOrg(orgD);
        collection.addOrg(orgE);


        List<Org> actualTree = OrgCollection.getInstance().getOrgTree(1,true);
        List<Org> expectedTree = new ArrayList<>();

        expectedTree.add(orgA);
        expectedTree.add(orgD);
        expectedTree.add(orgB);
        expectedTree.add(orgC);
        expectedTree.add(orgE);


        assertEquals(expectedTree.size(), actualTree.size());

        //Check each org in the expected list is in the actual list
        for(int i=0;i<expectedTree.size();i++)
        {
            assertEquals(expectedTree.get(i),actualTree.get(i));
        }

    }

    private void ValidateTrees(List<Org> actualTree, List<Org> expectedTree) {
        expectedTree.forEach((Org exOrg) -> {
            List<Org> orgs = actualTree.stream().filter(org -> org.getOrgId() == exOrg.getOrgId())
                    .collect(Collectors.toList());
            if(orgs.size() !=1)
            {
                fail("Not all nodes were found in the actual tree");
            }
        });
    }


}