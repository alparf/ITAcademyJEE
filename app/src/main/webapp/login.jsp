<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <c:import url="/include/head.html" />
    <title>Login</title>
</head>
<body>
    <c:import url="/include/header.jsp" />
    <div class="sign-in shadow">
        <div class="sign-in_title"><h2>Sign in and go!</h2></div>
        <form class="sign-in_form" action="LoginController" method="POST">
            <label>User</label>
            <input type="text" name="userName" placeholder="User name" required/>
            <label>Password</label>
            <input type="password" name="password" placeholder="Password" required/>
            <input type="submit" value="Sign in" id="signIn"/>
            <p class="exception">${exceptionMessage}</p>
        </form>
    </div>
</body>
</html>
