package schedule.builder.launcher;

import objects.Scheduler;
import reporter.ReporterScheduler;
import schedule.builder.algorithm.ConflictCounter;
import schedule.builder.algorithm.LocalSearch;
import schedule.builder.algorithm.Optimizer;
import schedule.builder.algorithm.Ranking;
import schedule.builder.scheduler.BaseSchedulerBuilder;
import schedule.builder.scheduler.SchedulerUtils;

public class Launcher {

    public static void main(String[] args) {

//    check if DB exist, if no build it

//        DatabaseUtil.dropDataBase("sbdb");
//
//        DatabaseUtil.autoCreateDB();
//        DatabaseUtil.autoFillDB("NewReports");

//
        System.out.println("=============================================");
        System.out.println("=== Data Base has been created and filled ===");
        System.out.println("=============================================");

        BaseSchedulerBuilder build = new BaseSchedulerBuilder();
        Scheduler scheduler = build.buildBaseScheduler();
        ReporterScheduler rs = null;
//        // save base scheduler
        rs = new ReporterScheduler("C:\\Schedule\\BaseSchedule", scheduler);
        SchedulerUtils.saveSchedulerToFile(scheduler, "BaseSchedule");
        // apply local search on base scheduler and save

        scheduler = LocalSearch.startLocalSearch(scheduler);
        // check if there are still any conflicts

        int conflicts = ConflictCounter.countConflict(scheduler);

        if (conflicts > 0){
            System.exit(1);
        }
        else{
            System.out.println("Conflicts were resolved");
        }
        int score = Ranking.getOptimizationScore(scheduler);
        System.out.println("Score after local search: " + score);
        rs = new ReporterScheduler("output\\SchedulerPostLocalSearch" + score, scheduler);

        scheduler = Optimizer.optimize(scheduler);
        score = Ranking.getOptimizationScore(scheduler);
        System.out.println("Score after optimization: " + score);
        rs = new ReporterScheduler("output\\SchedulerPostOptimization" + score, scheduler);

        boolean isScoreGood = false;
        int round = 0;
        while(!isScoreGood && round < 5){
            build = new BaseSchedulerBuilder();
            scheduler = build.buildBaseScheduler();
            scheduler = LocalSearch.startLocalSearch(scheduler);
            scheduler = Optimizer.optimize(scheduler);
            score = Ranking.getOptimizationScore(scheduler);
            System.out.println("Score in round " + round + ": "  + score);
            if (score >= 95){
//                isScoreGood = true;
                System.out.println("Saving Scheduler");
//                SchedulerUtils.saveSchedulerToFile(scheduler, "baseWithPractice"+ round + "score" + score);
                rs = new ReporterScheduler("output\\SchedulerRound" + round + "score" + score, scheduler);
            }
            round ++;
        }

    }

    public static void Launch(String classes, String courses, String teachers, String saveFolder){
//        DatabaseUtil.autoDropTables();
//        DatabaseUtil.autoCreateDB();
//        DatabaseUtil.fillDB(classes, courses, teachers);
        BaseSchedulerBuilder build = new BaseSchedulerBuilder();
        Scheduler scheduler = build.buildBaseScheduler();
        ReporterScheduler rs = null;
//        // save base scheduler
        rs = new ReporterScheduler("C:\\Schedule\\BaseSchedule", scheduler);
        SchedulerUtils.saveSchedulerToFile(scheduler, "BaseSchedule");
        // apply local search on base scheduler and save

        scheduler = LocalSearch.startLocalSearch(scheduler);
        // check if there are still any conflicts

        int conflicts = ConflictCounter.countConflict(scheduler);

        if (conflicts > 0){
            System.exit(1);
        }
        else{
            System.out.println("Conflicts were resolved");
        }
        int score = Ranking.getOptimizationScore(scheduler);
        System.out.println("Score after local search: " + score);
        rs = new ReporterScheduler("C:\\Schedule\\SchedulerPostLocalSearch" + score, scheduler);

        scheduler = Optimizer.optimize(scheduler);
        score = Ranking.getOptimizationScore(scheduler);
        System.out.println("Score after optimization: " + score);
        rs = new ReporterScheduler("C:\\Schedule\\SchedulerPostOptimization" + score, scheduler);

        boolean isScoreGood = false;
        int round = 0;
        while(!isScoreGood && round < 5){
            build = new BaseSchedulerBuilder();
            scheduler = build.buildBaseScheduler();
            scheduler = LocalSearch.startLocalSearch(scheduler);
            scheduler = Optimizer.optimize(scheduler);
            score = Ranking.getOptimizationScore(scheduler);
            System.out.println("Score in round " + round + ": "  + score);
            if (score >= 95){
//                isScoreGood = true;
            System.out.println("Saving Scheduler");
//                SchedulerUtils.saveSchedulerToFile(scheduler, "baseWithPractice"+ round + "score" + score);
            rs = new ReporterScheduler("C:\\Schedule\\SchedulerRound" + round + "score" + score, scheduler);
            }
            round ++;
        }
    }

}

