package com;

public class WorkOrder {
    private int id;
    private String description;
    private String senderName;
    private String status;
    private static int currentID = 1;

    public WorkOrder() {
        id = currentID;
        currentID++;
    }

    public WorkOrder(String description, String senderName, String status) {
        this.description = description;
        this.senderName = senderName;
        this.status = status;
        id = currentID;
        currentID++;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
