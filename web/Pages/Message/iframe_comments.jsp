<%--
////////////////////////////////////////////////////////////////////
// iframe_comment.jsp   test message sending function             //
// ver 1.0                                                        //
// Author: Group Work                                             //
////////////////////////////////////////////////////////////////////
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width">
  <script src="js\jquery-3.3.1.js"></script>
  <!-- <script type="text/javascript" src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.7/jquery.validate.min.js"></script> -->
  <!-- <script src="js\message.js"></script> -->
  <link rel="icon" href="img/ddd.ico">
  <link rel="stylesheet" href="css\message.css">
  <title>Message Box</title>
</head>


<body>
    <div class="title_line">
      <p>Comments received:</p>
    </div>

    <c:if test="${empty noteList}">
        <h1>No new message!</h1>

    </c:if>

    <c:forEach items="${noteList}" var="note">

        <div class=message_wraper>
            <!-- link to the post -->
<%--            <a  href="/DispatcherServlet?method=item&itemId=${note.getItemID()}&userName=<%=request.getParameter("userName")%>&category=${temp.getItem().getCatID()}&author=${temp.getUserName()}&commentCount=${temp.getCommentCount()}"
                name="recent_post1"></a>--%>
            <div class="comments_received">
                <div class="profile_img">
                    <img src="img/profile1.jpg" />
                </div>
                <div class="sender_info">
                    <p>${note.getAuthor()}</p>
                    <p>${note.getCreateTime()}</p>
                </div>
                <div class="content_box">
                    <p class="commnet_content">${note.getCommentContent()}</p>
                    <p class="original_post">Item: ${note.getItemName()}</p>
                </div>
            </div>
        </div>
    </c:forEach>


</body>
</html>
