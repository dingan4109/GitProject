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
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
            crossorigin="anonymous"></script>
</head>
<body>
<header class="row">
    <%@include file="pageLayout/navBar.jsp" %>
</header>
<section class="row">
    <div class="col-md-3">
        <%@include file="pageLayout/sideBar.jsp" %>
    </div>
    <div class="col-md-9">
        <h1>Borrow Code List</h1>
        <div class="row">
        </div>
        <br><br>
        <c:if test="${mess!=null}">
            <p>${mess}</p>
        </c:if>
        <table class="table table-striped table-hover" id="contract_detail_table">
            <thead>
            <tr>
                <th>Mã mượn sách</th>
                <th>Tên sách</th>
                <th>Tên học sinh</th>
                <th>Ngày mượn</th>
                <th>Ngày trả</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${borrowCodes}" var="item">
                <tr>
                    <td>${item.borrowCode}</td>
                    <c:forEach items="${bookList}" var="book">
                        <c:if test="${item.bookId==book.bookId}">
                            <td>${book.bookName}</td>
                        </c:if>
                    </c:forEach>
                    <c:forEach items="${studentList}" var="student">
                        <c:if test="${item.studentId==student.studentId}">
                            <td>${student.studentName}</td>
                        </c:if>
                    </c:forEach>
                    <td>${item.borrowStartDate}</td>
                    <td>${item.borrowEndDate}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</section>
<footer class="row">
    <%@include file="pageLayout/footer.jsp" %>
</footer>
</body>
</html>
