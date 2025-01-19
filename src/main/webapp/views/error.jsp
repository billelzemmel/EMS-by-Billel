<%--
  Created by IntelliJ IDEA.
  User: medam
  Date: 28/12/2024
  Time: 21:47
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="header.jsp" %>
<div class="container mt-5">
    <h1>An Error Occurred</h1>
    <div class="alert alert-danger" role="alert">
        <h4 class="alert-heading">Oops! Something went wrong.</h4>
        <p><strong>Error Message:</strong> ${error}</p>
        <hr>
        <p class="mb-0">Please try again later or contact support if the issue persists.</p>
    </div>
    <a href="${pageContext.request.contextPath}/events" class="btn btn-primary">Go Back to Events</a>
</div>
<%@ include file="footer.jsp" %>

