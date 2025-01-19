<%--
  Created by IntelliJ IDEA.
  User: medam
  Date: 18/12/2024
  Time: 12:22
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="header.jsp" %>
<div class="container mt-5">
    <h1 class="mb-4">User Login</h1>
    <form action="${pageContext.request.contextPath}/login" method="post">
        <div class="mb-3">
            <label for="email" class="form-label">Email</label>
            <input type="email" class="form-control" id="email" name="email" required>
        </div>
        <div class="mb-3">
            <label for="password" class="form-label">Password</label>
            <input type="password" class="form-control" id="password" name="password" required>
        </div>
        <button type="submit" class="btn btn-primary">Login</button>
    </form>
</div>
<%@ include file="footer.jsp" %>
