package models;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Created by Guady on 6/27/2017.
 */
//@RunWith(Parameterized.class)
public class OrgCollectionGetOrgTreeTest {
//    @Parameterized.Parameters
//    public static Collection<Object[]> data() {
//        return Arrays.asList( new Object[][] {
//
//            {       new Org(1,"Root", Optional.empty()),
//                    new Org(2,"Child",Optional.of(1)),
//                    new Org(3,"Child",Optional.of(1)),
//                    1,
//                    TestOrgTreeFactory.get().createTree("twoChildTree")
//
//            },
//
//            {
//                    new Org(3,"Child",Optional.of(1)),
//                    new Org(2,"Child",Optional.of(1)),
//                    new Org(1,"Root",Optional.empty()),
//                    1,
//                    TestOrgTreeFactory.get().createTree("twoChildTree")
//            },
//                {
//                        new Org(1,"Root", Optional.empty()),
//                        new Org(2,"Child",Optional.of(1)),
//                        new Org(3,"Child",Optional.of(2)),
//                        1,
//                        TestOrgTreeFactory.get().createTree("oneChildPerNodeTree")
//                }, {
//                new Org(1,"Root", Optional.empty()),
//                new Org(2,"Root",Optional.empty()),
//                new Org(3,"Child",Optional.of(1)),
//                1,
//                TestOrgTreeFactory.get().createTree("twoRootTree")
//        }, {
//                new Org(1,"Root", Optional.empty()),
//                new Org(2,"Root",Optional.empty()),
//                new Org(3,"Root",Optional.empty()),
//                3,
//                TestOrgTreeFactory.get().createTree("allRootTree")
//        },
//        });
//    }

//    private List<Org> inputOrgs;
//    private int rootOrgId;
//    private List<Org> expectedTree;
//
//    public OrgCollectionGetOrgTreeTest(Org orgA, Org orgB, Org orgC,int rootOrgId, List<Org> expected)
//    {
//        this.orgA = orgA;
//        this.orgB = orgB;
//        this.orgC = orgC;
//        this.rootOrgId = rootOrgId;
//        this.expectedTree = expected;
//    }
    @Before
    public void initialize()
    {
        OrgCollection.getInstance().clear();
    }

    @Test
    public void getOrgTreeTwoNodeTree()
    {
        OrgCollection collection = OrgCollection.getInstance();
        Org orgA = new Org(1, "Root", Optional.empty(),OrgCollection.getInstance());
        Org orgB = new Org(2, "Child", Optional.of(1),OrgCollection.getInstance());
        Org orgC = new Org(3, "Child", Optional.of(1),OrgCollection.getInstance());

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
        Org orgA = new Org(1, "Root", Optional.empty(),OrgCollection.getInstance());
        Org orgB = new Org(2, "Child", Optional.of(1),OrgCollection.getInstance());
        Org orgC = new Org(3, "Child", Optional.of(2),OrgCollection.getInstance());

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
    public void getOrgTreeOrder()
    {
        OrgCollection collection = OrgCollection.getInstance();
        Org orgA = new Org(1, "Root", Optional.empty(),OrgCollection.getInstance());
        Org orgB = new Org(2, "Child", Optional.of(1),OrgCollection.getInstance());
        Org orgC = new Org(3, "Child", Optional.of(2),OrgCollection.getInstance());
        Org orgD = new Org(4, "Child", Optional.of(1),OrgCollection.getInstance());
        Org orgE = new Org(5, "Child", Optional.of(3),OrgCollection.getInstance());

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