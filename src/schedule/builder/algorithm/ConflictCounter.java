package schedule.builder.algorithm;

import objects.Demand;
import objects.Lesson;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Guy on 29/03/2017.
 */
public class ConflictCounter {

    public static Set<Lesson> getConflictedLessons(ArrayList<Lesson> lessons){
        Set<Lesson> conflictLesson = new HashSet<>();


        return null;
    }

    /**
     * check for two lessons if they overlaps
     * @param lesson1
     * @param lesson2
     * @return true if lessons overlaps
     */
    private boolean isLessonOverlap(Lesson lesson1, Lesson lesson2){

        // lessons in different day - no overlaps
        if(lesson1.getClassRoom().getDay() != lesson2.getClassRoom().getDay()){
            return false;
        }
        // check if there is time overlaps

        int l1Start = lesson1.getClassRoom().getHour();
        int l1End = l1Start + lesson1.getCourse().getDuration();

        int l2Start = lesson2.getClassRoom().getHour();
        int l2End = l2Start + lesson2.getCourse().getDuration();

        boolean l1Overlaps = ((l1Start > l2Start) && (l1Start < l2End)) || ((l1End > l2Start) && (l1End < l2End));
        boolean l2Overlaps = ((l2Start > l1Start) && (l2Start < l1End)) || ((l2End > l1Start) && (l2End < l1End));
        return l1Overlaps || l2Overlaps;
    }

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
    private boolean demandConflict(Lesson lesson){
        // get teacher demands
        ArrayList<Demand> demands = lesson.getTeacher().getDemands();

        // check if there is conflict between teacher demand to lesson
        return false;
    }
}
