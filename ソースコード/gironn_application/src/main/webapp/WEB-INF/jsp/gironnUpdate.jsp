<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
pageContext.setAttribute("title", "議論コメント編集", PageContext.PAGE_SCOPE);
%>
<!DOCTYPE html >
<html lang="jp">
<%-- head部を読み込む --%>
<%@ include file="/WEB-INF/jsp/include/head.jsp"%>
<%@ include file="/WEB-INF/jsp/include/css.jsp"%>
<%@ include file="/WEB-INF/jsp/include/js.jsp"%>
<body>

	<div class="row">
		<div class="col">
			<div class="card">
				<div class="card-header">
					<h5>議論コメント編集</h5>
				</div>
				<form action="GironnUpdate" method="post">
					<input type="hidden" name="id" value="${id}"> <input
						type="hidden" name="gidaiId" value="${gidaiId}">
					<p>
						<textarea
							class="form-control<c:if test="${errors.comment != null }">is-invalid</c:if>"
							name="comment" rows="3" cols="60">${comment}</textarea>
						<span class="text-danger">${errors.comment}</span> <input
							type="submit" class="btn btn-secondary" value="編集">
				</form>

			</div>
		</div>
	</div>
</body>
</html>