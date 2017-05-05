package schedule.builder.build.scheduler;

import objects.*;
import schedule.builder.database.DataBaseMySQLImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by Guy on 01/02/2017.
 */
public class BuildBaseScheduler {

    /**
     * This class is responsible for building base scheduler
     * the main method should return Scheduler Obj which contains
     * all the lessons for all the years and semesters
     * without any consideration in conflicts
     */

    private int latestLessonTime = 15;

    private DataBaseMySQLImpl dao;
    private ArrayList<ClassRoom> classes;
    private HashMap<String, Course> courses;
    private HashMap<Integer, Teacher> teachers;

    private HashMap<Integer, ArrayList<Lesson>> teacherCourseMap = new HashMap<>();

    public BuildBaseScheduler(){
        // get dao object
        dao = DataBaseMySQLImpl.getInstance();
        // get all teachers
        // get all available classes
        classes = dao.getAllClassRooms();
        // get all courses
        courses = dao.getAllCourses();
        teachers = dao.getAllTeachersCourse();
    }

    public Scheduler buildBaseScheduler(){
        Scheduler scheduler = new Scheduler();
        // combine Lesson - <Teacher, Class, Course>
        while(courses.size() > 0){
            // select random course
            Random generator = new Random();
            Object[] values = courses.values().toArray();
            Course course = (Course) values[generator.nextInt(values.length)];
            // remove it from list
            courses.remove(course.getCode());

            // select class for this course - class should be 17:00 latest
            boolean isLateClass = true;
            int rand;
            ClassRoom classRoom = null;
            while(isLateClass){
                rand = (int)(Math.random() * classes.size());
                classRoom = classes.get(rand);
                if(classRoom.getHour() <= latestLessonTime){
                    // lesson will start at 17:00 at most
                    isLateClass = false;
                }
                //classes.remove(rand);
            }
            // get list of teachers for this course
            ArrayList<Teacher> teachersForCourse = getTeacherForCourse(course);
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
            // add lesson to teacher course map
            ArrayList<Lesson> lessonsToAdd = null;
            if((lessonsToAdd = teacherCourseMap.get(teacher.getID())) == null){
                lessonsToAdd = new ArrayList<>();
            }
            lessonsToAdd.add(lesson);
            teacherCourseMap.put(teacher.getID(), lessonsToAdd);
        }
        scheduler.setTeacherCourseMap(teacherCourseMap);
        return scheduler;
    }

    private ArrayList<Teacher> getTeacherForCourse(Course course){
        ArrayList<Integer> IDs = dao.getTeacherForCourse(course.getName());
        ArrayList<Teacher> teachers = getTeachersByIDs(IDs);
        return teachers;
    }

    /**
     * return teachers ID that can teach given course
     * @param course course to teach
     * @return ArrayList<String> of teachers IDs
     */
    private ArrayList<Integer> getTeachersIDsForCourse(Course course){
        ArrayList<Integer> teachers = new ArrayList<>();
        for (Teacher tc: this.teachers.values()) {
            if(tc.getCourses().contains(course)){
                teachers.add(tc.getID());
            }
        }
        return teachers;
    }

    /**
     *
     * @param IDs - ArrayList<int> of teachers ID
     * @return ArrayList<Teacher> teacher with same ID
     */
    private ArrayList<Teacher> getTeachersByIDs(ArrayList<Integer> IDs){
        ArrayList<Teacher> teachers = new ArrayList<>();
        Teacher teacher;
        for (int id: IDs) {
            if((teacher = this.teachers.get(id)) != null){
                teachers.add(teacher);
            }
        }
        return teachers;
    }

}
