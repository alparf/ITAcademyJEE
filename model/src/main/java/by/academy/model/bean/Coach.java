package by.academy.model.bean;

import by.academy.model.constant.ExceptionMessage;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Deque;
import java.util.List;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "coaches")
public class Coach extends AbstractEntity {
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    @OneToMany(mappedBy = "coach")
    private List<Salary> salaries;

    public Coach(User user, List<Salary> salaries) throws IllegalArgumentException {
        this.setUser(user);
        this.salaries = salaries;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) throws IllegalArgumentException {
        if (null != user && user.getUserType() == UserType.COACH) {
            this.user = user;
        } else {
            throw new IllegalArgumentException(ExceptionMessage.USER_HAVE_TO_BE_COACH);
        }
    }

    /**
     * Return average salary for last monthCount month or throw IllegalArgumentException
     * if monthCount < 1
     * @param monthCount month count
     * @return average salary for last monthCount month
     * @throws IllegalArgumentException if monthCount < 1
     */
    public int getAverageSalary(int monthCount) throws IllegalArgumentException {
        if (monthCount < 1) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_MONTH_COUNT);
        }
        int sum = this.getSalaries().stream()
                .limit(monthCount)
                .mapToInt(Salary::getValue)
                .sum();
        return sum / monthCount;
    }

    public void addSalary(Salary salary) {
        this.getSalaries().add(salary);
    }

    public static Builder newBuilder() {
        return new Coach().new Builder();
    }

    public class Builder {

        public Builder withUser(User user) {
            Coach.this.setUser(user);
            return this;
        }

        public Builder withSalaries(List<Salary> salaries) {
            Coach.this.setSalaries(salaries);
            return this;
        }

        public Coach build() {
            return Coach.this;
        }
    }
}
