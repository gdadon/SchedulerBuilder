package objects;

public class Lesson implements Comparable{
    private Teacher teacher;
    private Course course;
    private ClassRoom classRoom;

    private Lesson(LessonBuilder builder) {
        this.course = builder.course;
        this.teacher = builder.teacher;
        this.classRoom = builder.classRoom;
    }

    public Course getCourse() {
        return course;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public ClassRoom getClassRoom() {return classRoom; }

    @Override
    public int compareTo(Object o) {
        Lesson other = (Lesson) o;

        //check for day
        int sameDay = this.getClassRoom().getDay() - other.getClassRoom().getDay();
        if(sameDay != 0){
            return sameDay;
        }
        else{
            // check hour
            int hours = this.classRoom.getHour() - other.getClassRoom().getHour();
            return hours;
        }
    }

    @Override
    public String toString() {
        return "[" +
                "Day: " + classRoom.getDay() +
                ", Start Hour: " + classRoom.getHour() +
                ", Course: " + course.getName() +
                ", Teacher: " + teacher.getName() +
                "]\n";
    }

    public static class LessonBuilder{
        private Teacher teacher;
        private Course course;
        private ClassRoom classRoom;

        public LessonBuilder() {
        }

        public LessonBuilder setCourse(Course course) {
            this.course = course;
            return this;
        }

        public LessonBuilder setTeacher(Teacher teacher) {
            this.teacher = teacher;
            return this;
        }

        public LessonBuilder setClassRoom(ClassRoom classRoom){
            this.classRoom = classRoom;
            return this;
        }

        public Lesson build(){
            return new Lesson(this);
        }
    }
}
