package schedule.builder.scheduler;

import objects.Lesson;
import objects.Scheduler;

import java.io.*;

/**
 * Created by Guy on 05/05/2017.
 */
public class SchedulerUtils {

    public static void saveSchedulerToFile(Scheduler scheduler, String fileName){
        FileOutputStream fout = null;
        ObjectOutputStream oout = null;
        int numOfLessons = scheduler.getLessons().size();

        try {
            fout = new FileOutputStream(new File(fileName));
            oout = new ObjectOutputStream(fout);

            // write num of lessons to help better loading
            oout.writeObject(numOfLessons);

            for (Lesson lesson: scheduler.getLessons()) {
                oout.writeObject(lesson);
            }

            oout.close();
            fout.close();
            System.out.println("Scheduler has been saved to " + fileName);

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error initializing stream");
            e.printStackTrace();
        }
    }

    public static Scheduler loadSchedulerFromFile(String fileName){
        FileInputStream in = null;
        ObjectInputStream oin = null;
        Scheduler scheduler = new Scheduler();
        try {
            in = new FileInputStream(new File(fileName));
            oin = new ObjectInputStream(in);

            // first obj in file is number of lessons
            int numOfLessons = (int) oin.readObject();
            for(int i = 0; i < numOfLessons; i++){
                Lesson l = (Lesson) oin.readObject();
                scheduler.addLesson(l);
            }

            oin.close();
            in.close();

    } catch (FileNotFoundException e) {
        System.out.println("File not found");
        e.printStackTrace();
    } catch (IOException e) {
        System.out.println("May be End Of File.");
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    }

        return scheduler;
}
}
