
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	pageContext.setAttribute("title", "会員ログイン", PageContext.PAGE_SCOPE);
%>
<!DOCTYPE html>
<html lang="jp">
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
				<div class="card-header">ログイン</div>
				<div class="card-boddy">
				<form action="Login" method="post">
					<div class="m-3">
    					<label for="email" class="form-label">E-mailアドレス</label>
    					<input type="text" name="email" class="form-control" id="email" >
  					<div class="m-3">
    					<label for="InputPassword1" class="form-label">パスワード</label>
   						<input type="password" name="password" class="form-control" id="InputPassword1">
  					</div>
  					
  					<button type="submit" class="btn btn-primary">ログイン</button>
  				</div>
  				</form>
  			</div>
  			</div>
  			<a href="/gironn_application1/UserRegister">新規会員登録はこちら</a>
  			</div>
  		<div class="col-md-3"></div>
  		</div>
	</div>
</body>
</html>