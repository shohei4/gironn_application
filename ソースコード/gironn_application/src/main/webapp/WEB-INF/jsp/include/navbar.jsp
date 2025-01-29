<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<a class="navbar-brand" href="#">議論アプリ</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item active"><a class="nav-link"
					href="${root_path }/Main">議題一覧 <span class="sr-only">(current)</span></a>
				</li>
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
					role="button" data-toggle="dropdown" aria-haspopup="true"
					aria-expanded="false"> <c:out value="${user.name}" />
				</a>
					<div class="dropdown-menu" aria-labelledby="navbarDropdown">
						<a class="dropdown-item" href="${root_path }/Logout">ログアウト</a> <a
							class="dropdown-item" href="${root_path }/UserUpdate">ユーザー情報修正</a>
					</div></li>
			</ul>
			<form class="form-inline my-2 my-lg-0" action="${root_path}/Main"
				method="get">
				<input class="form-control mr-sm-2" type="search"
					placeholder="Search" aria-label="Search" name="key" id="key"
					value="${key}"/> 
				<select name="genre">
					<option value="0">0:なし</option>
					<option value="1">1:哲学</option>
					<option value="2">2:歴史</option>
					<option value="3">3:社会科学</option>
					<option value="4">4:自然科学</option>
					<option value="5">5:芸術.美術</option>
					<option value="6">6:文学</option>
					<option value="7">7:生活</option>
					<option value="8">8:その他</option>
				</select>
				<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
			</form>
		</div>
	</nav>
</body>
</html>