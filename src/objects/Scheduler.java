package objects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Guy on 29/03/2017.
 */
public class Scheduler implements Serializable{

    private static final long serialVersionUID = 5L;

    private ArrayList<Lesson> scheduler;
    private HashMap<Integer, ArrayList<Lesson>> teacherCourseMap;

    public Scheduler(){
        this.scheduler = new ArrayList<>();
        this.teacherCourseMap = new HashMap<>();
    }

    public void addLesson(Lesson lesson){
        this.scheduler.add(lesson);
        addLessonToMap(lesson);
    }

    private void addLessonToMap(Lesson lesson){
        ArrayList<Lesson> lessonsToAdd = null;
        if((lessonsToAdd = teacherCourseMap.get(lesson.getTeacher().getID())) == null){
            lessonsToAdd = new ArrayList<>();
        }
        lessonsToAdd.add(lesson);
        teacherCourseMap.put(lesson.getTeacher().getID(), lessonsToAdd);
    }

    public void removeLesson(Lesson lesson){
        this.scheduler.remove(lesson);
        removeLessonFromMap(lesson);
    }

    private void removeLessonFromMap(Lesson lesson){
        teacherCourseMap.get(lesson.getTeacher().getID()).remove(lesson);
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

    public ArrayList<Lesson> getLessons(){
        return scheduler;
    }

    @Override
    public String toString() {
        return "Scheduler{\n" + scheduler +
                '}';
    }

}
