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
        <div class="container-pillar content-center items-center">
            <div class="container-inline large-box header-home">
                <div class="container-inline">
                    <c:out value="${user.fio}"/>
                </div>
                <div class="container-inline content-end grow">
                    <a href="LogoutController">Sign out</a>
                </div>
            </div>
            <c:if test="${user.userType == UserType.ADMIN}">
                <form class="container-pillar"
                    id="userAddForm" action="UserAddController" method="POST">
                    <label >FIO</label>
                    <input type="text"/ name="fio" value="Сидоров Александр Петрович">
                    <label>User name</label>
                    <input type="text" name="userName" value="Sasha1990"/>
                    <label>Password</label>
                    <input type="password" name="password" value="12345678"/>
                    <div class="container-inline">
                        <div class="container-pillar">
                            <label>Age</label>
                            <input type="number" min="0" max="100" name="age" value="30"/>
                        </div>
                        <div class="container-pillar">
                            <label>User type</label>
                            <select name="userType">
                                <c:forEach var="entry" items="${userTypes}">
                                    <option>${entry}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <input type="submit" value="Add"/>
                    <p class="exception">${exceptionMessage}</p>
                </form>
            </c:if>
        </div>
    </c:if>
</body>
</html>