<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="by.academy.constant.FileConstant" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@400;700&display=swap" rel="stylesheet">
    <link href="resources/css/general.css" rel="stylesheet">
    <title>Home</title>
</head>
<body>
    <c:import url="header.jsp" />
    <c:if test="${empty user}">
        <% response.sendRedirect("login.jsp"); %>
    </c:if>
    <c:if test="${not empty user}">
        <c:out value="${user}"/>
    </c:if>
</body>
</html>