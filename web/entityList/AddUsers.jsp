<%--
  Created by IntelliJ IDEA.
  User: Temurbek
  Date: 6/27/2022
  Time: 1:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Add new user</title>
    <jsp:include page="../header/head.jsp"></jsp:include>

</head>
<body class="sb-nav-fixed">
<jsp:include page="../header/header.jsp"></jsp:include>
<div id="layoutSidenav">
    <jsp:include page="../header/Navbars.jsp"></jsp:include>

    <div id="layoutSidenav_content">
        <main>
            <c:if test="${msgUpdate!=null}">
                <div class="alert alert-warning alert-dismissible fade show" role="alert">
                    <strong>Hey! </strong><c:out value='${msgUpdate}'/>
                </div>
            </c:if>
            <div class="container px-4">
                <div class="row">
                    <div class="col-10 offset-1">
                        <h1 class="mt-4">Add Users</h1>
                        <ol class="breadcrumb mb-4">
                            <c:if test="${currentUser != null}">
                                <li class="breadcrumb-item active">Editig page</li>
                            </c:if>
                            <c:if test="${currentUser == null}">
                                <li class="breadcrumb-item active">Adding page</li>
                            </c:if>
                        </ol>
                        <%--                        Erors saving publishers--%>

                        <%--                        ---------------------------------%>
                        <c:if test="${currentUser==null}">
                        <form action="/createUsers" method="post">
                            </c:if>
                            <c:if test="${currentUser!=null}">
                            <form action="/updateUsers" method="post">
                                </c:if>
                                <!-- Name input -->
                                <c:if test="${currentUser != null}">
                                    <input type="hidden" name="id" value="<c:out value='${currentUser.id}' />"/>
                                </c:if>
                                <div class="form-outline mb-4">
                                    <label class="form-label" >Username</label>
                                    <input type="text"
                                           value="<c:out value='${currentUser.username}' />"
                                           name="username" class="form-control border"/>
                                </div>
                                <div class="form-outline mb-4">
                                    <label class="form-label" >Full name</label>
                                    <input type="text"
                                           value="<c:out value='${currentUser.fullName}' />"
                                           name="fullName" id="form4Example2" class="form-control border"/>
                                </div>
                                <div class="form-outline mb-4">
                                    <label class="form-label" >Password</label>

                                    <input type="text"
                                           value="<c:out value='${currentUser.password}' />"
                                           name="password" class="form-control border"/>
                                </div>
                                <div class="form-outline mb-4">
                                    <label class="form-label" >Phone number</label>

                                    <input type="text"
                                           value="<c:out value='${currentUser.phoneNumber}' />"
                                           name="phoneNumber" class="form-control border"/>
                                </div>
                                <div class="form-outline mb-4">
                                    <label class="form-label" >Email</label>
                                    <input type="email"
                                           value="<c:out value='${currentUser.email}' />"
                                           name="email" class="form-control border"/>
                                </div>
                                <div class="mt-2">
                                    <button type="submit" class="btn btn-primary  mb-4">Send</button>
                                    <button type="button" class="btn btn-danger  mb-4">Discard</button>
                                </div>
                            </form>
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