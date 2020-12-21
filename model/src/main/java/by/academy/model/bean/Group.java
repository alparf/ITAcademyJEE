package by.academy.model.bean;

import java.util.List;

public class Group {
    private String name;
    private User coach;
    private List<String> themes;
    private List<User> students;

    public Group() {
    }

    public Group(String name, User coach, List<String> themes, List<User> students) {
        this.name = name;
        this.coach = coach;
        this.themes = themes;
        this.students = students;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getCoach() {
        return coach;
    }

    public void setCoach(User coach) {
        this.coach = coach;
    }

    public List<String> getThemes() {
        return themes;
    }

    public void setThemes(List<String> themes) {
        this.themes = themes;
    }

    public List<User> getStudents() {
        return students;
    }

    public void setStudents(List<User> students) {
        this.students = students;
    }
}
