package com.devtask.integrator;

public enum MemberStatus {
    SUBSCRIBED("subscribed"),
    UNSUBSCRIBED("unsubscribed"),
    CLEANED("cleaned"),
    PENDING("pending"),
    TRANSACTIONAL("transactional");

    private String status;

    public String getStatus(){
        return this.status;
    }

    MemberStatus(String status) {
        this.status=status;
    }
}
