<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="cpath" value="${pageContext.servletContext.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
  <script src="loginExternal.js"></script>
<meta charset="UTF-8">
<title>로그인</title>
</head>



<body>
<h1>로그인</h1>
<div class="container mt-3">
   <form id="myfrm" action="${pageContext.request.contextPath}/auth/login.do" method="post">

    <div class="mb-3 mt-3">
      <label for="email">사용자아이디(직원번호):</label>
      <input type="text" class="form-control" id="email" placeholder="insert id" name="userid">
    </div>
    <div class="mb-3">
      <label for="pwd">Password(이메일):</label>
      <input type="password" class="form-control" id="pwd" placeholder="Enter password" name="pswd">
    </div>
    <div class="form-check mb-3">
      <label class="form-check-label">
        <input class="form-check-input" type="checkbox" name="remember" value="ok"> Remember me
    </div>
    <button type="submit" class="btn btn-primary">Login</button>
    <br><br>
  </form>
  <p>${info}</p>
  <script>

  </script>
  <c:set var="info" value="" scope="session"/>
</div>

</body>
</html>