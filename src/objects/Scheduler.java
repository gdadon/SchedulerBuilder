package objects;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Guy on 29/03/2017.
 */
public class Scheduler {

    private ArrayList<Lesson> scheduler;

    public Scheduler(){
        this.scheduler = new ArrayList<>();
    }

    public void addLesson(Lesson lesson){
        this.scheduler.add(lesson);
    }

    public void changeLesson(Lesson lesson){
        // TODO - decide how to change lesson - by date, teacher or course
    }

    public boolean removeLesson(Lesson lesson){
        return this.scheduler.remove(lesson);
    }

    public Set<Lesson> getLessonByDay(int day){
        HashSet<Lesson> lessons = new HashSet<>();
        for (Lesson l: this.scheduler) {
            if(l.getClassRoom().getDay() == day){
                lessons.add(l);
            }
        }
        return lessons;
    }

}
