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
        <form action="${pageContext.request.contextPath}/exhibition/user/exhibitions">
            <!-- Showroom -->
            <input type="text" name="showroom" class="form-control col-sm-3"
                   placeholder="<fmt:message key="placeholder.name_of_showroom"/>"/>
            <button type="submit" class="btn btn-primary mt-1">
                <fmt:message key="button.search"/>
            </button>
        </form>

        <c:if test="${sessionScope.access eq \"ADMIN\"}">
            <a href="${pageContext.request.contextPath}/exhibition/admin/exhibitions/add"
               class="btn btn-primary">
                <fmt:message key="link.add_new_exhibition"/>
            </a>
        </c:if>

        <c:if test="${requestScope.exhibitionList ne null}">
            <div class="card-columns mt-3">
                <c:forEach items="${exhibitionList}" var="exhibition">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">
                            <c:out value="${exhibition.name}"/>
                        </h5>
                        <h6 class="card-subtitle mb-2 text-muted">
                            <fmt:message key="label.showroom"/><c:out value="${exhibition.showroom}"/>
                        </h6>
                        <h6 class="card-subtitle mb-2 ">
                            <fmt:message key="label.price"/><c:out value="${exhibition.price}"/>
                        </h6>
                        <p class="card-text"><c:out value="${exhibition.description}"/></p><br/>
                        <h6 class="card-subtitle mb-2 ">
                            <fmt:message key="label.date"/><c:out value="${exhibition.date}"/>
                        </h6>
                        <form action="${pageContext.request.contextPath}/exhibition/user/buy-ticket"
                              method="post">
                            <input type="hidden" name="exhibitionId" value="${exhibition.id}">
                            <button type="submit" class="btn btn-link">
                                <fmt:message key="button.buy_ticket"/>
                            </button>
                        </form>
                    </div>
                    <div class="card-footer text-muted container">
                        <div class="row">
                            <p class="col align-self-center"><c:out value="${exhibition.author.username}"/></p>
                            <p class="col align-self-center"></p>
                            <c:if test="${exhibition.author eq currentUser}">
                                <form class="col align-self-center" method="post"
                                      action="${pageContext.request.contextPath}/exhibition/admin/exhibitions/edit">
                                    <input type="hidden" name="exhibitionId" value="${exhibition.id}"/>
                                    <button class="btn btn-secondary" type="submit">
                                        <fmt:message key="button.edit"/>
                                    </button>
                                </form>
                            </c:if>
                        </div>
                    </div>
                </div>
                </c:forEach>
            </div>
        </c:if>

        <!-- Pager -->
        <%@ include file="../parts/pager.jsp"%>

    </div>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
</body>
</html>