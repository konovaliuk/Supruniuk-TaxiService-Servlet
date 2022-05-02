package com.taxi.taxiservice.DAO.interfaces;

import com.taxi.taxiservice.Models.Order.*;

import java.util.ArrayList;

public interface IOrderDAO {
    ArrayList<Order> findAll();
    Order findByID(long id);
    ArrayList<Order> findByDriverID(long driverID);
    ArrayList<Order> findByClientID(long clientID);
    ArrayList<Order> findByDispatcherID(long dispatcherID);
    long save(OrderClient clientInfo);
    void updateByDispatcher(long orderId, OrderDispatcher dispatcherInfo);
    void updateByDriver(long orderId, OrderDriver driverInfo);
    void updateFeedback(long orderId, OrderFeedback feedback, String role);
}
