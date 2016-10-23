<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!-- navigator -->
<div class="container-fluid">
	<div class="row">
		<nav class="navbar navbar-default" role="navigation">
			<div class="container">
				<div class="navbar-header">
					<img alt="" class="navbar-brand" src="static/images/favicon.ico">
				</div>
				<div id="bs-example-navbar-collapse-1">
					<ul class="nav navbar-nav" id="nav">
						<li class="${active_main }"><a href="index.html">首页</a></li>
						<li class="${active_about }"><a href="blogger/aboutAuthor.html">关于博主</a></li>
						<li class="${active_bushit }"><a href="blogger/bishit.html">扯淡话题</a></li>
					</ul>
					<div style="float: right;">
						<form class="navbar-form navbar-right" role="search" action="${pageContex.request.contextPath}/blog/q.html" method="post" onsubmit="return checked()">
							<div class="form-group">
								<input id="q" name="q" value="${q }"type="text" class="form-control" placeholder="Search">
							</div>
							<button type="submit" class="btn btn-info">搜索</button>
						</form>
					</div>
				</div>
			</div>
		</nav>
	</div>
</div>

<script	type="text/javascript">
	function checked() {
		var q = document.getElementById("q").value.trim();
		if(q == null || q == '') {
			alert('输入为空');
			return false;
		}
		else {
			return true;
		}
	}
	
	
</script>
