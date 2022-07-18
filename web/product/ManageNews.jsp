<%@ page import="services.UserServices" %>
<%@ page import="java.util.List" %>
<%@ page import="entity.Users" %>
<%@ page import="entity.Publisher" %>
<%@ page import="services.PublisherService" %>
<%@ page import="services.ProductServices" %>
<%@ page import="entity.Product" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="database.DB" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %><%--
  Created by IntelliJ IDEA.
  User: Temurbek
  Date: 6/24/2022
  Time: 8:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Dashboard - SB Admin</title>
    <jsp:include page="../header/head.jsp"></jsp:include>
</head>
<body class="sb-nav-fixed">
<jsp:include page="../header/header.jsp"></jsp:include>
<div id="layoutSidenav">
    <jsp:include page="../header/Navbars.jsp"></jsp:include>
    <div id="layoutSidenav_content">
        <main>
            <c:if test="${msgDelete!=null}">
                <div class="alert alert-primary alert-dismissible fade show" role="alert">
                    <strong>Hey! </strong><c:out value='${msgDelete}'/>
                </div>
            </c:if>
            <div class="container-fluid px-4">
                <h1 class="mt-4">Dashboard</h1>
                <ol class="breadcrumb mb-4">
                    <li class="breadcrumb-item active">Dashboard</li>
                </ol>
                <div class="card mb-4">

                    <div class="card-body">
                        <table id="datatablesSimple" class="table align-middle mb-0 bg-white">
                            <thead class="bg-light">
                            <tr>
                                <th>Title</th>
                                <th>Category</th>
                                <th>Views number</th>
                                <%--                                <th>Status</th>--%>
                                <th colspan="2">Actions</th>
                            </tr>
                            </thead>
                            <tbody>
                            <%
                                ProductServices productServices = new ProductServices();
                                try {
                                    Connection connection = DB.getConnection();
                                    PreparedStatement preparedStatement = connection.prepareStatement("SELECT product.id, titles,category.name, " +
                                            "category_id, publisher_id,  \"counterOfView\", \"isDeleted\"\n" +
                                            "\tFROM public.product inner join category on product.category_id=category.id where \"isDeleted\"=false");
                                    ResultSet rs = preparedStatement.executeQuery();
                                    while (rs.next()) {
                            %>
                            <tr>
                                <td>
                                    <div class="d-flex align-items-center">
                                        <div class="ms-3">
                                            <p class="fw-bold mb-1"><%=rs.getString("titles")%>
                                            </p>
                                            <%--                                            <p class="text-muted mb-0">john.doe@gmail.com</p>--%>
                                        </div>
                                    </div>
                                </td>
                                <td>
                                    <p class="fw-normal mb-1"><%=rs.getString("name")%>
                                    </p>
                                </td>
                                <td>
                                    <%=rs.getString("counterOfView") == null ? 0 : rs.getString("counterOfView")%>
                                </td>
                                <td data-bs-toggle="tooltip" data-bs-placement="top" title="edit">
                                    <a class="mx-auto" href="editNewsForm?id=<%=rs.getLong("id")%>">
                                        <i style="color:#39C0ED" class="fas fa-highlighter">
                                        </i>
                                    </a>
                                </td>&nbsp;

                                <td data-bs-toggle="tooltip" data-bs-placement="top" title="delete">
                                    <a class="mx-auto" href="deleteNewsTemporary?id=<%=rs.getLong("id")%>">
                                        <i style="color:#F93154" class="fas fa-trash"></i>
                                    </a>
                                </td>
                            </tr>
                            <%
                                    }
                                } catch (SQLException exception) {
                                    DB.printSQLException(exception);
                                }
                            %>

                            </tbody>
                        </table>
                    </div>
                </div>
            </div>

        </main>
        <jsp:include page="../footer/footer.jsp"></jsp:include>
    </div>
</div>
<jsp:include page="../footer/extraLink.jsp"></jsp:include>
</body>
</html>