package objects;

public class Course {
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

    private Course(CourseBulider builder){
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

    public static class CourseBulider{

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

        public CourseBulider(){

        }

        public CourseBulider setCode(String _courseCode){
            this.code = _courseCode;
            return this;
        }

        public CourseBulider setDifficulty(int _courseDifficulty){
            this.difficulty = _courseDifficulty;
            return this;
        }

        public CourseBulider setName(String _courseName){
            this.name = _courseName;
            return this;
        }

        public CourseBulider setDuration(int duration) {
            this.duration = duration;
            return this;
        }

        public CourseBulider setMajor(String _courseMajor) {
            this.major = _courseMajor;
            return this;
        }

        public CourseBulider setYear(int _courseYear) {
            this.year = _courseYear;
            return this;
        }

        public CourseBulider setSemester(int _courseSemester) {
            this.semester = _courseSemester;
            return this;
        }

        public CourseBulider setPoints(double points) {
            this.points = points;
            return this;
        }

        public CourseBulider setExpectedStudents(int expectedStudents) {
            this.expectedStudents = expectedStudents;
            return this;
        }

        public CourseBulider setQuotaStudents(int quotaStudents) {
            this.quotaStudents = quotaStudents;
            return this;
        }

        public CourseBulider setExpectedClasses(int expectedClasses) {
            this.expectedClasses = expectedClasses;
            return this;
        }

        public Course build(){
            return new Course(this);
        }
    }
}

