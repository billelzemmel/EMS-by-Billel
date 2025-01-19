<%@ include file="views/header.jsp" %>
<div class="container mt-5">
    <div class="text-center">
        <h1 class="display-4 fw-bold mb-4">Welcome to the Event Management System</h1>
        <p class="lead">
            Your one-stop solution for organizing and managing events. Easily create, explore, and manage events to make your experience effortless and efficient.
        </p>
        <p>
            Whether you're planning a personal event or managing a professional gathering, we've got you covered.
        </p>
    </div>
    <div class="row justify-content-center mt-5">
        <div class="col-md-4 mb-3">
            <div class="card shadow border-0">
                <div class="card-body text-center">
                    <h5 class="card-title">Dashboard</h5>
                    <p class="card-text">
                        Access your personal dashboard to manage your events, track progress, and view insights.
                    </p>
                    <a href="${pageContext.request.contextPath}/dashboard" class="btn btn-primary btn-block">Go to Dashboard</a>
                </div>
            </div>
        </div>
        <div class="col-md-4 mb-3">
            <div class="card shadow border-0">
                <div class="card-body text-center">
                    <h5 class="card-title">Browse Events</h5>
                    <p class="card-text">
                        Discover exciting events happening around you. Find something that interests you and join in!
                    </p>
                    <a href="${pageContext.request.contextPath}/browse-events" class="btn btn-secondary btn-block">Browse Events</a>
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="views/footer.jsp" %>
