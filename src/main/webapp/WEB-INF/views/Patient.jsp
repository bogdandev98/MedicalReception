<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Пацієнт</title>
</head>
<body>
    Прізвище: ${patient.secondName}<br>
    Імя: ${patient.firstName}<br>
    Дата народження: ${patient.birthday}<br>
    <c:if test="${patient.sex}">
    Стать: чоловік<br>
    </c:if>
    <c:if test="${!patient.sex}">
        Стать: жінка<br>
    </c:if>
    Номер телефону: ${patient.mobilePhone}<br>

    <sec:authorize access="hasRole('ROLE_NURSE')">
        <c:forEach items="${files}" var="file">
            <h4> <a href="../nurse/downloadFile/${file.id}">Скачати файл ${file.fileName}</a>  </h4>
        </c:forEach>
        <h4> <a href="../nurse/edit/${patient.username}">Редагувати</a> </h4>
    </sec:authorize>

    <sec:authorize access="hasRole('ROLE_DOCTOR')">
        <c:forEach items="${patientForm.recipes}" var="recipe">
            <h4> ${recipe.text} </h4>
        </c:forEach>
        <c:forEach items="${files}" var="file">
            <h4> <a href="../doctor/downloadFile/${file.id}">Скачати файл ${file.fileName}</a>  </h4>
        </c:forEach>
        <c:set var="url" value="/doctor/sendRecipe"/>

        <form:form class="form-horizontal" method="POST" modelAttribute="recipe" action="${url}" accept-charset="UTF-8">
            <table>
                <tr>
                    <td><form:hidden path="id"/></td>
                </tr>
                <tr>
                    <td><form:hidden path="patient"/></td>
                </tr>
                <tr>
                    <td><form:hidden path="doctor"/></td>
                </tr>
                <tr>
                    <td><form:label path="text">Рецепт</form:label></td>
                    <td><form:textarea path="text"/></td>
                </tr>

                <tr>
                    <td><input type="submit" value="Submit"/></td>
                </tr>
            </table>
        </form:form>

        <h4> <a href="../doc/edit/${patient.username}">Редагувати</a> </h4>
    </sec:authorize>

    <sec:authorize access="hasRole('ROLE_PATIENT')">
        <c:forEach items="${patientForm.recipes}" var="recipe">
            <h4> ${recipe.text} </h4>
        </c:forEach>
        <h4> <a href="../patient/edit">Редагувати</a> </h4>
        <h4> <a href="../patient/reception">Записатись на прийом</a> </h4>
    </sec:authorize>


    <h4> <a href="../logout">Вийти</a> </h4>
</body>
</html>
