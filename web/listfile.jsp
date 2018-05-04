<%--
////////////////////////////////////////////////////////////////////
// listfile.jsp   just for test                                   //
// ver 1.0                                                        //
// Author: Group Work                                             //
////////////////////////////////////////////////////////////////////
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>Pictures for downloading</title>
</head>

<body>
<!-- 遍历Map集合 -->
<c:forEach var="map" items="${fileNameMap}">
    <c:url value="/DownLoadServlet" var="image">
        <c:param name="filename" value="${map.key}"></c:param>
    </c:url>
    ${me.value}<a href="${image}">Download</a>
    <br/>
</c:forEach>
</body>
</html>