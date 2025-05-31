package org.daespinoza.model;

public class Professor {
    private String id;
    private String name;
    private String email;
    private String phone;
    private String notes;

    public Professor(String name, String phone, String email, String notes) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.notes = notes;
    }

    public Professor(String id, String name, String phone, String email, String notes) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "Professor{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", notes='" + notes + '\'' +
                '}';
    }

    // Getters and setters
    public String getName() { return name; }
    public String getPhone() { return phone; }
    public String getEmail() { return email; }
    public String getNotes() { return notes; }
    public String getId() {return id; }

    public void setName(String name) { this.name = name; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setEmail(String email) { this.email = email; }
    public void setNotes(String notes) { this.notes = notes; }
    public void setId(String id) { this.id = id; }
}

