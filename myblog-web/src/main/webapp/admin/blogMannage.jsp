<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<title>博客管理页面</title>
	<!-- ueditor的配置文件 -->
	<link rel="stylesheet" type="text/css" href="static/easyui-1.5/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="static/easyui-1.5/themes/icon.css">
	<script type="text/javascript" src="static/easyui-1.5/jquery.min.js"></script>
	<script type="text/javascript" src="static/easyui-1.5/jquery.easyui.min.js"></script>
	
	<script type="text/javascript" charset="utf-8">
		function formatTitle(row, val) {
			return "<a target='_blank' href='${pageContext.request.contextPath}/blog/articles/" + val.id +".html'>"+val.title+"</a>";
		}
		function formatBlogType(row, val) {
			return row.typename;
		}
		function searchBlog(){
			$("#dg").datagrid('load',{
				"title":$("#s_title").val()
			});
		}
		function deleteBlog() {
			var selectRow = $('#dg').datagrid('getSelections');
			if(selectRow.length == 0) {
				$messager.alert('选择删除的数据');
			}
			var ids = [];
			for(var i = 0;i < selectRow.length;i++) {
				ids.push(selectRow[i].id);
			}
			console.log(ids);
			ids = ids.join(',');
			$.messager.confirm('系统提示','确定删除？' + selectRow.length + '条数据', function(r){
				if(r) {
					$.post('${pageContext.request.contextPath}/admin/blog/deleteBlog.do',{ids:ids},function(result){
						if(result.success) {
							$.messager.alert('系统提示','删除成功');
							$("#dg").datagrid("reload");
						} else {
							$.messager.alert('系统提示','删除失败');
						}
					},'json');
				}
			});
		}
		function openBlogModifyTab() {
			var selectRow = $('#dg').datagrid('getSelections');
			if(selectRow.length <= 0) {
				$.messager.alert('系统提示', '选择一条数据');
			} else if(selectRow.length >= 2) {
				$.messager.alert('系统提示', '只能选择一条数据进行修改');
			} else {
				var row = selectRow[0];
				window.parent.openTab('修改博客','modifyBlog.jsp?id='+row.id,'icon-writeblog');
			}
		}
	</script>
</head>
<body style="margin: 1px">
		<table id="dg" title="博客管理" class="easyui-datagrid" 
		  	fitColumns="true" pagination="true" rownumbers="true"
		  	url="${pageContext.request.contextPath}/admin/blog/listBlog.do" fit="true" toolbar="#tb">
		  <thead>
		  	<tr>
		  		<th field="cb" checkbox="true" align="center"></th>
		  		<th field="id" width="20" align="center">编号</th>
		  		<th field="title" width="150" align="center" formatter="formatTitle">标题</th>
		  		<th field="releasedate" width="50" align="center">发布日期</th>
		  		<th field="blogType" width="50" align="center" formatter="formatBlogType">博客类型</th>
		  	</tr>
		  </thead>
		</table>
		<div id="tb">
			<div>
				<a href="javascript:openBlogModifyTab()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
				<a href="javascript:deleteBlog()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
				&nbsp;标题&nbsp;<input type="text" id="s_title" size="20" onkeydown="if(event.keyCode==13) searchBlog()"/>
					<a href="javascript:searchBlog()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
			</div>
		</div>
</body>
</html>