<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML>
<html>
<head>
<title> ${title }</title>
<base href="<%=basePath%>">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="static/css/blog.css" rel="stylesheet" type="text/css"/>
<link href="static/css/bootstrap.css" rel="stylesheet" type="text/css"
	media="all">
<script src="static/js/jquery.min.js"></script>
<style type="text/css">
</style>
</head>
<body>

	<!-- 头部 -->
	<jsp:include page="common/header.jsp" flush="true"></jsp:include>
	<!-- 页面导航菜单 -->
	<jsp:include page="common/menu.jsp" flush="true"></jsp:include>
	<!-- 主体内容 -->
	<div class="container">
		<div class="row">
			<div class="col-md-9">
				<jsp:include page="${content}" flush="true"></jsp:include>
			</div>
			<div class="col-md-3">
				<jsp:include page="common/rslider.jsp" flush="true"></jsp:include>
			</div>
		</div>
	</div>
	<!-- 底部说明 -->
	<jsp:include page="common/footer.jsp" flush="true"></jsp:include>
	
</body>
</html>