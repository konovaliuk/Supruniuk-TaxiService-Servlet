package com.taxi.taxiservice.Models.Order;

import java.util.Objects;

public class OrderFeedback {
    private final String comment;
    private final int grade;

    public OrderFeedback(String comment, int grade) {
        this.comment = comment;
        this.grade = grade;
    }

    public boolean checkValid() {
        if(this.getComment() != null && this.getGrade() != 0) {
            return true;
        }
        return false;
    }

    public String getComment() {
        return comment;
    }

    public int getGrade() {
        return grade;
    }
}
