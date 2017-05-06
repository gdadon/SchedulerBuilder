package schedule.builder.scheduler;

import objects.ClassRoom;
import objects.Course;
import objects.Teacher;
import schedule.builder.database.DataBaseMySQLImpl;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Guy on 06/05/2017.
 *
 * This class will hold the data for build scheduler
 * Teachers, Classrooms and Courses list
 * The singleton use is for keep all this data in one place
 * and make sure that when we make change in one list it will force
 * this change into all other classes using this class
 *
 * the main uses for this class is in BaseSchedulerBuild and LocalSearch
 *
 */
public class BaseSchedulerData {

    private static DataBaseMySQLImpl dao;
    public static ArrayList<ClassRoom> classes;
    public static HashMap<String, Course> courses;
    public static HashMap<Integer, Teacher> teachers;

    private static BaseSchedulerData instance = new BaseSchedulerData();

    public static BaseSchedulerData getInstance() {
        return instance;
    }

    private BaseSchedulerData() {
        // get dao object
        dao = DataBaseMySQLImpl.getInstance();
        // get all teachers
        courses = dao.getAllCourses();
        // get all available classes
        classes = dao.getAllClassRooms();
        // get all courses
        teachers = dao.getAllTeachersCourse();
    }

    public static ArrayList<Teacher> getTeacherForCourse(Course course){
        ArrayList<Integer> IDs = dao.getTeacherForCourse(course.getName());
        ArrayList<Teacher> teachers = getTeachersByIDs(IDs);
        return teachers;
    }

    /**
     * return teachers ID that can teach given course
     * @param course course to teach
     * @return ArrayList<String> of teachers IDs
     */
    private static ArrayList<Integer> getTeachersIDsForCourse(Course course){
        ArrayList<Integer> teachers = new ArrayList<>();
        for (Teacher tc: instance.teachers.values()) {
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
    private static ArrayList<Teacher> getTeachersByIDs(ArrayList<Integer> IDs){
        ArrayList<Teacher> retTeachers = new ArrayList<>();
        Teacher teacher;
        for (int id: IDs) {
            if((teacher = instance.teachers.get(id)) != null){
                retTeachers.add(teacher);
            }
        }
        return retTeachers;
    }

}
