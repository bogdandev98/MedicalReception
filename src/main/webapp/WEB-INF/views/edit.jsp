<%--
  Created by IntelliJ IDEA.
  User: Bodya
  Date: 1/28/2019
  Time: 7:22 PM
  To change this template use File | Settings | File Templates.
--%>

<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
    <title>Редагувати</title>
</head>
<body>

<br />

<sec:authorize access="hasRole('ROLE_NURSE')">
    <c:set var="url" value="/nurse/save"/>
</sec:authorize>
<sec:authorize access="hasRole('ROLE_PATIENT')">
    <c:set var="url" value="/patient/save"/>
</sec:authorize>

<form:form class="form-horizontal" method="POST" modelAttribute="patientForm" action="${url}" accept-charset="UTF-8">

    <table>
       <%--"${patientForm.username}"--%>
        <tr>
            <td><form:hidden path="username" /></td>
        </tr>
        <tr>
            <td><form:label path="firstName">Імя</form:label></td>
            <td><form:input path="firstName"/></td>
        </tr>
        <tr>
            <td><form:label path="secondName">Прізвище</form:label></td>
            <td><form:input path="secondName"/></td>
        </tr>

        <tr>
            <td><form:label path="birthday">Дата народженн</form:label></td>
            <td><form:input path="birthday"/></td>
        </tr>
        <tr>
            <td><form:radiobutton path="sex" value="true" /> Male</td>
        <td><form:radiobutton path="sex" value="false"/> Female</td>
        </tr>
        <tr>
            <td><form:label path="mobilePhone">Номер телефону</form:label></td>
            <td><form:input path="mobilePhone"/></td>
        </tr>
        <tr>
            <td><input type="submit" value="Submit"/></td>
        </tr>
    </table>
</form:form>

<%--<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />--%>
</body>
</html>


