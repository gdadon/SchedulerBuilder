package utils;

import objects.Demand;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Guy on 03/06/2017.
 */
public class Utils {

    private static Map<Integer, String> days = initDaysMap();

    public static String DemandToString(Demand demand){
        return days.get(demand.getDay()) + " | " +
                demand.getStart() +":00 - " +
                demand.getEnd() + ":00 | " +
                demand.getReason() + " | " +
                demand.getStatus();
    }

    public static ArrayList<String> DemandsToString(ArrayList<Demand> demands){
        ArrayList<String> daysString = new ArrayList<>();
        for (Demand demand: demands){
            daysString.add(days.get(demand.getDay()) + " | " +
                            demand.getStart() +":00 - " +
                            demand.getEnd() + ":00 | " +
                            demand.getReason() + " | " +
                            demand.getStatus());
        }
        return daysString;
    }

    // init days map -> day num to value
    private static Map<Integer, String> initDaysMap(){
        Map<Integer, String> days = new HashMap();
        days.put(1, "Sunday");
        days.put(2, "Monday");
        days.put(3, "Tuesday");
        days.put(4, "Wednesday");
        days.put(5, "Thursday");
        days.put(6, "Friday");
        return days;
    }
}
