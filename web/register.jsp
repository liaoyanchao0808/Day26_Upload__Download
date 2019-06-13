<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2019/6/12
  Time: 19:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="UploadServlet" method="post" enctype="multipart/form-data">
        用户名:<input type="text" name="username"><br/>
        密码:<input type="text" name="password"><br/>
        图片:<input type="file" name="file"><br/>
        <input type="submit" value="注册">
    </form>
</body>
</html>
