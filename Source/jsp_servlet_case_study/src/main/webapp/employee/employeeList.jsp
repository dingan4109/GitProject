<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 5/30/2022
  Time: 8:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Employee</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="jquery/jquery-3.6.0.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
            crossorigin="anonymous"></script>

    <style>
        a {
            text-decoration: none;
            color: black;
        }
    </style>
</head>
<body class="container-fluid">
<header class="border">
    <%@include file="/header.jsp" %>
</header>
<nav class="border">
    <%@include file="/navBar.jsp" %>
</nav>
<main>
    <div class="row border">
        <%@include file="/sideBar.jsp" %>
        <div class="col-md-9 border">
            <h1>Employee List</h1>
            <div class="row">
                <a href="/employeeServlet?action=createEmployee">
                    <button class="btn btn-primary me-md-2"
                            style="background: lightgrey">Create new
                        employee
                    </button>
                </a>
            </div>
            <br><br>
            <c:if test="${mess!=null}">
                <p>${mess}</p>
            </c:if>
            <table class="table table-striped table-hover">
                <tr>
                    <th>Id</th>
                    <th>Name</th>
                    <th>Birthday</th>
                    <th>Id Card</th>
                    <th>Salary</th>
                    <th>Phone</th>
                    <th>Email</th>
                    <th>Address</th>
                    <th>Position</th>
                    <th>Education degree</th>
                    <th>Division</th>
                    <th>User name</th>
                    <th>Edit</th>
                    <th>Delete</th>
                </tr>
                <c:forEach items="${employeeList}" var="employee">
                    <tr>
                        <td>${employee.getEmployeeId()}</td>
                        <td>${employee.getEmployeeName()}</td>
                        <td>${employee.getEmployeeBirthday()}</td>
                        <td>${employee.getEmployeeIdCard()}</td>
                        <td>${employee.getEmployeeSalary()}</td>
                        <td>${employee.getEmployeePhone()}</td>
                        <td>${employee.getEmployeeEmail()}</td>
                        <td>${employee.getEmployeeAddress()}</td>
                        <c:forEach items="${positionList}" var="position">
                            <c:if test="${employee.getPositionId() == position.getPositionId()}">
                                <td><c:out value="${position.getPositionName()}"></c:out></td>
                            </c:if>
                        </c:forEach>
                        <c:forEach items="${educationDegreeList}" var="educationDegree">
                            <c:if test="${employee.getEducationDegreeId() == educationDegree.getEducationDegreeId()}">
                                <td><c:out value="${educationDegree.getEducationDegreeName()}"></c:out></td>
                            </c:if>
                        </c:forEach>
                        <c:forEach items="${divisionList}" var="division">
                            <c:if test="${employee.getDivisionId() == division.getDivisionId()}">
                                <td><c:out value="${division.getDivisionName()}"></c:out></td>
                            </c:if>
                        </c:forEach>
                        <td>${employee.getUsername()}</td>
                        <td><a href="/employeeServlet?action=edit&employee_id=${employee.getEmployeeId()}">
                            <svg
                                    xmlns="http://www.w3.org/2000/svg"
                                    width="16" height="16" fill="currentColor"
                                    class="bi bi-pencil-square" viewBox="0 0 16 16">
                                <path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"/>
                                <path fill-rule="evenodd"
                                      d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z"/>
                            </svg>
                        </a></td>
                        <td><a
                                onclick="employeeInfo('${employee.getEmployeeId()}','${employee.getEmployeeName()}','${employee.getEmployeeBirthday()}',
                                        '${employee.getEmployeeIdCard()}','${employee.getEmployeeSalary()}',
                                        '${employee.getEmployeePhone()}','${employee.getEmployeeEmail()}',
                                        '${employee.getEmployeeAddress()}','${employee.getPositionId()}',
                                        '${employee.getEducationDegreeId()}','${employee.getDivisionId()}',
                                        '${employee.getUsername()}')"
                                data-bs-toggle="modal"
                                data-bs-target="#staticBackdrop">
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
            </table>

            <div>
                <nav aria-label="...">
                    <ul class="pagination">
                        <c:if test="${currentPage!=1}">
                            <li class="page-item">
                                <a class="page-link text-body"
                                   href="/employeeServlet?currentPage=${currentPage - 1}">Previous
                                </a>
                            </li>
                        </c:if>
                        <c:forEach begin="1" end="${noOfPages}" var="i">
                            <c:choose>
                                <c:when test="${currentPage == i}">
                                    <li
                                            class="page-item active"><a class="page-link text-body"
                                                                        href="/employeeServlet?currentPage=${i}">
                                            ${i}</a></li>
                                </c:when>
                                <c:otherwise>
                                    <li
                                            class="page-item"><a class="page-link text-body"
                                                                 href="/employeeServlet?currentPage=${i}">
                                            ${i}</a></li>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                        <c:if test="${currentPage != noOfPages}">
                            <li class="page-item">
                                <a class="page-link text-body"
                                   href="/employeeServlet?currentPage=${currentPage + 1}">Next</a>
                            </li>
                        </c:if>
                    </ul>
                </nav>
            </div>
        </div>
    </div>

    <!-- Modal -->
    <div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
         aria-labelledby="staticBackdropLabel" aria-hidden="true">
        <div class="modal-dialog">
            <form action="/employeerServlet?action=delete" method="post">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="staticBackdropLabel">Confirm to delete employee?</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <input hidden type="text" id="id" name="employee_id">
                        <table>
                            <tr>
                                <td>Id:</td>
                                <td id="id"></td>
                            </tr>
                            <tr>
                                <td>Name:</td>
                                <td id="name"></td>
                            </tr>
                            <tr>
                                <td>Birthday:</td>
                                <td id="birthday"></td>
                            </tr>
                            <tr>
                                <td>Id card:</td>
                                <td id="idCard"></td>
                            </tr>
                            <tr>
                                <td>Salary:</td>
                                <td id="salary"></td>
                            </tr>
                            <tr>
                                <td>Phone:</td>
                                <td id="phone"></td>
                            </tr>
                            <tr>
                                <td>Email:</td>
                                <td id="email"></td>
                            </tr>
                            <tr>
                                <td>Address:</td>
                                <td id="address"></td>
                            </tr>
                            <tr>
                                <td>Position:</td>
                                <td id="position"></td>
                            </tr>
                            <tr>
                                <td>Education degree:</td>
                                <td id="degree"></td>
                            </tr>
                            <tr>
                                <td>Division:</td>
                                <td id="division"></td>
                            </tr>
                            <tr>
                                <td>Username:</td>
                                <td id="username"></td>
                            </tr>
                        </table>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                        <button type="submit" class="btn btn-primary">Delete</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <%--    End Modal--%>
</main>
<footer class="border">
    <%@include file="/footer.jsp" %>
</footer>
</body>
</html>

<script>
    function customerInfo(id, name, birthday, idCard, salary, phone, email, address,position,degree,division,username) {
        document.getElementById("id").value = id;
        document.getElementById("name").innerText = name;
        document.getElementById("birthday").innerText = birthday;
        document.getElementById("idCard").innerText = idCard;
        document.getElementById("salary").innerText = salary;
        document.getElementById("phone").innerText = phone;
        document.getElementById("email").innerText = email;
        document.getElementById("address").innerText = address;
        document.getElementById("position").innerText = position;
        document.getElementById("degree").innerText = degree;
        document.getElementById("division").innerText = division;
        document.getElementById("username").innerText = username;
    }
</script>