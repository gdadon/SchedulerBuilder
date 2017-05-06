package objects;

import java.io.Serializable;

public class Lesson implements Comparable, Serializable {

    private static final long serialVersionUID = 4L;
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

    /**
     * order is: day -> hour -> year -> semester -> course code
     * @param o
     * @return
     */
    @Override
    public int compareTo(Object o) {
        Lesson other = (Lesson) o;
        int compare;
        //check for day
        compare = this.getClassRoom().getDay() - other.getClassRoom().getDay();
        if(compare != 0){
            return compare;
        }
        // check hour
        compare = this.classRoom.getHour() - other.getClassRoom().getHour();
        if(compare != 0){
            return compare;
        }
        //check for year
        compare = this.getCourse().getYear() - other.getCourse().getYear();
        if(compare != 0){
            return compare;
        }
        //check for semester
        compare = this.getCourse().getSemester() - other.getCourse().getSemester();
        if(compare != 0){
            return compare;
        }

        return checkCoursesCode(this.getCourse().getCode(), other.getCourse().getCode());
    }

    /**
     *  this is helper for compareTo
     *  will replace the '-' in course code
     *  and return the bigger course code
     * @param code1
     * @param code2
     * @return
     */
    private int checkCoursesCode(String code1, String code2) {
        int code1Int = Integer.parseInt(code1.replace("-", ""));
        int code2Int = Integer.parseInt(code2.replace("-", ""));
        return code1Int - code2Int;
    }

    @Override
    public String toString() {
        return "[" +
                "Day: " + classRoom.getDay() +
                ", Sem: " + course.getSemester() +
                ", Year: " + course.getYear() +
                ", Start Hour: " + classRoom.getHour() +
                ", Course: " + course.getName() +
                ", Code: " + course.getCode() +
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
