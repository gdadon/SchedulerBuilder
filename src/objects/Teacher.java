package objects;

import java.io.Serializable;
import java.util.ArrayList;

public class Teacher implements Serializable{

    private static final long serialVersionUID = 3L;

    private int quotaHours;
    private int remainingHours;
    private int ID;
    private String name;
    private ArrayList<Demand> demands;
    private ArrayList<String> courses;

    private final int DEFAULT_QUOTA = 16;

    private Teacher(TeacherBuilder builder) {
        this.quotaHours = DEFAULT_QUOTA;
        this.remainingHours = DEFAULT_QUOTA;
        this.ID = builder.ID;
        this.name = builder.name;
        this.demands = builder.demands;
        this.courses = builder.courses;
    }

    public int getQuotaHours() {
        return quotaHours;
    }

    public int getRemainingHours() {
        return remainingHours;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Demand> getDemands() {
        return demands;
    }

    public void addDemand(Demand demand){
        this.demands.add(demand);
    }

    public ArrayList<String> getCourses() {return this.courses; }

    public void addCourse(String course){ this.courses.add(course); }

    public void reduceHour(int hours){
        this.remainingHours -= hours;
    }

    public void addName(String name) { this.name = name; }

    public void addRemainingHours(int hours) {
        this.remainingHours += hours;
    }

    @Override
    public String toString() {
        return "Teacher{ID=" + ID +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Teacher teacher = (Teacher) o;

        return ID == teacher.ID;
    }

    public static class TeacherBuilder {

        private int quotaHours;
        private int remainingHours;
        private int ID;
        private String name;
        private ArrayList<Demand> demands;
        private ArrayList<String> courses;

        public TeacherBuilder() {
            demands = new ArrayList<>();
            courses = new ArrayList<>();
        }

        public TeacherBuilder setQuotaHours(int quotaHours) {
            this.quotaHours = quotaHours;
            return this;
        }

        public TeacherBuilder setRemainingHours(int remainingHours) {
            this.remainingHours = remainingHours;
            return this;
        }

        public TeacherBuilder setID(int ID) {
            this.ID = ID;
            return this;
        }

        public TeacherBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public TeacherBuilder setDemands(ArrayList<Demand> demands) {
            this.demands = demands;
            return this;
        }

        public TeacherBuilder setCourses(ArrayList<String> courses){
            this.courses = courses;
            return this;
        }

        public Teacher build(){
            return new Teacher(this);
        }
    }
}
