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
<title>Insert title here</title>
	<!-- ueditor的配置文件 -->
	<script type="text/javascript" charset="utf-8" src="static/ueditor1_4_3_3/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="static/ueditor1_4_3_3/ueditor.all.min.js"> </script>
    <script type="text/javascript" charset="utf-8" src="static/ueditor1_4_3_3/lang/zh-cn/zh-cn.js"></script>
	<link rel="stylesheet" type="text/css" href="static/easyui-1.5/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="static/easyui-1.5/themes/icon.css">
	<script type="text/javascript" src="static/easyui-1.5/jquery.min.js"></script>
	<script type="text/javascript" src="static/easyui-1.5/jquery.easyui.min.js"></script>
</head>
<body>
    <div id="p" class="easyui-panel" title="修改博客" style="padding: 10px">
	<table cellspacing="20px">
		<tr>
			<td width="80px">博客标题：</td>
			<td>
				<input type="text" id="title" name="title" style="width: 400px"/>
			</td>
		</tr>
		<tr>
			<td>所属类别：</td>
			<td>
				<select class="easyui-combobox" style="width: 154px" id="blogTypeId" name="" 
					editable="false" panelHeight="auto">
					<option value="">请选择博客类别...</option>
					<c:forEach var="item" items="${blogType }">
						<option value="${item.id }">${item.typename }</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td valign="top">博客内容：</td>
			<td>
 				 <script id="editor" name="content" type="text/plain" style="width:100%; height:300px;"></script>
			</td>
		</tr>
		<tr>
			<td>关键字：</td>
			<td>
				<input type="text" id="keyWord" name="keyWord" style="width: 400px"/>&nbsp;(多个关键字中间用空格隔开)
			</td>
		</tr>
		<tr>
			<td></td>
			<td>
				<a href="javascript:submitData()" class="easyui-linkbutton" data-options="iconCls:'icon-submit'">修改博客</a>
			</td>
		</tr>
	</table>
</div>
    
    
    <script type="text/javascript">
   		var ue = UE.getEditor('editor');
		ue.addListener('ready', function() {
			UE.ajax.request('${pageContext.request.contextPath}/admin/blog/findBlogById.do',{
				method:'post',
				async:false,
				data:{'id':'${param.id}'},// 获得参数中的ID值  
				onsuccess:function(result) {
					var blog = eval('(' + result.responseText + ')');
					console.log(blog);
					$('#title').val(blog.title);
					$('#keyWord').val(blog.keyword);
					$('#blogTypeId').combobox('setValue', blog.blogType.id);
					UE.getEditor('editor').setContent(blog.content);
				}
			});
		});
		
		function submitData() {
			var title = $("#title").val();
			var blogTypeId = $("#blogTypeId").combobox("getValue")
			var content = UE.getEditor('editor').getContent()
			var keyWord = $("#keyWord").val();
			
			if(title==null || title==''){
				alert("请输入标题！");
			}else if(blogTypeId==null || blogTypeId==''){
				alert("请选择博客类别！");
			}else if(content==null || content==''){
				alert("请填写内容！");
			}else{
				$.post("${pageContext.request.contextPath}/admin/blog/saveAndUpdate.do",{
					'id':'${param.id}',
					'title':title,
					'typeid':blogTypeId,
					'contentNoTag':UE.getEditor('editor').getContentTxt(),
					'content':content,
					'summary':UE.getEditor('editor').getContentTxt().substr(0,155),
					'keyword':keyWord},function(result){
					if(result.success){
						alert("博客修改成功！");
					}else{
						alert("博客修改失败！");
					}
				},"json");
			}
		}
    </script>
    
</body>
</html>