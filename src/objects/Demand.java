package objects;

public class Demand {
    private int hour;
    private int day;
    
    public Demand(){
        
    }
    
    public Demand(int _hour, int _day){
        this.hour = _hour;
        this.day = _day;
    }
    
    public Demand(Demand _demandToCopy){
        this.hour = _demandToCopy.hour;
        this.day = _demandToCopy.day;
    }

    public int getHour() {
        return hour;
    }

    public int getDay() {
        return day;
    }

    public void setHour(int _hour) {
        this.hour = _hour;
    }

    public void setDay(int _day) {
        this.day = _day;
    }
}
