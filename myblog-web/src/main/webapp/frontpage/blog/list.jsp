<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<div class="data_list">
	<div class="data_list_title">
		<span class="glyphicon glyphicon-th-list"></span>&nbsp;&nbsp;最新博客
	</div>
	<div class="datas">
		<c:forEach items="${bList}" var="item">
			<ul>
				<li style="margin-bottom: 30px"><span class="date"><a
						href="blog/articles/${item.id }.html"><fmt:formatDate value="${item.releasedate }" type="date" pattern="yyyy年MM月dd日"/></a></span>
					<span class="title"><a href="blog/articles/${item.id }.html">${item.title}</a></span>
					<span class="summary">
						摘要: ${item.summary}...
					</span>
					<span class="img">
						<c:forEach items="${item.imageList}" var="imgItem">
							<a href="/blog/articles/52.html"> ${imgItem }</a> &nbsp;&nbsp; 
						</c:forEach>
					</span> 
					<span class="info">发表于 <fmt:formatDate value="${item.releasedate }" type="date" pattern="yyyy-MM-dd HH:mm"/> 阅读(${item.clickhit }) 评论(${item.replyhit })  </span>
				</li>
				<hr style="height: 5px; border: none; border-top: 1px dashed gray; padding-bottom: 10px;" />
			</ul>
		</c:forEach>
	</div>
</div>
<nav>
  <ul class="pagination">
	 ${pageCode}
  </ul>
</nav>

