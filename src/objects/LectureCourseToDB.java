package objects;

import java.util.ArrayList;

public class LectureCourseToDB {
    private String ID;
    private String name;
    private ArrayList<String> courses;

    public LectureCourseToDB() {
    }

    public LectureCourseToDB(String ID, String name) {
        this.ID = ID;
        this.name = name;
        this.courses = new ArrayList<String>();
    }

    public LectureCourseToDB(LectureCourseToDB _LectureCourseToDBToCopy) {
        this.ID = _LectureCourseToDBToCopy.ID;
        this.name = _LectureCourseToDBToCopy.name;
        this.courses = new ArrayList<>();
        for (int i = 0 ; i < _LectureCourseToDBToCopy.courses.size() ; ++i) {
            this.courses.add(_LectureCourseToDBToCopy.courses.get(i));
        }
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<String> courses) {
        this.courses = courses;
    }

    public void addCourse(String _course){
        this.courses.add(_course);
    }

    @Override
    public String toString() {
        return "LectureCourseToDB{" +
                "ID='" + ID + '\'' +
                ", name='" + name + '\'' +
                ", courses=" + courses.toString() +
                '}';
    }
}
