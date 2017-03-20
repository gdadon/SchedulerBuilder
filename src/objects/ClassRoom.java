package objects;

public class ClassRoom {
    private int day;
    private char size;
    private int hour;

    private ClassRoom(ClassRoomBuilder builder) {
        this.day = builder.day;
        this.size = builder.size;
        this.hour = builder.hour;
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

    @Override
    public String toString() {
        return "ClassRoom{" +
                "day=" + day +
                ", size=" + size +
                ", hour=" + hour +
                "}";
    }

    public static class ClassRoomBuilder {
        private int day;
        private char size;
        private int hour;

        public ClassRoomBuilder setDay(int _day) {
            this.day = _day;
            return this;
        }

        public ClassRoomBuilder setSize(char _size) {
            this.size = _size;
            return this;
        }

        public ClassRoomBuilder setHour(int _hour) {
            this.hour = _hour;
            return this;
        }

        public ClassRoom build(){
            return new ClassRoom(this);
        }
    }

}

