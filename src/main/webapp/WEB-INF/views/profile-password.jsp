<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="/WEB-INF/views/form-header.jsp" %>

<div class="slogan container container--90">
<div class="slogan--item">
<form method="POST">
    <div class="help--slides active" data-id="1">
        <div class="slogan--steps-title">Edytuj hasło dla użytkownika ${loggedUser.firstName}</div>
        <div><br></div>
        <ul class="help--slides-items">
            <li>
                <div class="col">
                    <div class="subtitle">Nowe Hasło:</div>
                    <div class="form-group"><input type="password"  name="password" placeholder="new password"/>
                    </div>
                </div>

            </li>
            <li>
                <div class="col">
                    <div class="subtitle">Powtórz nowe hasło:</div>
                    <div class="form-group"><input type="password"  name="password2" placeholder="Repeat new password"/>
                    </div>
                </div>
            </li>
        </ul>
    </div>
    <p style="color:crimson">${errorMsg}</p>
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
                                <input type="submit" value="Zatwierdź zmiany" class="btn prev-step" href="/app/profile/password"></p>
                        </div>
                    </li>
                </ul>

            </div>
        </div>
    </div>
</form>
<%@ include file="/WEB-INF/views/footer.jsp" %>