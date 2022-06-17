<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 6/12/2022
  Time: 11:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Side bar</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
            crossorigin="anonymous"></script>
</head>
<body>
<!--Main Navigation-->
<header>

    <!--Main layout-->
    <main style="margin-top: 58px">
        <div class="container pt-4">
            <img src="https://thietbiketnoi.com/wp-content/uploads/2020/01/tong-hop-hinh-nen-background-vector-designer-dep-do-phan-giai-fhd-2k-4k-moi-nhat-2-1024x683.jpg"
                 class="img-fluid">
        </div>
    </main>
    <!--Main layout-->

    <!--Main layout-->
    <main style="margin-top: 58px">
        <div class="container pt-4">
            <p>Sometimes you only need to paraphrase the information from one sentence. Here are some examples of
                paraphrasing individual sentences: Original: Her life spanned years of incredible change for women as
                they gained more rights than ever before. Paraphrase: She lived through the exciting era of women's
                liberation.</p>
        </div>
    </main>
    <!--Main layout-->

    <!-- Sidebar -->
    <nav
            id="sidebarMenu"
            class="collapse d-lg-block sidebar collapse bg-white"
    >
        <div class="position-sticky">
            <div class="list-group list-group-flush mx-3 mt-4">
                <a
                        href="/servlet?action=viewBorrowCodeList"
                        class="list-group-item list-group-item-action py-2 ripple"
                        aria-current="true"
                >
                    <i class="fas fa-tachometer-alt fa-fw me-3"></i
                    ><span>Borrow code list</span>
                </a>
<%--                <a--%>
<%--                        href="/serviceServlet?action=viewRentTypes"--%>
<%--                        class="list-group-item list-group-item-action py-2 ripple"--%>
<%--                >--%>
<%--                    <i class="fas fa-chart-area fa-fw me-3"></i--%>
<%--                    ><span>Rent type</span>--%>
<%--                </a>--%>
<%--                <a--%>
<%--                        href="/serviceServlet?action=viewServiceTypes"--%>
<%--                        class="list-group-item list-group-item-action py-2 ripple"--%>
<%--                ><i class="fas fa-lock fa-fw me-3"></i><span>Service type</span></a--%>
<%--                >--%>
<%--                <a--%>
<%--                        href="/employeeServlet?action=user"--%>
<%--                        class="list-group-item list-group-item-action py-2 ripple"--%>
<%--                ><i class="fas fa-chart-line fa-fw me-3"></i--%>
<%--                ><span>User</span></a--%>
<%--                >--%>
<%--                <a--%>
<%--                        href="/contractServlet?action=viewContractDetail"--%>
<%--                        class="list-group-item list-group-item-action py-2 ripple"--%>
<%--                >--%>
<%--                    <i class="fas fa-chart-pie fa-fw me-3"></i><span>Contract detail</span>--%>
<%--                </a>--%>
<%--                <a--%>
<%--                        href="/customerUseServiceServlet"--%>
<%--                        class="list-group-item list-group-item-action py-2 ripple"--%>
<%--                ><i class="fas fa-chart-bar fa-fw me-3"></i><span>Customer use service</span></a--%>
<%--                >--%>
            </div>
        </div>
    </nav>
    <!-- Sidebar -->
</header>
<!--Main Navigation-->
</body>
</html>
