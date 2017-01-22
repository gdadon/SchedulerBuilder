package objects;

public class Lesson {
    private Course course;
    private Lecturer lecturer;
    private int present;
    private String major;

    public Lesson() {
    }

    public Lesson(Course course, Lecturer lecturer, int present, String major) {
        this.course = course;
        this.lecturer = lecturer;
        this.present = present;
        this.major = major;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = new Course(course);
    }

    public Lecturer getLecturer() {
        return lecturer;
    }

    public void setLecturer(Lecturer lecturer) {
        this.lecturer = new Lecturer(lecturer);
    }

    public int getPresent() {
        return present;
    }

    public void setPresent(int present) {
        this.present = present;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }
}
