<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="data_list">
	<div class="data_list_title">
		<img src="static/images/user_icon.png" /> 博主信息
	</div>
	<div class="user_image">
		<img src="static/images/${blogger.imagename}" />
	</div>
	<div class="nickName">${blogger.nickname}</div>
	<div class="userSign">(${blogger.sign})</div>
</div>

<div class="data_list">
	<div class="data_list_title">
		<img src="static/images/byType_icon.png" />按日志类别
	</div>
	<div class="datas">
		<ul>
			<c:forEach var="item" items="${blogType }">
				<li><span><a href="index.html?typeId=${item.id }">${item.typename }(${item.blogCount })</a></span></li>
			</c:forEach>
		</ul>
	</div>
</div>

<div class="data_list">
	<div class="data_list_title">
		<img src="static/images/byDate_icon.png" />按日志日期
	</div>
	<div class="datas">
		<ul>
			<c:forEach var="item" items="${blogTime }">
				<li><span><a href="index.html?releaseDateStr=${item.releaseDateStr }">${item.releaseDateStr }(${item.blogCount })</a></span></li>
			</c:forEach>
		</ul>
	</div>
</div>

<div class="data_list">
	<div class="data_list_title">
		<img src="static/images/link_icon.png" />友情链接
	</div>
	<div class="datas">
		<ul>
			<c:forEach var="item" items="${allLink }">
				<li><span><a href="${item.linkurl }" target="_blank"
						style="color: red">${item.linkname }</a></span></li>
			</c:forEach>
		</ul>
	</div>
</div>