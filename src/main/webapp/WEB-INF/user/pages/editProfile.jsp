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
        <c:if test="${requestScope.message ne null}">
            <div class="alert alert-success" align="center">
                <strong>${message}</strong>
            </div>
        </c:if>

        <c:if test="${requestScope.error ne null}">
            <div class="alert alert-danger" align="center">
                <strong>${error}</strong>
            </div>
        </c:if>

        <div>
            <h5><fmt:message key="label.username"/>${user.username}</h5>
        </div>

        <form method="post" class="col-sm-4"
              action="${pageContext.request.contextPath}/exhibition/user/edit-profile">
            <!-- Username -->
            <b><fmt:message key="label.edit_username"/></b><br/>
            <label for="username"><fmt:message key="label.username"/></label>
            <input type="text" name="username" value="${user.username}" autofocus
                    id="username" class="form-control" required/>

            <button type="submit" class="btn btn-primary mt-2">
                <fmt:message key="button.save"/>
            </button>
        </form>

        <form method="post" class="col-sm-4"
              action="${pageContext.request.contextPath}/exhibition/user/edit-profile">
            <!-- Password -->
            <b><fmt:message key="label.edit_password"/></b><br/>
            <label for="password"><fmt:message key="label.password"/></label>
            <input type="password" name="password" id="password" class="form-control"
                    required pattern="^[\w]{1,20}$"/>

            <!-- Password Confirm -->
            <label for="passwordConfirm">
                <fmt:message key="label.confirm_password"/>
            </label>
            <input type="password" name="passwordConfirm" id="passwordConfirm"
                   class="form-control" required pattern="^[\w-]{1,20}$"/>

            <button type="submit" class="btn btn-primary mt-2">
                <fmt:message key="button.save"/>
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