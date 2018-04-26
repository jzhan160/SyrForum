<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>

<head>
    <meta charset="UTF-8">
    <title>Search Items</title>
</head>

<link href="Pages/Views/plugin/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="Pages/HomePage/css/common.css"/>
<link href="logo.ico" rel="shortcut icon"/>
<script src="Pages/Views/plugin/jquery.min.js"></script>
<script src="Pages/Views/plugin/largethumb.js"></script>
<script src="Pages/Views/plugin/bootstrap/js/bootstrap.min.js"></script>
<!--<script type="text/javascript" src="plugin/jquery.page.js"></script>-->
<!--<script src="js/common.js"></script>-->
<script src="Pages/Views/page.js"></script>

<body>
<div class="w_header">
    <div class="container">
        <div class="w_header_top nav-bar">
					<span class="w_header_nav">
					<ul class="nav-menu">
						<li><a href="<%=request.getContextPath()%>/DispatcherServlet?method=main&userName=<%=request.getParameter("userName")%>"
                               class="active">Home</a></li>
						<li><a href="">Section</a>
						     <ul>
                                <li><a href="<%=request.getContextPath()%>/DispatcherServlet?method=view&userName=<%=request.getAttribute("userName")%>&category=books">Used Books</a></li>
                                <li><a href="<%=request.getContextPath()%>/DispatcherServlet?method=view&userName=<%=request.getAttribute("userName")%>&category=cars">Used Cars</a></li>
                                <li><a href="<%=request.getContextPath()%>/DispatcherServlet?method=view&userName=<%=request.getAttribute("userName")%>&category=furniture">Used Furniture</a></li>
                            </ul>
						</li>
					</ul>
				    </span>
            <span class="w_header_userconsole">
					<ul class="nav-menu">
						<li><img src="Pages/Views/img/user.svg"/>
						    <ul>
                                <li><a href="<%=request.getContextPath()%>/DispatcherServlet?method=profile&userName=<%=request.getAttribute("userName")%>">My Profile</a></li>
                                <li><a href="#">Setting</a></li>
                                <li><a href="<%=request.getContextPath()%>/DispatcherServlet?method=logout">Log Out</a></li>
                            </ul>
						</li>
							<li>
							<div class="button">
								<img src="Pages/HomePage/img/notifications-bell-button.svg"/>
								<span class="button__badge">2</span>
							</div>
						</li>
                        <li><img src="Pages/Views/img/magnifier.svg"/>
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
<div class="w_container">
    <div class="container">
        <div class="row w_main_row">
            <div class=" w_main_left"> <!-- cancel col-lg-9 col-md-9 -->
                <div class="newpost">
                    <a href="<%=request.getContextPath()%>/Pages/Views/newPost.jsp?userName=<%=request.getAttribute("userName")%>">
                        <strong>Create New Post</strong>
                    </a>
                </div>
            </div>
        </div>

        <div class="panel-body">
            <!--Posts Start-->
            <c:if test="${empty items}">
                <h1>No result!</h1>

            </c:if>

            <c:if test="${!empty items}">
                <div class="contentList">
                    <c:forEach items="${items}" var="temp">
                        <!-- post contents -->
                        <div class="panel panel-default">
                            <div class="panel-body">
                                <h4><a class="title"
                                       href="/DispatcherServlet?method=item&itemId=${temp.getId()}&userName=<%=request.getAttribute("userName")%>&category=${temp.getCatID()}">${temp.getItemName()}</
                                    a>
                                </h4>
                                <p class="overView"></p>
                                <p><span class="count"><i class="glyphicon glyphicon-user"></i><a href="#">admin</a></span> <span class="count"><i
                                        class="glyphicon glyphicon-eye-open"></i>Reading:666</span><span
                                        class="count"><i class="glyphicon glyphicon-comment"></i>Comments:18</span><span
                                        class="count"><i class="glyphicon glyphicon-time"></i>2018-04-01</span></p>
                            </div>
                        </div>

                    </c:forEach>
                </div>
            </c:if>
            <!--Posts Ends-->

        </div>

    </div>
</div>
<div class="w_foot">
    <div class="w_foot_copyright">Yang Du Design <span>|</span>
        <a target="_blank" href="http://www.syr.edu/" rel="nofollow">SU Home Page</a>
    </div>
</div>
</body>
<script type="text/javascript">
    var $backToTopEle = $('<a href="javascript:void(0)" class="Hui-iconfont toTop" title="Top" alt="Top" style="display:none">^</a>').appendTo($("body")).click(function () {
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