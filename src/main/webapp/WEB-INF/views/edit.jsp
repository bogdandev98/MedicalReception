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
<!doctype html>
<html>
<head>
    <title>Редагувати</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="utf-8">
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <link rel="stylesheet" href="https://resources/demos/style.css">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <script>
        $( function() {
            $( "#datepicker" ).datepicker();
        } );
    </script>

</head>
<body>
<sec:authorize access="hasRole('ROLE_NURSE')">
    <c:set var="url" value="/nurse/save"/>
</sec:authorize>
<sec:authorize access="hasRole('ROLE_PATIENT')">
    <c:set var="url" value="/patient/save"/>
</sec:authorize>

<form:form class="form-horizontal" method="POST" modelAttribute="patientForm" action="${url}" accept-charset="UTF-8">

    <table>
        <tr>
            <td><form:hidden path="username" /></td>
        </tr>
        <tr>
            <td><form:label path="firstName">Імя</form:label></td>
            <td><form:input path="firstName"/></td>
        </tr>
        <tr>
            <td><form:label path="secondName">Прізвище</form:label></td>
            <td><form:input  path="secondName"/></td>
        </tr>

        <tr>
            <td><form:label path="birthday">Дата народженн</form:label></td>
            <td><form:input path="birthday" id="datepicker"/></td>
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

<form method="POST" action="/nurse/uploadFile/${patientForm.username}" enctype="multipart/form-data">

    <input type="file" name="file" /><br/><br/>
    <input type="submit" value="Submit" />
</form>
</body>
</html>


