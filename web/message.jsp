<%--
////////////////////////////////////////////////////////////////////
// message.jsp   just for test                                    //
// ver 1.0                                                        //
// Author: Group Work                                             //
////////////////////////////////////////////////////////////////////
--%>
<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
  <title>Message Notification</title>
</head>

<body>
${message}

<a href="<%=request.getContextPath()%>/ListFileServlet">Show Pictures</a>
</body>
</html>