package by.academy.model.bean;

import by.academy.model.constant.ExceptionMessage;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class Group extends AbstractEntity {
    private String name;
    private User coach;
    private List<String> themes;
    private List<User> students;

    public Group(long id, String name, User coach, List<String> themes, List<User> students) {
        super(id);
        this.name = name;
        this.setCoach(coach);
        this.themes = themes;
        this.students = students;
    }

    public void setCoach(User coach) {
        if ((null != coach) && (coach.getUserType() == UserType.COACH)) {
            this.coach = coach;
        } else {
            throw new IllegalArgumentException(ExceptionMessage.USER_HAVE_TO_BE_COACH);
        }
    }
}
