package com.taxi.taxiservice.Controllers;

import com.google.gson.Gson;
import com.taxi.taxiservice.DAO.OrderDaoImpl;
import com.taxi.taxiservice.Models.Order.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class OrderController {
    private OrderDaoImpl orderDao;
    Gson gson = new Gson();

    public OrderController() {
        orderDao = new OrderDaoImpl();
    }

    public void getOrders(HttpServletRequest req, HttpServletResponse res) {
        try {
            ArrayList<Order> orders = orderDao.findAll();
            String json = new Gson().toJson(orders);
            res.getWriter().write(json);
            res.getWriter().flush();

        } catch (Exception err) {
            System.out.println(err);
        }
    }

    public void getOrderById(HttpServletRequest req, HttpServletResponse res) {
        String id = req.getPathInfo().substring(1);

        try {
            long orderId = Long.parseLong(id);
            Order order = orderDao.findByID(orderId);
            if(order == null) {
                MessageController.badRequest(res,"Order not found");
            } else {
                String json = new Gson().toJson(order);
                res.getWriter().write(json);
            }
            res.getWriter().flush();
        } catch (Exception err) {
            System.out.println("Server error");
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
                    orders = orderDao.findByClientID(userId);
                    break;
                case "driver":
                    orders = orderDao.findByDriverID(userId);
                    break;
                case "dispatcher":
                    orders = orderDao.findByDispatcherID(userId);
                    break;
            }
            if(orders.size() == 0) {
                MessageController.badRequest(res,"Orders not found");
            } else {
                String json = new Gson().toJson(orders);
                res.getWriter().write(json);
            }
            res.getWriter().flush();
        } catch (Exception err) {
            System.out.println("Server error");
        }
    }
    public void createOrder(HttpServletRequest req, HttpServletResponse res) {
        try {
            String requestData = req.getReader().lines().collect(Collectors.joining());
            OrderClient orderClient = gson.fromJson(requestData, OrderClient.class);
            if(orderClient.checkValid()) {
                orderDao.save(orderClient);
                MessageController.sendResponseMessage(res,"Order successfully created");
            } else {
                MessageController.badRequest(res, "Order field invalid");
            }
        } catch (Exception err) {
            System.out.println("Server error");
        }
    }

    public void updateByDispatcher(HttpServletRequest req, HttpServletResponse res) {
        String [] path = req.getPathInfo().split("/");

        try {
            String requestData = req.getReader().lines().collect(Collectors.joining());
            OrderDispatcher orderDispatcher = gson.fromJson(requestData, OrderDispatcher.class);
            if(orderDispatcher.checkValid()) {
                long orderId = Long.parseLong(path[2]);
                if(orderDao.findByID(orderId) != null) {
                    orderDao.updateByDispatcher(orderId, orderDispatcher);
                    MessageController.sendResponseMessage(res,"Order updated successfully by dispatcher");
                } else {
                    MessageController.badRequest(res, "Order doesn`t exist");
                }
            } else {
                MessageController.badRequest(res, "Invalid field");
            }
            res.getWriter().flush();
        } catch (Exception err) {
            System.out.println("Server error");
        }
    }

    public void updateByDriver(HttpServletRequest req, HttpServletResponse res) {
        String [] path = req.getPathInfo().split("/");

        try {
            String requestData = req.getReader().lines().collect(Collectors.joining());
            OrderDriver orderDriver = gson.fromJson(requestData, OrderDriver.class);
            if(orderDriver.checkValid()) {
                long orderId = Long.parseLong(path[2]);
                if(orderDao.findByID(orderId) != null) {
                    orderDao.updateByDriver(orderId, orderDriver);
                    MessageController.sendResponseMessage(res,"Order updated successfully by driver");
                } else {
                    MessageController.badRequest(res, "Order doesn`t exist");
                }
            } else {
                MessageController.badRequest(res, "Invalid field");
            }
            res.getWriter().flush();
        } catch (Exception err) {
            System.out.println("Server error");
        }
    }

    public void leaveFeedback(HttpServletRequest req, HttpServletResponse res) {
        String [] path = req.getPathInfo().split("/");
        String role = req.getParameter("role");
        try {
            String requestData = req.getReader().lines().collect(Collectors.joining());
            OrderFeedback feedback = gson.fromJson(requestData, OrderFeedback.class);
            if(feedback.checkValid()) {
                long orderId = Long.parseLong(path[2]);
                if(orderDao.findByID(orderId) != null) {
                    orderDao.updateFeedback(orderId, feedback, role);
                    MessageController.sendResponseMessage(res,"Feedback successfully left");
                } else {
                    MessageController.badRequest(res, "Order doesn`t exist");
                }
            } else {
                MessageController.badRequest(res, "Invalid field");
            }
            res.getWriter().flush();
        } catch (Exception err) {
            System.out.println("Server error");
        }
    }
    public void destroy() {
        orderDao.closeConnection();
    }
}
