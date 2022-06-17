<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 6/13/2022
  Time: 11:25 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</head>
<body class="container-fluid">
<header class="row">
    <%@include file="pageLayout/navBar.jsp"%>
</header>
<section class="row">
    <div class="col-md-3">
        <%@include file="pageLayout/sideBar.jsp"%>
    </div>
    <div class="col-md-9">
        <main>
            <h1>Mượn sách</h1>
            <c:if test="${mess!=null}">
                <p>${mess}</p>
            </c:if>
            <br><br>
            <form class="row g-3 border" action="/servlet?action=borrowBook" method="post">
                <div class="col-md-12">
                    <label for="borrowCode" class="form-label">Mã mượn sách</label>
                    <c:if test="${borrowCode == null}">
                        <input type="text" class="form-control" id="borrowCode" name="borrowCode" required pattern="^MS-[\d]{4}$">
                    </c:if>
                    <c:if test="${borrowCode != null}">
                        <input type="text" class="form-control" id="borrowCode" name="borrowCode" required
                               pattern="^MS-[\d]{4}$" value="${borrowCode}">
                    </c:if>
                    <c:if test="${errors!=null}">
                        <p class="text-danger">${errors.get("borrowCode")}</p>
                    </c:if>
                </div>
                <div class="col-md-12">
                    <label for="bookName" class="form-label">Tên sách</label>
                    <input type="text" class="form-control" id="bookName" name="bookName" value="${bookName}" readonly>
                    <c:if test="${errors!=null}">
                        <p class="text-danger">${errors.get("bookName")}</p>
                    </c:if>
                </div>
                <div class="col-md-6">
                    <label class="form-label">Tên học sinh</label>
                    <select name="studentName">
                        <c:forEach items="${studentList}" var="student">
                            <option value="${student.studentId}">${student.studentName}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="col-md-12">
                    <label for="startDate" class="form-label">Ngày mượn sách </label>
                    <c:if test="${startDate == null}">
                        <input type="text" class="form-control" id="startDate" name="startDate" value="${today}" readonly>
                    </c:if>
                    <c:if test="${startDate != null}">
                        <input type="text" class="form-control" id="startDate" name="startDate" value="${startDate}"
                               readonly>
                    </c:if>
                </div>
                <div class="col-md-12">
                    <label for="endDate" class="form-label">Ngày trả sách </label>
                    <input type="date" class="form-control" id="endDate" name="endDate" required>
                    <c:if test="${errors!=null}">
                        <p class="text-danger">${errors.get("date")}</p>
                    </c:if>
                </div>
                <div class="col-6">
                    <button type="submit" class="btn btn-primary" style="background: lightgrey">Mượn sách</button>
                </div>
                <div class="col-6">
                    <a href="/servlet"><button type="button" class="btn btn-primary" style="background: lightgrey">Hủy</button></a>
                </div>
            </form>
        </main>
    </div>

</section>
<footer class="row">
    <%@include file="pageLayout/footer.jsp"%>
</footer>
</body>
</html>
