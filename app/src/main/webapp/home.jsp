<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="by.academy.constant.JSPConstant" %>
<%@ page import="by.academy.model.bean.UserType" %>

<% pageContext.setAttribute("userTypes", UserType.values()); %>

<!DOCTYPE html>
<html lang="en">
<head>
    <c:import url="/include/head.html" />
    <title>Home</title>
</head>
<body>
    <c:import url="/include/header.html" />
    <c:if test="${empty user}">
        <% response.sendRedirect(JSPConstant.LOGIN); %>
    </c:if>
    <c:if test="${not empty user}">
        <div class="home">
            <div class="home-menu">
                <c:out value="${user.userName}"/>
                <div class="sign-out"><a href="LogoutController">Sign out</a></div>
            </div>
            <c:if test="${user.userType == UserType.ADMIN}">
                <div class="admin-panel">
                    <div class="body">
                        <form id="userAddForm" action="UserAddController" method="POST">
                            <label>FIO</label>
                            <input type="text"/>
                            <label>Age</label>
                            <input type="number" min="0" max="100"/>
                            <label>User name</label>
                            <input type="text"/>
                            <label>Password</label>
                            <input type="password"/>
                            <select>
                                <c:forEach var="entry" items="${userTypes}">
                                    <option>${entry}</option>
                                </c:forEach>
                            </select>
                            <input type="submit" value="Add"/>
                        </form>
                    </div>
                </div>
            </c:if>
        </div>
    </c:if>
</body>
</html>