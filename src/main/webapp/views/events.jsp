<%--
  Created by IntelliJ IDEA.
  User: medam
  Date: 17/12/2024
  Time: 22:51
  To change this template use File | Settings | File Templates.
--%>

<%@ include file="header.jsp" %>
<div class="container mt-5">
    <h1 class="mb-4">Upcoming Events</h1>
    <!-- Add Create Event Button -->
    <div class="mb-4">
        <a href="events/create" class="btn btn-primary">Create New Event</a>
    </div>

    <c:choose>
        <c:when test="${not empty events}">
            <c:forEach var="event" items="${events}">
                <div class="card mb-4 shadow-sm">
                    <div class="card-body">
                        <h5 class="card-title">${event.title}</h5>
                        <p class="card-text">${event.description}</p>
                        <p class="card-text"><strong>Date:</strong> ${event.dateTime}</p>
                        <h6>Registered Users:</h6>
                        <ul class="list-group">
                            <c:forEach var="registration" items="${event.registrations}">
                                <li class="list-group-item">${registration.user.name} (${registration.user.email})</li>
                            </c:forEach>
                        </ul>

                        <!-- Edit and Delete Buttons -->
                        <div class="mt-3">
                            <a href="events/edit?id=${event.id}" class="btn btn-warning">Edit</a>
                            <form action="events/delete" method="post" class="d-inline" onsubmit="return confirm('Are you sure you want to delete this event?');">
                                <input type="hidden" name="id" value="${event.id}">
                                <button type="submit" class="btn btn-danger">Delete</button>
                            </form>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </c:when>
        <c:otherwise>
            <div class="alert alert-warning" role="alert">
                No events available at the moment!
            </div>
        </c:otherwise>
    </c:choose>
</div>
<%@ include file="footer.jsp" %>