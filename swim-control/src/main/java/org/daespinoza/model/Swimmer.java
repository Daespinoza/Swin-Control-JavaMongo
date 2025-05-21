package org.daespinoza.model;

public class Swimmer {
    private String id;
    private String name;
    private String phone;
    private String email;

    public Swimmer(String id, String name, String phone, String email) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Swimmer{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    // Getters and setters
}
