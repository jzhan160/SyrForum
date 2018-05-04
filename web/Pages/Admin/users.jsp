<%--
////////////////////////////////////////////////////////////////////
// users.jsp   show all the users for admin                       //
// ver 1.0                                                        //
// Author: Jiacheng Zhang                                         //
////////////////////////////////////////////////////////////////////
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <meta charset="UTF-8">
	<link rel="stylesheet" type="text/css" href="Pages/HomePage/css/common.css" />
	<link rel="stylesheet" type="text/css" href="Pages/Admin/css/mainpage.css" />
    <link rel="stylesheet" type="text/css" href="Pages/Admin/css/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="Pages/Admin/css/paper-dashboard.css" />
    <meta name="viewport" content="width=device-width initial-scale=1">
    <script src="Pages/Admin/js/jquery-3.3.1.js"></script>
	<title>User</title>
</head>

<style>
.header {
    background:url("Pages/Admin/img/Syracuse.jpg")  center top;
}
</style>
<body>
    <div class = "header">
        <!-- <img src = "mainpage-img/Syracuse.jpg" width="100%"> -->
        <div class="headertext">
            <h1>Syracuse Forum</h1>
            <h2>An Online Community of Second Hand Market</h2>
        </div>
    </div>
	<div class="m_header">
		<div class="m_container">
			<div class="m_nav-bar">
			</div>
		</div>
	</div>
	<div class="wrapper">
		<div class="sidebar" data-background-color="white" data-active-color="danger">
			<div class="sidebar-wrapper">	
				<ul class="nav">
					<li>
						<a href="<%=request.getContextPath()%>/DispatcherServlet?method=admin">
							<i class="ti-panel"></i>
							<p>Dashboard</p>
						</a>
					</li>
					<li class="active">
						<a href="<%=request.getContextPath()%>/DispatcherServlet?method=showUsers">
							<i class="ti-panel"></i>
							<p>User Profile</p>
						</a>
					</li>
					<li>
						<a href="<%=request.getContextPath()%>/DispatcherServlet?method=showItems">
							<i class="ti-panel"></i>
							<p>Post Profile</p>
						</a>
					</li>
					<li>
						<a  href="<%=request.getContextPath()%>/DispatcherServlet?method=logout">
							<i class="ti-panel"></i>
							<p>Log out</p>
						</a>
					</li>
				</ul>
			</div>
		</div>
		
		<div class="main-panel">
			<div class="panel-body">
				<c:if test="${!empty users}">
						<c:forEach items = "${users}" var = "user">
							<div class="contentList">
								<div class="panel panel-default">
									<div class="panel-body">
										<h4><a class="title" href="#">${user.getUserName()}</a></h4>
										<p>
											<span class="overView">User ID: &nbsp </span><span class="overView">${user.getId()}</span>
											<span class="overView">&nbsp Email: &nbsp </span><span class="overView">${user.getEmail()}</span>
											<span class="overView">&nbsp Birthday: &nbsp </span><span class="overView">${user.getUserBirthday()}</span>
											<span class="overView">&nbsp Gender: &nbsp </span><span class="overView">${user.getGender()}</span>
											<span><a action="action" type="button">&nbsp Delete</a></span>
											</span>
										</p>
									</div>
								</div>
							</div>
						</c:forEach>
				</c:if>
			</div>
		</div>
	</div>
	    
	<div class = "w_footer">
        <div class="w_foot_copyright">Tanming Cui Design <span>|</span>
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