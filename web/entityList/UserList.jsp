<%@ page import="services.UserServices" %>
<%@ page import="java.util.List" %>
<%@ page import="entity.Users" %><%--
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
            <div class="container-fluid px-4">
                <h1 class="mt-4">Dashboard</h1>
                <ol class="breadcrumb mb-4">
                    <li class="breadcrumb-item active">Dashboard</li>
                </ol>
                <div class="card mb-4">
                    <div class="card-header">

                        <i class="fas fa-table me-1"></i>
                        DataTable Example
                        <button style="float: right" type="button"
                                class="btn btn-success mx-auto">
                            Add New User
                        </button>
                    </div>

                    <div class="card-body">
                        <table id="datatablesSimple">
                            <thead>
                            <tr>
                                <th>Username</th>
                                <th>Full name</th>
                                <th>Password</th>
                                <th>Phone number</th>
                                <th>Email</th>
                                <th>Created time</th>
                                <th>Updated time</th>
                            </tr>
                            </thead>

                            <tfoot>
                            <tr>
                                <th>Username</th>
                                <th>Full name</th>
                                <th>Password</th>
                                <th>Phone number</th>
                                <th>Email</th>
                                <th>Created time</th>
                                <th>Updated time</th>
                            </tr>
                            </tfoot>
                            <tbody>
                            </tr>

                            <c:forEach var="users" items="${usersList}">
                                <tr>
                                    <td>${users.username}</td>
                                    <td><c:out value="${users.fullName}"/></td>
                                    <td><c:out value="${users.password}"/></td>
                                    <td><c:out value="${users.phoneNumber}"/></td>
                                    <td><c:out value="${users.email}"/></td>
                                    <td><c:out value="${users.created_at}"/></td>
                                    <td><c:out value="${users.updated_at}"/></td>

                                    <td data-bs-toggle="tooltip" data-bs-placement="top" title="edit">
                                        <a class="mx-auto"
                                           href="update?id=<c:out value='${users.id}'/>">
                                            <i style="color:#39C0ED" class="fas fa-highlighter">
                                            </i>
                                        </a>
                                    </td>&nbsp;

                                    <td data-bs-toggle="tooltip" data-bs-placement="top" title="delete">
                                        <a class="mx-auto"
                                           href="delete?id=<c:out value='${users.id}'/>">
                                            <i style="color:#F93154" class="fas fa-trash"></i>
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>


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