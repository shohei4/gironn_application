<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
pageContext.setAttribute("title", "議論一覧", PageContext.PAGE_SCOPE);
%>
<!DOCTYPE html >
<html lang="jp">
<%-- head部を読み込む --%>
<%@ include file="/WEB-INF/jsp/include/head.jsp"%>
<%@ include file="/WEB-INF/jsp/include/css.jsp"%>
<%@ include file="/WEB-INF/jsp/include/js.jsp"%>
<body>
	<div class="container">
		<%@ include file="/WEB-INF/jsp/include/navbar2.jsp"%>
		<div class="row">
			<div class="col">
				<div class="card">
					<div class="card-header">
						<h5>議論コメント投稿</h5>
					</div>
					<div class="card-body">
						<form action="GironnRegister" method="post">
							<textarea
								class="form-control<c:if test="${errors.comment != null }">is-invalid</c:if>"
								name="comment" rows="3" cols="60">${gironnItem.comment }</textarea>
							<span class="text-danger">${errors.comment}</span> <input
								type="hidden" name="gidaiId" value="<c:out value="${gidaiId}"/>">
							<p>
								<button type="submit" class="btn btn-outline-primary">投稿</button>
							</p>
						</form>
					</div>
				</div>
			</div>
			<div class="cards">
				<h5 class="card-header">コメント一覧</h5>
				<c:forEach var="item" items="${items}">
					<div class="card">
						<div class="card-body">
							<p>
								<c:out value="${item.userName}" />
								<c:out value="${item.registrationDate}" />
							</p>
							<p>
								<c:out value="${item.comment}" />
							</p>
							<div class="text-right">
								<div class="row">
									<c:choose>
										<c:when test="${item.userId == user.id}">
											<div class="col">
												<form action="GironnUpdate" action="get">
													<input type="hidden" name="comment" value="${item.comment}">
													<input type="hidden" name="id"
														value="<c:out value="${item.id}"/>"> <input
														type="hidden" name="gidaiId"
														value="<c:out value="${item.gidaiId}"/>"> <input
														type="submit" class="btn btn-secondary" value="編集">
												</form>
											</div>
											<div class="col">
												<form action="GironnDelete" method="post">
													<input type="hidden" name="comment" value="${item.comment}">
													<input type="hidden" name="id" value="${item.id}">
													<input type="hidden" name="gidaiId"
														value="<c:out value="${item.gidaiId}"/>">
													<button type="submit" class="btn btn-danger"
														name="isDeleted" value="1">削除</button>
												</form>
											</div>
										</c:when>
									</c:choose>
									<c:choose>
										<c:when test="${item.favorite != 0}">

											<div class="col">
												<form action="GironnFavorite" method="post">
													<button type="submit" class="btn btn-warning" name="flag"
														value="2">いいね</button>
													<input type="hidden" name="comment" value="${item.comment}">
													<input type="hidden" name="gidaiId"
														value="<c:out value="${item.gidaiId}"/>"> <input
														type="hidden" name="id" value="${item.id}">
													<p>
														<c:out value="${item.favoriteCount}"></c:out>
													</p>
												</form>
											</div>
										</c:when>
										<c:otherwise>
											<div class="col">
												<form action="GironnFavorite" method="post">
													<button type="submit" class="btn btn-outline-warning"
														name="flag" value="1">いいね</button>
													<input type="hidden" name="comment" value="${item.comment}">
													<input type="hidden" name="gidaiId"
														value="<c:out value="${item.gidaiId}"/>"> <input
														type="hidden" name="id" value="${item.id}">
													<p>
														<c:out value="${item.favoriteCount}"></c:out>
													</p>
												</form>
											</div>
										</c:otherwise>
									</c:choose>
								</div>
							</div>
						</div>
					</div>
					<div class="card">
						<div class="card-body">
							<form action="ReplyRegister" method="post">
								<textarea
									class="form-control<c:if test="${errors.comment != null }">is-invalid</c:if>"
									name="comment" rows="3" cols="60">${replyItem.comment }</textarea>
								<p>
									<button type="submit" class="btn btn-outline-primary">投稿</button>
									<input type="hidden" name="commentId"
										value="<c:out value="${item.id}"/>"> <input
										type="hidden" name="gidaiId"
										value="<c:out value="${item.gidaiId}"/>">
								</p>
							</form>
						</div>
					</div>
					<c:forEach var="replyItem" items="${item.replyItems}">
						<div class="card">
							<div class="card-body">
								<p>
									<c:out value="${replyItem.userName}" />
									<c:out value="${replyItem.registrationDate}" />
								</p>
								<p>
									<c:out value="${replyItem.comment}" />
								</p>
								<div class="text-right">
									<div class="row">
										<c:choose>
											<c:when test="${replyItem.userId == user.id}">
												<div class="col">
													<form action="ReplyUpdate" action="get">
														<input type="hidden" name="id"
															value="<c:out value="${replyItem.id}"/>"> <input
															type="hidden" name="gidaiId"
															value="<c:out value="${item.gidaiId}"/>"> <input
															type="submit" class="btn btn-secondary" value="編集">
													</form>
												</div>
												<div class="col">
													<form action="ReplyDelete" method="post">
														<input type="hidden" name="comment"
															value="${item.comment}"> <input type="hidden"
															name="id" value="${replyItem.id}"> <input
															type="hidden" name="gidaiId"
															value="<c:out value="${item.gidaiId}"/>">
														<button type="submit" class="btn btn-danger"
															name="isDeleted" value="1">削除</button>
													</form>
												</div>
											</c:when>
										</c:choose>
									</div>
								</div>
							</div>
						</div>
					</c:forEach>
				</c:forEach>
			</div>
		</div>
	</div>
</body>
</html>