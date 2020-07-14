<%--

    Author veni.vidi.dev (veni.vidi.dev@gmail.com)

--%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add User Form</title>
</head>
<body>

<a href="activities">View All</a><br/>

<h3>Add User To Activity</h3>
<form:form action="add" method="post" modelAttribute="userActivity">
    <table>
        <tr>
            <td><form:label path="activityId">Activity: </form:label></td>
            <td>
                <form:select path="activityId" name="activity" style="width:155px">
                    <c:forEach items="${activities}" var="activity">
                        <form:option value="${activity.id}">
                            ${activity.name}
                        </form:option>
                      </c:forEach>
                </form:select>
            </td>
        </tr>
        <tr>
            <td><form:label path="userId">User: </form:label></td>
            <td>
                <form:select path="userId" name="user" style="width:155px">
                    <c:forEach items="${users}" var="user">
                        <form:option value="${user.id}">
                            ${user.firstName} ${user.lastName}
                        </form:option>
                      </c:forEach>
                </form:select>
            </td>
        </tr>

        <tr><td colspan="2"><input type="submit" value="Add"/></td></tr>
    </table>
</form:form>

</body>
</html>