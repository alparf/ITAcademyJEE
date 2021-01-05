package by.academy.model.bean;

import by.academy.model.constant.ExceptionConstant;

import java.util.Deque;

public class Coach {
    private User user;
    private Deque<Integer> salaries;

    public Coach() {
    }

    public Coach(User user, Deque<Integer> salaries) throws IllegalArgumentException {
        this.setUser(user);
        this.salaries = salaries;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) throws IllegalArgumentException {
        if(null != user && user.getUserType() == UserType.COACH) {
            this.user = user;
        } else {
            throw new IllegalArgumentException(ExceptionConstant.USER_HAVE_TO_BE_COACH);
        }
    }

    public Deque<Integer> getSalaries() {
        return salaries;
    }

    public void setSalaries(Deque<Integer> salaries) {
        this.salaries = salaries;
    }

    /**
     * Return average salary for last monthCount month or throw IllegalArgumentException
     * if monthCount < 1
     * @param monthCount month count
     * @return average salary for last monthCount month
     * @throws IllegalArgumentException if monthCount < 1
     */
    public int getAverageSalary(int monthCount) throws IllegalArgumentException {
        if(monthCount < 1) {
            throw new IllegalArgumentException(ExceptionConstant.INVALID_MONTH_COUNT);
        }
        int sum = this.getSalaries().stream()
                .limit(monthCount)
                .mapToInt(salary -> salary)
                .sum();
        return sum / monthCount;
    }

    public void addSalary(int salary) {
        this.getSalaries().addFirst(salary);
    }

    @Override
    public String toString() {
        return "Coach{" +
                "user=" + user +
                ", salaries=" + salaries +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Coach) {
            if (null != this.getUser()) {
                return this.getUser().equals(((Coach) obj).getUser());
            }
        }
        return false;
    }
}
