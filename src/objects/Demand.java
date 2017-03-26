package objects;

public class Demand {

    private int day;
    private int start;
    private int end;
    private int total;
    private String reason;

    private Demand(DemandBuilder builder) {
        this.day = builder.day;
        this.start = builder.start;
        this.end = builder.end;
        this.total = builder.total;
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

    public int getTotal() {return total; }

    public String getReason() {
        return reason;
    }

    @Override
    public String toString() {
        return "Demand{" +
                "day=" + day +
                ", start=" + start +
                ", end=" + end +
                ", reason='" + reason + '\'' +
                '}';
    }

    public static class DemandBuilder{

        private int day;
        private int start;
        private int end;
        private int total;
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

        public DemandBuilder setTotal(int total){
            this.total = total;
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