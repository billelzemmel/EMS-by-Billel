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
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@WebServlet("/events/edit")
public class EditEventServlet extends HttpServlet {

    @Inject
    private EventService eventService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idParam = request.getParameter("id");

        if (idParam == null || idParam.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Event ID is required.");
            return;
        }

        try {
            Long eventId = Long.parseLong(idParam);
            Event event = eventService.getEventById(eventId);

            if (event == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Event not found.");
                return;
            }

            if (event.getDateTime() != null) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
                String formattedDateTime = event.getDateTime().format(formatter);
                request.setAttribute("formattedDateTime", formattedDateTime);
            } else {
                request.setAttribute("formattedDateTime", "");
            }

            request.setAttribute("event", event);
            request.getRequestDispatcher("/views/edit-event.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid Event ID format.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idParam = request.getParameter("id");
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String dateTime = request.getParameter("dateTime");

        if (idParam == null || idParam.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Event ID is required.");
            return;
        }

        try {
            Long eventId = Long.parseLong(idParam);
            Event event = eventService.getEventById(eventId);

            if (event == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Event not found.");
                return;
            }

            if (title == null || title.isEmpty() || description == null || description.isEmpty()) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Title and Description are required.");
                return;
            }

            try {
                LocalDateTime parsedDateTime = LocalDateTime.parse(dateTime);
                event.setTitle(title);
                event.setDescription(description);
                event.setDateTime(parsedDateTime);

                eventService.saveEvent(event);
                response.sendRedirect(request.getContextPath() + "/events");
            } catch (DateTimeParseException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid date-time format.");
            }

        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid Event ID format.");
        }
    }
}
