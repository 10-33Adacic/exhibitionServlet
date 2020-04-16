<nav aria-label="Page navigation example">
    <ul class="pagination justify-content-center">
        <!-- Previous -->
        <c:if test="${currentPage != 1}">
            <li class="page-item">
                <a href="${pageContext.request.contextPath}/exhibition/user/exhibitions?page=${currentPage - 1}"
                   class="page-link">
                    <fmt:message key="pager.link.previous"/>
                </a>
            </li>
        </c:if>

        <c:forEach begin="1" end="${numberOfPages}" var="i">
            <c:choose>
                <c:when test="${currentPage eq i}">
                    <li class="page-item disabled"><a class="page-link" href="#">${i}</a></li>
                </c:when>
                <c:otherwise>
                    <li class="page-item">
                        <a href="${pageContext.request.contextPath}/exhibition/user/exhibitions?page=${i}"
                           class="page-link">${i}</a>
                    </li>
                </c:otherwise>
            </c:choose>
        </c:forEach>


        <!-- Next -->
        <c:if test="${currentPage lt numberOfPages}">
        <li class="page-item">
            <a href="${pageContext.request.contextPath}/exhibition/user/exhibitions?page=${currentPage + 1}"
               class="page-link">
                <fmt:message key="pager.link.next"/>
            </a>
        <li class="page-item">
            </c:if>
    </ul>
</nav>