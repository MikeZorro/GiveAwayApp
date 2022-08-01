<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title>Donate Stuff</title>

    <link rel="stylesheet" href="<c:url value="resources/css/style.css"/>"/>
</head>
<body>
<header>
    <nav class="container container--70">
        <ul class="nav--actions">
            <li><a href="/login" class="btn btn--small btn--without-border">Zaloguj</a></li>
            <li><a href="/register" class="btn btn--small btn--highlighted">Załóż konto</a></li>
        </ul>

        <ul>
            <li><a href="/" class="btn btn--without-border active">Start</a></li>
            <li><a href="#steps" class="btn btn--without-border">O co chodzi?</a></li>
            <li><a href="#about-us" class="btn btn--without-border">O nas</a></li>
            <li><a href="#help" class="btn btn--without-border">Fundacje i organizacje</a></li>
            <li><a href="#form--contact" class="btn btn--without-border">Kontakt</a></li>
        </ul>
    </nav>
<section class="login-page">
    <h2>Załóż konto</h2>
    <form:form method="post" modelAttribute="user">
        <div class="form-group"><form:input  path="email" placeholder="Email"/>
            <p style="color:crimson"><form:errors path="email"/></p>
        </div>
        <div class="form-group">
            <form:input  path="firstName" placeholder="First Name"/>
            <p style="color:crimson"><form:errors path="firstName"/></p>
        </div>
        <div class="form-group">
         <form:input  path="lastName" placeholder="Last Name"/>
            <p style="color:crimson"><form:errors path="lastName"/></p>
        </div>
        <div class="form-group">
            <form:password  path="password" placeholder="Password"/>
            <p style="color:crimson"><form:errors path="password"/></p>
        </div>

        <div class="form-group">
            <input type="password"  name="password2" placeholder="Repeat password"/>
            <p style="color:crimson"><form:errors path="password"/></p>
        </div>

        <div class="form-group form-group--buttons">
            <a href="/login" class="btn btn--without-border">Zaloguj się</a>
            <input type="submit" class="btn btn--without-border" value="Załóż konto"/>
        </div>
    </form:form>
</section>
<%@ include file="/WEB-INF/views/footer.jsp" %>