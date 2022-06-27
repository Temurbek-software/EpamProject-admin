<%--
  Created by IntelliJ IDEA.
  User: Temurbek
  Date: 6/24/2022
  Time: 8:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%--Modal add user--%>
<%--Modal edit user--%>
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
                    data-bs-toggle="modal" data-bs-target="#ModalForm"
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
                </tr>
                </tfoot>
                <tbody>
                <c:forEach var="users" items="${usersList}">
                    <tr>
                        <td><c:out value="${users.username}"/></td>
                        <td><c:out value="${users.fullName}"/></td>
                        <td><c:out value="${users.password}"/></td>
                        <td><c:out value="${users.phoneNumber}"/></td>
                        <td><c:out value="${users.email}"/></td>
                        <td><c:out value="${users.created_at}"/></td>
                        <td>
                            <a class="mx-auto" data-bs-toggle="modal" data-bs-target="#ModalForm"
                               href="edit?id=<c:out value='${users.id}'/>">
                                <i style="color:#39C0ED" class="fas fa-highlighter"></i>
                            </a>
                        </td>&nbsp;
                        <td data-bs-toggle="tooltip" data-bs-placement="top" title="delete">
                            <a class="mx-auto" data-bs-toggle="modal" href="#exampleModal">
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

<div class="modal fade" id="ModalForm" tabindex="-1" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <!-- Login Form -->
            <c:if test="${currentUser != null}">
            <form action="update" method="post">
                </c:if>
                <c:if test="${currentUser == null}">
                <form action="create" method="post">
                    </c:if>
                    <div class="modal-header">

                        <c:if test="${currentUser != null}">
                            <h5 class="modal-title">Edit user</h5>
                        </c:if>
                        <c:if test="${currentUser == null}">
                         <h5 class="modal-title">Add New User</h5>
                        </c:if>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <div class="mb-3">
                            <label for="username">Username</label>
                            <input type="text" value="<c:out value='${currentUser.username}' />" name="username"
                                   class="form-control" id="username"
                                   placeholder="Enter Username">
                        </div>

                        <div class="mb-3">
                            <label for="fullName">Full name</label>
                            <input type="text" value="<c:out value='${currentUser.fullName}' />" name="fullName" class="form-control" id="fullName"
                                   placeholder="Enter full name">
                        </div>
                        <div class="mb-3">
                            <label for="password">Password</label>
                            <input type="password" value="<c:out value='${currentUser.password}' />" name="password" class="form-control" id="password"
                                   placeholder="Enter Password">
                        </div>
                        <div class="mb-3">
                            <label for="phoneNumber">Phone number</label>
                            <input type="text" value="<c:out value='${currentUser.phoneNumber}' />" name="phoneNumber" class="form-control" id="phoneNumber"
                                   placeholder="Enter phone number">
                        </div>
                        <div class="mb-3">
                            <label for="email">Email</label>
                            <input type="email" value="<c:out value='${currentUser.email}' />" name="email" class="form-control" id="email"
                                   placeholder="Enter Email">
                        </div>
                    </div>
                    <div class="modal-footer pt-4">
<%--                        <button type="submit" class="btn btn-danger mx-auto w-100">Cancel</button>--%>
                        <button type="submit" class="btn btn-success mx-auto w-100">Save</button>
                    </div>
                </form>
        </div>
    </div>
</div>
<%--Modal delete user--%>
<!-- Modal HTML -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Are you sure?</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                Do you really want to delete these records? This process cannot be undone.
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                <button type="button" class="btn btn-primary">
                    <a style="text-decoration: none; color: white"
                       href="delete?id=<c:out value='${users.id}'/>">
                        Delete
                    </a>
                </button>
            </div>
        </div>
    </div>
</div>
