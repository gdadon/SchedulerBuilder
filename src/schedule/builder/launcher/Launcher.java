package schedule.builder.launcher;

import objects.Scheduler;
import reporter.ReporterScheduler;
import schedule.builder.algorithm.LocalSearch;
import schedule.builder.algorithm.Optimizer;
import schedule.builder.algorithm.Ranking;
import schedule.builder.scheduler.BaseSchedulerBuilder;

public class Launcher {

    public static void main(String[] args) {

//    check if DB exist, if no build it
//        TODO need to add check if DB exist

//        DatabaseUtil.createDB();
//        DatabaseUtil.fillDB("NewReports");
//
//        System.out.println("=============================================");
//        System.out.println("=== Data Base has been created and filled ===");
//        System.out.println("=============================================");

        // build base scheduler
        BaseSchedulerBuilder build = new BaseSchedulerBuilder();
        Scheduler scheduler = build.buildBaseScheduler();
        // save base scheduler
        ReporterScheduler rs = new ReporterScheduler("output\\BaseScheduler", scheduler);
        // apply local search on base scheduler and save
        scheduler = LocalSearch.startLocalSearch(scheduler);
        System.out.println("Score after local search: " + Ranking.getOptimizationScore(scheduler));
        rs = new ReporterScheduler("output\\SchedulerPostLocalSearch", scheduler);

        scheduler = Optimizer.optimize(scheduler);
        System.out.println("Score after optimization: " + Ranking.getOptimizationScore(scheduler));
        rs = new ReporterScheduler("output\\SchedulerPostLocalSearch", scheduler);

        boolean isScoreGood = false;
        int round = 0;
        while(/*!isScoreGood &&*/ round < 10){
            build = new BaseSchedulerBuilder();
            scheduler = build.buildBaseScheduler();
            scheduler = LocalSearch.startLocalSearch(scheduler);
            scheduler = Optimizer.optimize(scheduler);
            int score = Ranking.getOptimizationScore(scheduler);
            System.out.println("Score in round " + round + ": "  + score);
            if (score >= 95){
//                isScoreGood = true;
                System.out.println("Saving Scheduler");
                rs = new ReporterScheduler("output\\SchedulerRound" + round + "score" + score, scheduler);
            }
            round ++;
        }
    }
}
