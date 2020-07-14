<%--

    Author veni.vidi.dev (veni.vidi.dev@gmail.com)

--%>
<!DOCTYPE html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
</head>
<body>

<a href="activities.jsp">View All</a><br/>

<h3>Login</h3>
<form:form action="login" method="post" modelAttribute="user">
    <table>
        <tr>
            <td><form:label path="username">Username: </form:label></td>
            <td>
                <form:input path="username"/>
            </td>
        </tr>
        <tr>
            <td><form:label path="password">Password: </form:label></td>
            <td>
                <form:input path="password" style="width:155px"/>
            </td>
        </tr>

        <tr><td colspan="2"><input type="submit" value="Login"/></td></tr>
    </table>
</form:form>

</body>
</html>