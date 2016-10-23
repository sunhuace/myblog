<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
	function showOtherComment() {
		$(".otherComment").show();
	}
	
	function loadimage() {
		document.getElementById("randImage").src = "frontpage/image.jsp?"
				+ Math.random();
	}

	function submitData() {
		var content = $("#content").val();
		var imageCode = $("#imageCode").val();
		if (content == null || content == "") {
			alert("请输入评论内容！");
		} else if (imageCode == null || imageCode == "") {
			alert("请填写验证码！");
		} else {
			$.post("comment/saveComment.do", {
				"content" : content,
				'imageCode' : imageCode,
				'blog.id' : '${blog.id}'
			}, function(result) {
				if (result.success) {
					window.location.reload();
					alert("评论已提成成功，审核通过后显示！");
				} else {
					alert(result.errorInfo);
				}
			}, "json");
		}
	}
</script>
<div class="data_list">
	<div class="data_list_title">
		<span class="glyphicon glyphicon-th-list"></span>&nbsp;&nbsp;博客信息
	</div>
	<div>
		<div class="blog_title">
			<h3>
				<strong> ${blog.title }</strong>
			</h3>
		</div>
		<div class="blog_info">
			发布时间：『
			<fmt:formatDate value="${blog.releasedate }" type="date"
				pattern="yyyy-MM-dd HH:mm" />
			』&nbsp;&nbsp;博客类别：${blog.blogType.typename}&nbsp;&nbsp;阅读(${blog.clickhit})
			评论(${blog.replyhit})
		</div>
		<div class="blog_content">${blog.content }</div>
		<div class="blog_keyWord">
			<strong>关键字</strong>
			<c:choose>
				<c:when test="${keywords==null }">
					&nbsp;&nbsp;无
				</c:when>
				<c:otherwise>
					<c:forEach items="${keywords }" var="keyword">
						&nbsp;&nbsp; <a href="">${keyword }</a>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</div>
		<div class="blog_lastAndNextPage">${pageCode2 }</div>
	</div>
</div>


<div class="data_list">
	<div class="data_list_title">
		<span class="glyphicon glyphicon-th-list"></span>&nbsp;&nbsp;评论信息
		<c:if test="${commentList.size()>10 }">
			<a href="javascript:showOtherComment()"
				style="float: right; padding-right: 40px;">显示所有评论</a>
		</c:if>
	</div>
	<div class="commentDatas">
		<c:choose>
			<c:when test="${commentList.size()==0 }">
				暂无评论
			</c:when>
			<c:otherwise>
				<c:forEach items="${commentList }" var="comment" varStatus="status">
					<c:choose>
						<c:when test="${status.index<10 }">
							<div class="comment">
								<span><font>${status.index+1 }楼&nbsp;&nbsp;&nbsp;&nbsp;${comment.userip }：</font>${comment.content }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[&nbsp;<fmt:formatDate
										value="${comment.commentdate }" type="date"
										pattern="yyyy-MM-dd HH:mm" />&nbsp;] </span>
							</div>
						</c:when>
						<c:otherwise>
							<div class="otherComment comment">
								<span><font>${status.index+1 }楼&nbsp;&nbsp;&nbsp;&nbsp;${comment.userip }：</font>${comment.content }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[&nbsp;<fmt:formatDate
										value="${comment.commentdate }" type="date"
										pattern="yyyy-MM-dd HH:mm" />&nbsp;] </span>
							</div>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</div>
</div>


<div class="data_list">
	<div class="data_list_title">
		<img src="static/images/publish_comment_icon.png" /> 发表评论
	</div>
	<div class="publish_comment">
		<div>
			<textarea style="width: 100%" rows="3" id="content" name="content"
				placeholder="来说两句吧..."></textarea>
		</div>
		<div class="verCode">
			验证码：<input type="text" value="" name="imageCode" id="imageCode"
				size="10" onkeydown="if(event.keyCode==13)form1.submit()" />&nbsp;<img
				onclick="javascript:loadimage();" title="换一张试试" name="randImage"
				id="randImage" src="frontpage/image.jsp" width="60" height="20"
				border="1" align="absmiddle">
		</div>
		<div class="publishButton">
			<button class="btn btn-primary" type="button" onclick="submitData()">发表评论</button>
		</div>
		</form>
	</div>
</div>
