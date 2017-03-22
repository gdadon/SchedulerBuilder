package objects;

import java.util.ArrayList;

public class TeacherCourse {
    private String ID;
    private String name;
    private ArrayList<String> courses;

    private TeacherCourse(TeacherCourseBuilder builder) {
        this.ID = builder.ID;
        this.name = builder.name;
        this.courses = builder.courses;
    }

    public String getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getCourses() {
        return courses;
    }

    public void addCourse(String _course){
        this.courses.add(_course);
    }

    public static class TeacherCourseBuilder{

        private String ID;
        private String name;
        private ArrayList<String> courses;

        public TeacherCourseBuilder() {
            this.courses = new ArrayList<>();
        }

        public TeacherCourseBuilder setID(String ID) {
            this.ID = ID;
            return this;
        }

        public TeacherCourseBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public TeacherCourseBuilder setCourses(ArrayList<String> courses) {
            this.courses = courses;
            return this;
        }

        public TeacherCourse build(){
            return new TeacherCourse(this);
        }
    }


}
