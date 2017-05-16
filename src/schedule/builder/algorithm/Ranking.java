package schedule.builder.algorithm;

import objects.Lesson;
import objects.Course;

import java.util.Set;
import java.util.SortedSet;

/**
 * Created by Liat on 02-May-17.
 */

public class Ranking {

    public static int getOptimizationScore(SortedSet<Lesson> scheduler){
        int optimizationScore=100;
        for (Lesson l: scheduler){
            optimizationScore=checkLessonScore(l, scheduler, optimizationScore);
        }
        return optimizationScore;
    }

    public static int checkLessonScore(Lesson lesson, SortedSet<Lesson> scheduler, int optimizationScore){
        if (isDifficultAndLateHour(lesson)){
            optimizationScore-=2;
        }

        if(isOnFriday(lesson)){
            optimizationScore--;
        }
        if(isFirstLessonAndAfter9(scheduler, lesson)){
            optimizationScore--;
        }
        if(isThereBlankHours(scheduler, lesson)){
            optimizationScore--;
        }
        return optimizationScore;
    }
    /**
     * if the course has difficulty of 2 or up, and the hour of the course is 15:00 or up- return true.
     * else return false
     * @param lesson
     * @return
     */
    public static boolean isDifficultAndLateHour(Lesson lesson){
        if(lesson.getCourse().getDifficulty()>=2 && lesson.getClassRoom().getHour()>=15){
            return true;
        }
        return false;
    }

    /**
     * check if the lesson is on friday.
     * @param lesson
     * @return true check if the lesson is on friday, false otherwise.
     */
    public static boolean isOnFriday(Lesson lesson){
        if(lesson.getClassRoom().getDay()==6){
            return true;
        }
        return false;
    }

    /**
     * check if the lesson starts after 9, and there is no lesson before that day.
     * @param lesson
     * @return true if this lesson start after 9 and there is no lesson before that day.
     */

    public static boolean isFirstLessonAndAfter9(SortedSet<Lesson> scheduler, Lesson lesson){
        if(lesson.getClassRoom().getHour()==9){
            return false;
        }
        int lessonDay= lesson.getClassRoom().getDay();
        int lessonYear= lesson.getCourse().getYear();
        SortedSet<Lesson> earlierLessons =scheduler.headSet(lesson);
        for(Lesson l: earlierLessons) {
            if (l.getClassRoom().getDay() == lessonDay && l.getCourse().getYear() == lessonYear
                    && l.getClassRoom().getHour()<lesson.getClassRoom().getHour()) {//there is course at the same day for the same year of students earlier that day
                return false;
            }
        }
        return true;
    }
    /**
     * check if there is blank hours:
     * if there is no lesson right after this lesson, but there is a course later that day.
     * @param lesson
     * @return true if there is blank hours
     */
    public static boolean isThereBlankHours(SortedSet<Lesson> scheduler, Lesson lesson){
        int lessonEnd= lesson.getClassRoom().getHour()+lesson.getCourse().getDuration();
        int lessonDay= lesson.getClassRoom().getDay();
        int lessonYear= lesson.getCourse().getYear();
        int lessonSemester= lesson.getCourse().getSemester();
        SortedSet <Lesson> laterLessons =scheduler.tailSet(lesson);
        for(Lesson l: laterLessons){
            if(l.getClassRoom().getDay()==lessonDay && l.getCourse().getYear()==lessonYear && l.getCourse().getSemester()==lessonSemester){
                if(l.getClassRoom().getHour()==lessonEnd){//if there is a course that start right after this course
                    return false;
                }
                if(l.getClassRoom().getHour()>lessonEnd){//if there is a course that start NOT right after this course
                    return true;
                }
            }
        }
        return false;
    }
}
