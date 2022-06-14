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
<body>
<header class="row">
    <%@include file="/pageLayout/navBar.jsp"%>
</header>
<section class="row">
    <div class="col-md-3">
        <%@include file="/pageLayout/sideBar.jsp"%>
    </div>
    <div class="col-md-9">
        <h1>Danh sách mặt bằng</h1>
        <div class="row">
            <div class="col-md-3">
                <a href="/servlet?action=create">
                    <button class="btn btn-primary me-md-2"
                            style="background: lightgrey">Tạo mặt bằng mới
                    </button>
                </a>
            </div>
<%--            SEARCHING--%>
            <div class="col-md-9">
                <form action="/servlet" method="get">
                    <input hidden name="action" value="search">
                    <div class="row">
                        <div class="col">
                            <input type="text" name="giaTien" placeholder="Nhập giá tiền">
                        </div>
                        <div class="col">
                            <select name="soTang">
                                <option value="">Select</option>
                                <c:forEach begin="1" end="15" var="i">
                                    <option>${i}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="col">
                            <select name="loaiMatBang">
                                <option value="">Select</option>
                                <c:forEach items="${loaiMatBangList}" var="item">
                                    <option
                                            value="${item.maLoaiMatBang}">
                                            ${item.tenLoaiMatBang}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="col">
                            <button type="submit">Search</button>
                        </div>
                    </div>
                </form>
            </div>
<%--            END SEARCH--%>
        </div>
        <br><br>
        <c:if test="${mess!=null}">
            <p>${mess}</p>
        </c:if>
        <table class="table table-striped table-hover" id="contract_detail_table">
            <thead>
            <tr>
                <th>Mã mặt bằng</th>
                <th>Trạng thái</th>
                <th>Diện tích</th>
                <th>Số tầng</th>
                <th>Loại mặt bằng</th>
                <th>Giá tiền</th>
                <th>Ngày bắt đầu</th>
                <th>Ngày kết thúc</th>
                <th>Edit</th>
                <th>Delete</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${matBangList}" var="matBang">
                <tr>
                    <td>${matBang.maMatBang}</td>
                    <c:forEach items="${trangThaiList}" var="trangThai">
                        <c:if test="${trangThai.maTrangThai == matBang.maTrangThai}">
                            <td><c:out value="${trangThai.tenTrangThai}"></c:out></td>
                        </c:if>
                    </c:forEach>
                    <td>${matBang.dienTich}</td>
                    <td>${matBang.soTang}</td>
                    <c:forEach items="${loaiMatBangList}" var="loaiMatBang">
                        <c:if test="${loaiMatBang.maLoaiMatBang == matBang.maLoaiMatBang}">
                            <td><c:out value="${loaiMatBang.tenLoaiMatBang}"></c:out></td>
                        </c:if>
                    </c:forEach>
                    <td>${matBang.giaTien}</td>
                    <td>${matBang.ngayBatDau}</td>
                    <td>${matBang.ngayKetThuc}</td>

                    <td><a
                            href="#">
                        <svg
                                xmlns="http://www.w3.org/2000/svg"
                                width="16" height="16" fill="currentColor"
                                class="bi bi-pencil-square" viewBox="0 0 16 16">
                            <path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"/>
                            <path fill-rule="evenodd"
                                  d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z"/>
                        </svg>
                    </a></td>
                    <td><a onclick="del('${matBang.maMatBang}')"
                           data-bs-toggle="modal"
                           data-bs-target="#delModal">
                        <svg
                                xmlns="http://www.w3.org/2000/svg"
                                width="16"
                                height="16" fill="currentColor"
                                class="bi bi-trash3" viewBox="0 0 16 16">
                            <path d="M6.5 1h3a.5.5 0 0 1 .5.5v1H6v-1a.5.5 0 0 1 .5-.5ZM11 2.5v-1A1.5 1.5 0 0 0 9.5 0h-3A1.5 1.5 0 0 0 5 1.5v1H2.506a.58.58 0 0 0-.01 0H1.5a.5.5 0 0 0 0 1h.538l.853 10.66A2 2 0 0 0 4.885 16h6.23a2 2 0 0 0 1.994-1.84l.853-10.66h.538a.5.5 0 0 0 0-1h-.995a.59.59 0 0 0-.01 0H11Zm1.958 1-.846 10.58a1 1 0 0 1-.997.92h-6.23a1 1 0 0 1-.997-.92L3.042 3.5h9.916Zm-7.487 1a.5.5 0 0 1 .528.47l.5 8.5a.5.5 0 0 1-.998.06L5 5.03a.5.5 0 0 1 .47-.53Zm5.058 0a.5.5 0 0 1 .47.53l-.5 8.5a.5.5 0 1 1-.998-.06l.5-8.5a.5.5 0 0 1 .528-.47ZM8 4.5a.5.5 0 0 1 .5.5v8.5a.5.5 0 0 1-1 0V5a.5.5 0 0 1 .5-.5Z"/>
                        </svg>
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
                        <span>Bạn có xác nhận xóa mặt bằng <span id="showMatBangXoa"></span> không?</span>
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
    <%@include file="/pageLayout/footer.jsp"%>
</footer>
</body>
</html>
<script>
    function del(maMatBangXoa) {
        document.getElementById("id").value = maMatBangXoa;
        document.getElementById("showMatBangXoa").innerText = maMatBangXoa;
    }
</script>