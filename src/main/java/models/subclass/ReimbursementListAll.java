package models.subclass;

import models.ReimbursementList;

import java.util.Date;

public class ReimbursementListAll extends ReimbursementList {

    public ReimbursementListAll() {
    }

    public ReimbursementListAll(Integer id, Integer authorId, String firstName, String lastName, String type, Double amount, String description, String status, Date timeSubmitted, Date timeResolved) {
        this.setId(id);
        this.setType(type);
        this.setStatus(status);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setAmount(amount);
        this.setDescription(description);
        this.setTimeSubmitted(timeSubmitted);
        this.setTimeResolved(timeResolved);
        this.setAuthorId(authorId);
    }

    @Override
    public String toString() {
        return "\n" + "ReimbursementListAll{" +
                " id=" + getId() +
                ", authorId=" + getAuthorId() +
                ", status='" + getStatus() + '\'' +
                ", firstName='" + getFirstName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                ", type='" + getType() + '\'' +
                ", amount=" + getAmount() +
                ", description='" + getDescription() + '\'' +
                ", timeSubmitted=" + getTimeSubmitted() +
                ", timeResolved=" + getTimeResolved() +
                '}';
    }
}