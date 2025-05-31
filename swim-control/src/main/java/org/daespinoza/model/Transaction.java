package org.daespinoza.model;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Transaction {
    private String id;
    private String type; // "INGRESO" o "EGRESO"
    private double amount;
    private String description;
    private List<String> relatedLessonIds;
    private LocalDate dateIssued;
    private LocalDate datePaid; // only applies to INGRESO
    private String swimmerId;   // only if applies

    public enum TransactionType {
        INGRESO, EGRESO
    }


    // Builder
    public Transaction(double amount, int type, String description,
                       LocalDate dateIssued) {
        this.amount = amount;
        this.description = description;
        this.relatedLessonIds = new ArrayList<>(); // empty list
        this.dateIssued = dateIssued;
        this.datePaid = null;
        this.swimmerId = null;

        if (type == 1){
            this.type = TransactionType.INGRESO.toString();
        } else if (type == 2) {
            this.type = TransactionType.EGRESO.toString();
        }
    }

    public Transaction(String id, int type, double amount, String description,
                       LocalDate dateIssued) {
        this.id = id;
        this.amount = amount;
        this.description = description;
        this.relatedLessonIds = new ArrayList<>(); // empty list
        this.dateIssued = dateIssued;
        this.datePaid = null;
        this.swimmerId = null;

        if (type == 1){
            this.type = TransactionType.INGRESO.toString();
        } else if (type == 2) {
            this.type = TransactionType.EGRESO.toString();
        }
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", relatedLessonIds=" + relatedLessonIds +
                ", dateIssued=" + dateIssued +
                ", datePaid=" + datePaid +
                ", swimmerId='" + swimmerId + '\'' +
                '}';
    }

    public void addRelatedLessonId(String lessonId) {
        if (lessonId != null && !lessonId.isEmpty()) {
            this.relatedLessonIds.add(lessonId);
        }
    }

    // Getters y Setters
    public String getId() { return id; }
    public String getType() { return type; }
    public double getAmount() { return amount; }
    public String getDescription() { return description; }
    public List<String> getRelatedLessonIds() { return relatedLessonIds; }
    public LocalDate getDateIssued() { return dateIssued; }
    public LocalDate getDatePaid() { return datePaid; }
    public String getSwimmerId() { return swimmerId; }

    public void setId(String id) { this.id = id; }
    public void setType(String type) { this.type = type; }
    public void setAmount(double amount) { this.amount = amount; }
    public void setDescription(String description) { this.description = description; }
    public void setRelatedLessonIds(List<String> relatedLessonIds) { this.relatedLessonIds = relatedLessonIds; }
    public void setDateIssued(LocalDate dateIssued) { this.dateIssued = dateIssued; }
    public void setDatePaid(LocalDate datePaid) { this.datePaid = datePaid; }
    public void setSwimmerId(String swimmerId) { this.swimmerId = swimmerId; }
}
