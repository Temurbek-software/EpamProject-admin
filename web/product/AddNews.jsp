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
    <title>Add new post</title>
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
                        <h1 class="mt-4">Add news</h1>
                        <ol class="breadcrumb mb-4">
                            <li class="breadcrumb-item active">Home</li>
                        </ol>
                        <form action="/createNews" method="post" enctype="multipart/form-data">
                            <!-- Name input -->
                            <div class="form-outline mb-4">
                                <input type="text" name="titles" id="form4Example1" class="form-control border"/>
                                <label class="form-label" for="form4Example1">Post title</label>
                            </div>
                            <div class="form-outline mb-4">
                                <input type="text" name="description" class="form-control border"/>
                                <label class="form-label">Post description</label>
                            </div>
                            <select name="name1"   class="form-select mb-4" aria-label="Default select example">
                                <option selected>Choose category</option>
                                <c:forEach var="categ" items="${categoryList}">
                                <option value="<c:out value='${categ.id}' />"><c:out value="${categ.name}"/></option>
                                </c:forEach>
                            </select>
                            <!-- Message input -->
                            <div class="form-outline mb-4">
                                <label class="form-label">Message</label>
                                <textarea name="textData" id="editor" style="min-height: 200px">
                                </textarea>
                            </div>
                            <div class="form-outline mb-4">
                                <input type="url" name="sourcelinkTo" class="form-control border"/>
                                <label class="form-label">Link for post</label>
                            </div>
                            <label for="customFile" class="form-label">Upload multiple files</label>
                            <input class="form-control" name="photofile" type="file" id="customFile" multiple/>
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