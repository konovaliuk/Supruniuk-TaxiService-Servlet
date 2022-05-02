package com.taxi.taxiservice.DAO;

import com.taxi.taxiservice.ConnectionPool;
import com.taxi.taxiservice.DAO.dbColumns.OrderDB;
import com.taxi.taxiservice.DAO.interfaces.IOrderDAO;
import com.taxi.taxiservice.Models.Order.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class OrderDaoImpl implements IOrderDAO {
    public Connection connection = null;

    public OrderDaoImpl() {
        try {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection("Order Data Source");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private Order getOrder(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(OrderDB.columnId);
        long clientID = resultSet.getLong(OrderDB.columnClientId);
        String origin = resultSet.getString(OrderDB.columnOriginAddress);
        String destination = resultSet.getString(OrderDB.columnDestinationAddress);
        int distance = resultSet.getInt(OrderDB.columnDistance);
        int people = resultSet.getInt(OrderDB.columnNumberOfPeople);
        long carTypeID = resultSet.getLong(OrderDB.columnCarTypeId);
        String creationDate = resultSet.getString(OrderDB.columnCreationDate);
        String clientComment = resultSet.getString(OrderDB.columnClientComment);
        int clientGrade = resultSet.getInt(OrderDB.columnClientGrade);
        long driverID = resultSet.getLong(OrderDB.columnDriverId);
        String waitingTime = resultSet.getString(OrderDB.columnWaitingTime);
        String driverComment = resultSet.getString(OrderDB.columnDriverComment);
        int driverGrade = resultSet.getInt(OrderDB.columnDriverGrade);
        long carID = resultSet.getLong(OrderDB.columnCarId);
        long dispatcherID = resultSet.getLong(OrderDB.columnDispatcherId);
        boolean approved = resultSet.getBoolean(OrderDB.columnApproved);
        String orderStatus = resultSet.getString(OrderDB.columnOrderStatus);
        String totalPayment = resultSet.getString(OrderDB.columnTotalPayment);

        return new Order(id, clientID, origin, destination, distance, people, carTypeID, creationDate, clientComment, clientGrade, driverID, waitingTime, driverComment, driverGrade, carID, dispatcherID, approved, orderStatus, totalPayment);
    }

    public ArrayList<Order> findAll() {
        String query = "select * from orders";
        ArrayList<Order> orders = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Order order = getOrder(resultSet);
                orders.add(order);
            }
        } catch (Exception error) {
            error.printStackTrace();
        }

        return orders;
    }

    public Order findByID(long orderID) {
        String query = "select * from orders where id=" + orderID;
        Order order = null;

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                order = getOrder(resultSet);
            }

        } catch (Exception error) {
            error.printStackTrace();
        }

        return order;
    }

    public ArrayList<Order> findByDriverID(long driverID) {
        String query = "select * from orders where driver_id=" + driverID;
        ArrayList<Order> orders = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Order order = getOrder(resultSet);
                orders.add(order);
            }

        } catch (Exception error) {
            error.printStackTrace();
        }

        return orders;
    }

    public ArrayList<Order> findByClientID(long clientID) {
        String query = "select * from orders where client_id=" + clientID;
        ArrayList<Order> orders = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Order order = getOrder(resultSet);
                orders.add(order);
            }

        } catch (Exception error) {
            error.printStackTrace();
        }

        return orders;
    }

    public ArrayList<Order> findByDispatcherID(long dispatcherID) {
        String query = "select * from orders where dispatcher_id=" + dispatcherID;
        ArrayList<Order> orders = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Order order = getOrder(resultSet);
                orders.add(order);
            }
        } catch (Exception error) {
            error.printStackTrace();
        }

        return orders;
    }

    public long save(OrderClient clientInfo) {
        long newOrderID = 0;
        String query = "call create_order(null, " + clientInfo.getClientID() + ",'" + clientInfo.getOriginAddress() + "','" + clientInfo.getDestinationAddress() + "'," + clientInfo.getDistance() + "," + clientInfo.getNumberOfPeople() + "," + clientInfo.getCarTypeId() + ")";

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                long ind = resultSet.getLong("ind");
                newOrderID = ind;
            }
        } catch (Exception error) {
            error.printStackTrace();
        }

        return newOrderID;
    }

    public void updateByDispatcher(long orderId, OrderDispatcher dispatcherInfo) {
        String query = "call update_order_dispatcher(" + orderId + "," + dispatcherInfo.getDispatcherID() + "," + dispatcherInfo.isApproved() + ",'" + dispatcherInfo.getTotalPayment() + "','" + dispatcherInfo.orderStatus() + "');";

        try {
            Statement statement = connection.createStatement();
            statement.execute(query);
        } catch (SQLException error) {
            error.printStackTrace();
        }
    }

    public void updateByDriver(long orderId, OrderDriver driverInfo) {
        String query = "call take_order_driver(" + orderId + "," + driverInfo.getDriverID() + ",'" + driverInfo.getWaitingTime() + "','" + driverInfo.getOrderStatus() + "'," + driverInfo.getCarId() + ");";

        try {
            Statement statement = connection.createStatement();
            statement.execute(query);
        } catch (SQLException error) {
            error.printStackTrace();
        }
    }

    public void updateFeedback(long orderId, OrderFeedback feedback, String role) {
        String query = "call grade_order_" + role + "(" + orderId + ",'" + feedback.getComment() + "'," + feedback.getGrade() + ");";

        try {
            Statement statement = connection.createStatement();
            statement.execute(query);
        } catch (SQLException error) {
            error.printStackTrace();
        }
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException error) {
            System.err.println(error.getMessage());
        }
    }
}
