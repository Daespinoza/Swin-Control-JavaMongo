package org.daespinoza.model;
import java.time.LocalDate;
import java.time.LocalTime;

public class SwimmingLesson {
    private String id;
    private String swimmer_id;
    private LocalDate lesson_date;
    private LocalTime lesson_time;
    private String status;
    private Boolean paid;
    private String paymentType;
    private String notes;

    public SwimmingLesson(String swimmer_id, LocalDate lesson_date,
                          LocalTime lesson_time, String paymentType, String notes) {
        this.swimmer_id = swimmer_id;
        this.lesson_date = lesson_date;
        this.lesson_time = lesson_time;
        this.status = "ACTIVA";
        this.paid = false;
        this.paymentType = paymentType;
        this.notes = notes;
    }

    public SwimmingLesson(String id, String swimmer_id, LocalDate lesson_date,
                          LocalTime lesson_time, String paymentType, String notes) {
        this.id = id;
        this.swimmer_id = swimmer_id;
        this.lesson_date = lesson_date;
        this.lesson_time = lesson_time;
        this.status = "ACTIVA";
        this.paid = false;
        this.paymentType = paymentType;
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "SwimmingLesson{" +
                "id='" + id + '\'' +
                ", swimmer='" + swimmer_id + '\'' +
                ", date='" + lesson_date.toString() + '\'' +
                ", time='" + lesson_time.toString() + '\'' +
                ", status='" + status + '\'' +
                ", paid='" + paid + '\'' +
                ", paymentType='" + paymentType + '\'' +
                ", notes='" + notes + '\'' +
                '}';
    }

    // Getters and setters
    public String getId() { return id; }
    public LocalDate getLesson_date() { return lesson_date; }
    public LocalTime getLesson_time() { return lesson_time; }
    public String getSwimmer_id() { return swimmer_id; }
    public String getStatus() { return status; }
    public String getPaymentType() { return paymentType; }
    public Boolean getPaid() { return paid; }
    public String getNotes() { return notes; }

    public void setId(String id) { this.id = id; }
    public void setLesson_date(LocalDate lesson_date) { this.lesson_date = lesson_date; }
    public void setLesson_time(LocalTime lesson_time ) { this.lesson_time = lesson_time; }
    public void setSwimmer_id(String swimmer_id) { this.swimmer_id = swimmer_id; }
    public void setStatus(String status) { this.status = status; }
    public void setPaymentType(String paymentType) { this.paymentType = paymentType; }
    public void setPaid(Boolean paid) { this.paid = paid; }
    public void setNotes(String notes) { this.notes = notes; }
}
