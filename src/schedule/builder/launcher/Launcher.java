package schedule.builder.launcher;

import schedule.builder.database.DatabaseUtil;

public class Launcher {

    public static void main(String[] args) {

//    check if DB exist, if no build it
//        TODO need to add check if DB exist

        DatabaseUtil.dropDataBase("sbdb");

        DatabaseUtil.createDB();
        DatabaseUtil.fillDB("Practice Reports");

        System.out.println("=============================================");
        System.out.println("=== Data Base has been created and filled ===");
        System.out.println("=============================================");
/*
        // build base scheduler
        BaseSchedulerBuilder build = new BaseSchedulerBuilder();
        Scheduler scheduler = build.buildBaseScheduler();
        ReporterScheduler rs = null;
//        // save base scheduler
//        ReporterScheduler rs = new ReporterScheduler("output\\BaseWithPractices", scheduler);
        SchedulerUtils.saveSchedulerToFile(scheduler, "BaseWithPractices");
        // apply local search on base scheduler and save
//        Scheduler scheduler = SchedulerUtils.loadSchedulerFromFile("BaseWithPractices");
//        ReporterScheduler rs = null;

        scheduler = LocalSearch.startLocalSearch(scheduler);
        // check if there are still any conflicts

        int conflicts = ConflictCounter.countConflict(scheduler);

        if (conflicts > 0){
            System.exit(1);
        }
        else{
            System.out.println("Conflicts were resolved");
        }

        System.out.println("Score after local search: " + Ranking.getOptimizationScore(scheduler));
        rs = new ReporterScheduler("output\\SchedulerPostLocalSearch", scheduler);

        scheduler = Optimizer.optimize(scheduler);
        System.out.println("Score after optimization: " + Ranking.getOptimizationScore(scheduler));
        rs = new ReporterScheduler("output\\SchedulerPostOptimization", scheduler);

        boolean isScoreGood = false;
        int round = 0;
        while(!isScoreGood && round < 10){
            build = new BaseSchedulerBuilder();
            scheduler = build.buildBaseScheduler();
            scheduler = LocalSearch.startLocalSearch(scheduler);
            scheduler = Optimizer.optimize(scheduler);
            int score = Ranking.getOptimizationScore(scheduler);
            System.out.println("Score in round " + round + ": "  + score);
//            if (score >= 95){
//                isScoreGood = true;
                System.out.println("Saving Scheduler");
//                SchedulerUtils.saveSchedulerToFile(scheduler, "baseWithPractice"+ round + "score" + score);
                rs = new ReporterScheduler("output\\SchedulerRound" + round + "score" + score, scheduler);
//            }
            round ++;
        }
        */
    }
}
