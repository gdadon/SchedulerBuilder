package objects;

public class Class {
    private int day;
    private char size;
    private int hour;

    public Class() {
    }

    public Class(int _day, char _size, int _hour) {
        this.day = _day;
        this.size = _size;
        this.hour = _hour;
    }

    public Class(Class _classToCopy) {
        this.day = _classToCopy.day;
        this.size = _classToCopy.size;
        this.hour = _classToCopy.hour;
    }

    public int getDay() {
        return day;
    }

    public char getSize() {
        return size;
    }

    public int getHour() {
        return hour;
    }

    public void setDay(int _day) {
        this.day = _day;
    }

    public void setSize(char _size) {
        this.size = _size;
    }

    public void hour(int _hour) {
        this.hour = _hour;
    }

    @Override
    public String toString() {
        return "Class{" +
                "day=" + day +
                ", size=" + size +
                ", hour=" + hour +
                "}";
    }
}

