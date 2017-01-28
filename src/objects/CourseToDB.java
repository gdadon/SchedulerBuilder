package objects;

public class CourseToDB {
    private String code;
    private String name;
    private int year;
    private int semester;
    private int duration;
    private double points;
    private int expectedStudents;
    private int qoutaStudents;
    private int expectedClasses;

    public CourseToDB() {
    }

    public CourseToDB(String code, String name, int year, int semester, int duration, double points, int expectedStudents,
                      int qoutaStudents, int expectedClasses) {
        this.code = code;
        this.name = name;
        this.year = year;
        this.semester = semester;
        this.duration = duration;
        this.points = points;
        this.expectedStudents = expectedStudents;
        this.qoutaStudents = qoutaStudents;
        this.expectedClasses = expectedClasses;
    }

    public CourseToDB(CourseToDB _courseToCopy) {
        this.code = _courseToCopy.code;
        this.name = _courseToCopy.name;
        this.year = _courseToCopy.year;
        this.semester = _courseToCopy.semester;
        this.duration = _courseToCopy.duration;
        this.points = _courseToCopy.points;
        this.expectedStudents = _courseToCopy.expectedStudents;
        this.qoutaStudents = _courseToCopy.qoutaStudents;
        this.expectedClasses = _courseToCopy.expectedClasses;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public double getPoints() {
        return points;
    }

    public void setPoints(double points) {
        this.points = points;
    }

    public int getExpectedStudents() {
        return expectedStudents;
    }

    public void setExpectedStudents(int expectedStudents) {
        this.expectedStudents = expectedStudents;
    }

    public int getQoutaStudents() {
        return qoutaStudents;
    }

    public void setQoutaStudents(int qoutaStudents) {
        this.qoutaStudents = qoutaStudents;
    }

    public int getExpectedClasses() {
        return expectedClasses;
    }

    public void setExpectedClasses(int expectedClasses) {
        this.expectedClasses = expectedClasses;
    }

    @Override
    public String toString() {
        return "CourseToDB{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", year=" + year +
                ", semester=" + semester +
                ", duration=" + duration +
                ", points=" + points +
                ", expectedStudents=" + expectedStudents +
                ", qoutaStudents=" + qoutaStudents +
                ", expectedClasses=" + expectedClasses +
                '}';
    }
}
