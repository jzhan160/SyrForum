<%--
////////////////////////////////////////////////////////////////////
// admin.jsp   the main page for adminstertor                     //
// ver 1.0                                                        //
// Author: Jiacheng Zhang                                         //
////////////////////////////////////////////////////////////////////
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
	<link rel="stylesheet" type="text/css" href="Pages/HomePage/css/common.css" />
	<link rel="stylesheet" type="text/css" href="Pages/Admin/css/mainpage.css" />
	<link rel="stylesheet" type="text/css" href="Pages/Admin/css/bootstrap.min.css" />

    <link rel="stylesheet" type="text/css" href="Pages/Admin/css/paper-dashboard.css" />
	<meta name="viewport" content="width=device-width initial-scale=1">
	<script src="Pages/Admin/js/jquery-3.3.1.js"></script>
	<script src="Pages/HomePage/js/jquery-3.3.1.js"></script>

	<title>Admin</title>

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
					<li class="active">
						<a href="<%=request.getContextPath()%>/DispatcherServlet?method=admin">
							<i class="ti-panel"></i>
							<p>Dashboard</p>
						</a>
					</li>
					<li>
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
				<div class="contentList">
					<div class="panel panel-default">
						<div class="panel-body">
							<h4><a class="title" href="#">Profile</a></h4>
							<p class="overView"><span>Current User Number: &nbsp </span><span>10</span></p>
							<p class="overView"><span>Current Post Number: &nbsp </span><span>20</span></p>
						</div>
					</div>                                    
				</div>
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