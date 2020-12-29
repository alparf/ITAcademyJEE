<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="by.academy.constant.ServletConstant" %>
<%@ page import="javax.servlet.http.Cookie" %>
<!DOCTYPE html>
<html>
<head>
    <c:import url="/include/head.html" />
    <title>Average</title>
</head>
<body>
    <c:import url="/include/header.jsp" />
    <div class="average">
        <form class="average_panel" method="POST" action="AverageSalariesController">
            <label>Last of months</label>
            <c:if test="${not empty monthCount}">
                <input type="number" class="average_panel_input"
                name="monthCount" value="${monthCount}"/>
            </c:if>
            <c:if test="${empty monthCount}">
                <input type="number" class="average_panel_input" name="monthCount"/>
            </c:if>
            <input type="submit" class="average_panel_btn" value="Show"/>
        </form>
        <c:forEach var="entry" items="${coachList}">
            <div class="salary_inner">
                <span class="item">${entry.user.fio}</span>
                <span>${entry.getAverageSalary(monthCount) / 100}</span>
            </div>
        </c:forEach>
    </div>
</body>
</html>