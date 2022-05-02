package com.taxi.taxiservice.Models.Order;

public class Order {
    private long id;
    private long client_id;
    private String origin_address;
    private String destination_address;
    private int distance;
    private int number_of_people;
    private long car_type_id;
    private String creation_date;
    private String client_comment;
    private int client_grade;

    private long driver_id;
    private String waiting_time;
    private String driver_comment;
    private int driver_grade;
    private long car_id;

    private long dispatcher_id;
    private boolean approved;
    private String order_status;
    private String total_payment;

    public Order(long id, long client_id, String origin_address, String destination_address,
                 int distance, int number_of_people, long car_type_id, String creation_date,
                 String client_comment, int client_grade, long driver_id, String waiting_time,
                 String driver_comment, int driver_grade, long car_id, long dispatcher_id,
                 boolean approved, String order_status, String total_payment) {
        this.id = id;
        this.client_id = client_id;
        this.origin_address = origin_address;
        this.destination_address = destination_address;
        this.distance = distance;
        this.number_of_people = number_of_people;
        this.car_type_id = car_type_id;
        this.creation_date = creation_date;
        this.client_comment = client_comment;
        this.client_grade = client_grade;
        this.driver_id = driver_id;
        this.waiting_time = waiting_time;
        this.driver_comment = driver_comment;
        this.driver_grade = driver_grade;
        this.car_id = car_id;
        this.dispatcher_id = dispatcher_id;
        this.approved = approved;
        this.order_status = order_status;
        this.total_payment = total_payment;
    }

    public Order() {}

    public long getId() {
        return id;
    }

    public long getCientID() {
        return client_id;
    }

    public String getOriginAddress() {
        return origin_address;
    }

    public String getDestinationAddress() {
        return destination_address;
    }

    public int getDistance() {
        return distance;
    }

    public int getNumberOfPeople() {
        return number_of_people;
    }

    public long getCarTypeId() {
        return car_type_id;
    }

    public String getCreationDate() {
        return creation_date;
    }

    public int getClientGrade() {
        return client_grade;
    }

    public String getClientComment() {
        return client_comment;
    }

    public long getDriverID() {
        return driver_id;
    }

    public String getWaitingTime() {
        return waiting_time;
    }

    public String getDriverComment() {
        return driver_comment;
    }

    public int getDriverGrade() {
        return driver_grade;
    }

    public long getCarId() {
        return car_id;
    }

    public long getDispatcherID() {
        return dispatcher_id;
    }

    public boolean isApproved() {
        return approved;
    }

    public String getOrderStatus() {
        return order_status;
    }

    public String getTotalPayment() {
        return total_payment;
    }
}
