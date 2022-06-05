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
    <title>Create Service Form</title>
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
            <h1>Create new service form</h1>
            <c:if test="${mess!=null}">
                <p>${mess}</p>
            </c:if>
            <a href="/serviceServlet"><button class="btn btn-primary me-md-2"
                                                             style="background: lightgrey">Back to service
                list</button></a>
            <form class="row g-3 border" method="post">
                    <div class="col-md-9">
                        <label for="name" class="form-label">Name</label>
                        <input type="text" class="form-control" id="name" name="name">
                    </div>
                    <div class="col-md-9">
                        <label for="area" class="form-label">Area</label>
                        <input type="text" class="form-control" id="area" name="area">
                    </div>
                    <div class="col-md-9">
                        <label for="cost" class="form-label">Cost</label>
                        <input type="text" class="form-control" id="cost" name="cost">
                    </div>
                    <div class="col-md-9">
                        <label for="max_people" class="form-label">Max people</label>
                        <input type="text" class="form-control" id="max_people" name="max_people">
                    </div>
                    <div class="col-md-6">
                        <label class="form-label">Rent type</label>
                        <select name="rent_type">
                            <c:forEach items="${rentTypes}" var="rentType">
                                <option>${rentType.getRentTypeName()}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-md-6">
                        <label class="form-label">Service type</label>
                        <select name="service_type">
                            <c:forEach items="${serviceTypes}" var="serviceType">
                                <option>${serviceType.getServiceTypeName()}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-6">
                        <label for="standard_room" class="form-label">Standard room</label>
                        <input type="text" class="form-control" id="standard_room" name="standard_room">
                    </div>
                    <div class="col-6">
                        <label for="description" class="form-label">Description</label>
                        <input type="text" class="form-control" id="description" name="description">
                    </div>
                    <div class="col-6">
                        <label for="pool_area" class="form-label">Pool area</label>
                        <input type="text" class="form-control" id="pool_area" name="pool_area">
                    </div>
                    <div class="col-6">
                        <label for="floors" class="form-label">Number of floors</label>
                        <input type="text" class="form-control" id="floors" name="floors">
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
                    <button type="submit" class="btn btn-primary">Create customer</button>
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
