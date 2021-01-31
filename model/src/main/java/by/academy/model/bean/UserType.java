package by.academy.model.bean;

public enum UserType {
    STUDENT("STUDENT"),
    COACH("COACH"),
    ADMIN("ADMIN");

    private final String name;

    UserType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}