package objects;

import java.util.ArrayList;

public class Teacher {
    private int quotaHours;
    private int remainingHours;
    private int ID;
    private String name;
    private ArrayList<Demand> demands;

    public Teacher(){
        
    }

    public Teacher(TeacherBuilder builder) {
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

        public void setQuotaHours(int quotaHours) {
            this.quotaHours = quotaHours;
        }

        public void setRemainingHours(int remainingHours) {
            this.remainingHours = remainingHours;
        }

        public void setID(int ID) {
            this.ID = ID;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setDemands(ArrayList<Demand> demands) {
            this.demands = demands;
        }

        public Teacher build(){
            return new Teacher(this);
        }
    }
}
