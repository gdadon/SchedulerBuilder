package objects;

public class ClassRoom {
    private int day;
    private char size;
    private int hour;

    public ClassRoom() {
    }

    public ClassRoom(int _day, char _size, int _hour) {
        this.day = _day;
        this.size = _size;
        this.hour = _hour;
    }

    public ClassRoom(ClassRoom _classRoomToCopy) {
        this.day = _classRoomToCopy.day;
        this.size = _classRoomToCopy.size;
        this.hour = _classRoomToCopy.hour;
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
        return "ClassRoom{" +
                "day=" + day +
                ", size=" + size +
                ", hour=" + hour +
                "}";
    }
}

