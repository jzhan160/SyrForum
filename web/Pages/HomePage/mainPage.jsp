<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
    <link rel="stylesheet" type="text/css" href="Pages/HomePage/css/common.css"/>
    <link rel="stylesheet" type="text/css" href="Pages/HomePage/css/mainpage.css"/>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width initial-scale=1">
    <script src="Pages/HomePage/js/jquery-3.3.1.js"></script>
</head>

<style>
    .header {
        background: url("Pages/HomePage/img/Syracuse.jpg")   center top;
    }
</style>
<script>
    function onClick() {
        document.getElementById("messageClick").action = "<%=request.getContextPath()%>/DispatcherServlet?method=messages&userName=<%=request.getParameter("userName")%>";
            document.getElementById("messageClick").submit();
    }
</script>
<body>
<div class="header">
    <!-- <img src = "mainpage-img/Syracuse.jpg" width="100%"> -->
    <div class="headertext">
        <h1>Syracuse Forum</h1>
        <h2>An Online Community of Second Hand Market</h2>
    </div>
</div>
<div class="m_header">
    <div class="m_container">
        <div class="m_nav-bar">
				<span class="m_header_nav">
				<ul class="nav-menu">
					<li><a href="" class="active">Home</a></li>
					<li><a href="">Section</a>
						<ul>
                            <li><a href="<%=request.getContextPath()%>/DispatcherServlet?method=view&userName=<%=request.getParameter("userName")%>&category=books">Used Books</a></li>
                            <li><a href="<%=request.getContextPath()%>/DispatcherServlet?method=view&userName=<%=request.getParameter("userName")%>&category=cars">Used Cars</a></li>
                            <li><a href="<%=request.getContextPath()%>/DispatcherServlet?method=view&userName=<%=request.getParameter("userName")%>&category=furniture">Used Furniture</a></li>
                        </ul>
					</li>
				</ul>
			    </span>
            <span class="m_header_userconsole">
				<ul class="nav-menu">

					<li><img src="Pages/HomePage/img/user.svg" style="opacity:0;"/>
					</li>
			    	<li><img src="Pages/HomePage/img/user.svg"/>
					    <ul>
                            <li><a href="<%=request.getContextPath()%>/DispatcherServlet?method=profile&userName=<%=request.getParameter("userName")%>">My Profile</a></li>
                            <li><a href="<%=request.getContextPath()%>/DispatcherServlet?method=showfavorites&userName=<%=request.getParameter("userName")%>">My Favorites</a></li>
                            <li><a href="<%=request.getContextPath()%>/DispatcherServlet?method=logout">Log Out</a></li>
                        </ul>
					</li>
                    <li>
							<form class="button" id="messageClick" action="#"
                                  method="post">
								<img src="Pages/HomePage/img/notifications-bell-button.svg" id="messages" onclick="onClick()"/>
							</form>
                    </li>

                    <li><img src="Pages/HomePage/img/magnifier.svg"/>
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
                </span>
        </div>
    </div>
</div>
<div class="row">
    <div class="leftcolumn">
        <div class="leftcard">
            <a href="<%=request.getContextPath()%>/DispatcherServlet?method=view&userName=<%=request.getParameter("userName")%>&category=books">
                <img src="Pages/HomePage/img/used books.jpg" width="100%">
                <div class="container">
                    <h3>Used Books</h3>
                    <p>5 views | 3 posts</p>
                    <p>Explore your desired book</p>
                </div>
            </a>
        </div>
        <div class="leftcard">
            <a href="<%=request.getContextPath()%>/DispatcherServlet?method=view&userName=<%=request.getParameter("userName")%>&category=cars">
                <img src="Pages/HomePage/img/usedcars.jpg" width="100%">
                <div class="container">
                    <h3>Used Cars</h3>
                    <p>5 views | 3 posts</p>
                    <p>Buy or sell here</p>
                </div>
            </a>
        </div>
        <div class="leftcard">
            <a href="">
                <img src="Pages/HomePage/img/SyracusePeople.jpg" width="100%">
                <div class="container">
                    <h3>People</h3>
                    <p>5 views | 3 posts</p>
                    <p>Meet your friends here</p>
                </div>
            </a>
        </div>
    </div>
    <div class="rightcolumn">
        <div class="rightcard">
            <a href="<%=request.getContextPath()%>/DispatcherServlet?method=view&userName=<%=request.getParameter("userName")%>&category=furniture">
                <img src="Pages/HomePage/img/used furniture.jpg" width="100%">
                <div class="container">
                    <h3>Used Furniture</h3>
                    <p>5 views | 3 posts</p>
                    <p>Trade your used furniture here</p>
                </div>
            </a>
        </div>
        <div class="rightcard">
            <a href="#">
                <img src="Pages/HomePage/img/Syracuselife.jpg" width="100%">
                <div class="container">
                    <h3>Syracuse Life</h3>
                    <p>5 views | 3 posts</p>
                    <p>Events and dome tickets</p>
                </div>
            </a>
        </div>
        <div class="rightcard">
            <a href="#">
                <img src="Pages/HomePage/img/joinus.jpg" width="100%">
                <div class="container">
                    <h3>Join Us</h3>
                    <p>5 views | 3 posts</p>
                    <p>Join our community</p>
                </div>
            </a>
        </div>
    </div>
</div>
<div class="w_footer">
    <div class="w_foot_copyright">Tanming Cui Design <span>|</span>
        <a target="_blank" href="http://www.syr.edu/" rel="nofollow">SU Home Page</a>
    </div>
</div>

</body>
<script type="text/javascript">
    var $backToTopEle = $('<a href="javascript:void(0)" class="Hui-iconfont toTop" title="TOP" alt="TOP" style="display:none">^</a>').appendTo($("body")).click(function () {
        $("html, body").animate({scrollTop: 0}, 120);
    });
    var backToTopFun = function () {
        var st = $(document).scrollTop(),
            winh = $(window).height();
        (st > 0) ? $backToTopEle.show() : $backToTopEle.hide();
        /*IE6下的定位*/
        if (!window.XMLHttpRequest) {
            $backToTopEle.css("top", st + winh - 166);
        }
    };
    $(function () {
        $(window).on("scroll", backToTopFun);
        backToTopFun();
    });
</script>
</html>