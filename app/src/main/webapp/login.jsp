<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <c:import url="/include/head.html" />
    <title>Login</title>
</head>
<body>
    <c:import url="/include/header.html" />
    <div class="div-login">
        <div class="inner">
            <form id="loginForm" action="LoginController" method="POST">
                <label>User</label>
                <input type="text" name="userName" value="admin"/>
                <label>Password</label>
                <input type="password" name="password" value="admin"/>
                <input type="submit" value="Sign in" id="signIn"/>
            </form>
        </div>
    </div>
</body>
</html>