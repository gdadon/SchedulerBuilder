package objects;

public class Demand {

    private int start;
    private int end;
    private int day;
    private String reason;

    private Demand(DemandBuilder builder) {
        this.start = builder.start;
        this.end = builder.end;
        this.day = builder.day;
        this.reason = builder.reason;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public int getDay() {
        return day;
    }

    public String getReason() {
        return reason;
    }

    public static class DemandBuilder{

        private int start;
        private int end;
        private int day;
        private String reason;

        public DemandBuilder() {

        }

        public DemandBuilder setStart(int start) {
            this.start = start;
            return this;
        }

        public DemandBuilder setEnd(int end) {
            this.end = end;
            return this;
        }

        public DemandBuilder setDay(int day) {
            this.day = day;
            return this;
        }

        public DemandBuilder setReason(String reason) {
            this.reason = reason;
            return this;
        }

        public Demand build(){
            return new Demand(this);
        }
    }
}