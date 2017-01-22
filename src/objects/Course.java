package objects;

public class Course {
    private String code;
    private int difficulty;
    private String name;
    private boolean isSingle;
    private int duration;
    private String major;
    private char year;
    private char semester;
    
    public Course(){
    
    }

    public Course(String _courseCode, int _courseDifficulty, String _courseName, boolean _courseIsSingle, int _courseDuration,
                  String _courseMajor, char _courseYear, char _courseSemester) {
        this.code = _courseCode;
        this.difficulty = _courseDifficulty;
        this.name = _courseName;
        this.isSingle = _courseIsSingle;
        this.duration = _courseDuration;
        this.major = _courseMajor;
        this.year = _courseYear;
        this.semester = _courseSemester;
    }

    public Course(Course _courseToCopy){
        this.code = _courseToCopy.code;
        this.difficulty = _courseToCopy.difficulty;
        this.name = _courseToCopy.name;
        this.isSingle = _courseToCopy.isSingle;
        this.duration = _courseToCopy.duration;
        this.major = _courseToCopy.major;
        this.year = _courseToCopy.year;
        this.semester = _courseToCopy.semester;
    }

    public String getMajor() {
        return major;
    }

    public char getYear() {
        return year;
    }

    public char getSemester() {
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

    public boolean getSingle(){
        return this.isSingle;
    }

    public int getDuration() {
        return duration;
    }

    public void setCode(String _courseCode){
        this.code = _courseCode;
    }

    public void setDifficulty(int _courseDifficulty){
        this.difficulty = _courseDifficulty;
    }

    public void setName(String _courseName){
        this.name = _courseName;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setSingle(boolean _courseIsSingle){
        this.isSingle = _courseIsSingle;
    }

    public void setMajor(String _courseMajor) {
        this.major = _courseMajor;
    }

    public void setYear(char _courseYear) {
        this.year = _courseYear;
    }

    public void setSemester(char _courseSemester) {
        this.semester = _courseSemester;
    }
}

