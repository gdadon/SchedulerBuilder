package schedule.builder.algorithm;

import objects.ClassRoom;
import objects.Lesson;
import objects.Scheduler;
import objects.Teacher;
import schedule.builder.database.DataBaseMySQLImpl;
import schedule.builder.scheduler.BaseSchedulerData;

import java.util.ArrayList;

/**
 * Created by Guy on 26/04/2017.
 *
 * This class will count all the conflicts in given scheduler
 * make one change in random way and will return the scheduler that has been received
 *
 */
public class LocalSearch {

    private static boolean isConflicted = true;

    public static Scheduler startLocalSearch(Scheduler scheduler){

        for (int i = 0; i < 500 && isConflicted; i++){
            System.out.println("Round " + i);
            scheduler = localSearchRun(scheduler);
        }
        if(isConflicted){
            System.out.println("Scheduler still have conflicts");
        }
        return scheduler;
    }

    private static Scheduler localSearchRun(Scheduler scheduler){

        // count conflicts lessons due to overlaps
        ArrayList<Lesson> conflictedOverLaps = ConflictCounter.getOverLapsLessons(scheduler);
        int overLaps = conflictedOverLaps.size();
        // count conflicts over quota
        ArrayList<Teacher> conflictedOverQuota = ConflictCounter.getOverQuotaTeachers(scheduler);
        int overQuota = conflictedOverQuota.size();
        // count conflicts due to demands
        ArrayList<Lesson> conflictedDemands = ConflictCounter.getDemandsConflicts(scheduler);
        int demands = conflictedDemands.size();
        // count single shows conflicts
        ArrayList<Lesson> singleConflicts = ConflictCounter.getSingleCourseConflicts(scheduler);
        int singles = singleConflicts.size();
        // count unavailable classrooms conflicts
        ArrayList<Lesson> classesConflicts = ConflictCounter.getConflictedClassrooms(scheduler);
        int classrooms = classesConflicts.size();
        System.out.println("Total conflicts: " + (overLaps + overQuota + demands + singles + classrooms));

        if(overLaps > 0){
            System.out.println("Overlaps conflicted: " + overLaps);
            return swapClassesTime(scheduler, conflictedOverLaps);
        }

        if(overQuota > 0){
            // change course's teacher
            System.out.println("Over Quota conflicted: " + overQuota);
            return swapTeacher(scheduler, conflictedOverQuota);
        }

        if(demands > 0){
            System.out.println("Demands conflicted: " + demands);
            return swapClassesTime(scheduler, conflictedDemands);
        }

        if(singles > 0){
            System.out.println("Single show conflicted: " + singles);
            return swapClassesTime(scheduler, singleConflicts);
        }

        if(classrooms > 0){
            // draw new classrooms for this lesson
            System.out.println("Classrooms conflicted: " + classrooms);
            return drawNewClassForLesson(scheduler, classesConflicts);
        }

        isConflicted = false;
        return scheduler;

    }


    /**
     * This method swap lessons time by changing the classRoom of randomly selected lesson
     * @param scheduler
     * @param conflictedLessons
     * @return
     */
    private static Scheduler swapClassesTime(Scheduler scheduler, ArrayList<Lesson> conflictedLessons){
        // need to change this lesson time
        // select random lesson to swap and remove from scheduler
        int rand = (int)(Math.random() * conflictedLessons.size());
        Lesson conflictLesson = conflictedLessons.get(rand);
        scheduler.removeLesson(conflictLesson);
        System.out.println("Selected conflict lesson to swap: " + conflictLesson);
        int teacherID = conflictLesson.getTeacher().getID();
        // draw random teacher
        ArrayList id = new ArrayList(scheduler.getTeacherCourseMap().keySet());
        rand = (int)(Math.random()* id.size());
        int randID = (int) id.get(rand);
        while(randID == teacherID){
            rand = (int)(Math.random()* id.size());
            randID = (int) id.get(rand);
        }
        // select random lesson to swap with and remove from scheduler
        ArrayList<Lesson> lessons = scheduler.getTeacherCourseMap().get(randID);
        rand = (int)(Math.random() * lessons.size());
        Lesson randLesson = lessons.get(rand);
        scheduler.removeLesson(randLesson);
        System.out.println("Selected rand lesson to swap: " + randLesson);
        ClassRoom classToSwap = conflictLesson.getClassRoom();
        conflictLesson.setClassRoom(randLesson.getClassRoom());
        randLesson.setClassRoom(classToSwap);

        // return "new" lessons to scheduler
        scheduler.addLesson(conflictLesson);
        scheduler.addLesson(randLesson);

        return scheduler;
    }

    /**
     *  This method will swap teacher due to over quota
     *  takes random teacher and remove one of his courses
     * @param scheduler
     * @param conflictedTeachers
     * @return
     */
    private static Scheduler swapTeacher(Scheduler scheduler, ArrayList<Teacher> conflictedTeachers){
        // select teacher
        int rand = (int)(Math.random() * conflictedTeachers.size());
        Teacher quotaTeacher = conflictedTeachers.get(rand);
        // select course
        ArrayList<Lesson> lessons = scheduler.getTeacherCourseMap().get(quotaTeacher.getID());
        rand = (int)(Math.random() * lessons.size());
        Lesson lesson = lessons.get(rand);
        // assign selected course to other teacher who can taught it
        DataBaseMySQLImpl dao = DataBaseMySQLImpl.getInstance();
        ArrayList<Integer> ids = dao.getTeacherForCourse(lesson.getCourse().getName());
        Teacher randTeacher = null;
        boolean isSelected = false;
        int round = ids.size() * 10;
        // keep drawing teacher until selected teacher can teach this course
        // or until fixed number of round based on teachers that can teach this course
        while(round > 0 && !isSelected){
            rand = (int)(Math.random() * ids.size());
            int randTeacherId = ids.get(rand);
            randTeacher = scheduler.getTeacherCourseMap().get(randTeacherId).get(0).getTeacher();
            if(randTeacher.getRemainingHours() > lesson.getCourse().getDuration()){
                isSelected = true;
                break;
            }
            round--;
        }
        if(!isSelected){
            // there is no other teacher for this course
            // return this scheduler, next run of local search may improve state
            return  scheduler;
        }
        // need to update remaining hours of both selected teachers
        quotaTeacher.addRemainingHours(lesson.getCourse().getDuration());
        randTeacher.reduceHour(lesson.getCourse().getDuration());

        // update scheduler
        scheduler.removeLesson(lesson);
        scheduler.addLesson(new Lesson.LessonBuilder().setTeacher(randTeacher).
                setCourse(lesson.getCourse()).
                setClassRoom(lesson.getClassRoom()).
                build());
        return scheduler;
    }

    private static Scheduler drawNewClassForLesson(Scheduler scheduler, ArrayList<Lesson> conflicted){
        BaseSchedulerData data = BaseSchedulerData.getInstance();
        int rand = (int)(Math.random() * conflicted.size());
        Lesson lesson = conflicted.get(rand);
        scheduler.removeLesson(lesson);
        ClassRoom classRoom = data.getClassForLesson(lesson.getCourse());
        lesson.setClassRoom(classRoom);
        scheduler.addLesson(lesson);
        return scheduler;
    }

}
