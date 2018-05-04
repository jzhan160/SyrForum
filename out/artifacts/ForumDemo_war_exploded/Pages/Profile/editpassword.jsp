<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width">
  <script src="Pages/Profile/js/jquery-3.3.1.js"></script>
  <script src="Pages/Profile/js/edit_password.js"></script>
  <link rel="shortcut icon" href="">
  <link rel="stylesheet" href="Pages/Profile/css/newpost.css">
  <title>Edit Personal Profile</title>
</head>
<script>
    function onClick() {
        document.getElementById("messageClick").action = "<%=request.getContextPath()%>/DispatcherServlet?method=messages";
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
          <li class="userconsole"><img src="Pages/Profile/img/user.svg" >
            <ul>
              <li><a href="<%=request.getContextPath()%>/DispatcherServlet?method=profile&userName=<%=request.getParameter("userName")%>">My Profile</a></li>
              <li><a href="#">Setting</a></li>
              <li><a href="<%=request.getContextPath()%>/DispatcherServlet?method=logout">Logout</a></li>
            </ul>
          </li>
          <!-- <li class="userconsole"><img src="img/notifications-bell-button.svg" ></li> -->
          <li class="userconsole">
            <div class="notification_button">
              <form class="button" id="messageClick" action="#"
                    method="post">
                <img src="Pages/HomePage/img/notifications-bell-button.svg" id="messages" onclick="onClick()"/>
              </form>
            </div>
          </li>
          <li class="userconsole"><img src="Pages/Profile/img/magnifier.svg" >
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

    <section class="edit_password">
      <form id="pw_form" action="<%=request.getContextPath()%>/DispatcherServlet?method=editPassword&userName=<%=request.getParameter("userName")%>" method = "post">
        <div>

          <div>
            <span>New password:</span>
            <input type="password" name="password" >

          </div>
          <div>
          <span></span>
          <span id="first_warning" >Please provide your password.</span>
        </div>
          <div>
            <span>Retype new password:</span>
            <input type="password" name="retypepwd" >

            </div>
            <div>
              <span></span>
              <span id="second_warning" >Please confirm your password.</span>
            </div>
          </div>

          <div class="buttons">
            <input type="submit" id = "submitbtn" value="Submit" >
            <input type="button" id = "calcenlbtn" value="Cancel">
          </a>
          </div >
        </form>
      </section>


      <footer>
        <p>Copyright (c) 2018 Copyright Holder All Rights Reserved.</p>
      </footer>
    </div>
  </body>
  </html>
