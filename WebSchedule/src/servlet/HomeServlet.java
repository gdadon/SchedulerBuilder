package servlet;

import objects.UserInfo;
import schedule.builder.database.DBUsersMySqlImpl;
import schedule.builder.database.DataBaseMySQLImpl;
import utils.SessionUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Guy on 02/06/2017.
 */
@WebServlet(name = "HomeServlet", urlPatterns = {"/Home"})
public class HomeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserInfo user = SessionUtils.getLoginedUser(request.getSession());
        RequestDispatcher dispatcher = null;
        if(user.getPrivilege() == 0){
            dispatcher = this.getServletContext().getRequestDispatcher("/public/user/home.jsp");
        }
        else{
            DataBaseMySQLImpl dao = DataBaseMySQLImpl.getInstance();
            int pendingDemands = dao.getPendingDemand();
            request.setAttribute("pendingDemands", pendingDemands);
            dispatcher = this.getServletContext().getRequestDispatcher("/public/admin/home.jsp");
        }
        dispatcher.forward(request, response);
    }
}
