<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	pageContext.setAttribute("title", "新規会員登録", PageContext.PAGE_SCOPE);
%>
<!DOCTYPE html>
<html lang ="jp">

<%-- head部を読み込む --%>
<%@ include file="/WEB-INF/jsp/include/head.jsp"%>
<%@ include file="/WEB-INF/jsp/include/css.jsp"%>
<%@ include file="/WEB-INF/jsp/include/js.jsp"%>

<body>

	<div class="container-md">
		<div class="row">
		<div class="col-md-3"></div>
			<div class="col-md-6">
			<div class="card my-3">
				<div class="card-header">新規会員登録</div>
				<div class="card-boddy">
				<c:if test="${db_error != null }">
					<div class="alert alert-danger" role="alert">${db_error }</div>
				</c:if>
				<form action="UserRegister" method="post">
					<div class="m-3">
    					<label for="email" class="form-label">Emailアドレス</label>
    					<input type="text" name="email" class="form-control<c:if test="${errors.email != null}">is-invalid</c:if>" 
    					id="email1" aria-describedby="emailHelp" value="${user.email}">
    					<c:if test="${errors.email!=null}">
    					<span class="text-danger">${errors.email}</span>
    					</c:if>
  					<div class="m-3">
    					<label for="InputPassword1" class="form-label">パスワード</label>
   						<input type="password" name="password" class="form-control<c:if test="${errors.password!=null}"> is-invalid</c:if>"
						value="<c:out value="${user.password}"/>" id="InputPassword1">
						<c:if test="${errors.password!=null}">
							<span class="text-danger">${errors.password}</span>
						</c:if>
  					</div>
  					<div class="m-3">
   				 		<label for="name" class="form-label">ニックネーム</label>
   				 		<input type="text" name="name" class="form-control<c:if test="${errors.name!=null}"> is-invalid</c:if>" 
   				 		id="InputName1"value="<c:out value="${user.name}"/>">
   				 		<c:if test="${errors.name!=null}">
							<span class="text-danger">${errors.name}</span>
						</c:if>
						
  					</div>
  					<button type="submit" class="btn btn-primary">登録</button>
  				</div>
  				</form>
  			</div>
  			</div>
  			</div>
  		<div class="col-md-3"></div>
  		</div>
	</div>
	
</body>
</html>