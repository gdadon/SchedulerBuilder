package servlet;

import objects.UserInfo;
import schedule.builder.database.DBUsersMySqlImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Guy on 23/05/2017.
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/Login"})
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String userName = request.getParameter("username");
        String password = request.getParameter("password");
        boolean hasError = false;
        String errorString = null;
        int priv = -1;
        UserInfo user = null;

        if (userName == null || password == null || userName.length() == 0 || password.length() == 0) {
            hasError = true;
            errorString = "Required username and password!";
        } else {
            DBUsersMySqlImpl dao = DBUsersMySqlImpl.getInstance();
            user = dao.findUser(userName, password);
            dao.closeConnection();
            if (user == null) {
                hasError = true;
                errorString = "User Name or password invalid";
            }
        }

        if (hasError) {
            // Store information in request attribute, before forward.
            request.setAttribute("errorString", errorString);

            // Forward to login page
            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/public/Login.jsp");
            dispatcher.forward(request, response);
        }
        // If no error
        // Store user information in Session
        // And redirect to home page based on user privilege
        else {
            if (priv == 0) {
                // redirect to regular user
                RequestDispatcher dispatcher = request.getRequestDispatcher("public/home.jsp");
                dispatcher.forward(request, response);
            } else {
                // redirect to admin page
//                response.sendRedirect("public/home.jsp");

                Cookie loginCookie = new Cookie("user",user.getName());
                //setting cookie to expiry in 30 mins
                loginCookie.setMaxAge(30*60);
                response.addCookie(loginCookie);
                response.sendRedirect(request.getContextPath() + "/Home");

//            RequestDispatcher view = request.getRequestDispatcher("public/home.jsp");
//            view.forward(request, response);

            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/public/Login.jsp");
        dispatcher.forward(request, response);
    }
}
