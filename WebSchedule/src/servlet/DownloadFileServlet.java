package servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet(name = "DownloadFileServlet", urlPatterns = {"/DownloadFileServlet"})
public class DownloadFileServlet extends HttpServlet {
 
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // reads input file from an absolute path

        // if you want to use a relative path to context root:
        String relativePath = getServletContext().getRealPath("");
        String downloadPath = relativePath + "\\download";

        String s = request.getParameter("file");
//        System.out.println(s);
        
        if(s.equals("template"))
        {
            downloadPath += "\\Template.rar";
        }else if(s.equals("results"))
        {
        	downloadPath += "\\result.txt";
        }else if(s.equals("schedule")){
            downloadPath += "\\Schedule.rar";
        }

        File downloadFile = new File(downloadPath);
        FileInputStream inStream = new FileInputStream(downloadFile);   
        // obtains ServletContext
        ServletContext context = getServletContext();

        // gets MIME type of the file
        String mimeType = context.getMimeType(relativePath);
        if (mimeType == null) {        
            // set to binary type if MIME mapping not found
            mimeType = "application/octet-stream";
        }
        System.out.println("MIME type: " + mimeType);
         
        // modifies response
        response.setContentType(mimeType);
        response.setContentLength((int) downloadFile.length());
         
        // forces download
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
        response.setHeader(headerKey, headerValue);
         System.out.println("headerValue: " + headerValue);
        // obtains response's output stream
        OutputStream outStream = response.getOutputStream();
         
        byte[] buffer = new byte[4096];
        int bytesRead = -1;
         
        while ((bytesRead = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, bytesRead);
        }
         
        inStream.close();
        outStream.close();     
    }
}