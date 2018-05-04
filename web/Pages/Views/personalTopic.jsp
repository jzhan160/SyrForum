<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>

<head>
    <meta charset="UTF-8">
    <title>Recent Posts</title>
</head>

<link href="Pages/Views/plugin/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="Pages/HomePage/css/common.css" />
<link href="logo.ico" rel="shortcut icon" />
<script src="Pages/Views/plugin/jquery.min.js"></script>
<script src="Pages/Views/plugin/largethumb.js"></script>
<script src="Pages/Views/plugin/bootstrap/js/bootstrap.min.js"></script>
<!--<script type="text/javascript" src="plugin/jquery.page.js"></script>-->
<!--<script src="js/common.js"></script>-->
<script src="Pages/Views/page.js"></script>
<script>
    function onClick() {
        document.getElementById("messageClick").action = "<%=request.getContextPath()%>/DispatcherServlet?method=messages&userName=<%=request.getParameter("userName")%>";
        document.getElementById("messageClick").submit();
    }
</script>
<body>
<div class="w_header">
    <div class="container">
        <div class="w_header_top nav-bar">
					<span class="w_header_nav">
					<ul class="nav-menu">
						<li><a href="<%=request.getContextPath()%>/DispatcherServlet?method=main&userName=<%=request.getParameter("userName")%>" class="active">Home</a></li>
						<li><a href="">Section</a>
						     <ul>
                                <li><a href="<%=request.getContextPath()%>/DispatcherServlet?method=view&userName=<%=request.getParameter("userName")%>&category=books">Used Books</a></li>
                                <li><a href="<%=request.getContextPath()%>/DispatcherServlet?method=view&userName=<%=request.getParameter("userName")%>&category=cars">Used Cars</a></li>
                                <li><a href="<%=request.getContextPath()%>/DispatcherServlet?method=view&userName=<%=request.getParameter("userName")%>&category=furniture">Used Furniture</a></li>
                            </ul>
						</li>
					</ul>
				    </span>
            <span class="w_header_userconsole">
					<ul class="nav-menu">
						<li><img src="Pages/Views/img/user.svg" />
						    <ul>
                                <li><a href="<%=request.getContextPath()%>/DispatcherServlet?method=profile&userName=<%=request.getParameter("userName")%>">My Profile</a></li>
                                <li><a href="<%=request.getContextPath()%>/DispatcherServlet?method=showfavorites&userName=<%=request.getParameter("userName")%>">My Favorites</a></li>
                                <li><a href="<%=request.getContextPath()%>/DispatcherServlet?method=logout">Log Out</a></li>
                            </ul>
						</li>
							<li>
							<div class="button">
							<form class="button" id="messageClick" action="#"
                                  method="post">
								    <img src="Pages/HomePage/img/notifications-bell-button.svg" id="messages" onclick="onClick()"/>
							</form>
							</div>
						</li>
                        <li><img src="Pages/Views/img/magnifier.svg" />
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
                    <a href="<%=request.getContextPath()%>/Pages/Views/newPost.jsp?userName=<%=request.getParameter("userName")%>">
                        <strong>Create New Post</strong>
                    </a>
                </div>
            </div>
        </div>

        <!-- single -->
        <div class="panel panel-default">
            <div class="panel-body">
                <h3>${topic.getTitle()}</h3>

                <div class="panel panel-default">
                    <c:forEach items="${items}" var="item">
                    <div class="panel-body">
                        <div class="contentleft">
                                <h4><a class="title" href="">Name: ${item.getItemName()}</a></h4>
                                <p class="overView">Description: ${item.getDescription()}</p>
                                <p class="overView">Price: ${item.getPrice()}</p>
                        </div>
                        <div class="contentImage_large">
                            <div class="row">
                                <a href="#" class="works-grid">
                                    <ul>
                                        <c:if test="${item.getImagePath() != ''}">
                                                <li><img id="main" src="${item.getImagePath()}" alt="..." /></li>
                                        </c:if>
                                    </ul>
                                </a>
                            </div>
                        </div>

                    </div>
                    </c:forEach>
                </div>
            </div>
        </div>
        <c:if test="${!empty comments}">
            <div class="panel panel-default">
                <div class="panel-body">
                    <div class="agileits_three_comments">
                        <h3>Comments</h3>
                    </div>

                    <c:forEach items = "${comments}" var = "comment">
                        <div class="panel panel-default">
                            <div class="contentList">
                                <div class="panel-body innercontentbox">
                                    <div class="info">
                                        <h4><span class="glyphicon glyphicon-user" aria-hidden="true"></span>${comment.getKey().replaceAll(" .+$", "")}</h4>
                                        <p><span class="glyphicon glyphicon-time" aria-hidden="true"></span>${comment.getValue().getCreateTime().substring(0,16)}</p>
                                    </div>
                                    <p class="text">${comment.getValue().getContent()}</p>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </c:if>

        <div class="panel panel-default">
            <div class="panel-body">
                <div class="leave_comment">
                    <h3><span>Reply</span></h3>
                    <form action="<%=request.getContextPath()%>/DispatcherServlet?method=addComment&itemId=<%=request.getAttribute("itemId")%>&userName=<%=request.getParameter("userName")%>" method="post">
                        <textarea placeholder="contents" name="contents" required=""></textarea>
                        <input type="submit" value="SUBMIT">
                    </form>
                </div>
            </div>
        </div>
        <!-- //single -->

    </div>
</div>
<div class="w_foot">
    <div class="w_foot_copyright">Yang Du Design <span>|</span>
        <a target="_blank" href="http://www.syr.edu/" rel="nofollow">SU Home Page</a>
    </div>
</div>
</body>
<script type="text/javascript">
    var $backToTopEle = $('<a href="javascript:void(0)" class="Hui-iconfont toTop" title="Top" alt="Top" style="display:none">^</a>').appendTo($("body")).click(function() {
        $("html, body").animate({ scrollTop: 0 }, 120);
    });
    var backToTopFun = function() {
        var st = $(document).scrollTop(),
            winh = $(window).height();
        (st > 0) ? $backToTopEle.show(): $backToTopEle.hide();
        /*IE6下的定位*/
        if(!window.XMLHttpRequest) {
            $backToTopEle.css("top", st + winh - 166);
        }
    };

    $(function() {
        $(window).on("scroll", backToTopFun);
        backToTopFun();
    });
</script>
</html>