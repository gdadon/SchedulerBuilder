<%--
  Created by IntelliJ IDEA.
  User: Guy
  Date: 02/06/2017
  Time: 17:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%
    String userName = null;
    Cookie[] cookies = request.getCookies();
    if(cookies != null){
        for(Cookie cookie : cookies){
            if(cookie.getName().equals("user")) userName = cookie.getValue();
        }
    }
    if(userName == null || userName == ""){
//        response.sendRedirect(request.getContextPath() + "/Login");
        RequestDispatcher dispatcher = this.getServletConfig().getServletContext().getRequestDispatcher("/public/Login.jsp");
        dispatcher.forward(request, response);
    }
%>
<div>
    <h1>Hello again, <%=userName.replace('_', ' ')%></h1>
    <nav>
        <ul class="group">
            <li><a href="${pageContext.request.contextPath}/Home">Home</a></li>
            <li><a href="${pageContext.request.contextPath}/aDemands">Demands</a></li>
            <li><a href="${pageContext.request.contextPath}/Reports">Reports</a></li>
            <li><a href="${pageContext.request.contextPath}/aSchedule">Schedule</a></li>
            <li><a href="${pageContext.request.contextPath}/Users">Users</a></li>
            <li><a id="logoutStyle" href="${pageContext.request.contextPath}/Logout">Logout</a></li>
        </ul>
    </nav>
    <script>setActive()</script>
</div>