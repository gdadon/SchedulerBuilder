package objects;

import java.io.Serializable;

public class Course implements Serializable{

    private static final long serialVersionUID = 1L;

    private String code;
    private int difficulty;
    private String name;
    private int duration;
    private String major;
    private int year;
    private int semester;
    private double points;
    private int expectedStudents;
    private int quotaStudents;
    private int expectedClasses;

    private Course(CourseBuilder builder){
        this.code = builder.code;
        this.difficulty = builder.difficulty;
        this.name = builder.name;
        this.duration = builder.duration;
        this.major = builder.major;
        this.year = builder.year;
        this.semester = builder.semester;
        this.points = builder.points;
        this.expectedStudents = builder.expectedStudents;
        this.quotaStudents = builder.quotaStudents;
        this.expectedClasses = builder.expectedClasses;
    }

    public String getMajor() {
        return major;
    }

    public int getYear() {
        return year;
    }

    public int getSemester() {
        return semester;
    }

    public String getCode(){
        return this.code;
    }

    public int getDifficulty(){
        return this.difficulty;
    }

    public String getName(){
        return this.name;
    }

    public int getDuration() {
        return duration;
    }

    public double getPoints() {
        return points;
    }

    public int getExpectedStudents() {
        return expectedStudents;
    }

    public int getQuotaStudents() {
        return quotaStudents;
    }

    public int getExpectedClasses() {
        return expectedClasses;
    }

    public static class CourseBuilder {

        private String code;
        private int difficulty;
        private String name;
        private int duration;
        private String major;
        private int year;
        private int semester;
        private double points;
        private int expectedStudents;
        private int quotaStudents;
        private int expectedClasses;

        public CourseBuilder setCode(String _courseCode){
            this.code = _courseCode;
            return this;
        }

        public CourseBuilder setDifficulty(int _courseDifficulty){
            this.difficulty = _courseDifficulty;
            return this;
        }

        public CourseBuilder setName(String _courseName){
            this.name = _courseName;
            return this;
        }

        public CourseBuilder setDuration(int duration) {
            this.duration = duration;
            return this;
        }

        public CourseBuilder setMajor(String _courseMajor) {
            this.major = _courseMajor;
            return this;
        }

        public CourseBuilder setYear(int _courseYear) {
            this.year = _courseYear;
            return this;
        }

        public CourseBuilder setSemester(int _courseSemester) {
            this.semester = _courseSemester;
            return this;
        }

        public CourseBuilder setPoints(double points) {
            this.points = points;
            return this;
        }

        public CourseBuilder setExpectedStudents(int expectedStudents) {
            this.expectedStudents = expectedStudents;
            return this;
        }

        public CourseBuilder setQuotaStudents(int quotaStudents) {
            this.quotaStudents = quotaStudents;
            return this;
        }

        public CourseBuilder setExpectedClasses(int expectedClasses) {
            this.expectedClasses = expectedClasses;
            return this;
        }

        public Course build(){
            return new Course(this);
        }
    }
}

