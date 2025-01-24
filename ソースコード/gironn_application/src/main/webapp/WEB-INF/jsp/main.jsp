<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- ページのタイトルを設定する --%>
<%
pageContext.setAttribute("title", "議題一覧", PageContext.PAGE_SCOPE);
%>
<!DOCTYPE html>
<html lang="jp">

<%-- head部を読み込む --%>
<%@ include file="/WEB-INF/jsp/include/head.jsp"%>
<%@ include file="/WEB-INF/jsp/include/css.jsp"%>
<%@ include file="/WEB-INF/jsp/include/js.jsp"%>
<body>

	<div class="container">
	<%@ include file="/WEB-INF/jsp/include/navbar.jsp" %>
		<div class="row">
			<div class="col">
				<div class="card">
					<div class="card-header">
						<h5>議題投稿</h5>
					</div>
					<div class="card-body">
						<form action="GidaiRegister" method="post">
							<p>
								<label for="gidaiName">議題名</label>
							</p>
							<textarea
								class="form-control<c:if test="${errors.gidaiName!=null}">is-invalid</c:if>" 
							name="gidaiName" rows="3" cols="60">${gidaiItem.gidaiName }</textarea>
							<span class="text-danger">${errors.gidaiName}</span>
							<p>
								<select name="genre">
									<option value="1">1:哲学</option>
									<option value="2">2:歴史</option>
									<option value="3">3:社会科学</option>
									<option value="4">4:自然科学</option>
									<option value="5">5:芸術.美術</option>
									<option value="6">6:文学</option>
									<option value="7">7:生活</option>
									<option value="8">8:その他</option>
								</select>
								<button type="submit" class="btn btn-outline-primary">投稿</button>
							</p>
						</form>
					</div>
				</div>
			</div>
		</div>
		<div class="cards">
			<h5 class="card-header">議題一覧</h5>
			<c:forEach var="item" items="${items}">
				<div class="card">
					<div class="card-body">
						<p>
							<c:out value="${item.registrationDate}" />
							<c:out value="${item.userName}" />
						</p>
						<h5>
							<c:out value="${item.gidaiName}" />
						</h5>						
						<div class="row">						
							<c:choose>
								<c:when test="${item.pickUp!=0}">
									<div class="col">
										<form>
											<button type=button class="btn btn-warning">Pick</button>
										</form>
									</div>
								</c:when>
								<c:otherwise>
									<div class="col">
										<form action="PickUpRegister" method="post">
											<input type="hidden" name="gidaiId"
												value="<c:out value="${item.id}"/>"> <input
												type="submit" value="Pick" class="btn btn-outline-warning">
										</form>
									</div>
								</c:otherwise>
							</c:choose>
							<div class="col">
								<form action="GironnList" method="get">
									<input type="submit" value="コメント" class="btn btn-outline-info">
									<input type="hidden" name="gidaiId"
										value="<c:out value="${item.id}"/>">
								</form>
							</div>
							<c:choose>
							<c:when test="${item.userId == user.id}">
							<div class="col-2">
								<form action="GidaiDelete" method="post">
								<input type="submit" value="削除" class="btn btn-danger">
								<input type="hidden" name="id" value="${item.id }">								
								</form>
							</div>
							</c:when>
							</c:choose>
							<a href="/gironn_application1/PickUpList" class="link-warning">ピックアップリストへ</a>
						</div>
					</div>
				</div>
				</c:forEach>		
		</div>
</div>
</body>
</html>