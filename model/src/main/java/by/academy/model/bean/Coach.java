package by.academy.model.bean;

import by.academy.model.constant.ExceptionMessage;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Deque;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
public class Coach {
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    private Deque<Salary> salaries;

    public Coach(User user, Deque<Salary> salaries) throws IllegalArgumentException {
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
                .mapToInt(salary -> salary.getValue())
                .sum();
        return sum / monthCount;
    }

    public void addSalary(Salary salary) {
        this.getSalaries().addFirst(salary);
    }

    public static Builder newBuilder() {
        return new Coach().new Builder();
    }

    public class Builder {

        public Builder withUser(User user) {
            Coach.this.setUser(user);
            return this;
        }

        public Builder withSalaries(Deque<Salary> salaries) {
            Coach.this.setSalaries(salaries);
            return this;
        }

        public Coach build() {
            return Coach.this;
        }
    }
}
