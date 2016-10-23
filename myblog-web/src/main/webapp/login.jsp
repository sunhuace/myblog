<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>博客首页</title>
<link href="${pageContext.request.contextPath}/static/css/bootstrap.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/static/css/signin.css" rel="stylesheet">
</head>
<body>
    <div class="signin">
        <div class="signin-head">
            <img src="${pageContext.request.contextPath}/static/images/test/head_120.png" alt="" class="img-circle">
        </div>
        <form class="form-signin" role="form" action="${pageContext.request.contextPath}/blogger/login.do" method="post">
            <input type="text" name="username" class="form-control" placeholder="用户名" value="${blogger.username }" required autofocus />
            <input type="password" name="password" class="form-control" placeholder="密码"  value="${blogger.password }" required />
            <button class="btn btn-lg btn-warning btn-block" type="submit">登录</button>
            <label class="checkbox">
                <input type="checkbox" value="remember-me"> 记住我
                <span style="color: red; float:right;">${errorInfo}</span>
            </label>
        </form>
    </div>
</body>
</html>
