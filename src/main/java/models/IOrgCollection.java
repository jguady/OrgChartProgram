package models;

import java.util.List;

/**
 * Created by Guady on 6/27/2017.
 */
public interface IOrgCollection {

    IOrg getOrg(Integer orgId);

    List<Org> getOrgTree(int orgId, boolean inclusive);
}
