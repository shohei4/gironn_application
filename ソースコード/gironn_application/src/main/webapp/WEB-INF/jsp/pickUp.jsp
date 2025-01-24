<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
pageContext.setAttribute("title", "ピクアップ一覧", PageContext.PAGE_SCOPE);
%>
<!DOCTYPE html>
<html lang=jp>

<%-- head部を読み込む --%>
<%@ include file="/WEB-INF/jsp/include/head.jsp"%>
<%@ include file="/WEB-INF/jsp/include/css.jsp"%>
<%@ include file="/WEB-INF/jsp/include/js.jsp"%>
<body>
	<div class="container">
		<%@ include file="/WEB-INF/jsp/include/navbar.jsp"%>
		<div class="row">
			<div class="col">
				<div class="cards">
					<h5 class="card-header">ピックアップ一覧</h5>
					<p class="card-text">
						<c:forEach var="item" items="${items}">
							<div class="row">
								<div class="col">
									<div class="card">
										<div class="card-body">
											<div class="row">
												<p>
													<c:out value="${item.registrationDate}" />
													<c:out value="${item.userName}" />
												</p>
												<p>
													<c:out value="${item.gidaiName }"/>
												</p>
												<div class="col">
													<form action="PickUpDelete" method="post">
														<button type="submit" class="btn btn-danger">
															削除 <input type="hidden" name="gidaiId"
																value="<c:out value="${item.id}"/>">
														</button>
													</form>
												</div>
												<div class="col">
													<form action="GironnList" method="get">
														<input type="submit" value="コメント"
															class="btn btn-outline-info"> <input
															type="hidden" name="gidaiId" value="${item.id }">
													</form>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</c:forEach>
						<a href="/gironn_application1/Main">議題一覧</a>
				</div>
			</div>
		</div>
	</div>
</body>
</html>