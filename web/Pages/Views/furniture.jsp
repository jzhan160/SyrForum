<%--
////////////////////////////////////////////////////////////////////
// furniture.jsp   main page for furniture category               //
// ver 1.0                                                        //
// Author: Group Work                                             //
////////////////////////////////////////////////////////////////////
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<html>

	<head>
		<meta charset="UTF-8">
		<title>Forum Category Used Furniture</title>
	</head>

	<link href="Pages/Views/plugin/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="Pages/HomePage/css/common.css" />
	<link href="logo.ico" rel="shortcut icon" />
	<script src="Pages/Views/plugin/jquery.min.js"></script>
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
					<div class=" w_main_left"> <!-- col-lg-9 col-md-9 -->
						<!--����ͼ��ʼ-->
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
										<img src="Pages/Views/img/slider/used furnitures 1.jpg" alt="...">
										<div class="carousel-caption">
											<h1>Used Furnitures</h1>
											<h3>Explore Desired Furnitures in a Cheaper Price!</h3>
										</div>
									</div>
									<div class="item">
										<img src="Pages/Views/img/slider/used furnitures 2.jpg" alt="...">
										<div class="carousel-caption">
										    <h1>Used Furnitures</h1>
											<h3>Sell Your Old Furnitures in a Desired Price!</h3>
										</div>	
									</div>
								</div>

								<!-- Controls -->
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

						<div class="panel panel-default contenttop">
							<a href="#">
								<strong>List of Sticky Posts</strong>
								<h3 class="title">First Post</h3>
								<p class="overView">Some message</p>
							</a>
						</div>
						<div class="newpost">
							<a href="<%=request.getContextPath()%>/Pages/Views/newPost.jsp?userName=<%=request.getParameter("userName")%>">
								<strong>Create New Post</strong>
							</a>
						</div>

						<div class="panel panel-default">
							<div class="panel-heading">
								<h3 class="panel-title"><strong>Newly Posts</strong></h3>
							</div>

							<div class="panel-body">
								<!--Posts Start-->
								<c:if test="${!empty itemsInfo}">
									<div class="contentList">
										<c:forEach items="${itemsInfo}" var="temp">
											<!-- post contents -->
											<div class="panel panel-default">
												<div class="panel-body">
													<h4><a class="title"
														   href="/DispatcherServlet?method=item&itemId=${temp.getItem().getId()}&userName=<%=request.getAttribute("userName")%>&category=<%=request.getAttribute("category")%>&author=${temp.getUserName()}&commentCount=${temp.getCommentCount()}">${temp.getItem().getItemName()}</
														a>
													</h4>
													<p class="overView"></p>
													<p>
														<span class="count">
														<i class="glyphicon glyphicon-user"></i>
																${temp.getUserName()}</span>
														<span class="count"><i class="glyphicon glyphicon-eye-open">

														</i>Reading:${temp.getItem().getReadingTimes()}</span><span class="count">
														<i class="glyphicon glyphicon-comment"></i>Comments:${temp.getCommentCount()}</span><span
															class="count"><i
															class="glyphicon glyphicon-time"></i>${temp.getCreateTime()}</span>
													</p>
												</div>
											</div>

										</c:forEach>
									</div>
								</c:if>
								<!--Posts Ends-->

							</div>
							<div class="center">
							    <div class="pagination">
                                    <input action="action" type="button" value="&laquo;" onclick="window.history.go(-1); return false;" />
                                    <a href="#">1</a>
                                    <a href="#">2</a>
                                    <a href="#">3</a>
                                    <a href="#">4</a>
                                    <a href="#">5</a>
                                    <a href="#">...</a>
                                    <a href="#">&raquo;</a>
                                </div>
							</div>				
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
		var $backToTopEle = $('<a href="javascript:void(0)" class="Hui-iconfont toTop" title="���ض���" alt="���ض���" style="display:none">^</a>').appendTo($("body")).click(function() {
			$("html, body").animate({ scrollTop: 0 }, 120);
		});
		var backToTopFun = function() {
			var st = $(document).scrollTop(),
				winh = $(window).height();
			(st > 0) ? $backToTopEle.show(): $backToTopEle.hide();
			/*IE6�µĶ�λ*/
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