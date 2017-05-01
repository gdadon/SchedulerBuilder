package schedule.builder.algorithm;

import objects.Demand;
import objects.Lesson;
import objects.Scheduler;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Guy on 29/03/2017.
 */
public class ConflictCounter {

    public static Set<Lesson> getConflictedLessons(Scheduler scheduler){
        Set<Lesson> conflictLesson = new HashSet<>();
        for (Integer teacherId: scheduler.getTeacherCourseMap().keySet()) {

        }
        return null;
    }

    /**
     * check if lessons has the same teacher at overlap time
     * @param lesson1
     * @param lesson2
     * @return true if lessons overlaps
     */
    private boolean isLessonOverlap(Lesson lesson1, Lesson lesson2){

        // check if same teacher
        if(!isSameTeacher(lesson1, lesson2)){
            return false;
        }

        // lessons in different day - no overlaps
        if(lesson1.getClassRoom().getDay() != lesson2.getClassRoom().getDay()){
            return false;
        }

        // same teacher and date
        // check if there is time overlaps

        int l1Start = lesson1.getClassRoom().getHour();
        int l1End = l1Start + lesson1.getCourse().getDuration();

        int l2Start = lesson2.getClassRoom().getHour();
        int l2End = l2Start + lesson2.getCourse().getDuration();

        boolean l1Overlaps = ((l1Start > l2Start) && (l1Start < l2End)) || ((l1End > l2Start) && (l1End < l2End));
        boolean l2Overlaps = ((l2Start > l1Start) && (l2Start < l1End)) || ((l2End > l1Start) && (l2End < l1End));
        return l1Overlaps || l2Overlaps;
    }

    /**
     * check if same teacher for both lessons
     * @param l1
     * @param l2
     * @return true if same teacher, false otherwise
     */
    private boolean isSameTeacher(Lesson l1, Lesson l2){
        if(l1.getTeacher().getID() == l2.getTeacher().getID()){
            return true;
        }
        return false;
    }

    /**
     * checks for conflict between teacher demands to current lesson
     * @param lesson - lesson to check
     * @return true if there is a conflict, otherwise false
     */
    public static boolean demandConflict(Lesson lesson){
        // get teacher demands
        ArrayList<Demand> demands = lesson.getTeacher().getDemands();
        // check if there is conflict between teacher demand to lesson

        int lessonDay = lesson.getClassRoom().getDay();
        int lessonStart = lesson.getClassRoom().getHour();
        int lessonEnd = lesson.getCourse().getDuration() + lessonStart;

        for (Demand demand: demands) {
            if(demand.getDay() == lessonDay){
                if(demand.getStart() >= lessonStart && demand.getStart() < lessonEnd){
                    return true;
                }
                else if(demand.getEnd() >= lessonStart && demand.getEnd() < lessonEnd){
                    return true;
                }
            }
        }

        return false;
    }
}
