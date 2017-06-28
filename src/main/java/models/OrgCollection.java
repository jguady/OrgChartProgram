package models;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Guady on 6/27/2017.
 */
public class OrgCollection implements IOrgCollection{

    private static OrgCollection ourInstance = new OrgCollection();

    public static OrgCollection getInstance() {
        return ourInstance;
    }
    protected Map<Integer,Org> orgMap;


    private OrgCollection() {
        this.orgMap = new HashMap<>();
    }

    @Override
    public Org getOrg(int orgId) {
        return orgMap.get(orgId);
    }

    public void addOrg(Org org)
    {
        orgMap.put(org.getOrgId(), org);
    }

    public Org removeOrg(int orgId)
    {
        return orgMap.remove(orgId);
    }

    @Override
    public List<Org> getOrgTree(int orgId, boolean inclusive) {
        Org rootOrg = orgMap.get(orgId);
        //if inclusive create a new list with org in it
        List<Org> results = new ArrayList<>();

        //add all children of org to list
        //Queue<Org> queue = new LinkedList<>();
//        List<Org> childOrgs = rootOrg.getChildOrgs();
//        queue.addAll(childOrgs);
//        results.addAll(childOrgs);
//        while(!queue.isEmpty())
//        {
//            Org currentOrg = queue.remove();
//            List<Org> childOrgs = currentOrg.getChildOrgs();
//            results.addAll(childOrgs);
//            queue.addAll(childOrgs);
//        }



        Deque<Org> stack = new LinkedList<>();
        stack.push(rootOrg);

        while(!stack.isEmpty())
        {
            Org currentOrg = stack.pop();
            results.add(currentOrg);
            List<Org> childOrgs = currentOrg.getChildOrgs();
            for (Org child :childOrgs)
            {
                stack.push(child);

            }
        }
        if(!inclusive)
            results.remove(0);

//        do{
//            Org currentOrg = stack.pop();
//
//            List<Org> childOrgs = currentOrg.getChildOrgs();
//
//
//        }
//        while(!stack.isEmpty());

        return results;
        //Make a queue add all children
        //while list is not empty add children to list and queue children

    }

    public int size()
    {
        return orgMap.size();
    }
    public boolean isEmpty()
    {
        return orgMap.isEmpty();
    }

    public List<Org> getAllRoots()
    {

        return orgMap.values().stream()
                .filter(org -> !org.getParentOrgId().isPresent())
                .collect(Collectors.toList());
    }

    public void clear()
    {
        orgMap.clear();
    }
}
