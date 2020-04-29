<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<html lang="${sessionScope.lang}">
<head>
    <meta charset="UTF-8">
    <title>Exhibition</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
</head>
<body>
    <div class="container mt-2">
        <c:if test="${sessionScope.access eq \"ADMIN\"}">
            <%@ include file="/WEB-INF/admin/parts/navbarAdmin.jsp" %>
        </c:if>
        <c:if test="${sessionScope.access eq \"USER\"}">
            <%@ include file="../parts/navbarUser.jsp" %>
        </c:if>
    </div>

    <div class="container mt-2">
        <div>
            <h5>
                <fmt:message key="label.username"/>
                <c:if test="${requestScope.currentUser ne null}">
                    ${currentUser.username}
                </c:if>
            </h5>
            <h5>
                <fmt:message key="label.balance"/>
                <c:if test="${requestScope.currentUser ne null}">
                    ${currentUser.accountMoney}
                </c:if>
            </h5>
        </div>
        <form action="${pageContext.request.contextPath}/exhibition/user/add-money"
              method="post" class="form-group mt-4">
            <label for="money"><b>Replenish balance</b></label>
            <input type="text" name="money" placeholder="1-9999" autofocus
                   required id="money" class="form-control col-sm-3 mt-1"
                   pattern="[1-9]{1}|^[1-9]{1}[0-9]{1}|^[1-9]{1}[0-9]{2}|^[1-9]{1}[0-9]{3}">
            <button class="btn btn-primary mt-2" type="submit">
                <fmt:message key="button.add"/>
            </button>
        </form>
    </div>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
</body>
</html>