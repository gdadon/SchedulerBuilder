package objects;

import java.util.ArrayList;

public class TeacherCourse {
    private int ID;
    private ArrayList<String> courses;

    private TeacherCourse(TeacherCourseBuilder builder) {
        this.ID = builder.ID;
        this.courses = builder.courses;
    }

    public int getID() {
        return ID;
    }

    public ArrayList<String> getCourses() {
        return courses;
    }

    public void addCourse(String _course){
        this.courses.add(_course);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TeacherCourse that = (TeacherCourse) o;

        return this.ID == that.ID;
    }

    public static class TeacherCourseBuilder{

        private int ID;
        private ArrayList<String> courses;

        public TeacherCourseBuilder() {
            this.courses = new ArrayList<>();
        }

        public TeacherCourseBuilder setID(int ID) {
            this.ID = ID;
            return this;
        }

        public TeacherCourseBuilder setCourses(ArrayList<String> courses) {
            this.courses = courses;
            return this;
        }

        public TeacherCourseBuilder setCourses(String course){
            this.courses.add(course);
            return this;
        }

        public TeacherCourse build(){
            return new TeacherCourse(this);
        }
    }

}
