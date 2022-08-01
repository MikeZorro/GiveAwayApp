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

            <div class="slogan--steps">
                <div class="slogan--steps-title">Wystarczą 4 proste kroki:</div>
                <ul class="slogan--steps-boxes">
                    <li>
                        <div><em>1</em><span>Wybierz rzeczy</span></div>
                    </li>
                    <li>
                        <div><em>2</em><span>Spakuj je w worki</span></div>
                    </li>
                    <li>
                        <div><em>3</em><span>Wybierz fundację</span></div>
                    </li>
                    <li>
                        <div><em>4</em><span>Zamów kuriera</span></div>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</header>

<section class="form--steps">
    <div class="form--steps-instructions">
        <div class="form--steps-container">
            <h3>Ważne!</h3>
            <p data-step="1" class="active">
                Uzupełnij szczegóły dotyczące Twoich rzeczy. Dzięki temu będziemy
                wiedzieć komu najlepiej je przekazać.
            </p>
            <p data-step="2">
                Uzupełnij szczegóły dotyczące Twoich rzeczy. Dzięki temu będziemy
                wiedzieć komu najlepiej je przekazać.
            </p>
            <p data-step="3">
                Wybierz jedną, do
                której trafi Twoja przesyłka.
            </p>
            <p data-step="4">Podaj adres oraz termin odbioru rzeczy.</p>
        </div>
    </div>

    <div class="form--steps-container">
        <div class="form--steps-counter">Krok <span>1</span>/4</div>
        <form:form method="post" modelAttribute="donation">
            <!-- STEP 1: class .active is switching steps -->
            <div data-step="1" class="active">
                <H1 style="color:crimson"><form:errors path="*">This form has errors, please try again</form:errors></H1>

                <h3>Zaznacz co chcesz oddać:</h3>

                <c:forEach var="category" items="${categoryList}">
                    <div class="form-group form-group--checkbox">
                        <label>
                            <form:checkbox value="${category.id}" path="categoryList" class="checkbox"/>
                            <span class="checkbox"></span>
                            <span class="description">${category.name}</span>

                        </label>
                    </div>
                </c:forEach>
                <p style="color:crimson"><form:errors path="categoryList"/></p>
                <div class="form-group form-group--buttons">
                    <button type="button" class="btn next-step">Dalej</button>
                </div>
            </div>


            <!-- STEP 2 -->
            <div data-step="2">
                <h3>Podaj liczbę 60l worków, w które spakowałeś/aś rzeczy:</h3>

                <div class="form-group form-group--inline">
                    <label>
                        Liczba 60l worków:
                        <form:input type="number" name="bags" step="1" min="1" path="quantity"/>
                        <p style="color:crimson"><form:errors path="quantity"/></p>
                    </label>
                </div>

                <div class="form-group form-group--buttons">
                    <button type="button" class="btn prev-step">Wstecz</button>
                    <button type="button" class="btn next-step">Dalej</button>
                </div>
            </div>


            <!-- STEP 3 -->
            <div data-step="3">
                <h3>Wybierz organizacje, której chcesz pomóc:</h3>
                <c:forEach var="institution" items="${institutions}">
                    <div class="form-group form-group--checkbox">
                        <label>
                            <form:radiobutton value="${institution.id}" path="institution"/>
                            <span class="checkbox radio"></span>
                            <span class="description">
                  <div class="title">Fundacja"${institution.name}"</div>
                  <div class="subtitle">
                    Cel i misja: ${institution.description}
                  </div>
                </span>

                        </label>
                    </div>
                </c:forEach>
                <p style="color:crimson"><form:errors path="institution"/></p>

                <div class="form-group form-group--buttons">
                    <button type="button" class="btn prev-step">Wstecz</button>
                    <button type="button" class="btn next-step">Dalej</button>
                </div>
            </div>

            <!-- STEP 4 -->
            <div data-step="4">
                <h3>Podaj adres oraz termin odbioru rzecz przez kuriera:</h3>

                <div class="form-section form-section--columns">
                    <div class="form-section--column">
                        <h4>Adres odbioru</h4>
                        <div class="form-group form-group--inline">
                            <label> Ulica <form:input type="text" name="address" path="street"/> </label>
                            <p style="color:crimson"><form:errors path="street"/></p>
                        </div>

                        <div class="form-group form-group--inline">
                            <label> Miasto <form:input type="text" name="city" path="city"/> </label>
                            <p style="color:crimson"><form:errors path="city"/></p>
                        </div>

                        <div class="form-group form-group--inline">
                            <label>
                                Kod pocztowy <form:input type="text" name="postcode" path="zipCode"/>
                            </label>
                            <p style="color:crimson"><form:errors path="zipCode"/></p>
                        </div>
                    </div>

                    <div class="form-section--column">
                        <h4>Termin odbioru</h4>
                        <div class="form-group form-group--inline">
                            <label> Data <form:input type="date" name="data" path="pickUpDate"/> </label>
                            <p style="color:crimson"><form:errors path="pickUpDate"/></p>

                        </div>

                        <div class="form-group form-group--inline">
                            <label> Godzina <form:input type="time" name="time" path="pickUpTime"/> </label>
                            <p style="color:crimson"><form:errors path="pickUpTime"/></p>
                        </div>

                        <div class="form-group form-group--inline">
                            <label>
                                Uwagi dla kuriera
                                <form:textarea name="more_info" rows="5"
                                               path="pickUpComment"></form:textarea><form:errors path="pickUpComment"/>

                            </label>
                        </div>
                    </div>
                </div>
                <div class="form-group form-group--buttons">
                    <button type="button" class="btn prev-step">Wstecz</button>
                    <input type="submit" ></input>
                </div>
            </div>

        </form:form>
    </div>
</section>


<%@ include file="/WEB-INF/views/footer.jsp" %>