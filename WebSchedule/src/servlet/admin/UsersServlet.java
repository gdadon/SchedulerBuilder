package servlet.admin;

import objects.UserInfo;
import schedule.builder.database.DBUsersMySqlImpl;
import utils.SessionUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

/**
 * Created by Guy on 02/06/2017.
 */
@WebServlet(name = "UsersServlet", urlPatterns = {"/Users"})
public class UsersServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // check that session is not null
        HttpSession session = request.getSession();
        if(session != null){
            UserInfo user = SessionUtils.getLoginedUser(request.getSession());
            if(user != null){

                DBUsersMySqlImpl dao = DBUsersMySqlImpl.getInstance();
                // get all users from DB - <ID, Name>
                Map<String, String> users = dao.getAllUsers();
                request.setAttribute("usersList", users);
                RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/public/admin/users.jsp");
                dispatcher.forward(request, response);
            }
        }
        else{
            response.sendRedirect(request.getContextPath() + "/Login");
        }

    }
}
