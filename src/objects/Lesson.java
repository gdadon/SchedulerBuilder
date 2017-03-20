package objects;

public class Lesson {
    private Course course;
    private Teacher teacher;
    private int present;
    private String major;

    public Lesson(LessonBuilder builder) {
        this.course = builder.course;
        this.teacher = builder.teacher;
        this.present = builder.present;
        this.major = builder.major;
    }

    public Course getCourse() {
        return course;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public int getPresent() {
        return present;
    }

    public String getMajor() {
        return major;
    }

    public static class LessonBuilder{
        private Course course;
        private Teacher teacher;
        private int present;
        private String major;

        public LessonBuilder() {
        }

        public void setCourse(Course course) {
            this.course = course;
        }

        public void setTeacher(Teacher teacher) {
            this.teacher = teacher;
        }

        public void setPresent(int present) {
            this.present = present;
        }

        public void setMajor(String major) {
            this.major = major;
        }

        public Lesson build(){
            return new Lesson(this);
        }
    }
}
