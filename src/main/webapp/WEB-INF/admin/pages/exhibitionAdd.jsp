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
    <%@ include file="../parts/navbarAdmin.jsp" %>
</div>

<div class="container mt-2">
    <c:if test="${requestScope.error ne null}">
        <div class="alert alert-danger" align="center">
            <strong><c:out value="${error}"/></strong>
        </div>
    </c:if>
    <div align="center">
        <h5><fmt:message key="label.exhibition_add"/></h5>
    </div>

    <div align="center">
        <div class="form-group mt-3">
            <div class="col-sm-6">
                <form action="${pageContext.request.contextPath}/exhibition/admin/exhibitions/add" method="post">

                    <!-- Name -->
                    <div class="form-group">
                        <label for="exhibition"><fmt:message key="label.exhibition_name"/></label>
                        <input type="text" name="name" class="form-control" id="exhibition" pattern=".{2,30}$"
                               placeholder="<fmt:message key="placeholder.exhibition_name"/>" required
                               value="<c:if test="${requestScope.name ne null}">${name}</c:if>"/>
                    </div>

                    <!-- Showroom -->
                    <div class="form-group">
                        <label for="showroom"><fmt:message key="label.exhibition_showroom"/></label>
                        <input type="text" name="showroom" class="form-control" pattern="^.{2,30}$"
                               id="showroom" placeholder="<fmt:message key="placeholder.exhibition_showroom"/>" required
                               value="<c:if test="${requestScope.showroom ne null}">${showroom}</c:if>"/>
                    </div>

                    <!-- Description -->
                    <div class="form-group">
                        <label for="description"><fmt:message key="label.exhibition_description"/></label>
                        <input type="text" name="description" class="form-control" id="description"
                               placeholder="<fmt:message key="placeholder.exhibition_description"/>" required
                               value="<c:if test="${requestScope.description ne null}">${description}</c:if>"/>
                    </div>

                    <!-- Price -->
                    <div class="form-group">
                        <label for="price"><fmt:message key="label.exhibition_price"/></label>
                        <input type="text" name="price" class="form-control" id="price" placeholder="0"
                               pattern="[1-9]{1}|^[1-9]{1}[0-9]{1}|^[1-9]{1}[0-9]{1}[0-9]{1}|^1000" required
                               value="<c:if test="${requestScope.price ne null}">${price}</c:if>"/>
                    </div>

                    <!-- Date -->
                    <div class="form-group">
                        <label for="date"><fmt:message key="label.exhibition_date"/></label>
                        <input type="date" name="date" class="form-control"
                               id="date"  required
                               value="<c:if test="${requestScope.date ne null}">${date}</c:if>"/>
                    </div>

                    <div class="form-group">
                        <button type="submit" class="btn btn-primary">
                            <fmt:message key="button.add"/>
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        rossorigin="anonymous"></script>
</body>
</html>