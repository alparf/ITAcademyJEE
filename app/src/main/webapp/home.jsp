<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="by.academy.constant.PageName" %>
<%@ page import="by.academy.model.bean.UserType" %>

<% pageContext.setAttribute("userTypes", UserType.values()); %>

<!DOCTYPE html>
<html lang="en">
<head>
    <c:import url="/include/head.html" />
    <title>Home</title>
</head>
<body>
    <c:import url="/include/header.jsp" />
    <c:if test="${empty user}">
        <% response.sendRedirect(PageName.LOGIN); %>
    </c:if>
    <div class="home">
        <c:if test="${not empty user}">
            <c:if test="${user.userType == UserType.ADMIN}">
                <div class="home_nav">
                    <nav class="home_nav_inner">
                        <a id="addUser-btn" class="home_nav_inner_link" href="#">Add new user</a>
                        <a id="userList-btn" class="home_nav_inner_link" href="#">User list</a>
                        <a id="coachList-btn" class="home_nav_inner_link" href="#">Coach list</a>
                        <a id="salary-btn" class="home_nav_inner_link" href="#">Salaries</a>
                    </nav>
                </div>
                <div class="home_desktop">
                    <div id="addUser" class="add-user shadow">
                        <div class="add-user_form_title"><h2>Add new user</h2></div>
                        <form class="add-user_form"
                            id="userAddForm" action="UserController" method="POST">
                            <label >FIO</label>
                            <input type="text"/ name="fio" placeholder="First name Last name" required>
                            <label>User name</label>
                            <input type="text" name="userName" placeholder="User name to sign in" required/>
                            <label>Password</label>
                            <input type="password" name="password" placeholder="Password" required/>
                            <div class="add-user_form_container">
                                <div class="add-user_form_container_inner">
                                    <label>Age</label>
                                    <input type="number" min="0" max="100" name="age" required placeholder="Age"/>
                                </div>
                                <div class="add-user_form_container_inner">
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
                    </div>
                    <div id="userList" class="user-list shadow" hidden>
                        <h3>User List</h3>
                    </div>
                    <div id="coachList" class="coach shadow" hidden>
                        <h3>Coach List</h3>
                    </div>
                    <div id="salary" class="salary shadow" hidden><h3>Salaries</h3>
                        <c:forEach var="entry" items="${coachList}">
                            <div class="salary_inner">
                                <span class="item">${entry.user.fio}</span>
                                <span>${entry.salaries}</span>
                            </div>
                        </c:forEach>
                    </div>
                </c:if>
            </c:if>
        </div>
    </div>
    <script type="text/javascript" src="resources/js/home.js"></script>
</body>
</html>
