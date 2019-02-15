<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
        <h4> <a href="../nurse/edit/${patient.username}">Редагувати</a> </h4>
    </sec:authorize>
    <sec:authorize access="hasRole('ROLE_PATIENT')">
        <h4> <a href="../patient/edit">Редагувати</a> </h4>
        <h4> <a href="../patient/reception">Записатись на прийом</a> </h4>
    </sec:authorize>


    <h4> <a href="../logout">Вийти</a> </h4>
</body>
</html>
