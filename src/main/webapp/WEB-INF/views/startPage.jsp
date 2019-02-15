<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<html>
<body>
<sec:authorize access="hasRole('ROLE_NURSE')">
    <h4> <a href="../nurse/patients/">Домашня сторінка</a> </h4>
</sec:authorize>
<sec:authorize access="hasRole('ROLE_DOCTOR')">
    <h4> <a href="../doctor/patients/">Домашня сторінка</a> </h4>
</sec:authorize>
<sec:authorize access="hasRole('ROLE_PATIENT')">
    <h4> <a href="../patient/homepage">Домашня сторінка</a> </h4>
</sec:authorize>
</body>

<h4> <a href="../logout">Вийти</a> </h4>
</html>