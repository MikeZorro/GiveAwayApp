<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="/WEB-INF/views/form-header.jsp" %>

<div class="slogan container container--90">
    <div class="slogan--item">
        <h1>
            Oddaj rzeczy, których już nie chcesz<br/>
            <span class="uppercase">potrzebującym</span>
        </h1>

    </div>
</div>
</header>
<section class="form--steps">

    <div class="form--steps-container">
        <div data-step="5" class="active">
            <h3>Podsumowanie Twojej darowizny</h3>

            <div class="summary">
                <div class="form-section">
                    <h4>Oddajesz:</h4>
                    <form:form method="post" modelAttribute="donation">
                    <ul>
                        <li>
                            <span class="icon icon-bag"></span>
                            <span class="summary--text"
                            >${donation.quantity} worki <c:forEach var="category"
                            items="${categoryList}">${category.name}, </c:forEach></span>
                        </li>
                        <li>
                            <span class="icon icon-hand"></span>
                            <span class="summary--text"
                            >Dla fundacji "${donation.institution.name}"</span
                            >
                        </li>
                    </ul>
                </div>

                <div class="form-section form-section--columns">
                    <div class="form-section--column">
                        <h4>Adres odbioru:</h4>
                        <ul>
                            <li>${donation.street}</li>
                            <li>${donation.city}</li>
                            <li>${donation.zipCode}</li>
                        </ul>
                    </div>

                    <div class="form-section--column">
                        <h4>Termin odbioru:</h4>
                        <ul>
                            <li>${donation.pickUpDate}</li>
                            <li>${donation.pickUpTime}</li>
                            <li>${donation.pickUpComment}</li>
                        </ul>
                    </div>
                </div>
            </div>
            <br>
            <br>


            <div class="form-group form-group--buttons">
                <a class="btn prev-step" href="/app/donate"> Powrot do formularza</a>
                <button type="submit" class="btn">Potwierdzam</button>
                </form:form>

            </div>
        </div>
</section>
</div>
</section>


<%@ include file="/WEB-INF/views/footer.jsp" %>