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
    public Org getOrg(Integer orgId) {
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
        List<Org> results = new ArrayList<>();

        Stack<Org> stack = new Stack<>();
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

        return results;
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
                .filter(org -> org.getParentOrgId() == null)
                .collect(Collectors.toList());
    }

    public void clear()
    {
        orgMap.clear();
    }
}
