package objects;

public class Demand {

    private int start;
    private int end;
    private int day;

    public Demand(){

    }

    public Demand(int start, int end, int day) {
        this.start = start;
        this.end = end;
        this.day = day;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }
}
