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
        <h1>Book List</h1>
        <div class="row">
            <%--            SEARCHING--%>
<%--            <div class="col-md-9">--%>
<%--                <form action="/customerServlet" method="get">--%>
<%--                    <input hidden name="action" value="search">--%>
<%--                    <div class="row">--%>
<%--                        <div class="col">--%>
<%--                            <input type="text" name="name" placeholder="Input name">--%>
<%--                        </div>--%>
<%--                        <div class="col">--%>
<%--                            <input type="text" name="address" placeholder="Input address">--%>
<%--                        </div>--%>
<%--                        <div class="col">--%>
<%--                            <select name="typeId">--%>
<%--                                <option value="">Select</option>--%>
<%--                                <c:forEach items="${}" var="item">--%>
<%--                                    <option--%>
<%--                                            value="${customerType.getCustomerTypeId()}">--%>
<%--                                            ${customerType.getCustomerTypeName()}</option>--%>
<%--                                </c:forEach>--%>
<%--                            </select>--%>
<%--                        </div>--%>
<%--                        <div class="col">--%>
<%--                            <button type="submit">Search</button>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                </form>--%>
<%--            </div>--%>
            <%--            END SEARCH--%>
        </div>
        <br><br>
        <c:if test="${mess!=null}">
            <p>${mess}</p>
        </c:if>
        <table class="table table-striped table-hover" id="contract_detail_table">
            <thead>
            <tr>
                <th>Mã sách</th>
                <th>Tên sách</th>
                <th>Tác giả</th>
                <th>Mô tả</th>
                <th>Số lượng</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${bookList}" var="book">
                <tr>
                    <td>${book.bookId}</td>
                    <td>${book.bookName}</td>
                    <td>${book.bookAuthor}</td>
                    <td>${book.description}</td>
                    <td>${book.bookQuantity}</td>
                    <td><a href="/servlet?action=borrowBook&bookName=${book.bookName}">
                            <button class="btn btn-primary me-md-2"
                                    style="background: lightgrey">Mượn sách
                            </button>
                    </a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <!-- Modal -->
    <div class="modal fade" id="delModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
         aria-labelledby="staticBackdropLabel" aria-hidden="true">
        <div class="modal-dialog">
            <form action="/servlet?action=delete" method="post">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="staticBackdropLabel">Xác nhận xóa?</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <input hidden type="text" id="id" name="maMatBangXoa">
                        <span>Bạn có xác nhận xóa <span id="showMatBangXoa"></span>không?</span>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Không</button>
                        <button type="submit" class="btn btn-primary">Xóa</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <%--    End Modal--%>
</section>
<footer class="row">
    <%@include file="pageLayout/footer.jsp" %>
</footer>
</body>
</html>
<%--<script>--%>
<%--    function employeeInfo(maMatBangXoa) {--%>
<%--        document.getElementById("id").value = maMatBangXoa;--%>
<%--        document.getElementById("showMatBangXoa").innerText = maMatBangXoa;--%>
<%--    }--%>
<%--</script>--%>