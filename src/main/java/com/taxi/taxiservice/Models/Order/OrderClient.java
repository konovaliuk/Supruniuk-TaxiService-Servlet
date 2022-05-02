package com.taxi.taxiservice.Models.Order;

public class OrderClient {
    private final long client_id;
    private final String origin_address;
    private final String destination_address;
    private final int distance;
    private final int number_of_people;
    private final long car_type_id;

    public OrderClient(long client_id, String origin_address, String destination_address,
                       int distance, int number_of_people, long car_type_id) {
        this.client_id = client_id;
        this.origin_address = origin_address;
        this.destination_address = destination_address;
        this.distance = distance;
        this.number_of_people = number_of_people;
        this.car_type_id = car_type_id;
    }

    public boolean checkValid() {
        if(this.getDestinationAddress() != null && this.getOriginAddress() != null &&
        this.getClientID() != 0 && this.getDistance() != 0 && this.getCarTypeId() != 0 &&
        this.getNumberOfPeople() != 0) {
            return true;
        }
        return false;
    }

    public long getClientID() {
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
}
