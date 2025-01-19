package org.ems.ems.controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.ems.ems.models.User;
import org.ems.ems.services.UserService;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.io.IOException;
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    @Inject
    private UserService userService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

            // Forward to login page
            request.getRequestDispatcher("/views/register.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (name == null || email == null || password == null || name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            request.setAttribute("error", "All fields are required.");
            request.getRequestDispatcher("/views/register.jsp").forward(request, response);
            return;
        }

        // Hash password
        // BCrypt: A strong hashing algorithm that includes a salt to defend against rainbow table attacks.
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        // Save user
        User user = new User(name, email, hashedPassword,"USER");
        userService.saveUser(user);

        response.sendRedirect("login");
    }
}
