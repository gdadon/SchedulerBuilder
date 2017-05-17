package schedule.builder.algorithm;

import objects.Demand;
import objects.Lesson;
import objects.Scheduler;
import objects.Teacher;
import schedule.builder.scheduler.BaseSchedulerData;

import java.util.ArrayList;

/**
 * Created by Guy on 29/03/2017.
 */
public class ConflictCounter {

    /**
     * This method is responsible for counting the conflicted lessons in given scheduler
     * the conflict that is being checks here are only in time overlaps
     * @param scheduler scheduler to count conflicts on
     * @return ArrayList of conflicted lessons
     */
    public static ArrayList<Lesson> getOverLapsLessons(Scheduler scheduler){
        ArrayList<Lesson> conflictLesson = new ArrayList<>();

        for (Integer id: scheduler.getTeacherCourseMap().keySet()
                ) {
            // get all courses of teacher by its ID
            ArrayList<Lesson> lessons = scheduler.getTeacherCourseMap().get(id);
            for(int i = 0; i < lessons.size(); i++){
                for(int j = i+1; j < lessons.size(); j++){
                    if(isLessonOverlap(lessons.get(i), lessons.get(j))){
                        conflictLesson.add(lessons.get(i));
                        conflictLesson.add(lessons.get(j));
                    }
                }
            }
        }
        return conflictLesson;
    }

    /**
     * This method is responsible for counting the over quota teachers in given scheduler
     * @param scheduler
     * @return ArrayList of over quota teachers
     */
    public static ArrayList<Teacher> getOverQuotaTeachers(Scheduler scheduler){
        ArrayList<Teacher> overQuota = new ArrayList<>();
        for (Integer id: scheduler.getTeacherCourseMap().keySet()) {
            Teacher teacher = scheduler.getTeacherCourseMap().get(id).get(0).getTeacher();
            if(teacher.getRemainingHours() < 0){
                overQuota.add(teacher);
            }
        }
        return overQuota;
    }

    /**
     * This method is responsible for counting the demands conflict in given scheduler
     * each teacher is being compared with his demands
     * @param scheduler
     * @return ArrayList of over quota teachers
     */
    public static ArrayList<Lesson> getDemandsConflicts(Scheduler scheduler){
        ArrayList<Lesson> conflicts = new ArrayList<>();
        for (Integer id: scheduler.getTeacherCourseMap().keySet()) {
            for (Lesson lesson: scheduler.getTeacherCourseMap().get(id)
                    ) {
                if(isDemandConflict(lesson)){
                    conflicts.add(lesson);
                }
            }
        }
        return conflicts;
    }

    /**
     * This method is responsible for counting the single course collision  in given scheduler
     * collision is when there is only one "show" of course and it is at the same time
     * as another one "show" course, meaning that student will have to choose which
     * course attend to, and this is HARD conflict.
     * @param scheduler
     * @return
     */
    public static ArrayList<Lesson> getSingleCourseConflicts(Scheduler scheduler){
        ArrayList<Lesson> lessons = scheduler.getLessons();
        ArrayList<Lesson> conflicted = new ArrayList<>();
        for(int i = 0; i < lessons.size(); i++){
            for(int j = i+1; j < lessons.size(); j++){
                Lesson lesson1 = lessons.get(i);
                Lesson lesson2 = lessons.get(j);
                if(lesson1.getCourse().getExpectedClasses() == 1 &&
                        lesson2.getCourse().getExpectedClasses() == 1){
                    // lessons have 1 show, checks if overlaps
                    if(isLessonOverlap(lesson1, lesson2)){
                        lessons.add(lesson1);
                        lessons.add(lesson2);
                    }
                }
            }
        }
        return conflicted;
    }

    /**
     * This method will count the conflicted classrooms,
     * if the lesson set on non-free classrooms this is a conflict
     * @param scheduler given scheduler to count conflicts on
     * @return array of conflicted lessons
     */
    public static ArrayList<Lesson> getConflictedClassrooms(Scheduler scheduler){
        ArrayList<Lesson> conflicts = new ArrayList<>();
        BaseSchedulerData data = BaseSchedulerData.getInstance();
        for (Lesson lesson: scheduler.getLessons()
                ) {
            if(data.checkConflictLessonClassroom(lesson)){
                conflicts.add(lesson);
            }
        }
        return conflicts;
    }

    /**
     * check if lessons has the same teacher at overlap time
     * @param lesson1
     * @param lesson2
     * @return true if lessons overlaps
     */
    private static boolean isLessonOverlap(Lesson lesson1, Lesson lesson2){

        // check if same semester
        if(lesson1.getCourse().getSemester() != lesson2.getCourse().getSemester()){
            return false;
        }

        // lessons in different day - no overlaps
        if(lesson1.getClassRoom().getDay() != lesson2.getClassRoom().getDay()){
            return false;
        }

        // same teacher, semester and day
        // check if there is hour overlaps

        int l1Start = lesson1.getClassRoom().getHour();
        int l1End = l1Start + lesson1.getCourse().getDuration();

        int l2Start = lesson2.getClassRoom().getHour();
        int l2End = l2Start + lesson2.getCourse().getDuration();

        boolean l1Overlaps = ((l1Start >= l2Start) && (l1Start < l2End)) || ((l1End > l2Start) && (l1End < l2End));
        boolean l2Overlaps = ((l2Start >= l1Start) && (l2Start < l1End)) || ((l2End > l1Start) && (l2End < l1End));
        return l1Overlaps || l2Overlaps;
    }

    /**
     * check if same teacher for both lessons
     * @param l1
     * @param l2
     * @return true if same teacher, false otherwise
     */
    private static boolean isSameTeacher(Lesson l1, Lesson l2){
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
    public static boolean isDemandConflict(Lesson lesson){
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


    public static int countConflict(Scheduler scheduler){
        int total = 0;

        total += getConflictedClassrooms(scheduler).size();
        total += getDemandsConflicts(scheduler).size();
        total += getOverLapsLessons(scheduler).size();
        total += getOverQuotaTeachers(scheduler).size();
        total += getSingleCourseConflicts(scheduler).size();

        return total;
    }


}
