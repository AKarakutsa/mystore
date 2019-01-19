<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/mystore.css">

    <title>MyStore</title>
  </head>
  <body>
    <div class="container">
      <div class="row min-height-parent align-items-center">
        <div class="col-sm-12">
          <div class="text-center">
            <a href="${pageContext.request.contextPath}/mystore/shop"
               class="btn btn-primary active"
               role="button"
               aria-pressed="true">Go shopping!</a>
          </div>
        </div>
      </div>
    </div>
  </body>
</html>