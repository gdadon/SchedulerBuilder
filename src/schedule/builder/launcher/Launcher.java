package schedule.builder.launcher;

import objects.Scheduler;
import schedule.builder.build.scheduler.BuildBaseScheduler;
import schedule.builder.database.DatabaseUtil;

public class Launcher {

    public static void main(String[] args) {

//    check if DB exist, if no build it
//        TODO need to add check if DB exist

        DatabaseUtil.createDB();
        DatabaseUtil.fillDB("Liats Reports");

        System.out.println("=============================================");
        System.out.println("=== Data Base has been created and filled ===");
        System.out.println("=============================================");

        BuildBaseScheduler build = new BuildBaseScheduler();
        Scheduler scheduler = build.buildBaseScheduler();
        System.out.println(scheduler);
    }
}
