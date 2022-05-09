package com.taxi.taxiservice.Controllers;

import com.google.gson.Gson;
import com.taxi.taxiservice.Models.ServerMessage;

import javax.servlet.http.HttpServletResponse;

public class MessageController {
    static void badRequest(HttpServletResponse res, String message) {
        try {
            ServerMessage serverMessage = new ServerMessage(message);
            String jsonMessage = new Gson().toJson(serverMessage);
            res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            res.getWriter().write(jsonMessage);
            res.getWriter().flush();
        } catch (Exception err) {
            System.out.println("Server error");
        }
    }

    static void internal(HttpServletResponse res) {
        try {
            ServerMessage serverMessage = new ServerMessage("Server error...");
            String jsonMessage = new Gson().toJson(serverMessage);
            res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            res.getWriter().write(jsonMessage);
            res.getWriter().flush();
        } catch (Exception err) {
            System.out.println("Server error");
        }
    }

    static void sendResponseMessage(HttpServletResponse res, String message) {
        try {
            ServerMessage serverMessage = new ServerMessage(message);
            String jsonMessage = new Gson().toJson(serverMessage);
            res.getWriter().write(jsonMessage);
            res.getWriter().flush();
        } catch (Exception err) {
            System.out.println("Server error");
        }
    }
}
