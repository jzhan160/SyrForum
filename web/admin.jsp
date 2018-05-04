<%--
////////////////////////////////////////////////////////////////////
// admin.jsp   just for test                                      //
// ver 1.0                                                        //
// Author: Group Work                                             //
////////////////////////////////////////////////////////////////////
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Admin</title>
</head>
<body>

<a href="<%=request.getContextPath()%>/DispatcherServlet?method=showUsers">Show users</a>
<a href="<%=request.getContextPath()%>/DispatcherServlet?method=showItems">Show Items</a>
<a href="<%=request.getContextPath()%>/DispatcherServlet?method=logout">Log Out</a>
<c:if test="${!empty users}">
    <table  id="users_table">
        <c:forEach items = "${users}" var = "user">
            <tr>
                <th>UserName: ${user.getUserName()}</th>
                <th>Email: ${user.getEmail()}</th>
                <th>Gender: ${user.getGender()}</th>
                <th>Birthday: ${user.getUserBirthday()}</th>
                <th><a href="">delete</a></th>
            </tr>
        </c:forEach>
    </table>

</c:if>
<c:if test="${!empty itemsInfo}">
<table  id="items_table">
    <c:forEach items = "${itemsInfo}" var = "itemInfo">
        <tr>
            <th><a href="/DispatcherServlet?method=item&itemId=${itemInfo.getItem().getId()}&userName=admin&category=${itemInfo.getItem().getCatID()}&author=${itemInfo.getUserName()}&commentCount=${itemInfo.getCommentCount()}">
            ItemName: ${itemInfo.getItem().getItemName()}</a></th>
            <th><a href="/DispatcherServlet?method=deleteItem&itemId=${itemInfo.getItem().getId()}&userName=admin">delete</a></th>
        </tr>
    </c:forEach>
</table>
</c:if>
</body>
</html>
