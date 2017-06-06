<%--
  Created by IntelliJ IDEA.
  User: Guy
  Date: 05/06/2017
  Time: 19:14
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
    if(userName == null) response.sendRedirect("/Login");
%>
<div>
    <h1>Hello again, <%=userName.replace('_', ' ') %></h1>
    <nav>
        <ul class="group">
            <li><a href="${pageContext.request.contextPath}/Home">Home</a></li>
            <li><a href="${pageContext.request.contextPath}/uDemands">Demands</a></li>
            <li><a href="${pageContext.request.contextPath}/Schedule">Schedule</a></li>
        </ul>
    </nav>
    <script>setActive()</script>
</div>