<%@ page import="services.UserServices" %>
<%@ page import="java.util.List" %>
<%@ page import="entity.Users" %>
<%@ page import="entity.Publisher" %>
<%@ page import="services.PublisherService" %><%--
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
            <c:if test="${msgPublisher!=null}">
                <div class="alert alert-primary alert-dismissible fade show" role="alert">
                    <strong>Hey! </strong><c:out value='${msgPublisher}'/>
                </div>
            </c:if>
            <c:if test="${msgUpdatePublisher!=null}">
                <div class="alert alert-success alert-dismissible fade show" role="alert">
                    <strong>Hey! </strong><c:out value='${msgUpdatePublisher}'/>
                </div>
            </c:if>
            <c:if test="${msgPublisher1!=null}">
                <div class="alert alert-warning alert-dismissible fade show" role="alert">
                    <strong>Hey! </strong><c:out value='${msgPublisher1}'/>
                </div>
            </c:if>
            <div class="container-fluid px-4">
                <h1 class="mt-4">Publisher list</h1>
                <ol class="breadcrumb mb-4">
                    <li class="breadcrumb-item active">Dashboard</li>
                </ol>
                <div class="card mb-4">
                    <div class="card-header">

                        <i class="fas fa-table me-1"></i>
                        Publisher table
                        <%--                        <button style="float: right" type="button"--%>
                        <%--                                class="btn btn-success mx-auto">--%>
                        <%--                            Add New User--%>
                        <%--                        </button>--%>
                    </div>

                    <div class="card-body">
                        <table id="datatablesSimple" class="table align-middle mb-0 bg-white">
                            <thead class="bg-light">
                            <tr>
                                <th>nameOfCompany</th>
                                <th>username</th>
                                <th>address</th>
                                <th>phoneNumber</th>
                                <th>email</th>
                                <th>password</th>
                                <th>isActive</th>
                                <th>isBlocked</th>
                                <th colspan="2">Actions</th>
                            </tr>
                            </thead>
                            <tbody>
                            <%
                                PublisherService publisherService = new PublisherService();
                                List<Publisher> publisherList = publisherService.getAlPublishers();
                                for (Publisher publisher : publisherList) {
                            %>
                            <tr>
                                <td>
                                    <div class="d-flex align-items-center">
                                        <div class="ms-3">
                                            <p class="fw-bold mb-1"><%=publisher.getNameOfCompany()%>
                                            </p>
                                            <%--                                            <p class="text-muted mb-0">john.doe@gmail.com</p>--%>
                                        </div>
                                    </div>
                                </td>
                                <td>
                                    <p class="fw-normal mb-1"><%=publisher.getUsername() == null ? "" : publisher.getUsername()%>
                                    </p>
                                </td>
                                <td>
                                    <p class="fw-normal mb-1"><%=publisher.getAddress()%>
                                    </p>
                                </td>
                                <td>
                                    <p class="fw-normal mb-1"><%=publisher.getPhoneNumber()%>
                                    </p>
                                </td>
                                <td>
                                    <p class="fw-normal mb-1"><%=publisher.getEmail()%>
                                    </p>
                                </td>
                                <td>
                                    <p class="fw-normal mb-1"><%=publisher.getPassword()%>
                                    </p>
                                </td>

                                <td>
                                    <p class="fw-normal mb-1">
                                        <%
                                            if (publisher.isActive()) {
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
                                    <a href="blockPublisher?id=<%=publisher.getId()%>">
                                        <%
                                            if (publisher.isBlocked()) {
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
                                <td data-bs-toggle="tooltip" data-bs-placement="top" title="edit">
                                    <a class="mx-auto" href="editPub?id=<%=publisher.getId()%>">
                                        <i style="color:#39C0ED" class="fas fa-highlighter">
                                        </i>
                                    </a>
                                </td>&nbsp;

                                <td data-bs-toggle="tooltip" data-bs-placement="top" title="delete">
                                    <a class="mx-auto" href="deletePub?id=<%=publisher.getId()%>">
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