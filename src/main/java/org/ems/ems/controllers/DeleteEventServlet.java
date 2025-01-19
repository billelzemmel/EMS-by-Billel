package org.ems.ems.controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.ems.ems.services.EventService;

import java.io.IOException;

@WebServlet("/events/delete")
public class DeleteEventServlet extends HttpServlet {

    @Inject
    private EventService eventService;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idParam = request.getParameter("id");

        if (idParam == null || idParam.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Event ID is required.");
            return;
        }

        try {
            Long eventId = Long.parseLong(idParam);
            boolean isDeleted = eventService.deleteEvent(eventId); // Ensure `deleteEvent` returns a boolean.

            if (isDeleted) {
                response.sendRedirect(request.getContextPath() + "/events");
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Event not found.");
            }
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid Event ID format.");
        }
    }
}
