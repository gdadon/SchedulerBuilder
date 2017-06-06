package servlet.user;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import objects.Demand;
import objects.Status;
import objects.UserInfo;
import schedule.builder.database.DataBaseMySQLImpl;
import utils.DBUtils;
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
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Guy on 02/06/2017.
 */
@WebServlet(name = "UserDemandServlet", urlPatterns = {"/uDemands"})
public class UserDemandServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        UserInfo user = SessionUtils.getLoginedUser(request.getSession());
        DBUtils instance = DBUtils.getInstance();
        ArrayList<String> stringDemands = instance.getUserDemands(user.getId());
        String error = null;
        boolean hasError = false;

        int day = Integer.parseInt(request.getParameter("day"));
        int start = Integer.parseInt(request.getParameter("start"));
        int end = Integer.parseInt(request.getParameter("end"));
        String reason = request.getParameter("reason");

        // validate demand
        if(start >= end){
            hasError = true;
            error = "Demands time is wrong, end hour should be later than start hour";
        }

        if(hasError){
            request.setAttribute("error", error);
            request.setAttribute("day", day);
            request.setAttribute("start", start);
            request.setAttribute("end", end);
            request.setAttribute("reason", reason);
        }
        else{
            // add demand to DB

            DataBaseMySQLImpl dao = DataBaseMySQLImpl.getInstance();
            try {
                dao.addDemand(user.getId(), day, start, end, reason, Status.PENDING);
            } catch (MySQLIntegrityConstraintViolationException e) {
                // duplicate entry
                hasError = true;
                error = "Demand already exist";
            } catch (SQLException e) {
                hasError = true;
                error = "Something went wrong, please try again later";
            } finally {
                dao.closeConnection();
                request.setAttribute("error", error);
            }
            // check add status -> pass or error
            if(!hasError){
                // demand was added to DB
                // add demands to demand cache and store it as request attribute
                stringDemands = instance.updateDemandList(user.getId(),
                        new Demand.DemandBuilder().setDay(day)
                                .setStart(start)
                                .setEnd(end)
                                .setReason(reason)
                                .setStatus(Status.PENDING)
                                .build());

            }
            else {
                request.setAttribute("error", error);
                request.setAttribute("day", day);
                request.setAttribute("start", start);
                request.setAttribute("end", end);
                request.setAttribute("reason", reason);
            }
        }
        request.setAttribute("demandsList", stringDemands);
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/public/user/demands.jsp");
        dispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // get current user ID for pulling database info
        // check that session is nuo null
        HttpSession session = request.getSession();
        if(session != null){
            UserInfo user = SessionUtils.getLoginedUser(request.getSession());
            if(user != null){
                request.setAttribute("id", user.getId());

                // get all demands by ID
                //check if demands are in cache
                DBUtils instance = DBUtils.getInstance();
                ArrayList<String> stringDemands = instance.getUserDemands(user.getId());
                if(stringDemands == null){
                    // not in cache, load from DB
                    DataBaseMySQLImpl dao = DataBaseMySQLImpl.getInstance();
                    ArrayList<Demand> demands = dao.getDemandOfTeacher(user.getId());
                    // parse all demands to strings
                    stringDemands = Utils.DemandsToString(demands);
                    // store demands list at demands cache (DBUtils)
                    instance.addUserDemands(user.getId(), stringDemands);
                }
                request.setAttribute("demandsList", stringDemands);
                SessionUtils.storeLoginedUser(session, user);
                RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/public/user/demands.jsp");
                dispatcher.forward(request, response);
            }
        }
        else{
            response.sendRedirect(request.getContextPath() + "/Login");
        }
    }

}
