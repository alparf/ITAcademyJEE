<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="by.academy.constant.ServletConstant" %>
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
        <% response.sendRedirect(ServletConstant.LOGIN); %>
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
                    <input type="submit" value="Add User"/>
                    <p class="exception">${exceptionMessage}</p>
                </form>
                <div class="container-inline large-box header-home">List of users</div>
                <c:forEach var="entry" items="${userList}">
                    <form class="container-inline content-start items-center large-box"
                        action="UserRemoveController" method="POST">
                        <span class="item" >${entry.fio}</span>
                        <span class="item">${entry.age}</span>
                        <span class="item">${entry.userName}</span>
                        <span class="item">${entry.userType}</span>
                        <div class="container-inline content-end grow">
                            <input type="text" name="userNameToRemove" value="${entry.userName}" hidden/>
                            <input type="submit" value="Remove" grow/>
                        </div>
                    </form>
                </c:forEach>
                <div class="container-inline large-box header-home">List of coach</div>
                <c:forEach var="entry" items="${userList}">
                    <c:if test="${entry.userType == UserType.COACH}">
                        <form class="container-inline content-start items-center large-box"
                            action="CoachAddSalary" method="POST">
                            <span name="coachName" class="item">${entry.fio}</span>
                            <input name="salary" type="number" min="0"/>
                            <span>BYN</span>
                            <div class="container-inline content-end grow">
                                <input type="text" name="coachName" value="${entry.userName}" hidden/>
                                <input type="submit" value="Add Salary"/>
                            </div>
                        </form>
                    </c:if>
                </c:forEach>
                <div class="container-inline large-box header-home">Average salaries</div>
                <c:forEach var="entry" items="${coachList}">
                    <div class="container-inline content-start items-center large-box"
                        <span class="item">${entry.user.fio}</span>
                        <span>${entry.salaries}</span>
                    </div>
                </c:forEach>
            </c:if>
        </div>
    </c:if>
</body>
</html>