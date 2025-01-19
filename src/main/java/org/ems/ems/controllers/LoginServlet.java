package org.ems.ems.controllers;

import jakarta.inject.Inject;
import jakarta.security.enterprise.AuthenticationException;
import jakarta.security.enterprise.AuthenticationStatus;
import jakarta.security.enterprise.SecurityContext;
import jakarta.security.enterprise.authentication.mechanism.http.AuthenticationParameters;
import jakarta.security.enterprise.credential.UsernamePasswordCredential;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.ems.ems.models.User;
import org.ems.ems.services.UserService;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Inject
    private SecurityContext securityContext;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Check if the user is already authenticated
        if (securityContext.getCallerPrincipal() != null) {
            // Redirect to events page if already logged in
            response.sendRedirect("dashboard");
        } else {
            // Forward to login page
            request.getRequestDispatcher("/views/login.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        System.out.println("Attempting to authenticate:");

        if (email == null || password == null) {
            response.sendRedirect("views/login-error.jsp?message=Missing+credentials");
            return;
        }
        // Authenticate using SecurityContext
        AuthenticationStatus status = securityContext.authenticate(
                request,
                response,
                AuthenticationParameters.withParams().credential(new UsernamePasswordCredential(email, password))
        );
        System.out.println("Authentication status: " + status);

        switch (status) {
            case SUCCESS:
//                User user = userService.getUserByEmail(email);
//                System.out.println("User set in session: " + user.getName());
//                request.getSession().setAttribute("loggedInUser", user);
                response.sendRedirect(request.getContextPath() + "/dashboard");
                // Redirect to dashboard after successful login
                break;
            case SEND_FAILURE:
                response.sendRedirect("views/login-error.jsp?message=Invalid+credentials");
                break;
            case NOT_DONE:
                response.sendRedirect("views/login-error.jsp?message=Authentication+not+completed");
                break;
            case SEND_CONTINUE:
                // For mechanisms like two-factor authentication, SSO, etc.
                break;
            default:
                response.sendRedirect("login-error.jsp?message=Unknown+authentication+status");
        }
    }
}
