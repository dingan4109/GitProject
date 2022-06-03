<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 5/30/2022
  Time: 8:44 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Create Customer Form</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
            crossorigin="anonymous"></script>

    <style>
        a{
            text-decoration: none;
            color: black;
        }
    </style>
</head>
<body class="container-fluid">
<header class="border">
    <%@include file="/header.jsp"%>
</header>
<nav class="border">
    <%@include file="/navBar.jsp"%>
</nav>
<main>
    <div class="row border">
        <%@include file="/sideBar.jsp"%>
        <div class="col-md-9 border">
            <h1>Edit customer form</h1>
            <c:if test="${mess!=null}">
                <p>${mess}</p>
            </c:if>
            <a href="/customerServlet?currentPage=1"><button class="btn btn-primary me-md-2"
                                                       style="background: lightgrey">Back to customer list</button></a>
            <form class="row g-3 border" method="post">
                <div class="col-md-3">
                    <label class="form-label">Customer type</label>
                    <select name="customer_type">
                        <c:forEach items="${customerTypes}" var="type">
                            <option>${type.getCustomerTypeName()}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="col-md-9">
                    <label for="name" class="form-label">Name</label>
                    <input type="text" class="form-control" id="name" name="name"
                           placeholder="${customer.getCustomerName()}">
                </div>
                <div class="col-md-6">
                    <label for="birthday" class="form-label">Birthday</label>
                    <input type="date" class="form-control" id="birthday" name="birthday"
                           placeholder="${customer.getCustomerBirthday()}">
                </div>
                <div class="col-6">
                    <label for="gender" class="form-label">Gender</label>
                    <input type="text" class="form-control" id="gender" name="gender"
                           placeholder="${customer.getCustomerGender()}">
                </div>
                <div class="col-6">
                    <label for="idCard" class="form-label">Id Card</label>
                    <input type="text" class="form-control" id="idCard" name="idCard"
                           placeholder="${customer.getCustomerIdCard()}">
                </div>
                <div class="col-md-6">
                    <label for="phone" class="form-label">Phone</label>
                    <input type="text" class="form-control" id="phone" name="phone"
                           placeholder="${customer.getCustomerPhone()}">
                </div>
                <div class="col-md-6">
                    <label for="email" class="form-label">Email</label>
                    <input type="text" class="form-control" id="email" name="email"
                           placeholder="${customer.getCustomerEmail()}">
                </div>
                <div class="col-md-12">
                    <label for="address" class="form-label">Address</label>
                    <input type="text" class="form-control" id="address" name="address"
                           placeholder="${customer.getCustomerAddress()}">
                </div>
                <div class="col-12">
                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" id="gridCheck">
                        <label class="form-check-label" for="gridCheck">
                            Check me out
                        </label>
                    </div>
                </div>
                <div class="col-12">
                    <button type="submit" class="btn btn-primary">Edit customer</button>
                </div>
            </form>
        </div>
    </div>
</main>
<footer class="border">
    <%@include file="/footer.jsp"%>
</footer>
</body>
</html>
