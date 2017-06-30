package models;

/**
 * Created by Guady on 6/27/2017.
 */
public class User {
    private int userId;
    private int orgId;
    private int numFiles;
    private int numBytes;

    public User() {}

    public User(int userId, int orgId, int numFiles, int numBytes) {
        this.userId = userId;
        this.orgId = orgId;
        this.numFiles = numFiles;
        this.numBytes = numBytes;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getOrgId() {
        return orgId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }

    public int getNumFiles() {
        return numFiles;
    }

    public void setNumFiles(int numFiles) {
        this.numFiles = numFiles;
    }

    public int getNumBytes() {
        return numBytes;
    }

    public void setNumBytes(int numBytes) {
        this.numBytes = numBytes;
    }

}
