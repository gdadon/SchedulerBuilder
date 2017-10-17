package servlet.admin;

import objects.Demand;
import objects.UserInfo;
import schedule.builder.database.DataBaseMySQLImpl;
import utils.SessionUtils;
import utils.Utils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Guy on 06/06/2017.
 */
@WebServlet(name = "AdminDemandServlet", urlPatterns = {"/aDemands"})
public class AdminDemandServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // check that session is not null
        HttpSession session = request.getSession();
        if(session != null){
            UserInfo user = SessionUtils.getLoginedUser(request.getSession());
            if(user != null){

                DataBaseMySQLImpl dao = DataBaseMySQLImpl.getInstance();
                // get all demands from DB
                HashMap<Integer, ArrayList<Demand>> demandList = dao.getAllDemands();
                // map ID -> Names
                HashMap<Integer, String> idToName = dao.getTeacherIdNameMap();
                Map<Integer, String> daysToString = Utils.initDaysMap();
                HashMap<String, ArrayList<Demand>> stringDemands = new HashMap<>();
                // add day as string to each demand
                for(Map.Entry<Integer, ArrayList<Demand>> entry: demandList.entrySet()){
                    for(Demand d: entry.getValue()){
                        d.addDayStr(daysToString.get(d.getDay()));
                    }
                    stringDemands.put(idToName.get(entry.getKey()), entry.getValue());
                }

                request.setAttribute("demandsList", stringDemands);
//                SessionUtils.storeLoginedUser(session, user);
                RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/public/admin/demands.jsp");
                dispatcher.forward(request, response);
            }
        }
        else{
            response.sendRedirect(request.getContextPath() + "/Login");
        }
    }

}
