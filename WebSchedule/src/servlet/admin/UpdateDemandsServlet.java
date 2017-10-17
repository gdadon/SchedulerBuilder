package servlet.admin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Guy on 12/08/2017.
 */
@WebServlet(name = "UpdateDemandsServlet", urlPatterns = {"/updateDemands"})
public class UpdateDemandsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // check if setAllApproved flag is on -> "UPDATE demand SET status=1;"
        // else set new status
    }
}
