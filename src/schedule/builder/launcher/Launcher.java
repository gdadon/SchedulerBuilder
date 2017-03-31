package schedule.builder.launcher;

import schedule.builder.database.DatabaseUtil;

public class Launcher {

    public static void main(String[] args) {

    //check if DB exsist, if no build it
        //TODO need to add check if DB exsist
        DatabaseUtil.createDB();
        DatabaseUtil.fillDB();

    }
}
