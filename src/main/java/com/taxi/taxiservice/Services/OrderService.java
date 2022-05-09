package com.taxi.taxiservice.Services;

import com.taxi.taxiservice.DAO.OrderDaoImpl;
import com.taxi.taxiservice.DAO.RoleDaoImpl;
import com.taxi.taxiservice.DAO.UsersDaoImpl;
import com.taxi.taxiservice.Models.Order.*;

import java.util.ArrayList;

public class OrderService {

    private OrderDaoImpl orderDao;

    public OrderService() {
        orderDao = new OrderDaoImpl();
    }

    public ArrayList<Order> getAll() {
        ArrayList<Order> orders = orderDao.findAll();
        return orders;
    }

    public Order getById(long id) {
        Order order = orderDao.findByID(id);
        return order;
    }

    public ArrayList<Order> getByClientId(long id) {
        ArrayList<Order> orders = orderDao.findByClientID(id);
        return orders;
    }

    public ArrayList<Order> getByDriverId(long id) {
        ArrayList<Order> orders = orderDao.findByDriverID(id);
        return orders;
    }

    public ArrayList<Order> getByDispatcherId(long id) {
        ArrayList<Order> orders = orderDao.findByDispatcherID(id);
        return orders;
    }

    public void create(OrderClient order) {
        orderDao.save(order);
    }

    public void updateByDispatcher(long id, OrderDispatcher order) {
        orderDao.updateByDispatcher(id, order);
    }

    public void updateByDriver(long id, OrderDriver order) {
        orderDao.updateByDriver(id, order);
    }

    public void updateFeedback(long id, OrderFeedback orderFeedback, String role) {
        orderDao.updateFeedback(id, orderFeedback, role);
    }

    public void closeConnection() {
        orderDao.closeConnection();
    }
}
