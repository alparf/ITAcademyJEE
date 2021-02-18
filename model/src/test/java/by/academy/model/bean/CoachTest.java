package by.academy.model.bean;

import by.academy.model.constant.ExceptionMessage;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

public class CoachTest extends TestCase {

    @Test
    public void testGetAverageSalary() {
        int monthCount = 2;
        int expected = 150;
        User user = User.newBuilder()
                .withFio("fio")
                .withAge(22)
                .withUserName("user")
                .withPassword("user")
                .withUserType(UserType.COACH)
                .build();
        Deque<Salary> salaries = new LinkedList<>();
        salaries.add(Salary.newBuilder()
                .withValue(100)
                .build());
        salaries.add(Salary.newBuilder()
                .withValue(200)
                .build());
        Coach coach = Coach.newBuilder()
                .withUser(user)
                .withSalaries(salaries)
                .build();
            assertEquals(expected, coach.getAverageSalary(monthCount));
        try {
            monthCount = -1;
            coach.getAverageSalary(monthCount);
            Assert.fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException exception) {
            assertEquals(exception.getMessage(), ExceptionMessage.INVALID_MONTH_COUNT);
        }
    }
}