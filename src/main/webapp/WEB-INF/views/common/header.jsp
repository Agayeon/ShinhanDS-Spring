<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<style>
.myclass {background-color: orange; }
</style>
<c:set var="cpath" value="${pageContext.servletContext.contextPath}" />

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
 <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<!-- 빈 파비콘 (브라우저 요청 방지) -->
<link rel="icon" href="data:;base64,iVBORw0KGgo=">

	<p class="myclass">현재 로그인한 직원: ${loginEmp.first_name}님 : ${loginEmp==null?"게스트":loginEmp.first_name}님 환영합니다.</p>
	<c:if test="${loginEmp!=null}">
		<a class="btn btn-primary" href="${cpath}/auth/loginout.do">로그아웃</a>
	</c:if>