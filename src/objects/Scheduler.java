package objects;

import java.util.*;

/**
 * Created by Guy on 29/03/2017.
 */
public class Scheduler {

    private SortedSet<Lesson> scheduler;
    private HashMap<Integer, ArrayList<Lesson>> teacherCourseMap;

    public Scheduler(){
        this.scheduler = new TreeSet<>();
        this.teacherCourseMap = new HashMap<>();
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

    public void setTeacherCourseMap(HashMap<Integer, ArrayList<Lesson>>  map){
        this.teacherCourseMap = map;
    }

    public HashMap<Integer, ArrayList<Lesson>>  getTeacherCourseMap(){
        return this.teacherCourseMap;
    }

    @Override
    public String toString() {
        return "Scheduler{\n" + scheduler +
                '}';
    }

}
