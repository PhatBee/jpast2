<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 10/10/2024
  Time: 21:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/commons/taglib.jsp" %>
<a href=${pageContext.request.contextPath}/admin/video/add>Add Video</a>
<table border="1" width="100%">
    <tr>
        <th>STT</th>
        <th>VideoID</th>
        <th>CategoryName</th>
        <th>Poster</th>
        <th>Title</th>
        <th>Description</th>
        <th>Views</th>
        <th>Active</th>
        <th>Action</th>
    </tr>

    <c:forEach items="${listvideo}" var="video" varStatus="STT">
        <tr>
            <td>${STT.index+1 }</td>

            <td>${video.videoid }</td>
            <td>${video.category.categoryname }</td>

            <c:if test="${video.poster.substring(0,5)=='https'}">
                <c:url value="${video.poster}" var="imgUrl"></c:url>
            </c:if>

            <c:if test="${video.images.substring(0,5)!='https'}">
                <c:url value="/image?fname=${video.poster}" var="imgUrl"></c:url>
            </c:if>
            <td><img height="150" width="200" src="${imgUrl}"/></td>

            <td>${video.title }</td>
            <td>${video.description }</td>
            <td>${video.views }</td>

            <td>
                <c:if test="${video.active == 1}">
                    <span>Hoạt động</span>
                </c:if>
                <c:if test="${video.active != 1}">
                    <span>Khoá</span>
                </c:if>
            </td>


            <td><a href="<c:url value='/admin/video/edit?id=${video.categoryid }'/>">Sửa</a>
                | <a href="<c:url value='/admin/video/delete?id=${video.categoryid }'/>">Xóa</a>
            </td>
        </tr>


    </c:forEach>
</table>
