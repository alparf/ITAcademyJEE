package by.academy.model.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Salary extends AbstractEntity {
    private int value;
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
