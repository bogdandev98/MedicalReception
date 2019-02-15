<%@page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <title>Пацієнти</title>
</head>
<body>

<h2>Пацієнти</h2>

<sec:authorize access="hasRole('ROLE_DOCTOR')">
    <c:forEach items="${patients}" var="patient" >
        <h4> <a href="../doctor/${patient.username}">${patient.firstName} ${patient.secondName}</a> </h4>
    </c:forEach>
</sec:authorize>

<sec:authorize access="hasRole('ROLE_NURSE')">
    <c:forEach items="${patients}" var="patient" >
        <h4> <a href="../nurse/${patient.username}">${patient.firstName} ${patient.secondName}</a> </h4>
    </c:forEach>
</sec:authorize>

<h4> <a href="../logout">Вийти</a> </h4>
</body>
</html>
