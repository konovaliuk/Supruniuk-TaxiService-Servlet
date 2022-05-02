package com.taxi.taxiservice.Models.Order;

import java.util.Objects;

public class OrderDispatcher {
    private final long dispatcher_id;
    private final boolean approved;
    private final String order_status;
    private final double total_payment;

    public OrderDispatcher(long dispatcher_id, boolean approved,
                           String order_status, double total_payment) {
        this.dispatcher_id = dispatcher_id;
        this.approved = approved;
        this.order_status = order_status;
        this.total_payment = total_payment;
    }

    public boolean checkValid() {
        if(this.getDispatcherID() != 0 && this.orderStatus() != null &&
                this.getTotalPayment() != 0 &&
                (Objects.equals(this.orderStatus(), "pending")
                        || Objects.equals(this.orderStatus(), "interrupted")
                        || Objects.equals(this.orderStatus(), "executing")
                        || Objects.equals(this.orderStatus(), "completed"))) {
            return true;
        }
        return false;
    }
    public long getDispatcherID() {
        return dispatcher_id;
    }

    public boolean isApproved() {
        return approved;
    }

    public String orderStatus() {
        return order_status;
    }

    public double getTotalPayment() {
        return total_payment;
    }
}
