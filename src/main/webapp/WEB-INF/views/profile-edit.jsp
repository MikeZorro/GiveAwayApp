<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="/WEB-INF/views/form-header.jsp" %>
<div class="slogan container container--90">
<div class="slogan--item">
    <form:form method="post" modelAttribute="user">
    <div class="help--slides active" data-id="1">
        <div class="slogan--steps-title"> Profil uzytkownika ${user.firstName}</div>
        <div class="slogan--steps-title">Szczegóły:</div>
        <div><br></div>
        <ul class="help--slides-items">
            <li>
                <div class="col">
                    <div class="subtitle">Email:</div>
                    <div class="form-group"><form:input  path="email" placeholder="${user.email}"/>
                        <p style="color:crimson"><form:errors path="email"/></p>
                    </div>
                </div>

            </li>
            <li>
                <div class="col">
                    <div class="subtitle">Imie:</div>
                    <div class="form-group"><form:input  path="firstName" placeholder="${user.firstName}"/>
                        <p style="color:crimson"><form:errors path="firstName"/></p>
                    </div>
                </div>
            </li>
            <li>
                <div class="col">
                    <div class="subtitle">Nazwisko:</div>
                    <div class="form-group"><form:input  path="lastName" placeholder="${user.lastName}"/>
                        <p style="color:crimson"><form:errors path="lastName"/></p>
                    </div>
                </div>
            </li>
            <form:hidden  path="id" placeholder="${user.id}"/>

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
                            <p data-step="1" class="active"><a class="btn next-step" href="/app/profile/view">Porzuć zmiany</a>
                                <input type="submit" value="Zapisz Zmiany" class="btn prev-step" href="/app/profile/password"></p>
                        </div>
                    </li>
                </ul>

            </div>
        </div>
    </div>
    </form:form>
<%@ include file="/WEB-INF/views/footer.jsp" %>