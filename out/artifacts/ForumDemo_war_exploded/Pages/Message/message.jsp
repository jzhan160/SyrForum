<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
              <li><a href=" ">HOME</a></li>
              <li><a href="">SECTION</a>
                  <ul>
                      <li><a href="Forum UsedBook.html">Used Books</a></li>
                      <li><a href="Forum UsedCar.html">Used Cars</a></li>
                      <li><a href="Forum UsedFurniture.html">Used Furniture</a></li>
                      <li><a href="mainpage.html">Syracuse Life</a></li>
                      <li><a href="mainpage.html">People</a></li>
                      <li><a href="mainpage.html">Join Us</a></li>
                  </ul>
              </li>
              <li><a href="message.jsp">Message Box</a>
            </li>
            <li class="userconsole"><img src="Pages/Message/img/user.svg" >
              <ul>
                <li><a href="myprofile.html">My Profile</a></li>
                <!-- <li><a href="#">Setting</a></li> -->
                <li><a href="#">Logout</a></li>
              </ul>
            </li>
            <!-- <li class="userconsole"><img src="img/notifications-bell-button.svg" ></li> -->
            <li class="userconsole">
                <a href="message.jsp">
              	<div class="notification_button">
              		<img src="Pages/Message/img/notifications-bell-button.svg" />
              		<span class="button__badge">2</span>
              	</div>
              </a>
            </li>

            <li class="userconsole"><img src="Pages/Message/img/magnifier.svg" >
                  <ul>
                    <li>
                          <div class="w_search">
                              <div class="w_searchbox">
                                <input type="text" placeholder="search" />
                                <button>Search</button>
                              </div>
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
            <ul>
              <li id="comments_iframe"><a >Comments</a></li>
              <li id="messages_iframe"><a >Messages</a></li>
            </ul>
          </div>

          <div class="message_content">
            <iframe src="Pages/Message/iframe_comments.html" frameBoder=0></iframe>
          </div>
          <div class="latest_contact">
            <p>Latest Contact:</p>
          </div>
      </section>
      <div class="clearfix"></div >


  <footer>
    <p>Copyright (c) 2018 Copyright Holder All Rights Reserved.</p>
  </footer>

</body>
</html>
