package objects;

/**
 * Created by Guy on 03/06/2017.
 */
public enum Status {

    PENDING("Pending"),
    ACCEPT("Approved"),
    DECLINE("Declined");

    private String name;

    private Status(String value) {
        name = value;
    }

    public String getName() {
        return name;
    }
}

