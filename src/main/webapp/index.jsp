
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>

<head>
    <title>请选择要解析的excel</title>
</head>
<body>
<form action="/UploadServlet" enctype="multipart/form-data" method="post">

    <input type="file" name="pic" >
    <br>
    <input type="submit" value="解析excel">

</form>

</body>

