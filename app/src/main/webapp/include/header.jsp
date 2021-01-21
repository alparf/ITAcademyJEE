<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="by.academy.constant.PageConstant" %>
<header class="header">
    <div class="header_inner">
        <div class="header_logo">IT-Academy</div>
        <nav class="nav">
            <a class="nav_link" href="<%= PageConstant.INDEX %>">Welcome</a>
            <a class="nav_link" href="<%= PageConstant.AVERAGE %>">Average</a>
            <c:if test="${not empty user}">
                <a class="nav_link" href="<%= PageConstant.HOME %>">${user.userName}</a>
                <a class="nav_link" href="LogoutController">Sign out</a>
            </c:if>
            <c:if test="${empty user}">
                <a class="nav_link" href="<%= PageConstant.LOGIN %>">Sign in</a>
            </c:if>
        </nav>
    </div>
</header>
