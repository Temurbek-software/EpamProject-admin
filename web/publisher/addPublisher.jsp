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
    <title>Add new publisher</title>
    <jsp:include page="../header/head.jsp"></jsp:include>
    <style>
        .ck-content {
            min-height: 200px !important;
        }
    </style>
</head>
<body class="sb-nav-fixed">
<jsp:include page="../header/header.jsp"></jsp:include>
<div id="layoutSidenav">
    <jsp:include page="../header/Navbars.jsp"></jsp:include>

    <div id="layoutSidenav_content">
        <main>
            <div class="container px-4">
                <div class="row">
                    <div class="col-10 offset-1">
                        <h1 class="mt-4">Add Publisher</h1>
                        <ol class="breadcrumb mb-4">
                            <c:if test="${currentPub != null}">
                                <li class="breadcrumb-item active">Editig page</li>
                            </c:if>
                            <c:if test="${currentPub == null}">
                                <li class="breadcrumb-item active">Adding page</li>
                            </c:if>
                        </ol>
<%--                        Erors saving publishers--%>
                        <c:if test="${msg!=null}">
                            <div class="alert alert-warning alert-dismissible fade show" role="alert">
                                <strong>Hey!</strong><c:out value='${msg}'/>
                            </div>
                        </c:if>
                        <c:if test="${result==false}">
                            <div class="alert alert-warning alert-dismissible fade show" role="alert">
                                <strong>Hey!</strong> We got Arror in saving
                                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                        </c:if>
                        <c:if test="${result==true}">
                            <div class="alert alert-success" role="alert">
                                We made it and  saved data.
                            </div>
                        </c:if>
                      <c:if test="${currentPub==null}">
                        <form action="/addPub" method="post">
                        </c:if>
                            <c:if test="${currentPub!=null}">
                            <form action="/updatePub" method="post">
                            </c:if>
                            <!-- Name input -->
                                <c:if test="${currentPub != null}">
                                    <input type="hidden" name="id" value="<c:out value='${currentPub.id}' />" />
                                </c:if>
                            <div class="form-outline mb-4">
                                <input type="text"
                                       value="<c:out value='${currentPub.nameOfCompany}' />"
                                       name="nameOfCompany" id="form4Example1" class="form-control border"/>
                                <label class="form-label" for="form4Example1">Name for company</label>
                            </div>
                            <div class="form-outline mb-4">
                                <input type="text"
                                       value="<c:out value='${currentPub.username}' />"
                                       name="username" id="form4Example2" class="form-control border"/>
                                <label class="form-label" for="form4Example1">username</label>
                            </div>
                            <div class="form-outline mb-4">
                                <input type="text"
                                       value="<c:out value='${currentPub.address}' />"
                                       name="address" class="form-control border"/>
                                <label class="form-label">Address</label>
                            </div>
                            <div class="form-outline mb-4">
                                <input type="text"
                                       value="<c:out value='${currentPub.phoneNumber}' />"
                                       name="phoneNumber" class="form-control border"/>
                                <label class="form-label">Phone number</label>
                            </div>
                            <div class="form-outline mb-4">
                                <input type="email"
                                       value="<c:out value='${currentPub.email}' />"
                                       name="email" class="form-control border"/>
                                <label class="form-label">Email</label>
                            </div>
                            <div class="form-outline mb-4">
                                <input type="password"
                                       value="<c:out value='${currentPub.password}' />"
                                       name="password" class="form-control border"/>
                                <label class="form-label">Password</label>
                            </div>
                            <!-- Message input -->
                            <div class="form-outline mb-4">
                                <label class="form-label">Description</label>
                                <textarea name="textData"
                                          id="editor" style="min-height: 200px">
                                </textarea>
                            </div>

                            <!-- Submit button -->
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
<script src="https://cdn.ckeditor.com/ckeditor5/34.1.0/classic/ckeditor.js"></script>
<script>
    ClassicEditor
        .create(document.querySelector('#editor'))
        .then(editor => {
            console.log(editor);
        })
        .catch(error => {
            console.error(error);
        });
</script>
</body>
</html>