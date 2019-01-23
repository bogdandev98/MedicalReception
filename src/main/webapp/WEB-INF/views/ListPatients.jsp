<%@page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <title>Example</title>
</head>
<body>

<h2>Patients</h2>

<c:forEach items="${patients}" var="patient" >
    <h4> <a href="../${patient.id}">${patient.firstName} ${patient.secondName}</a> </h4>
</c:forEach>

<c:url value="/logout" var="logoutUrl" />
<form action="${logoutUrl}" method="post" id="logoutForm">
    <input type="hidden" name="${_csrf.parameterName}"
           value="${_csrf.token}" />
</form>

</body>
</html>
