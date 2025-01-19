<%--
  Created by IntelliJ IDEA.
  User: medam
  Date: 18/12/2024
  Time: 12:15
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="header.jsp" %>
<div class="container mt-5">
    <h1 class="mb-4">Register for Event</h1>
    <form action="event-register" method="post">
        <div class="mb-3">
            <label for="userId" class="form-label">Your User ID</label>
            <input type="text" class="form-control" id="userId" name="userId" required>
        </div>
        <div class="mb-3">
            <label for="eventId" class="form-label">Event</label>
            <select class="form-select" id="eventId" name="eventId" required>
                <c:forEach var="event" items="${events}">
                    <option value="${event.id}">${event.title}</option>
                </c:forEach>
            </select>
        </div>
        <button type="submit" class="btn btn-primary">Register</button>
    </form>
</div>
<%@ include file="footer.jsp" %>
