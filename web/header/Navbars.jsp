<%--
  Created by IntelliJ IDEA.
  User: Temurbek
  Date: 6/27/2022
  Time: 3:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="layoutSidenav_nav">
    <nav class="sb-sidenav accordion sb-sidenav-dark" id="sidenavAccordion">
        <div class="sb-sidenav-menu">
            <div class="nav">
                <div class="sb-sidenav-menu-heading">Core</div>
                <a class="nav-link" ref="<%=request.getContextPath()%>/">
                    <div class="sb-nav-link-icon"><i class="fas fa-tachometer-alt"></i></div>
                    Dashboard
                </a>
                <div class="sb-sidenav-menu-heading">Interface</div>
                <%--                    Category--%>
                <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapseLayouts"
                   aria-expanded="false" aria-controls="collapseLayouts">
                    <div class="sb-nav-link-icon"><i class="fas fa-columns"></i></div>
                    Category
                    <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                </a>
                <div class="collapse" id="collapseLayouts" aria-labelledby="headingOne"
                     data-bs-parent="#sidenavAccordion">
                    <nav class="sb-sidenav-menu-nested nav">
                        <a class="nav-link" href="<%=request.getContextPath()%>/users">add category </a>
                        <a class="nav-link" href="layout-sidenav-light.html">manage category</a>
                    </nav>
                </div>

<%--                for post--%>
                <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapsePages"
                   aria-expanded="false" aria-controls="collapsePages">
                    <div class="sb-nav-link-icon">
                        <i class="fas fa-book-open"></i>
                    </div>
                    Posts
                    <div class="sb-sidenav-collapse-arrow">
                        <i class="fas fa-angle-down"></i>
                    </div>
                </a>
                <div class="collapse" id="collapsePages" aria-labelledby="headingTwo"
                     data-bs-parent="#sidenavAccordion">
                    <nav class="sb-sidenav-menu-nested nav accordion" id="sidenavAccordionPages">
                        <a href="<%=request.getContextPath()%>/showNewsForm" class="nav-link"
                           data-bs-target="#pagesCollapseAuth"
                           aria-controls="pagesCollapseAuth">
                            add news
                        </a>

                        <a class="nav-link collapsed" href="displayNews"
                           data-bs-target="#pagesCollapseError"
                           aria-controls="pagesCollapseError">
                            manage news
                        </a>
                        <a class="nav-link collapsed" href="deletedNews"
                           data-bs-target="#pagesCollapseError"
                           aria-controls="pagesCollapseError">
                            deleted news
                        </a>

                    </nav>

                </div>

<%--                for publisher --%>
                <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapsePagespublisher"
                   aria-expanded="false" aria-controls="collapsePagespublisher">
                    <div class="sb-nav-link-icon">
                        <i class="fas fa-book-open"></i>
                    </div>
                    Publisher
                    <div class="sb-sidenav-collapse-arrow">
                        <i class="fas fa-angle-down"></i>
                    </div>
                </a>
                <div class="collapse" id="collapsePagespublisher" aria-labelledby="headingTwo"
                     data-bs-parent="#sidenavAccordion">
                    <nav class="sb-sidenav-menu-nested nav accordion" >
                        <a href="<%=request.getContextPath()%>/displayPublisher" class="nav-link"
                           data-bs-target="#pagesCollapseAuth"
                           aria-controls="pagesCollapseAuth">
                           Add Publishers
                        </a>
                        <a  class="nav-link collapsed" href="managePublishers"
                           data-bs-target="#pagesCollapseError"
                           aria-controls="pagesCollapseError">
                            Manage publisher
                        </a>
                    </nav>
                </div>
                <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapseLayouts1"
                   aria-expanded="false" aria-controls="collapseLayouts">
                    <div class="sb-nav-link-icon"><i class="fas fa-columns"></i></div>
                    Messages
                    <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                </a>
                <div class="collapse" id="collapseLayouts1" aria-labelledby="headingOne"
                     data-bs-parent="#sidenavAccordion">
                    <nav class="sb-sidenav-menu-nested nav">
                        <a class="nav-link" href="/users">Contact message</a>
                        <a class="nav-link" href="layout-sidenav-light.html">Complaining message</a>
                    </nav>
                </div>
<%--                For users--%>
                <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapseLayouts2"
                   aria-expanded="false" aria-controls="collapseLayouts">
                    <div class="sb-nav-link-icon"><i class="fas fa-columns"></i></div>
                    Users
                    <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                </a>
                <div class="collapse" id="collapseLayouts2" aria-labelledby="headingOne"
                     data-bs-parent="#sidenavAccordion">
                    <nav class="sb-sidenav-menu-nested nav">
                        <a class="nav-link" href="/addUser">Add users</a>
                        <a class="nav-link" href="/userList">Manage users</a>
                        <a class="nav-link" href="">Deleted users</a>
                    </nav>
                </div>
<%--                Addional page--%>
                <div class="sb-sidenav-menu-heading">Additional</div>

                <a class="nav-link" href="/users">
                    <div class="sb-nav-link-icon"><i class="fas fa-table"></i></div>
                    Users
                </a>
            </div>
        </div>
        <div class="sb-sidenav-footer">
            <div class="small">Come on !!</div>
        </div>
    </nav>
</div>
