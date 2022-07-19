<%@ page import="services.UserServices" %>
<%@ page import="java.util.List" %>
<%@ page import="entity.Users" %>
<%@ page import="entity.Publisher" %>
<%@ page import="services.PublisherService" %>
<%@ page import="java.util.stream.Collectors" %><%--
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
    <title>Users list</title>
    <jsp:include page="../header/head.jsp"></jsp:include>
</head>
<body class="sb-nav-fixed">
<jsp:include page="../header/header.jsp"></jsp:include>
<div id="layoutSidenav">
    <jsp:include page="../header/Navbars.jsp"></jsp:include>
    <div id="layoutSidenav_content">
        <main>

            <c:if test="${msgUpdate!=null}">
                <div class="alert alert-success alert-dismissible fade show" role="alert">
                    <strong>Hey! </strong><c:out value='${msgUpdate}'/>
                </div>
            </c:if>
            <c:if test="${msgDeleteComplete!=null}">
                <div class="alert alert-success alert-dismissible fade show" role="alert">
                    <strong>Hey! </strong><c:out value='${msgDeleteComplete}'/>
                </div>
            </c:if>
            <c:if test="${msgDelete!=null}">
            <div class="alert alert-success alert-dismissible fade show" role="alert">
                <strong>Hey! </strong><c:out value='${msgDelete}'/>
            </div>
        </c:if>

            <div class="container-fluid px-4">
                <h1 class="mt-4">Deleted users</h1>
                <ol class="breadcrumb mb-4">
                    <li class="breadcrumb-item active">Dashboard</li>
                </ol>
                <div class="card mb-4">
                    <div class="card-header">

                        <i class="fas fa-table me-1"></i>
                        Users table
<%--                        <button style="float: right" type="button"--%>
<%--                                class="btn btn-success mx-auto">--%>
<%--                            Add New User--%>
<%--                        </button>--%>
                    </div>

                    <div class="card-body">
                        <table id="datatablesSimple" class="table align-middle mb-0 bg-white">
                            <thead class="bg-light">
                            <tr>
                                <th>username</th>
                                <th>password</th>
                                <th>isActive</th>
                                <th>isBlocked</th>
                                <th>created_at</th>
                                <th>updated_at</th>
                                <th colspan="2">Actions</th>
                            </tr>
                            </thead>
                            <tbody>
                            <%
                                UserServices userServices = new UserServices();
                                List<Users> usersList=userServices.getAllUser()
                                        .stream().filter(s->s.isDeleted()==true)
                                        .collect(Collectors.toList());
                                for (Users users : usersList) {
                            %>
                            <tr>
                                <td>
                                    <div class="d-flex align-items-center">
                                        <div class="ms-3">
                                            <p class="fw-bold mb-1"><%=users.getUsername()%>
                                            </p>
                                            <%--                                            <p class="text-muted mb-0">john.doe@gmail.com</p>--%>
                                        </div>
                                    </div>
                                </td>
                                <td>
                                    <p class="fw-normal mb-1"><%=users.getPassword()%>
                                    </p>
                                </td>
                                <td>
                                    <p class="fw-normal mb-1">
                                        <%
                                            if (users.isActive()) {
                                        %>
                                        <span class=" rounded-pill badge bg-success">active</span>
                                        <%
                                        } else {
                                        %>
                                        <span class=" rounded-pill badge bg-danger">inActive</span>
                                        <%
                                            }
                                        %>
                                    </p>
                                </td>
                                <td>
                                    <a href="blockUsersInDeleted?id=<%=users.getId()%>">
                                        <%
                                            if (users.isBlocked()) {
                                        %>
                                        <p class="fw-normal mb-1">
                                            <span class=" rounded-pill badge bg-primary">unblock</span>
                                        </p>
                                        <%
                                        } else {
                                        %>
                                        <p class="fw-normal mb-1">
                                            <span class=" rounded-pill badge bg-info text-dark">block</span>
                                        </p>
                                        <%
                                            }
                                        %>

                                    </a>
                                </td>
                                <td>
                                    <p class="fw-normal mb-1"><%=users.getCreated_at()%>
                                    </p>
                                </td>
                                <td>
                                    <p class="fw-normal mb-1"><%=users.getUpdated_at()%>
                                    </p>
                                </td>
                                <td data-bs-toggle="tooltip" data-bs-placement="top" title="edit">
                                    <a class="mx-auto" href="recoverUsers?id=<%=users.getId()%>">
                                        <i style="color:#39C0ED" class="fas fa-history"></i>
                                        </i>
                                    </a>
                                </td>&nbsp;

                                <td data-bs-toggle="tooltip" data-bs-placement="top" title="delete">
                                    <a class="mx-auto" href="deleteUsers?id=<%=users.getId()%>">
                                        <i style="color:#F93154" class="fas fa-trash"></i>
                                    </a>
                                </td>
                                    <%
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