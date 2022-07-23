<%@ page import="services.CookieService" %>
<%@ page import="entity.Publisher" %>
<%@ page import="payload.PublisherDto" %><%--
  Created by IntelliJ IDEA.
  User: Temurbek
  Date: 6/24/2022
  Time: 7:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    CookieService service = new CookieService();
    Publisher publisher = service.getPublisher(request);
    PublisherDto publisherDto = service.getAdminName(request);
%>
<nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
    <!-- Navbar Brand-->
    <a class="navbar-brand ps-3" href="<%=request.getContextPath()%>/">Online news portal</a>
    <!-- Sidebar Toggle-->
    <button class="btn btn-link btn-sm order-1 order-lg-0 me-4 me-lg-0" id="sidebarToggle" href="#!"><i
            class="fas fa-bars"></i></button>
    <!-- Navbar Search-->
    <form class="d-none d-md-inline-block form-inline ms-auto me-0 me-md-3 my-2 my-md-0">
        <div class="input-group">
            <input class="form-control" type="text" placeholder="Search for..." aria-label="Search for..."
                   aria-describedby="btnNavbarSearch"/>
            <button class="btn btn-primary" id="btnNavbarSearch" type="button"><i class="fas fa-search"></i></button>
        </div>
    </form>

    <%
        if (publisher.getId() == 0) {
    %>
    <ul class="navbar-nav ms-auto ms-md-0 me-3 me-lg-4">
        <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" id="navbarDropdown1"
               href="#" role="button" data-bs-toggle="dropdown"
               aria-expanded="false">
                <%=publisherDto.getUsername()%>
                <i class="fas fa-user fa-fw"></i></a>
            <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown1">
<%--                <li><a class="dropdown-item" href="userProfile">Settings</a></li>--%>
<%--                <li><a class="dropdown-item" href="messaging">Message</a></li>--%>
                <li><hr class="dropdown-divider"/></li>
                <li><a class="dropdown-item" href="/logout">Logout</a></li>
            </ul>
        </li>
    </ul>

    <%
    } else {
        if (publisher.isActive()) {
    %>
    <ul class="navbar-nav ms-auto ms-md-0 me-3 me-lg-4">
        <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown"
               aria-expanded="false">
                <%=publisher.getUsername()%>
                <i class="fas fa-user fa-fw"></i></a>
            <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">
                <li><a class="dropdown-item" href="userProfile">Settings</a></li>
                <li><a class="dropdown-item" href="messaging">Message</a></li>
                <li>
                    <hr class="dropdown-divider"/>
                </li>
                <li><a class="dropdown-item" href="/logout">Logout</a></li>
            </ul>
        </li>
    </ul>

    <%
            }
        }
    %>
    <!-- Navbar-->
    <%--    <c:if test="${publisherSession!=null}">--%>

    <%--    </c:if>--%>
</nav>