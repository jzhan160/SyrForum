<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width">
  <script src="Pages/Message/js/jquery-3.3.1.js"></script>
  <script src="Pages/Message/js/message.js"></script>
  <link rel="icon" href="Pages/Message/img/ddd.ico">
  <link rel="stylesheet" href="Pages/Message/css/message.css">
  <title>Messages</title>
</head>

<script>
    function onClick() {
        document.getElementById("messageClick").action = "<%=request.getContextPath()%>/DispatcherServlet?method=messages&userName=<%=request.getParameter("userName")%>";
        document.getElementById("messageClick").submit();
    }
</script>

<body>
    <!-- title and header background -->
      <header>
        <div>
          <h1>Syracuse Forum</h1>
          <h2>An Online Community of Second Hand Market</h2>
        </div>
      </header>

    <!-- navigation part -->
      <section class="navi">
        <div class="nav-bar">
          <ul class="nav-menu">
              <li><a href="/DispatcherServlet?method=main&userName=<%=request.getParameter("userName")%>">HOME</a></li>
              <li><a href="">SECTION</a>
                  <ul>
                      <li><a href="<%=request.getContextPath()%>/DispatcherServlet?method=view&userName=<%=request.getParameter("userName")%>&category=books">Used Books</a></li>
                      <li><a href="<%=request.getContextPath()%>/DispatcherServlet?method=view&userName=<%=request.getParameter("userName")%>&category=cars">Used Cars</a></li>
                      <li><a href="<%=request.getContextPath()%>/DispatcherServlet?method=view&userName=<%=request.getParameter("userName")%>&category=furniture">Used Furniture</a></li>
                  </ul>
              </li>
            <%--  <li><a href="message.jsp">Message Box</a>--%>
            </li>
            <li class="userconsole"><img src="Pages/Message/img/user.svg" >
              <ul>
                  <ul>
                      <li><a href="<%=request.getContextPath()%>/DispatcherServlet?method=profile&userName=<%=request.getParameter("userName")%>">My Profile</a></li>
                      <li><a href="<%=request.getContextPath()%>/DispatcherServlet?method=showfavorites&userName=<%=request.getParameter("userName")%>">My Favorites</a></li>
                      <li><a href="<%=request.getContextPath()%>/DispatcherServlet?method=logout">Logout</a></li>
                  </ul>
              </ul>
            </li>
            <!-- <li class="userconsole"><img src="img/notifications-bell-button.svg" ></li> -->
            <li class="userconsole">

              	<div class="button">
                <form class="button" id="messageClick" action="#"
                      method="post">
                    <img src="Pages/HomePage/img/notifications-bell-button.svg" id="messages" onclick="onClick()"/>
                </form>
            </div>
              </a>
            </li>

            <li class="userconsole"><img src="Pages/Message/img/magnifier.svg" >
                  <ul>
                    <li>
                          <div class="w_search">
                              <form class="w_searchbox" method="post" action="<%=request.getContextPath()%>/DispatcherServlet?method=search&userName=<%=request.getParameter("userName")%>">
                                  <input type="text" placeholder="search" name="keyword"/>
                                  <button type="submit">Search</button>
                              </form>
                            </div>
                    </li>
                </ul>
                </li>
          </ul>
        </div>
      </section>
      <section class="message">

          <div class="message_section">
              <p>My Message Box</p>
          <%--<ul>
          <li id="comments_iframe"><a >Comments</a></li>
          <li id="messages_iframe"><a >Messages</a></li>
          </ul>--%>
          </div>
          <div class="message_content">

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
                                  <img src="Pages/Message/img/profile1.jpg" />
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

          </div>


      </section>
      <div class="clearfix"></div >


  <footer>
    <p>Copyright (c) 2018 Copyright Holder All Rights Reserved.</p>
  </footer>

</body>
</html>
