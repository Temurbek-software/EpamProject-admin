<%--
  Created by IntelliJ IDEA.
  User: Temurbek
  Date: 6/17/2022
  Time: 11:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="card mb-4">
    <div class="card-header">
        <i class="fas fa-table me-1"></i>
        Table of users
    </div>
    <div class="card-body">
        <table id="datatablesSimple">
            <thead>
            <tr>
                <th>User name</th>
                <th>Full Name</th>
                <th>password</th>
                <th>phoneNumber</th>
                <th>email</th>
                <th>CreatedTime</th>
            </tr>
            </thead>
            <tfoot>
            <tr>
                <th>User name</th>
                <th>Full Name</th>
                <th>password</th>
                <th>phoneNumber</th>
                <th>email</th>
                <th>CreatedTime</th>
            </tr>
            </tfoot>
            <tbody>
            <c:forEach var="users" items="${usersList}">
                <tr>
                    <td><c:out value="${users.username}" /></td>
                    <td><c:out value="${users.fullName}" /></td>
                    <td><c:out value="${users.password}" /></td>
                    <td><c:out value="${users.phoneNumber}" /></td>
                    <td><c:out value="${users.email}" /></td>
                    <td><c:out value="${users.createdTime}" /></td>
                </tr>
            </c:forEach>

            </tbody>
        </table>
    </div>
</div>
