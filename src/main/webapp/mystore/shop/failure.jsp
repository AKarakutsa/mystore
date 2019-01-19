<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.min.css">

    <title>Success</title>
</head>
<body>
<div class="py-5">
    <div id="container" class="container">
        <div class="alert alert-danger" role="alert">
            <h4 class="alert-heading">Order didn't created!</h4>
            <p>Product ended or order was incorrect.</p>
            <hr>
            <p class="mb-0"><a href="/mystore" class="alert-link">You can try buy again</a></p>
        </div>
    </div>
</div>
</body>
</html>
