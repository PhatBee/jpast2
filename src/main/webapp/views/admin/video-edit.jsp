<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 10/10/2024
  Time: 22:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/commons/taglib.jsp"%>
<form action="<c:url value="/admin/video/update"/>" method="post" enctype="multipart/form-data">

    <input type="text" id="videoId" name="videoId" value = "${video.videoId}" hidden="hidden" ><br>

    <label for="title">Title:</label><br>
    <input type="text" id="title" name="title" value = "${video.title}"><br>

    <label for="poster">Poster:</label><br>
    <input type="text" id="poster" name="poster"><br>

    <label for="poster1">Upload File:</label><br>
    <input type="file" id="poster1" name="poster1" onchange = "chooseFile(this)"> <br><br>

    <c:if test="${video.poster.substring(0,5)=='https'}">
        <c:url value="${video.poster}" var="imgUrl"></c:url>
    </c:if>

    <c:if test="${video.poster.substring(0,5)!='https'}">
        <c:url value="/image?fname=${video.poster}" var="imgUrl"></c:url>
    </c:if>
    <td><img id="Hinh" height="150" width="200" src="${imgUrl}"/></td>
    <br>

    <label for="poster1">Choose Category:</label><br>
    <select name="categoryid" id="categoryid">
        <c:forEach items= "${listcate}" var="cate">
            <option value = ${cate.categoryid}>${cate.categoryname}</option>
        </c:forEach>
    </select><br>

    <label for="description">Description:</label><br>
    <input type="text" id="description" name="description" value="${video.description}"><br>

    <label for="views">Views:</label><br>
    <input type="text" id="views" name="views" value="${video.views}"><br>


    <label for="active">Active:</label><br>
    <input type="radio" id="activeon" name="active" value="1" ${video.active==1?'checked':''}>
    <label for="activeon">Hoạt động</label>
    <input type="radio" id="activeoff" name="active" value="0" ${video.active==0?'checked':''}>
    <label for="activeoff">Khoá</label>
    <br>
    <hr>
    <input type="submit" value="Edit">
</form>
