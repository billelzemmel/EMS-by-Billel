package org.ems.ems.controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.ems.ems.models.Event;
import org.ems.ems.models.Registration;
import org.ems.ems.models.User;
import org.ems.ems.services.EventService;
import org.ems.ems.services.RegistrationService;
import org.ems.ems.services.UserService;

import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet("/event-register")
public class EventRegisterServlet extends HttpServlet {

    @Inject
    private RegistrationService registrationService;

    @Inject
    private UserService userService;

    @Inject
    private EventService eventService;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userIdParam = request.getParameter("userId");
        String eventIdParam = request.getParameter("eventId");

        if (userIdParam == null || eventIdParam == null) {
            response.sendRedirect("event-register?error=Missing+User+or+Event+ID");
            return;
        }

        try {
            Long userId = Long.parseLong(userIdParam);
            Long eventId = Long.parseLong(eventIdParam);

            User user = userService.getUserById(userId);
            Event event = eventService.getEventById(eventId);

            if (user == null) {
                response.sendRedirect("event-register?error=User+not+found");
                return;
            }

            if (event == null) {
                response.sendRedirect("event-register?error=Event+not+found");
                return;
            }

            if (registrationService.isUserAlreadyRegistered(user, event)) {
                response.sendRedirect("event-register?error=Already+registered+for+this+event");
                return;
            }

            if (event.getDateTime().isBefore(LocalDateTime.now())) {
                response.sendRedirect("event-register?error=Event+already+ended");
                return;
            }

            Registration registration = new Registration(
                    System.currentTimeMillis(),
                    user,
                    event,
                    LocalDateTime.now()
            );

            registrationService.saveRegistration(registration);
            response.sendRedirect("events?success=Registered+successfully");

        } catch (NumberFormatException e) {
            response.sendRedirect("event-register?error=Invalid+User+or+Event+ID+format");
        } catch (Exception e) {
            response.sendRedirect("event-register?error=An+unexpected+error+occurred");
        }
    }
}
