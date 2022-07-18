<%@ page import="services.UserServices" %>
<%@ page import="java.util.List" %>
<%@ page import="entity.Users" %>
<%@ page import="entity.Publisher" %>
<%@ page import="services.PublisherService" %>
<%@ page import="services.ProductServices" %>
<%@ page import="entity.Product" %>
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
    <title>Deleted news</title>
    <jsp:include page="../header/head.jsp"></jsp:include>
</head>
<body class="sb-nav-fixed">
<jsp:include page="../header/header.jsp"></jsp:include>
<div id="layoutSidenav">
    <jsp:include page="../header/Navbars.jsp"></jsp:include>
    <div id="layoutSidenav_content">

        <main>
            <c:if test="${msg1!=null}">
                <div class="alert alert-warning alert-dismissible fade show" role="alert">
                    <strong>Hey! </strong><c:out value='${msg}'/>
                </div>
            </c:if> <c:if test="${msgRecover!=null}">
            <div class="alert alert-warning alert-dismissible fade show" role="alert">
                <strong>Hey!  </strong><c:out value='${msgRecover}'/>
            </div>
        </c:if>
            <c:if test="${msg!=null}">
                <div class="alert alert-warning alert-dismissible fade show" role="alert">
                    <div class="alert alert-secondary" role="alert">
                        <strong>Hey! </strong><c:out value='${msg}'/>
                    </div>
                </div>
            </c:if>

            <div class="container-fluid px-4">
                <h1 class="mt-4">Here deleted list</h1>
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
                        <table id="datatablesSimple" class="table align-middle mb-0 bg-white">
                            <thead class="bg-light">
                            <tr>
                                <th>titles</th>
                                <th>counterOfView</th>
                                <th>created_at</th>
                                <th>updated_at</th>
                                <th colspan="2">Actions</th>
                            </tr>
                            </thead>
                            <tbody>
                            <%
                                ProductServices productServices = new ProductServices();
                                List<Product> deletedProduct = productServices.getAllProduct().stream()
                                        .filter(s -> s.isDeleted() == true).collect(Collectors.toList());
                                for (Product product : deletedProduct) {
                            %>
                            <tr>
                                <td>
                                    <div class="d-flex align-items-center">
                                        <div class="ms-3">
                                            <p class="fw-bold mb-1"><%=product.getTitles()%>
                                            </p>
                                            <%--                                            <p class="text-muted mb-0">john.doe@gmail.com</p>--%>
                                        </div>
                                    </div>
                                </td>
                                <td>
                                    <p class="fw-normal mb-1"><%=product.getCounterOfView()%>
                                    </p>
                                </td>
                                <td>
                                    <p class="fw-normal mb-1"><%=product.getCreated_at()%>
                                    </p>
                                </td>
                                <td>
                                    <p class="fw-normal mb-1"><%=product.getUpdated_at()%>
                                    </p>
                                </td>

                                <td data-bs-toggle="tooltip" data-bs-placement="top" title="recover">
                                    <a class="mx-auto" href="recover?id=<%=product.getId()%>">
                                        <i style="color:#39C0ED" class="fas fa-history"></i>
                                    </a>
                                </td>&nbsp;

                                <td data-bs-toggle="tooltip" data-bs-placement="top" title="delete">
                                    <a class="mx-auto" href="deleteNews?id=<%=product.getId()%>">
                                        <i style="color:#F93154" class="fas fa-trash"></i>
                                    </a>
                                </td>
                            </tr>
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