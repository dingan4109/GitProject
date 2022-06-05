<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 6/1/2022
  Time: 8:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Navigation Bar</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    <style>
        a{
            text-decoration: none;
            color: black;
        }
        .sub-list {
            visibility: hidden;
            position: absolute;
            top:12%;
        }
        .sub-list-hover:hover .sub-list{
            visibility: visible;
        }
    </style>
</head>
<body>
<div class="row">
    <div class="col-md-9">
        <ul class="list-unstyled list-inline">
            <li class="list-inline-item"><a href="#">Home</a></li>
            <li class="list-inline-item"><a href="/employeeServlet?currentPage=1">Employee</a></li>
            <li class="list-inline-item sub-list-hover"><a
                    href="/customerServlet?currentPage=1">Customer
            </a></li>
            <li class="list-inline-item"><a href="/serviceServlet">Service</a></li>
            <li class="list-inline-item"><a href="#">Contract</a></li>
        </ul>
    </div>
    <div class="col-md-3">
        <input type="search" placeholder="Input to search" style="border-radius: 5px">
    </div>
</div>
</body>
</html>
