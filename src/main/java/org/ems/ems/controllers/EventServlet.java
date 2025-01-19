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
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/events")
public class EventServlet extends HttpServlet {

    @Inject
    private EventService eventService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Retrieve all events
            List<Event> events = eventService.getAllEvents();
            if (events == null || events.isEmpty()) {
                request.setAttribute("message", "No events available at the moment.");
            } else {
                // Handle optional search by title
                String searchQuery = request.getParameter("search");
                if (searchQuery != null && !searchQuery.isEmpty()) {
                    events = events.stream()
                            .filter(event -> event.getTitle().toLowerCase().contains(searchQuery.toLowerCase()))
                            .collect(Collectors.toList());
                    request.setAttribute("searchQuery", searchQuery); // To retain the search input on the frontend
                }
                request.setAttribute("events", events);
            }
            request.getRequestDispatcher("/views/events.jsp").forward(request, response);
        } catch (Exception e) {
            // Log the error
            e.printStackTrace();
            request.setAttribute("error", "An error occurred while fetching events.");
            request.getRequestDispatcher("/views/error.jsp").forward(request, response);
        }
    }
}
