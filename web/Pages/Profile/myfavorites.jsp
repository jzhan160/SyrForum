<%--
////////////////////////////////////////////////////////////////////
// myfavortes.jsp   list all favorites                            //
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
  <script src="Pages/Profile/js/jquery-3.3.1.js"></script>
  <script src="Pages/Profile/js/myprofile.js"></script>
  <link rel="icon" href="img/ddd.ico">
  <link rel="stylesheet" href="Pages/Profile/css/myprofile.css">
  <title>My Profile</title>
</head>
<script>
    function onClick() {
        document.getElementById("messageClick").action = "<%=request.getContextPath()%>/DispatcherServlet?method=messages&userName=<%=request.getParameter("userName")%>";
        document.getElementById("messageClick").submit();
    }
</script>

<body>
  <div class="frame">
    <header>
      <div>
        <h1>Syracuse Forum</h1>
        <h2>An Online Community of Second Hand Market</h2>
      </div>
    </header>


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
          </li>
          <li class="user_console"><img src="Pages/Profile/img/user.svg" >
              <ul>
                  <li><a href="<%=request.getContextPath()%>/DispatcherServlet?method=profile&userName=<%=request.getParameter("userName")%>">My Profile</a></li>
                  <li><a href="<%=request.getContextPath()%>/DispatcherServlet?method=showfavorites&userName=<%=request.getParameter("userName")%>">My Favorites</a></li>
                  <li><a href="<%=request.getContextPath()%>/DispatcherServlet?method=logout">Logout</a></li>
              </ul>
          </li>
          <li class="user_console">
            <div class="notification_button">
                <form class="button" id="messageClick" action="#"
                      method="post">
                    <img src="Pages/HomePage/img/notifications-bell-button.svg" id="messages" onclick="onClick()"/>
                </form>
            </div>
          </li>

          <li class="user_console"><img src="Pages/Profile/img/magnifier.svg" >
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
    <!-- <div class="clearfix"></div > -->

    <section>
      <div class="profile_frame">
        <div class="containner">

          <div class="recent_posts">
            <p><b>My favorites</b></p>

              <c:forEach items="${itemsInfo}" var="temp">
                  <div>
                      <p>${temp.getItem().getItemName()}</p>
                      <a  href="/DispatcherServlet?method=item&itemId=${temp.getItem().getId()}&userName=<%=request.getParameter("userName")%>&category=${temp.getItem().getCatID()}&author=${temp.getUserName()}&commentCount=${temp.getCommentCount()}"
                         name="recent_post1">Open</a>
                  </div>
              </c:forEach>
              </div>

        </div>
      </div>
    </section>

    <!-- hidden popup window -->
    <div id="interface">
      <p>Select your profile image:</p>
      <div>
        <img class="profileselection" src="Pages/Profile/img/profile1.jpg">
        <img class="profileselection" src="Pages/Profile/img/profile2.jpg">
        <img class="profileselection" src="Pages/Profile/img/profile3.jpg">
        <img class="profileselection" src="Pages/Profile/img/profile4.jpg">
        <img class="profileselection" src="Pages/Profile/img/profile5.jpg">
        <img class="profileselection" src="Pages/Profile/img/profile6.jpg">
      </div>
      <div>
        <input type="radio"  name="profile" value="1" checked>
        <input type="radio"  name="profile" value="2">
        <input type="radio"  name="profile" value="3">
        <input type="radio"  name="profile" value="4">
        <input type="radio"  name="profile" value="5">
        <input type="radio"  name="profile" value="6">
      </div>

      <input type="button" value="Cancel">
      <input type="button" value="Select">


    </div>

    <!-- <div class="clearfix"></div > -->
    <footer>
      <p>Copyright (c) 2018 Copyright Holder All Rights Reserved.</p>
    </footer>

  </body>
  </html>
