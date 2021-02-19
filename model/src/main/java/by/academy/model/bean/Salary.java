package by.academy.model.bean;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Table(name = "salaries")
public class Salary extends AbstractEntity {
    @Column(name = "value", nullable = false)
    private int value;
    @ManyToOne
    @JoinColumn(name = "coach_id", referencedColumnName = "id", nullable = false)
    private User coach;

    public static Builder newBuilder() {
        return new Salary().new Builder();
    }

    public class Builder {
        public Builder withId(long id) {
            Salary.this.setId(id);
            return this;
        }

        public Builder withValue(int value) {
            Salary.this.setValue(value);
            return this;
        }

        public Builder withCoach(User coach) {
            Salary.this.setCoach(coach);
            return this;
        }

        public Salary build() {
            return Salary.this;
        }
    }
}
