<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
     <link href="resources/css/general.css" rel="stylesheet">
    <title>Login</title>
</head>
<body>
    <c:import url="header.jsp" />
    <form id="loginForm" action="LoginController">
        <input type="text" name="userName" placeholder="user name"/>
        <input type="password" name="password" placeholder="password"/>
    </form>
    <button form="loginForm">sign in</button>
</body>
</html>