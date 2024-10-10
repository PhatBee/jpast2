<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 23/09/2024
  Time: 7:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/commons/taglib.jsp"%>
<c:url value="/" var="URL"></c:url>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%@include file="/commons/admin/header.jsp" %>

    <sitemesh:write property="body"/>

    <script src="${URL}assets/global/plugins/jquery.min.js" type="text/javascript"></script>
    <script>
            function chooseFile(fileInput){
                if (fileInput.files && fileInput.files[0]){
                    var reader = new FileReader();
                    reader.onload = function(e) {
                        $('#imagess').attr('src', e.target.result);
                    }
                    reader.readAsDataURL(fileInput.files[0]);
                }

            }
    </script>

    <%@include file="/commons/admin/footer.jsp" %>
</body>
</html>
