<%@ page import="services.MessageService" %>
<%@ page import="java.util.List" %>
<%@ page import="entity.Complain" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.text.ParseException" %>
<%@ page import="database.DB" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="payload.ComplainDTO" %>
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
            <%--            <c:if test="${msgDelete!=null}">--%>
            <%--                <div class="alert alert-primary alert-dismissible fade show" role="alert">--%>
            <%--                    <strong>Hey! </strong><c:out value='${msgDelete}'/>--%>
            <%--                </div>--%>
            <%--            </c:if>--%>
            <div class="container-fluid px-4">
                <h1 class="mt-4">Dashboard</h1>
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
                                <th>Message</th>
                                <th>UserName</th>
                                <th>Publisher</th>
                                <th>Created time</th>
                            </tr>
                            </thead>
                            <tbody>
                            <%
                                MessageService messageService = new MessageService();
                                List<ComplainDTO> complainDTOS = messageService.getAllComplainMsg();
                                for (ComplainDTO complainDTO : complainDTOS) {
                            %>
                                <tr>
                                    <th><%=complainDTO.getId()%></th>
                                    <th><%=complainDTO.getMessage()%>></th>
                                    <th><%=complainDTO.getUsername()%></th>
                                    <th><%=complainDTO.getPublisherName()%></th>
                                    <th><%=complainDTO.getCreated_at()%></th>
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