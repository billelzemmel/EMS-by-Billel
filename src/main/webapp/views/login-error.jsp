<%--
  Created by IntelliJ IDEA.
  User: medam
  Date: 30/12/2024
  Time: 19:37
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="header.jsp" %>
<div class="container mt-5 text-center">
  <h1 class="text-danger">Login Error</h1>

  <!-- Extract and display the error message -->
  <c:choose>
    <c:when test="${not empty param.message}">
      <p class="mb-4">${param.message}</p>
    </c:when>
    <c:otherwise>
      <p class="mb-4">An unknown error occurred. Please try again.</p>
    </c:otherwise>
  </c:choose>

  <a href="${pageContext.request.contextPath}/login" class="btn btn-primary">Back to Login</a>
</div>
<%@ include file="footer.jsp" %>

