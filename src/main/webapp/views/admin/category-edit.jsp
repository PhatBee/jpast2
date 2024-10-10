<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 30/09/2024
  Time: 9:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<form action="${pageContext.request.contextPath}/admin/category/update" method="post" enctype="multipart/form-data">
    <label for="categoryid">Category Name:</label><br>
    <input type="text" id="categoryid" name="categoryid" hidden="hidden" value="${cate.categoryid}"><br>
    <label for="categoryname">Category Name:</label><br>
    <input type="text" id="categoryname" name="categoryname" value="${cate.categoryname}"><br>

    <label for="images">Images:</label><br>
    <input type="text" id="images" name="images"><br>

    <label for="images1">Upload File:</label><br>
    <c:if test="${cate.images.substring(0,5)=='https'}">
        <c:url value="${cate.images}" var="imgUrl"></c:url>
    </c:if>

    <c:if test="${cate.images.substring(0,5)!='https'}">
        <c:url value="/image?fname=${cate.images}" var="imgUrl"></c:url>
    </c:if>
    <img id = "imagess" height="150" width="200" src="${imgUrl}"/>
    <input type="file" onchange="chooseFile(this)" id="images1" name="images1" value="${cate.images}"><br><br>

    <label for="status">Status:</label><br>
    <input type="radio" id="statuson" name="status" value="1" ${cate.status==1?'checked':''}>
    <label for="statuson">Hoạt động</label>
    <input type="radio" id="statusoff" name="status" value="0" ${cate.status==0?'checked':''}>
    <label for="statusoff">Khoá</label>
    <input type="submit" value="Submit">
</form>
