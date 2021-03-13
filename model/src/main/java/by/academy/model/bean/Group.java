package by.academy.model.bean;

import by.academy.model.constant.ExceptionMessage;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Table(name = "groups")
public class Group extends AbstractEntity {
    @Column(name = "name", nullable = false, unique = true)
    private String name;
    @OneToOne
    @JoinColumn(name = "coach_id", referencedColumnName = "id")
    private User coach;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Theme> themes;
    @OneToMany(cascade = CascadeType.ALL)
    private List<User> students;

    public Group(long id, String name, User coach, List<Theme> themes, List<User> students) {
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

    public static Builder getBuilder() {
        return new Group().new Builder();
    }

    public class Builder {
        public Builder withId(long id) {
            Group.this.setId(id);
            return this;
        }

        public Builder withName(String name) {
            Group.this.setName(name);
            return this;
        }

        public Builder withCoach(User coach) {
            Group.this.setCoach(coach);
            return this;
        }

        public Builder withThemes(List<Theme> themes) {
            Group.this.setThemes(themes);
            return this;
        }

        public Builder withStudents(List<User> students) {
            Group.this.setStudents(students);
            return this;
        }

        public Group build() {
            return Group.this;
        }
    }
}
