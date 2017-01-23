package objects;

public class DemandToDB extends Demand {
    private String reason;

    public DemandToDB(int start, int end, int day, String reason) {
        super(start, end, day);
        this.reason = reason;
    }

    public DemandToDB(Demand _demandToCopy, String reason) {
        super(_demandToCopy);
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "DemandToDB{" + super.toString() +
                " reason='" + reason + '\'' +
                '}';
    }
}
