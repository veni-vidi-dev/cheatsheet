<%--

    Author veni.vidi.dev (veni.vidi.dev@gmail.com)

--%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Activities</title>
</head>
<body>

    <a href="logout">LogOut</a>

    <h2 class="hello-title">Number of activities: ${activities.size()}</h2>
    <table>
        <c:forEach
            items="${activities}"
            var="userActivity"
        >
            <tr>
                <td>
                    <c:out value="${userActivity.firstName}" />
                </td>
                <td>
                    <c:out value="${userActivity.lastName}" />
                </td>
                <td>
                    <c:out value="${userActivity.activityName}" />
                </td>
                <td>
                    <c:out value="${userActivity.start}" />
                </td>
                <td>
                    <c:out value="${userActivity.end}" />
                </td>
            </tr>
        </c:forEach>
    <table>
    <a href="addusertoactivity">Add User To Activity</a>
</body>
</html>