package objects;

import java.io.Serializable;

public class Demand implements Serializable{

    private static final long serialVersionUID = 2L;

    private String dayStr;
    private int day;
    private int start;
    private int end;
    private String reason;
    private Status status;

    private Demand(DemandBuilder builder) {
        this.day = builder.day;
        this.start = builder.start;
        this.end = builder.end;
        this.reason = builder.reason;
        this.status = builder.status;
    }

    public void addDayStr(String day){
        this.dayStr = day;
    }

    public String getDayStr() {return dayStr; }

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

    public Status getStatus() { return status; }

    public void setStatus(Status status) { this.status = status; }

    @Override
    public String toString() {
        return "Demand{" +
                "day=" + day +
                ", start=" + start +
                ", end=" + end +
                ", reason= " + reason +
                ", status= " + status + "}" ;
    }

    public static class DemandBuilder{

        private int day;
        private int start;
        private int end;
        private String reason;
        private Status status;

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

        public DemandBuilder setStatus(Status status){
            this.status = status;
            return this;
        }

        public Demand build(){
            return new Demand(this);
        }
    }
}