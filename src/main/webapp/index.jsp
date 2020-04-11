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
        <%@ include file="/WEB-INF/parts/navbarGuest.jsp" %>
    </div>
    <div class="container mt-2">

        <div align="center">
            <h5>
                <fmt:message key="label.welcome"/>
                <c:if test="${sessionScope.username ne null}">
                    ${sessionScope.username}
                </c:if>
                <c:if test="${sessionScope.username eq null}">
                    <fmt:message key="label.guest"/>
                </c:if>
            </h5>
            <h5>
                <c:if test="${sessionScope.access eq \"SUPER_ADMIN\"}">
                    <a href="${pageContext.request.contextPath}/exhibition/super_admin">
                        <fmt:message key="link.your_page"/>
                    </a>
                </c:if>
                <c:if test="${sessionScope.access eq \"ADMIN\"}">
                    <a href="${pageContext.request.contextPath}/exhibition/admin">
                        <fmt:message key="link.your_page"/>
                    </a>
                </c:if>
                <c:if test="${sessionScope.access eq \"USER\"}">
                    <a href="${pageContext.request.contextPath}/exhibition/user">
                        <fmt:message key="link.your_page"/>
                    </a>
                </c:if>
            </h5>
        </div>

    </div>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
</body>
</html>