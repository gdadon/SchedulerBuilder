package objects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;

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

    public TreeSet<Lesson> getLessonByDay(int day, int semester, int year){
        TreeSet<Lesson> lessons = new TreeSet<>();
        for (Lesson l: this.scheduler) {
            if(l.getClassRoom().getDay() == day
                    && l.getCourse().getSemester() == semester
                    && l.getCourse().getYear() == year){
                lessons.add(l);
            }
        }
        return lessons;
    }

    public ArrayList<Lesson> getFirstLessonOfDay(int day, int semester, int year){
        ArrayList<Lesson> lessonsToRet = new ArrayList<>();
        TreeSet<Lesson> lessons = getLessonByDay(day, semester, year);
        if(lessons.size() == 0){
            return lessonsToRet;
        }
        // pull the first lesson and remove it from set
        Lesson lesson = lessons.first();
        lessons.remove(lesson);
        // add lesson to return arrayList
        lessonsToRet.add(lesson);
        // pull all other lessons until other lesson start time has shown
        Lesson lesson1 = null;
        while(lessons.size() > 0 &&((lesson1 = lessons.first()) != null)){
            // if same hour add to arrayList, otherwise break
            if(lesson.getClassRoom().getHour() != lesson1.getClassRoom().getHour()){
                break;
            }
            lessonsToRet.add(lesson1);
            lessons.remove(lesson1);
        }
        return lessonsToRet;
    }

    public Lesson getLessonByCode(String code){
        for (Lesson lesson: scheduler) {
            if(lesson.getCourse().getCode().equals(code)){
                return lesson;
            }
        }
        return null;
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
