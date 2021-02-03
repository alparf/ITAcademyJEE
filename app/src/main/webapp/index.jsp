<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="by.academy.constant.ServletProperties" %>
<%@ page import="by.academy.constant.PageName" %>
<%@ page import="javax.servlet.http.Cookie" %>
<!DOCTYPE html>
<html>
<head>
    <c:import url="/include/head.html" />
    <title>Index</title>
</head>
<body>
    <%
        Cookie[] cookies = null;
        cookies = request.getCookies();
        if(null != cookies) {
            for (Cookie cookie: cookies) {
               if("isVisited".equals(cookie.getName())) {
                  
               }
            }
        }
    %>
    <c:import url="/include/header.jsp" />
    <div class="welcome">
       <span class="welcome_message">welcome!</span>
    </div>
</body>
</html>