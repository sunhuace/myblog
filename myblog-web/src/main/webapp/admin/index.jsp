<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>博客管理后台</title>
<link rel="stylesheet" type="text/css"
	href="static/easyui-1.5/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="static/easyui-1.5/themes/icon.css">
<script type="text/javascript" src="static/easyui-1.5/jquery.min.js"></script>
<script type="text/javascript"
	src="static/easyui-1.5/jquery.easyui.min.js"></script>

<script type="text/javascript">
	function openTab(text, url, icon) {
		if($('#tabs').tabs('exists', text)) {
			$('#tabs').tabs('select',text);
		} else {
			var content = "<iframe frameborder=0 scrolling='auto' style='width:100%;height:100%'" 
						 +"src='admin/"+url+"'></iframe>";
			$('#tabs').tabs('add',{
				title:text,
				iconCls:icon,
				closable:true,
				content:content
			})
		}
	}
</script>
</head>
<body class="easyui-layout">
	<div data-options="region:'north'" style="height: 50px"></div>
	<div data-options="region:'south',split:false" style="height: 20px;"></div>
	<div data-options="region:'west',split:true" title="主菜单"
		style="width: 210px;">
		<div id="aa" class="easyui-accordion" style="height: 100%;">
			<div title="Title1" data-options="iconCls:'icon-save'"
				style="overflow: auto; padding: 10px;">
				<a href="javascript:openTab('写博客','writeBlog.jsp','icon-add')">写博客</a></br>
				<a href="javascript:openTab('博客列表','blogMannage.jsp','icon-add')">博客列表</a><br>
				<a href="javascript:openTab('博客类别','blogTypeManage.jsp','icon-add')">博客类别</a>
				<a href="javascript:openTab('评论管理','commentManage.jsp','icon-add')">评论管理</a>
			</div>
			<div title="Title2"
				data-options="iconCls:'icon-reload',selected:true"
				style="padding: 10px;">content2</div>
			<div title="Title3">content3</div>
		</div>
	</div>
	<div data-options="region:'center'"
		style="width: 100%">
		<div id="tabs" class="easyui-tabs" style="width: 100%; height: 100%;">
			<div title="欢迎页面" style="padding: 20px; display: none;" data-options="closable:true"></div>
		</div>
	</div>
</body>
</html>