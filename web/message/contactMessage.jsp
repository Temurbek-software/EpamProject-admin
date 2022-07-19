<%@ page import="services.MessageService" %>
<%@ page import="java.util.List" %>
<%@ page import="entity.Contacts" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Contact Message</title>
    <jsp:include page="../header/head.jsp"></jsp:include>
</head>
<body class="sb-nav-fixed">
<jsp:include page="../header/header.jsp"></jsp:include>
<div id="layoutSidenav">
    <jsp:include page="../header/Navbars.jsp"></jsp:include>
    <div id="layoutSidenav_content">
        <main>
            <c:if test="${msg!=null}">
                <div class="alert alert-primary alert-dismissible fade show" role="alert">
                    <strong>Hey! </strong><c:out value='${msg}'/>
                </div>
            </c:if>
            <div class="container-fluid px-4">
                <h1 class="mt-4">Contact message</h1>
                <ol class="breadcrumb mb-4">
                    <li class="breadcrumb-item active">Dashboard</li>
                </ol>
                <div class="card mb-4">

                    <div class="card-body">
                        <%--                        <table id="datatablesSimple" class="table align-middle mb-0 bg-white">--%>
                        <table id="datatablesSimple" class="table table-striped" style="width:100%">
                            <thead>
                            <tr>
                                <th>Id</th>
                                <th>Name</th>
                                <th>Email</th>
                                <th>Message</th>
                                <th>Created time</th>
                                <th>Action</th>
                            </tr>
                            </thead>
                            <tbody>
                            <%
                                MessageService messageService = new MessageService();
                                List<Contacts> contactsList = messageService.getAllMsg();
                                for (Contacts contacts : contactsList) {
                            %>
                            <tr>
                                <th><%=contacts.getId()%>
                                </th>
                                <th><%=contacts.getUsername()%>
                                </th>
                                <th><%=contacts.getEmail()%>
                                </th>
                                <th><%=contacts.getTextResponse()%>
                                </th>
                                <th><%=contacts.getCreated_at()%>
                                </th>
                                <th>
                                    <a class="mx-auto" href="deleteContactMessage?id=<%=contacts.getId()%>">
                                        <span class=" rounded-pill badge bg-danger">delete</span>
                                    </a>
                                </th>
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