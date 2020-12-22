<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link href="resources/css/general.css" rel="stylesheet">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@400;700&display=swap" rel="stylesheet">
    <title>Login</title>
</head>
<body>
    <c:import url="header.jsp" />
    <div class="div-login">
        <div class="inner">
            <form id="loginForm" action="LoginController" method="POST">
                <label>User</label>
                <input type="text" name="userName"/>
                <label>Password</label>
                <input type="password" name="password"/>
                <input type="submit" value="Sign in" id="signIn"/>
            </form>
        </div>
    </div>
</body>
</html>