package objects;

import java.util.ArrayList;

public class Teacher {
    private int quotaHours;
    private int remainingHours;
    private int ID;
    private String name;
    private ArrayList<Demand> demads;

    public Teacher(){
        
    }

    public Teacher(TeacherBulider builder) {
        this.quotaHours = builder.quotaHours;
        this.remainingHours = builder.remainingHours;
        this.ID = builder.ID;
        this.name = builder.name;
        this.demads = new ArrayList<>();
        for(int i = 0; i < builder.demads.size() ; i++){
            this.demads.add(builder.demads.get(i));
        }
    }

    public ArrayList<Demand> getDemads() {
        return demads;
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

    public static class TeacherBulider{

        private int quotaHours;
        private int remainingHours;
        private int ID;
        private String name;
        private ArrayList<Demand> demads;

        public TeacherBulider() {
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

        public void setDemads(ArrayList<Demand> demads) {
            this.demads = demads;
        }

        public Teacher build(){
            return new Teacher(this);
        }
    }
}
