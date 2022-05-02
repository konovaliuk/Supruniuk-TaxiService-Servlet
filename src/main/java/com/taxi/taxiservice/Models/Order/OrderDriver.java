package com.taxi.taxiservice.Models.Order;

import java.util.Objects;

public class OrderDriver {
    private final long driver_id;
    private final String waiting_time;
    private final String order_status;
    private final long car_id;

    public OrderDriver(long driver_id, String waiting_time,
                       String order_status, long car_id) {
        this.driver_id = driver_id;
        this.waiting_time = waiting_time;
        this.order_status = order_status;
        this.car_id = car_id;
    }

    public boolean checkValid() {
        if(this.getDriverID() != 0 && this.getWaitingTime() != null &&
                this.getOrderStatus() != null && this.getCarId() != 0 &&
                (Objects.equals(this.getOrderStatus(), "pending") || Objects.equals(this.getOrderStatus(), "interrupted") || Objects.equals(this.getOrderStatus(), "executing") || Objects.equals(this.getOrderStatus(), "completed")) ) {
            return true;
        }
        return false;
    }
    public long getDriverID() {
        return driver_id;
    }

    public String getOrderStatus() {
        return order_status;
    }

    public String getWaitingTime() {
        return waiting_time;
    }

    public long getCarId() {
        return car_id;
    }
}
