<%--
  Created by IntelliJ IDEA.
  User: medam
  Date: 19/12/2024
  Time: 23:19
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="header.jsp" %>
<div class="container mt-5">
    <h1>Edit Event</h1>
    <form action="${pageContext.request.contextPath}/events/edit" method="post">
        <input type="hidden" name="id" value="${event.id}">
        <div class="mb-3">
            <label for="title" class="form-label">Event Title</label>
            <input type="text" class="form-control" id="title" name="title" value="${event.title}" required>
        </div>
        <div class="mb-3">
            <label for="description" class="form-label">Event Description</label>
            <textarea class="form-control" id="description" name="description" rows="3" required>${event.description}</textarea>
        </div>
        <div class="mb-3">
            <label for="dateTime" class="form-label">Event Date & Time</label>
            <input type="datetime-local" class="form-control" id="dateTime" name="dateTime"
                   value="${formattedDateTime}" required>
        </div>

        <button type="submit" class="btn btn-primary">Update Event</button>
    </form>
</div>
<%@ include file="footer.jsp" %>
