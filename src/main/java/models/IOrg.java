package models;

import java.util.List;

/**
 * Created by Guady on 6/27/2017.
 */
public interface IOrg {
    int getTotalNumUsers();
    int getTotalNumFiles();
    int getTotalNumBytes();
    List<Org> getChildOrgs();
}
