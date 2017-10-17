package servlet;

import schedule.builder.database.DataBaseMySQLImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Guy on 07/06/2017.
 */
@WebServlet(name = "DBServlet", urlPatterns = {"/DB"})
public class DBServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DataBaseMySQLImpl dao = DataBaseMySQLImpl.getInstance();

    }
}
