package servlet.admin;

import schedule.builder.launcher.Launcher;
import utils.FileMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Guy on 12/06/2017.
 */
@WebServlet(name = "BuildScheduleServlet", urlPatterns = {"/BuildSchedule"})
public class BuildScheduleServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String classes = FileMap.getPathForOptionFile(FileMap.OptionFile.CLASS);
        String courses = FileMap.getPathForOptionFile(FileMap.OptionFile.COURSES);
        String teachers = FileMap.getPathForOptionFile(FileMap.OptionFile.TEACHERS);

        Launcher.Launch(classes, courses, teachers, "");
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

//        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/public/admin/schedule.jsp");
//        dispatcher.forward(request, response);
    }
}
