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
    <c:import url="/include/header.jsp" />
    <c:if test="${empty user}">
        <% response.sendRedirect(ServletConstant.LOGIN); %>
    </c:if>
    <c:if test="${not empty user}">
        <c:if test="${user.userType == UserType.ADMIN}">
            <div class="add-user">
                <form class="add-user_form"
                    id="userAddForm" action="UserAddController" method="POST">
                    <label >FIO</label>
                    <input type="text"/ name="fio" value="Сидоров Александр Петрович">
                    <label>User name</label>
                    <input type="text" name="userName" value="Sasha1990"/>
                    <label>Password</label>
                    <input type="password" name="password" value="12345678"/>
                    <div class="add-user_form_container">
                        <div class="add-user_form_container_inner">
                            <label>Age</label>
                            <input type="number" min="0" max="100" name="age" value="30"/>
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
            <div class="user-list"><h3>User List</h3>
                <c:forEach var="entry" items="${userList}">
                    <form class="user-list_form"
                        action="UserRemoveController" method="POST">
                        <span class="item" >${entry.fio}</span>
                        <span class="item">${entry.age}</span>
                        <span class="item">${entry.userName}</span>
                        <span class="item">${entry.userType}</span>
                        <input type="text" name="userNameToRemove" value="${entry.userName}" hidden/>
                        <input type="submit" value="Remove" grow/>
                    </form>
                </c:forEach>
            </div>
            <div class="coach"><h3>Coach List</h3>
                <c:forEach var="entry" items="${userList}">
                    <c:if test="${entry.userType == UserType.COACH}">
                        <form class="coach_form"
                            action="CoachAddSalary" method="POST">
                            <span name="coachName" class="item">${entry.fio}</span>
                            <input name="salary" type="text" placeholder="235.15"/>
                            <span>BYN</span>
                            <div class="container-inline content-end grow">
                                <input type="text" name="coachName" value="${entry.userName}" hidden/>
                                <input type="submit" value="Add Salary"/>
                            </div>
                        </form>
                    </c:if>
                </c:forEach>
            </div>
            <div class="salary"><h3>Salaries</h3>
                <c:forEach var="entry" items="${coachList}">
                    <div class="salary_inner">
                        <span class="item">${entry.user.fio}</span>
                        <span>${entry.salaries}</span>
                    </div>
                </c:forEach>
            </div>
        </c:if>
    </c:if>
</body>
</html>
