<%--
  Created by IntelliJ IDEA.
  User: medam
  Date: 10/01/2025
  Time: 12:38
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="header.jsp" %>
<div class="container mt-5">
    <!-- Welcome Section -->
    <div class="jumbotron text-center bg-primary text-white py-5 rounded">
        <h1 class="display-4">Welcome, ${loggedInUser.name}!</h1>
        <p class="lead">Here's an overview of your activity and tools to get started.</p>
    </div>

    <!-- Cards Section -->
    <div class="row text-center my-4">
        <div class="col-md-4 mb-4">
            <div class="card border-primary shadow-sm">
                <div class="card-body">
                    <h5 class="card-title text-primary">Manage Events</h5>
                    <p class="card-text">Create, view, and manage your events effortlessly.</p>
                    <a href="events" class="btn btn-outline-primary">Go to Events</a>
                </div>
            </div>
        </div>
        <div class="col-md-4 mb-4">
            <div class="card border-success shadow-sm">
                <div class="card-body">
                    <h5 class="card-title text-success">Profile</h5>
                    <p class="card-text">Update your profile details and preferences.</p>
                    <a href="profile" class="btn btn-outline-success">Edit Profile</a>
                </div>
            </div>
        </div>
        <div class="col-md-4 mb-4">
            <div class="card border-warning shadow-sm">
                <div class="card-body">
                    <h5 class="card-title text-warning">Settings</h5>
                    <p class="card-text">Manage your account settings and preferences.</p>
                    <a href="settings" class="btn btn-outline-warning">View Settings</a>
                </div>
            </div>
        </div>
    </div>

    <!-- Statistics Section -->
    <div class="row my-4">
        <div class="col-md-3 mb-4">
            <div class="card text-white bg-primary shadow-sm">
                <div class="card-body">
                    <h5 class="card-title">Total Events</h5>
                    <h3>25</h3>
                </div>
            </div>
        </div>
        <div class="col-md-3 mb-4">
            <div class="card text-white bg-success shadow-sm">
                <div class="card-body">
                    <h5 class="card-title">Upcoming Events</h5>
                    <h3>5</h3>
                </div>
            </div>
        </div>
        <div class="col-md-3 mb-4">
            <div class="card text-white bg-warning shadow-sm">
                <div class="card-body">
                    <h5 class="card-title">Messages</h5>
                    <h3>12</h3>
                </div>
            </div>
        </div>
        <div class="col-md-3 mb-4">
            <div class="card text-white bg-danger shadow-sm">
                <div class="card-body">
                    <h5 class="card-title">Alerts</h5>
                    <h3>3</h3>
                </div>
            </div>
        </div>
    </div>

    <!-- Recent Activity -->
    <div class="card my-4 shadow-sm">
        <div class="card-header bg-light">
            <h5 class="mb-0">Recent Activity</h5>
        </div>
        <ul class="list-group list-group-flush">
            <li class="list-group-item">You created an event: <strong>Annual Meetup</strong> on Jan 5, 2025.</li>
            <li class="list-group-item">You updated your profile information.</li>
            <li class="list-group-item">You received a message from <strong>John Doe</strong>.</li>
            <li class="list-group-item">You marked <strong>Weekly Standup</strong> as completed.</li>
        </ul>
    </div>
</div>
<%@ include file="footer.jsp" %>
