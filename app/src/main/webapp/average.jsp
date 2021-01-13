<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="by.academy.constant.PageConstant" %>
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
        <div class="average_title"><h2>Average salaries</h2></div>
        <form class="average_panel" method="POST" action="AverageSalariesController">
            <div class="average_panel_inner">
                <label>Last of months</label>
                <c:if test="${not empty monthCount}">
                    <input type="number" class="average_panel_input" min="1" name="monthCount" value="${monthCount}"/>
                </c:if>
                <c:if test="${empty monthCount}">
                    <input type="number" class="average_panel_input" name="monthCount" min="1"/>
                </c:if>
            </div>
            <input type="submit" class="average_panel_btn" value="Show"/>
        </form>
        <c:forEach var="entry" items="${averageSalaries.entrySet()}">
            <div class="salary_inner">
                <span class="item">${entry.getKey()}</span>
                <span>${entry.getValue() / 100} BYN</span>
            </div>
        </c:forEach>
    </div>
</body>
</html>