<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 30/09/2024
  Time: 8:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<form action="<c:url value="/admin/category/insert"/>" method="post" enctype="multipart/form-data">
    <label for="categoryname">Category Name:</label><br>
    <input type="text" id="categoryname" name="categoryname"><br>

    <label for="images">Images:</label><br>
    <input type="text" id="images" name="images"><br>

    <label for="images1">Upload File:</label><br>
    <img height="150" width="200" src="" id="imagess" />
    <input type="file" onchange="chooseFile(this)" id="images1" name="images1"><br><br>

    <label for="status">Status:</label><br>
    <input type="radio" id="statuson" name="status" value="1">
    <label for="statuson">Hoạt động</label>
    <input type="radio" id="statusoff" name="status" value="0">
    <label for="statusoff">Khoá</label>
    <br>
    <hr>
    <input type="submit" value="Submit">
</form>
