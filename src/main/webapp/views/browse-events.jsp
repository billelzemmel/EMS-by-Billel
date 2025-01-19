<%--
  Created by IntelliJ IDEA.
  User: medam
  Date: 14/01/2025
  Time: 11:36
  To change this template use File | Settings | File Templates.
--%>

<%@ include file="header.jsp" %>
<div class="container my-4">
    <h1 class="mb-4">Browse Events</h1>
    <form class="mb-4" method="get" action="${pageContext.request.contextPath}/browse-events">
        <div class="input-group">
            <input type="text" class="form-control" name="search" placeholder="Search events" value="${param.search}">
            <button class="btn btn-primary" type="submit">Search</button>
        </div>
    </form>

    <div class="row">
        <c:forEach var="event" items="${events}">
            <div class="col-md-4">
                <div class="card mb-4">
                    <div class="card-body">
                        <h5 class="card-title">${event.title}</h5>
                        <p class="card-text">${event.description}</p>
                        <p class="text-muted">${event.dateTime}</p>
                        <c:if test="${not empty loggedInUser}">
                            <a href="${pageContext.request.contextPath}/register?eventId=${event.id}" class="btn btn-primary">Register</a>
                        </c:if>
                        <c:if test="${empty loggedInUser}">
                            <button class="btn btn-secondary" disabled>Register (Login Required)</button>
                        </c:if>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>

<%@ include file="footer.jsp" %>
