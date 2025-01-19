package org.ems.ems.controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.ems.ems.models.Event;
import org.ems.ems.services.EventService;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

@WebServlet("/events/create")
public class CreateEventServlet extends HttpServlet {

    @Inject
    private EventService eventService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/views/create-event.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String dateTime = request.getParameter("dateTime");

        // Validation
        if (title == null || title.isEmpty() || dateTime == null || dateTime.isEmpty()) {
            request.setAttribute("error", "Title and Date/Time are required.");
            request.getRequestDispatcher("/views/create-event.jsp").forward(request, response);
            return;
        }

        try {
            Event event = new Event();
            //event.setId(System.currentTimeMillis() + (long) (Math.random() * 1000)); // Generate ID
            event.setTitle(title);
            event.setDescription(description);
            event.setDateTime(LocalDateTime.parse(dateTime));

            eventService.saveEvent(event);
            response.sendRedirect(request.getContextPath() + "/events");
        } catch (DateTimeParseException e) {
            request.setAttribute("error", "Invalid Date/Time format. Please use the correct format.");
            request.getRequestDispatcher("/views/create-event.jsp").forward(request, response);
        }
    }
}
