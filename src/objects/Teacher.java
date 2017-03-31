package objects;

import java.util.ArrayList;

public class Teacher {
    private int quotaHours;
    private int remainingHours;
    private int ID;
    private String name;
    private ArrayList<Demand> demands;

    private Teacher(TeacherBuilder builder) {
        this.quotaHours = builder.quotaHours;
        this.remainingHours = builder.remainingHours;
        this.ID = builder.ID;
        this.name = builder.name;
        this.demands = builder.demands;
    }

    public ArrayList<Demand> getDemands() {
        return demands;
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

    public void addDemand(Demand demand){
        this.demands.add(demand);
    }

    public static class TeacherBuilder {

        private int quotaHours;
        private int remainingHours;
        private int ID;
        private String name;
        private ArrayList<Demand> demands;

        public TeacherBuilder() {
            demands = new ArrayList<>();
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

        public Teacher build(){
            return new Teacher(this);
        }
    }
}
