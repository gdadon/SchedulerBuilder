package schedule.builder.scheduler;

import objects.*;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Guy on 01/02/2017.
 */
public class BaseSchedulerBuilder {

    /**
     * This class is responsible for building base scheduler
     * the main method should return Scheduler Obj which contains
     * all the lessons for all the years and semesters
     * without any consideration in conflicts
     */

    private int latestLessonTime = 15;

    private BaseSchedulerData data;

    public BaseSchedulerBuilder(){
        data = BaseSchedulerData.getInstance();
    }

    public Scheduler buildBaseScheduler(){
        Scheduler scheduler = new Scheduler();
        // combine Lesson - <Teacher, Class, Course>
        while(data.courses.size() > 0){
            // select random course
            Random generator = new Random();
            Object[] values = data.courses.values().toArray();
            Course course = (Course) values[generator.nextInt(values.length)];
            // remove it from list
            data.courses.remove(course.getCode());

            // select class for this course - class should be 17:00 latest
            boolean isLateClass = true;
            int rand;
            ClassRoom classRoom = null;
            while(isLateClass){
                rand = (int)(Math.random() * data.classes.size());
                classRoom = data.classes.get(rand);
                if(classRoom.getHour() <= latestLessonTime){
                    isLateClass = false;
                }
                //classes.remove(rand);
            }
            // get list of teachers for this course
            ArrayList<Teacher> teachersForCourse = data.getTeacherForCourse(course);
            // select teacher for course
            rand = (int)(Math.random() * teachersForCourse.size());
            Teacher teacher = teachersForCourse.get(rand);
            // check if teacher had passed quota
            if(teacher.getRemainingHours() <  course.getDuration()){
                // teacher had passed quota - iterate over all teachers and find the one that can teach
                // if no can teach set the rand teacher that draw first, conflictCounter will resole it later
                for (Teacher teacher2: teachersForCourse) {
                    if (teacher2.getRemainingHours() >= course.getDuration() ){
                        teacher = teacher2;
                        break;
                    }
                }
            }
            // reduce the selected teacher quota hours
            teacher.reduceHour(course.getDuration());
            //build Lesson and add to scheduler
            Lesson lesson = new Lesson.LessonBuilder().setTeacher(teacher)
                    .setCourse(course)
                    .setClassRoom(classRoom)
                    .build();
            scheduler.addLesson(lesson);
        }
        return scheduler;
    }

}
