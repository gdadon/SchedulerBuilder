package objects;

import java.util.ArrayList;

public class Lecturer {
    private int quotaHours;
    private int remainingHours;
    private int ID;
    private String name;
    ArrayList<Demand> demads;

    public Lecturer(){
        
    }

    public Lecturer(int _lecturerQuotaHours, int _lecturerRemainingHours, int _lecturerID, String _lecturerName) {
        this.quotaHours = _lecturerQuotaHours;
        this.remainingHours = _lecturerRemainingHours;
        this.ID = _lecturerID;
        this.name = _lecturerName;
        this.demads = new ArrayList<>();
    }

    public Lecturer(Lecturer _lecturerToCopy) {
        this.quotaHours = _lecturerToCopy.quotaHours;
        this.remainingHours = _lecturerToCopy.remainingHours;
        this.ID = _lecturerToCopy.ID;
        this.name = _lecturerToCopy.name;
        this.demads = new ArrayList<>();
        for(int i = 0; i < _lecturerToCopy.demads.size() ; i++){
            this.demads.add(_lecturerToCopy.demads.get(i));
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

    public void setLectureDemand(Demand temp){
        this.demads.add(new Demand(temp));
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
}
