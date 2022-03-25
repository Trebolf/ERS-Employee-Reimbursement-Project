package models.subclass;

import models.ReimbursementList;

import java.util.Date;

public class ReimbursementListPersonal extends ReimbursementList {

    public ReimbursementListPersonal() {
    }

    public ReimbursementListPersonal(Double amount, String description, Integer authorId, Integer typeId) {
        this.setAmount(amount);
        this.setDescription(description);
        this.setAuthorId(authorId);
        this.setTypeId(typeId);
    }

    public ReimbursementListPersonal(Integer id, String type, Double amount, String description, String status, Date timeSubmitted, Date timeResolved) {
        this.setId(id);
        this.setType(type);
        this.setStatus(status);
        this.setAmount(amount);
        this.setDescription(description);
        this.setTimeSubmitted(timeSubmitted);
        this.setTimeResolved(timeResolved);
    }

    public ReimbursementListPersonal(Integer id, Integer typeId, Double amount, String description, String status, Date timeSubmitted, Date timeResolved) {
        this.setId(id);
        this.setTypeId(typeId);
        this.setStatus(status);
        this.setAmount(amount);
        this.setDescription(description);
        this.setTimeSubmitted(timeSubmitted);
        this.setTimeResolved(timeResolved);
    }

    @Override
    public String toString() {
        return "\n" + "ReimbursementListPersonal{" +
                "id=" + getId() +
                ", type='" + getType() + '\'' +
                ", amount=" + getAmount() +
                ", status='" + getStatus() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", timeSubmitted=" + getTimeSubmitted() +
                ", timeResolved=" + getTimeResolved() +
                '}';
    }
}