<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="/WEB-INF/views/form-header.jsp" %>
<div class="slogan container container--90">
<div class="slogan--item">


<div class="help--slides active" data-id="1">
    <div class="slogan--steps-title"> Profil uzytkownika ${user.firstName}</div>
    <div class="slogan--steps-title">Szczegóły:</div>
    <div><br></div>
    <ul class="help--slides-items">
    <li>
        <div class="col">
            <div class="subtitle">Email:</div>
            <div class="title">${user.email}</div>
        </div>

    </li>
    <li>
        <div class="col">
            <div class="subtitle">Imie:</div>
            <div class="title">${user.firstName}</div>
        </div>
    </li>
    <li>
        <div class="col">
            <div class="subtitle">Nazwisko:</div>
            <div class="title">${user.lastName}</div>
        </div>
    </li>
    <li>
        <div class="col">
            <div class="subtitle">Hasło:</div>
           <div class="title"> *********</div>
        </div>
    </li>

</ul>
</div>
</div>
</div>
</header>
<div class="slogan container container--90">
<div class="slogan--steps">
<div class="slogan--item">
    <div class="help--slides active" data-id="1">
        <ul class="help--slides-items">
            <li>
                <div class="col">
    <p data-step="1" class="active"><a class="btn next-step" href="/app/profile/edit">Edytuj Profil</a>
        <a class="btn next-step" href="/app/profile/password">Zmień hasło</a></p>
                </div>
            </li>
            </ul>

</div>
</div>
</div>


<%@ include file="/WEB-INF/views/footer.jsp" %>