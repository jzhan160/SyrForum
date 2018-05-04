<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width">
        <script src="js\jquery-3.3.1.js"></script>
        <script src="js\newpost.js"></script>
        <link rel="shortcut icon" href="">
        <link rel="stylesheet" href="css\styles1.css">
        <link rel="stylesheet" type="text/css" href="Pages/HomePage/css/common.css" />
        <title>Create New Post</title>
    </head>
    <script>
        function onClick() {
            document.getElementById("messageClick").action = "<%=request.getContextPath()%>/DispatcherServlet?method=messages";
            document.getElementById("messageClick").submit();
        }
    </script>
    <body>
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
                    <li><a href="#">SECTION</a>
                        <ul>
                            <li><a href="<%=request.getContextPath()%>/DispatcherServlet?method=view&userName=<%=request.getParameter("userName")%>&category=books">Used Books</a></li>
                            <li><a href="<%=request.getContextPath()%>/DispatcherServlet?method=view&userName=<%=request.getParameter("userName")%>&category=cars">Used Cars</a></li>
                            <li><a href="<%=request.getContextPath()%>/DispatcherServlet?method=view&userName=<%=request.getParameter("userName")%>&category=furniture">Used Furniture</a></li>
                        </ul>
                    </li>
                    </li>
                    <li class="userconsole"><img src="img/user.svg" >
                        <ul>
                            <li><a href="<%=request.getContextPath()%>/DispatcherServlet?method=profile&userName=<%=request.getParameter("userName")%>">My Profile</a></li>
                            <li><a href="#">Setting</a></li>
                            <li><a href="<%=request.getContextPath()%>/DispatcherServlet?method=logout">Logout</a></li>
                        </ul>
                    </li>
                    <li class="userconsole"><form class="button" id="messageClick" action="#"
                                                  method="post">
                        <img src="Pages/HomePage/img/notifications-bell-button.svg" id="messages" onclick="onClick()"/>
                    </form></li>
                </ul>
            </div>
        </section>

        <section class="userinput">
          <%--  <form action="<%=request.getContextPath()%>/DispatcherServlet?method=post&userName=<%=request.getParameter("userName")%>" method = "post" >
                <div>
                    <span>Title</span>
                    <input type="text" name="Title">
                    <span>Address</span>
                    <input type="text" name="Address">
                    <span>Contact</span>
                    <input type="text" name="Contact">
                </div>
                <div id="plus">
                    <Button type="button" id="plusitem" >+</Button>
                    <label for="plusitem">Add a new Item.</label>
                </div>
    
                <div class="buttons">
                    <input type="submit" id = "submitbtn" value="Submit" >
                    <input action="action" type="Button" id = "calcenlbtn" value="Cancel" onclick="window.history.go(-1); return false;" />
                </div >
            </form>--%>
              <form action="<%=request.getContextPath()%>/UploadHandleServlet?userName=<%=request.getParameter("userName")%>" enctype="multipart/form-data" method="post">
                <div>
                    <span>Title</span>
                    <input type="text" name="Title">
                    <span>Address</span>
                    <input type="text" name="Address">
                    <span>Contact</span>
                    <input type="text" name="Contact">
                </div>
                <div id="plus">
                    <Button type="button" id="plusitem" >+</Button>
                    <label for="plusitem">Add a new Item.</label>
                </div>

                <div class="buttons">
                    <input type="submit" id = "submitbtn" value="Submit" >
                    <input action="action" type="Button" id = "calcenlbtn" value="Cancel" onclick="window.history.go(-1); return false;" />
                </div >
            </form>
        </section>

        <footer>
	        <p>Zhihui Hong Design <span>|</span>
    		    <a target="_blank" href="http://www.syr.edu/" rel="nofollow">SU Home Page</a>
    	    </p>
        </footer>
	    
    </body>
</html>
