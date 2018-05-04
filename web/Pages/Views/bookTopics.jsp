<%--
////////////////////////////////////////////////////////////////////
// bookTopics.jsp   show detailed information for one item        //
// ver 1.0                                                        //
// Author: Group Work                                             //
////////////////////////////////////////////////////////////////////
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Forum Category Used Books</title>
	</head>

	<link href="Pages/Views/plugin/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="Pages/HomePage/css/common.css" />
	<link href="logo.ico" rel="shortcut icon" />
	<script src="Pages/Views/plugin/jquery.min.js"></script>
	<script src="Pages/Views/plugin/largethumb.js"></script>
	<script src="Pages/Views/plugin/bootstrap/js/bootstrap.min.js"></script>

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
						<!--滚动图开始-->
						<div class="panel panel-default">
							<div id="w_carousel" class="carousel slide w_carousel" data-ride="carousel">
								<!-- Indicators -->
								<ol class="carousel-indicators">
									<li data-target="#w_carousel" data-slide-to="0" class="active"></li>
									<li data-target="#w_carousel" data-slide-to="1"></li>
								</ol>

								<!-- Wrapper for slides -->
								<div class="carousel-inner" role="listbox">
									<div class="item active">
										<img src="Pages/Views/img/slider/used books 1.jpg" alt="...">
										<div class="carousel-caption">
											<h1>Used Books</h1>
											<h3>Explore Desired Books in a Cheaper Price!</h3>
										</div>
									</div>
									<div class="item">
										<img src="Pages/Views/img/slider/used books 2.jpg" alt="...">
										<div class="carousel-caption">
										    <h1>Used Books</h1>
											<h3>Sell Your Old Books in a Desired Price!</h3>
										</div>	
									</div>
								</div>

								<!-- Controls Privious & Next-->
								<a class="left carousel-control" href="#w_carousel" role="button" data-slide="prev">
									<span class="glyphicon glyphicon-chevron-left"></span>
									<span class="sr-only">Previous</span>
								</a>
								<a class="right carousel-control" href="#w_carousel" role="button" data-slide="next">
									<span class="glyphicon glyphicon-chevron-right"></span>
									<span class="sr-only">Next</span>
								</a>
							</div>
						</div>
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
						<h3> <%=request.getAttribute( "itemName")%></h3>
						<ul class="tools">
							<li><span class="glyphicon glyphicon-user" aria-hidden="true"><%=request.getAttribute("author")%></span><p></p></li>
							<li><span class="glyphicon glyphicon-eye-open" aria-hidden="true">Reading:<%=request.getAttribute("readingTimes")%></span><p></p></li>
							<li><span class="glyphicon glyphicon-comment" aria-hidden="true">Comments:<%=request.getAttribute("commentCount")%></span><p></p></li>
							<li><span class="glyphicon glyphicon-time" aria-hidden="true"><%=request.getAttribute("createTime")%></span></li>
						</ul>
						<div class="panel panel-default">
							<div class="panel-body">
								<div class="contentleft">
									<p class="overView">Name: <%=request.getAttribute( "itemName")%></p>
									<p class="overView">Description: <%=request.getAttribute("itemDesc")%></p>
									<p class="overView">Address: <%=request.getAttribute("address")%></p>
									<p class="overView">Contact info: <%=request.getAttribute("contact")%></p>
									<p class="overView">Price: <%=request.getAttribute("price")%></p>


									<!-- Favorite -->
									<a id = "hrefForFav" href="<%=request.getContextPath()%>/DispatcherServlet?method=favorite&userName=<%=request.getAttribute("userName")%>&itemId=<%=request.getAttribute("itemId")%>&author=<%=request.getAttribute("author")%>&category=<%=request.getAttribute("category")%>&commentCount=<%=request.getAttribute("commentCount")%>&favorite=add" >
										<button type="button" id ="butForFav" >
											Favorite</button>
									</a>

									<script>
                                        if(<%=request.getAttribute("favExist")%>){
                                            document.getElementById("butForFav").innerHTML = "unfavorite";
                                            document.getElementById("hrefForFav").href = "<%=request.getContextPath()%>/DispatcherServlet?method=favorite&userName=<%=request.getAttribute("userName")%>&itemId=<%=request.getAttribute("itemId")%>&author=<%=request.getAttribute("author")%>&category=<%=request.getAttribute("category")%>&commentCount=<%=request.getAttribute("commentCount")%>&favorite=del";
										}
									</script>

								</div>
								<div class="contentImage_large">
									<div class="row">
										<a href="#" class="works-grid">
											<ul>
												<c:if test="${!empty imagePath}">
													<li><img id="main" src="<%=request.getAttribute("imagePath")%>" alt="..." /></li>
												</c:if>
											</ul>
										</a>
									</div>
								</div>

							</div>
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
												<p><span class="glyphicon glyphicon-time" aria-hidden="true"></span>${comment.getValue().getCreateTime().substring(0,16)}</p >
											</div>
											<p class="text">${comment.getValue().getContent()}</p >
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
							<form action="<%=request.getContextPath()%>/DispatcherServlet?method=addComment&itemId=<%=request.getAttribute("itemId")%>&userName=<%=request.getParameter("userName")%>&category=<%=request.getParameter("category")%>&author=<%=request.getAttribute("author")%>&commentCount=<%=Integer.parseInt((String) request.getAttribute("commentCount"))+1%>" method="post">
								<textarea placeholder="contents" name="contents" required=""></textarea>
								<input type="submit" value="SUBMIT">
							</form>
						</div>
					</div>
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
        var $backToTopEle = $('<a href="javascript:void(0)" class="Hui-iconfont toTop" title="返回顶部" alt="返回顶部" style="display:none">^</a>').appendTo($("body")).click(function() {
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