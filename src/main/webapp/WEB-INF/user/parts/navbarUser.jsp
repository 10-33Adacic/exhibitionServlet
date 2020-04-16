<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand" href="/"><fmt:message key="nav.link.home"/></a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/exhibition/user/exhibitions">
                    <fmt:message key="nav.link.exhibitions"/>
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/exhibition/user/bought-tickets">
                    <fmt:message key="nav.link.tickets"/>
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/exhibition/user/edit-profile">
                    <fmt:message key="nav.link.profile"/>
                </a>
            </li>
        </ul>

        <div class="navbar-text mr-2"> ${sessionScope.username}</div>

        <div class="mr-2">
            <a href="?sessionLocale=en" class="btn btn-info"><fmt:message key="language.en"/></a>
            <a href="?sessionLocale=ua" class="btn btn-info"><fmt:message key="language.ua"/></a>
        </div>

        <div class="mr-3">
            <c:if test="${sessionScope.username eq null}">
                <a href="${pageContext.request.contextPath}/exhibition/login"
                   class="btn btn-primary">
                    <fmt:message key="nav.link.login"/>
                </a>
            </c:if>
            <c:if test="${sessionScope.username ne null}">
                <a href="${pageContext.request.contextPath}/exhibition/logout"
                   class="btn btn-primary">
                    <fmt:message key="nav.link.logout"/>
                </a>
            </c:if>
        </div>

    </div>
</nav>