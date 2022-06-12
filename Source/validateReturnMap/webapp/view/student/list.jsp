<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 5/20/2022
  Time: 6:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--thu vien jstl--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="bootstrap4.6.0/css/bootstrap.min.css">
    <script src="jquery/jquery-3.6.0.min.js"></script>
    <script src="bootstrap4.6.0/js/bootstrap.min.js"></script>
</head>
<body>
<a href="/student?action=add">Thêm mới</a>
<h1>Danh sách sinh viên</h1>
<c:if test="${mess !=null}">
    <p class="text-danger">${mess}</p>
</c:if>
<table class="table table-striped">
    <tr>
        <th>STT</th>
        <th>Id</th>
        <th>Name</th>
        <th>Gender</th>
        <th>Birthday</th>
        <th>Point</th>
        <th>Rank</th>
        <th>Account</th>
        <th>Class id</th>
        <th>Email</th>
        <th>Edit</th>
        <th>Delete</th>

    </tr>
    <c:forEach items="${studentList}" var="student" varStatus="status">
        <tr>
            <td>${status.count}</td>
            <td>${student.id}</td>
            <td>${student.name}</td>

            <c:if test="${student.gender}">
               <td>Nam</td>
            </c:if>
            <c:if test="${!student.gender}">
                <td>Nữ</td>
            </c:if>

            <td>${student.birthday}</td>
            <td>${student.point}</td>
            <c:choose>
                <c:when test="${student.point>=8}">
                    <td>Giỏi</td>
                </c:when>
                <c:when test="${student.point>=7}">
                    <td>Khá</td>
                </c:when>
                <c:when test="${student.point>=5}">
                    <td>Trung bình</td>
                </c:when>
                <c:otherwise>
                    <td>Yếu</td>
                </c:otherwise>
            </c:choose>
            <td>${student.account}</td>
            <td>${student.classId}</td>
                <%--Lấy thông tin tên lớp--%>
<%--            <c:forEach items="${classCGList}" var="cls">--%>
<%--                <c:if test="${cls.id==student.classId}">--%>
<%--                    <td>${cls.name}</td>--%>
<%--                </c:if>--%>
<%--            </c:forEach>--%>
            <td><c:out value="${student.email}"></c:out></td>
            <td><input type="button" class="btn btn-secondary" value="Edit"></td>
            <td>
                <button type="button" onclick="showInfo('${student.id}','${student.name}')"
                        class="btn btn-outline-danger" data-toggle="modal"
                        data-target="#staticBackdrop">
                    Delete
                </button>
            </td>
        </tr>
    </c:forEach>
</table>

<!-- Modal -->
<div class="modal fade" id="staticBackdrop" data-backdrop="static" data-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
    <div class="modal-dialog">
<%--        thêm thẻ form--%>
        <form action="/student?action=delete" method="post">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="staticBackdropLabel">Modal title</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <input  hidden type="text" id="idStudent" name="idDelete">
                <span>Bạn có muốn xóa sinh viên </span>
                <span class="text-danger" id="nameStudent"></span> không?<span/>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                <button type="submit" class="btn btn-primary">Delete</button>
            </div>
        </div>
    </form>
    </div>
</div>
<script>
    function showInfo(id,name) {
     document.getElementById("idStudent"). value= id;
     document.getElementById("nameStudent").innerText=name;
    }
</script>
</body>
</html>
