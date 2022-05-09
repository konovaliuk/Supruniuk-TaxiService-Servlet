package com.taxi.taxiservice.Controllers;

import com.google.gson.Gson;
import com.taxi.taxiservice.Models.Order.*;
import com.taxi.taxiservice.Services.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class OrderController {
    private OrderService orderService;
    Gson gson = new Gson();

    public OrderController() {
        orderService = new OrderService();
    }

    public void getOrders(HttpServletRequest req, HttpServletResponse res) {
        try {
            ArrayList<Order> orders = orderService.getAll();
            String json = new Gson().toJson(orders);
            res.getWriter().write(json);
            res.getWriter().flush();

        } catch (Exception err) {
            MessageController.internal(res);
        }
    }

    public void getOrderById(HttpServletRequest req, HttpServletResponse res) {
        String id = req.getPathInfo().substring(1);

        try {
            long orderId = Long.parseLong(id);
            Order order = orderService.getById(orderId);
            if (order == null) {
                MessageController.badRequest(res, "Order not found");
            } else {
                String json = new Gson().toJson(order);
                res.getWriter().write(json);
            }
            res.getWriter().flush();
        } catch (Exception err) {
            MessageController.internal(res);
        }
    }

    public void getOrdersByRole(HttpServletRequest req, HttpServletResponse res) {
        String role = req.getParameter("role");
        String id = req.getParameter("id");

        try {
            long userId = Long.parseLong(id);
            ArrayList<Order> orders = new ArrayList<Order>();
            switch (role) {
                case "client":
                    orders = orderService.getByClientId(userId);
                    break;
                case "driver":
                    orders = orderService.getByDriverId(userId);
                    break;
                case "dispatcher":
                    orders = orderService.getByDispatcherId(userId);
                    break;
            }
            if (orders.size() == 0) {
                MessageController.badRequest(res, "Orders not found");
            } else {
                String json = new Gson().toJson(orders);
                res.getWriter().write(json);
            }
            res.getWriter().flush();
        } catch (Exception err) {
            MessageController.internal(res);
        }
    }

    public void createOrder(HttpServletRequest req, HttpServletResponse res) {
        try {
            String requestData = req.getReader().lines().collect(Collectors.joining());
            OrderClient orderClient = gson.fromJson(requestData, OrderClient.class);
            if (orderClient.checkValid()) {
                orderService.create(orderClient);
                MessageController.sendResponseMessage(res, "Order successfully created");
            } else {
                MessageController.badRequest(res, "Order Validation failed");
            }
        } catch (Exception err) {
            MessageController.internal(res);
        }
    }

    public void updateByDispatcher(HttpServletRequest req, HttpServletResponse res) {
        String[] path = req.getPathInfo().split("/");

        try {
            String requestData = req.getReader().lines().collect(Collectors.joining());
            OrderDispatcher orderDispatcher = gson.fromJson(requestData, OrderDispatcher.class);
            if (orderDispatcher.checkValid()) {
                long orderId = Long.parseLong(path[2]);
                if (orderService.getById(orderId) != null) {
                    orderService.updateByDispatcher(orderId, orderDispatcher);
                    MessageController.sendResponseMessage(res, "Order updated successfully by dispatcher");
                } else {
                    MessageController.badRequest(res, "Order doesn`t exist");
                }
            } else {
                MessageController.badRequest(res, "Validation failed");
            }
            res.getWriter().flush();
        } catch (Exception err) {
            MessageController.internal(res);
        }
    }

    public void updateByDriver(HttpServletRequest req, HttpServletResponse res) {
        String[] path = req.getPathInfo().split("/");

        try {
            String requestData = req.getReader().lines().collect(Collectors.joining());
            OrderDriver orderDriver = gson.fromJson(requestData, OrderDriver.class);
            if (orderDriver.checkValid()) {
                long orderId = Long.parseLong(path[2]);
                if (orderService.getById(orderId) != null) {
                    orderService.updateByDriver(orderId, orderDriver);
                    MessageController.sendResponseMessage(res, "Order updated successfully by driver");
                } else {
                    MessageController.badRequest(res, "Order doesn`t exist");
                }
            } else {
                MessageController.badRequest(res, "Validation failed");
            }
            res.getWriter().flush();
        } catch (Exception err) {
            MessageController.internal(res);
        }
    }

    public void leaveFeedback(HttpServletRequest req, HttpServletResponse res) {
        String[] path = req.getPathInfo().split("/");
        String role = req.getParameter("role");
        try {
            String requestData = req.getReader().lines().collect(Collectors.joining());
            OrderFeedback feedback = gson.fromJson(requestData, OrderFeedback.class);
            if (feedback.checkValid()) {
                long orderId = Long.parseLong(path[2]);
                if (orderService.getById(orderId) != null) {
                    orderService.updateFeedback(orderId, feedback, role);
                    MessageController.sendResponseMessage(res, "Feedback successfully left");
                } else {
                    MessageController.badRequest(res, "Order doesn`t exist");
                }
            } else {
                MessageController.badRequest(res, "Validation failed");
            }
            res.getWriter().flush();
        } catch (Exception err) {
            MessageController.internal(res);
        }
    }

    public void destroy() {
        orderService.closeConnection();
    }
}
