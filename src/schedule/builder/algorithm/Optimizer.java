package schedule.builder.algorithm;

import objects.ClassRoom;
import objects.Lesson;
import objects.Scheduler;
import schedule.builder.scheduler.BaseSchedulerData;

import java.util.ArrayList;
import java.util.TreeSet;

/**
 * Created by Guy on 16/05/2017.
 */
public class Optimizer {

    private static BaseSchedulerData data = BaseSchedulerData.getInstance();

    public static Scheduler optimize(Scheduler scheduler){
        // move lesson to start at 9:00
        scheduler = setFirstLessonAt9(scheduler);
        // move single course in day to other dat with courses
        scheduler = moveSingleCourseToOtherDay(scheduler);
        // close gaps ("windows")
        return closeGaps(scheduler);
    }


    /**
     * This method will try to start the day sooner as possible
     * optimize start will be at 9:00
     * @return
     */
    public static Scheduler setFirstLessonAt9(Scheduler scheduler){
        for(int day = 1; day <= 6; day++){
            for(int sem = 1; sem <= 2; sem++){
                for(int year = 1; year <= 3; year++){
                    // if first lesson of day start after 9, try to swap it
                    ArrayList<Lesson> lessons = scheduler.getFirstLessonOfDay(day,sem,year);
                    for (Lesson lesson: lessons
                         ) {
                        checkAndMoveLesson(scheduler, lesson, 9, day);
                    }
                }
            }
        }
        return scheduler;
    }

    /**
     * This method will try to minimize the gaps in scheduler
     * gaps are also knows as "windows"
     * @param scheduler
     * @return
     */
    private static Scheduler closeGaps(Scheduler scheduler){
        // get each day courses
        for(int day = 1; day <= 5; day++){
            for(int sem = 1; sem <= 2; sem++){
                for(int year = 1; year <= 3; year++){
                    TreeSet<Lesson> lessons = scheduler.getLessonByDay(day,sem,year);
                    while(lessons.size() > 1){
                        //try to move next lesson to the lesson before it
                        Lesson firstLesson = lessons.pollFirst();
                        Lesson nextLesson = lessons.first();
                        if(firstLesson.getClassRoom().getHour() != nextLesson.getClassRoom().getHour()){
                            // lessons starts at different times, try to set them one after another
                            checkAndMoveLesson(scheduler, nextLesson,
                                    firstLesson.getClassRoom().getHour() + firstLesson.getCourse().getDuration(),
                                    firstLesson.getClassRoom().getDay());

                        }
                    }
                }
            }
        }
        // check if there is gaps
        // try to close gaps
        return scheduler;
    }

    private static boolean checkAndMoveLesson(Scheduler scheduler, Lesson lesson, int newHour, int newDay){
        // check if class exist
        // if lesson needs big classRooms check for it, if now check for both big and small
        ClassRoom newClassRoom;
        if(lesson.getCourse().getExpectedStudents() >= 50){
            if(!data.isClassExist(newDay, newHour, 'B')){
                // no big class, lesson cant be moved
                return false;
            }
            // create new class for lesson
            newClassRoom = new ClassRoom.ClassRoomBuilder().
                    setDay(newDay).
                    setHour(newHour).
                    setSize('B').
                    build();

        }
        else{
            // expected students are less then 50,
            // check if small or big classes exist
            if(data.isClassExist(newDay, newHour, 'S')){
                // create new Small class for lesson
                newClassRoom = new ClassRoom.ClassRoomBuilder().
                        setDay(newDay).
                        setHour(newHour).
                        setSize('S').
                        build();
            }
            else if (data.isClassExist(newDay, newHour, 'B')) {
                // create new Big class for lesson
                newClassRoom = new ClassRoom.ClassRoomBuilder().
                        setDay(newDay).
                        setHour(newHour).
                        setSize('B').
                        build();
            }
            else{
                // no class exist for lesson, cant be moved
                return false;
            }

        }
        // move to new date
        ClassRoom oldClass = lesson.getClassRoom();

        lesson.setClassRoom(newClassRoom);

        // check if cause conflicts

        if(ConflictCounter.countConflict(scheduler) > 0){
            lesson.setClassRoom(oldClass);
            System.out.println("Cant move lesson due to conflict");
            return false;
        }

        return true;
    }

    /**
     * This method will move single courses in day to other day with curses
     * @return
     */
    private static Scheduler moveSingleCourseToOtherDay(Scheduler scheduler){
        // check if any day has only 1 course
        for(int day = 1; day <= 5; day++) {
            for (int sem = 1; sem <= 2; sem++) {
                for (int year = 1; year <= 3; year++) {
                    if(isSingleCourseInDay(scheduler, day, sem, year)){
                        Lesson singleLesson = scheduler.getLessonByDay(day, sem, year).first();
                        // course is single in that day
                        // move that course to other day
                        for(int otherDay = 1; otherDay<= 5; otherDay++){
                            if(!(day == otherDay)){
                                // check only other days
                                TreeSet<Lesson> lessons = scheduler.getLessonByDay(otherDay, sem, year);
                                if(lessons.size() == 0){
                                    break;
                                }
                                Lesson lastLesson = lessons.last();
                                if(checkAndMoveLesson(scheduler, singleLesson,
                                        lastLesson.getClassRoom().getHour() + lastLesson.getCourse().getDuration(),
                                        otherDay)){
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }

        return scheduler;
    }

    private static boolean isSingleCourseInDay(Scheduler scheduler, int day, int sem, int year){
        TreeSet<Lesson> lessons = scheduler.getLessonByDay(day, sem, year);
        if(lessons.size() == 1){
            return  true;
        }
        return false;
    }


}
