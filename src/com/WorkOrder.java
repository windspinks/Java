package com;

public class WorkOrder {
    private int id;
    private String description;
    private String senderName;
    private Status status;
    private static int currentID = 5000;

    public WorkOrder() {
        id = currentID;
        currentID+=10;
    }

    public WorkOrder(String description, String senderName) {
        this.description = description;
        this.senderName = senderName;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "WorkOrder{" +
            "id=" + id +
            ", description='" + description + '\'' +
            ", senderName='" + senderName + '\'' +
            ", status=" + status +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WorkOrder workOrder = (WorkOrder) o;

        if (id != workOrder.id) return false;
        if (description != null ? !description.equals(workOrder.description) : workOrder.description != null)
            return false;
        if (senderName != null ? !senderName.equals(workOrder.senderName) : workOrder.senderName != null) return false;
        return status == workOrder.status;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (senderName != null ? senderName.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }
}
