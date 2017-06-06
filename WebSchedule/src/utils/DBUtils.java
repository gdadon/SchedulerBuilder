package utils;

import objects.Demand;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Guy on 05/06/2017.
 */
public class DBUtils {

    private static DBUtils instance = null;
    // demands cache
    private static HashMap<String, ArrayList<String>> demands;
    // hold number of call for each ID, after 10 calls remove from map for refresh -> load new state from db
    private static HashMap<String, Integer> demandsCalls;

    private DBUtils(){
        demands = new HashMap<>();
        demandsCalls = new HashMap<>();
    }

    public static DBUtils getInstance(){
        if(instance == null){
            instance = new DBUtils();
        }
        return instance;
    }

    public void addUserDemands(String id, ArrayList<String> demandList){
        demands.put(id, demandList);
        demandsCalls.put(id, 1);
    }

    public ArrayList<String> updateDemandList(String id, Demand demand){
        ArrayList<String> updateDemands = demands.get(id);
        updateDemands.add(Utils.DemandToString(demand));
        demands.put(id, updateDemands);
        return updateDemands;
    }

    public ArrayList<String> getUserDemands(String id){
        Integer times = demandsCalls.get(id);
        ArrayList<String> returnDemands = null;
        if(times == null){
            return returnDemands;
        }
        returnDemands = demands.get(id);
        if(times++ > 10){
            // need to remove from map and reset times called
            times = 0;
            demands.remove(id);
        }
        demandsCalls.put(id, times);
        return returnDemands;
    }

    public void clearUserData(String id){
        demands.remove(id);
    }
}
