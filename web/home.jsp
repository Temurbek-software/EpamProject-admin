<%--
  Created by IntelliJ IDEA.
  User: Temurbek
  Date: 6/24/2022
  Time: 4:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Dashboard - SB Admin</title>
    <jsp:include page="header/head.jsp"></jsp:include>
</head>
<body class="sb-nav-fixed">
<jsp:include page="header/header.jsp"></jsp:include>
<div id="layoutSidenav">
    <jsp:include page="header/Navbars.jsp"></jsp:include>
    <div id="layoutSidenav_content">
        <main>
<%--            Here we can add news for our project--%>

            <jsp:include page="statistic/statistics.jsp"></jsp:include>
        </main>
        <jsp:include page="footer/footer.jsp"></jsp:include>
    </div>
</div>
<jsp:include page="footer/extraLink.jsp"></jsp:include>
</body>
</html>
