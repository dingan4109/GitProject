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
    <title></title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
            crossorigin="anonymous"></script>
</head>
<body class="container-fluid">
<header class="row">
    <%@include file="/pageLayout/navBar.jsp" %>
</header>
<section class="row">
    <div class="col-md-3">
        <%@include file="/pageLayout/sideBar.jsp" %>
    </div>
    <div class="col-md-9">
        <main>
            <h1>Tạo mặt bằng mới</h1>
            <c:if test="${mess!=null}">
                <p>${mess}</p>
            </c:if>
            <a href="/servlet">
                <button class="btn btn-primary me-md-2"
                        style="background: lightgrey">Quay lại danh sách mặt bằng
                </button>
            </a>
            <br><br>
            <form class="row g-3 border" method="post">

                <div class="col-md-12">
                    <div class="col-md-12">
                        <label for="maMatBang" class="form-label">Mã mặt bằng</label>
                        <input type="text" class="form-control" id="maMatBang" name="maMatBang" required
                               pattern="^[A-Z\d]{3}-[A-Z\d]{2}-[A-Z\d]$" value="${matBang.maMatBang}">
                        <c:if test="${errors!=null}">
                            <p class="text-danger">${errors.get("maMatBang")}</p>
                        </c:if>
                    </div>
                    <div>
                        <label class="form-label">Trạng thái</label>
                        <select name="trangThai">
                            <c:forEach items="${trangThaiList}" var="trangThai">
                                <c:if test="${trangThai.maTrangThai == matBang.maTrangThai}">
                                    <option value="${trangThai.maTrangThai}" selected>${trangThai.tenTrangThai}</option>
                                </c:if>
                                <c:if test="${trangThai.maTrangThai != matBang.maTrangThai}">
                                    <option value="${trangThai.maTrangThai}">${trangThai.tenTrangThai}</option>
                                </c:if>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-md-12">
                        <label for="dienTich" class="form-label">Diện tích</label>
                        <input type="text" class="form-control" id="dienTich" name="dienTich" required
                               value="${matBang.dienTich}">
                        <c:if test="${errors!=null}">
                            <p class="text-danger">${errors.get("dienTich")}</p>
                        </c:if>
                    </div>

                    <div class="col-md-12">
                        <label class="form-label">Số tầng</label>
                        <select name="soTang">
                        <c:forEach begin="1" end="15" var="i">
                            <option>${i}</option>
                        </c:forEach>
                        </select>
                    </div>
                    <div>
                        <label class="form-label">Loại mặt bằng</label>
                        <select name="loaiMatBang">
                            <c:forEach items="${loaiMatBangList}" var="loaiMatBang">
                                <c:if test="${loaiMatBang.maLoaiMatBang == loaiMatBang.maLoaiMatBang}">
                                    <option value="${loaiMatBang.maLoaiMatBang}" selected>${loaiMatBang.tenLoaiMatBang}</option>
                                </c:if>
                                <c:if test="${loaiMatBang.maLoaiMatBang != loaiMatBang.maLoaiMatBang}">
                                    <option value="${loaiMatBang.maLoaiMatBang}" selected>${loaiMatBang.tenLoaiMatBang}</option>
                                </c:if>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-md-12">
                        <label for="giaTien" class="form-label">Giá tiền</label>
                        <input type="text" class="form-control" id="giaTien" name="giaTien" required
                               value="${matBang.giaTien}">
                        <c:if test="${errors!=null}">
                            <p class="text-danger">${errors.get("maMatBang")}</p>
                        </c:if>
                    </div>
                    <div class="col-md-12">
                        <label for="ngayBatDau" class="form-label">Ngày bắt đầu</label>
                        <input type="date" class="form-control" id="ngayBatDau" name="ngayBatDau" required
                               value="${matBang.ngayBatDau}">
                    </div>

                    <div class="col-md-12">
                        <label for="ngayKetThuc" class="form-label">Ngày kết thúc</label>
                        <input type="date" class="form-control" id="ngayKetThuc" name="ngayKetThuc" required
                               value="${matBang.ngayKetThuc}">
                        <c:if test="${errors!=null}">
                            <p class="text-danger">${errors.get("ngay")}</p>
                        </c:if>
                    </div>
                    <div class="col-12">
                        <button type="submit" class="btn btn-primary" style="background: lightgrey">Create contract
                            detail
                        </button>
                    </div>
            </form>
        </main>
    </div>

</section>
<footer class="row">
    <%@include file="/pageLayout/footer.jsp" %>
</footer>
</body>
</html>
