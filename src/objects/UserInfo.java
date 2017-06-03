package objects;

/**
 * Created by Guy on 02/06/2017.
 */
public class UserInfo {

    private String id;
    private String name;
    private int privilege;

    public UserInfo(String id, String name, int priv){
        this.id = id;
        this.name = name;
        this.privilege = priv;
    }

    public String getId() {
        return id;
    }

    public int getPrivilege() {
        return privilege;
    }

    public String getName() {
        return name;
    }
}
