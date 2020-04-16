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

        <h5><fmt:message key="label.username"/>${user.username}</h5>

        <h5><fmt:message key="label.tickets"/></h5>

                <c:if test="${requestScope.tickets ne null}">
                    <table class="table">
                        <thead class="thead-light">
                            <tr>
                                <th scope="col"><fmt:message key="table.th.name"/></th>
                                <th scope="col"><fmt:message key="table.th.showroom"/></th>
                                <th scope="col"><fmt:message key="table.th.description"/></th>
                                <th scope="col"><fmt:message key="table.th.price"/></th>
                                <th scope="col"><fmt:message key="table.th.date"/></th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${tickets}" var="ticket">
                                <tr>
                                    <td style="max-width: 25px" scope="row">${ticket.name}</td>
                                    <td style="max-width: 25px">${ticket.showroom}</td>
                                    <td style="max-width: 150px">${ticket.description}</td>
                                    <td style="max-width: 25px">${ticket.price}</td>
                                    <td style="max-width: 25px">${ticket.date}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>

                </c:if>
    </div>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
</body>
</html>