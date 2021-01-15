package by.academy.model.bean;

import by.academy.model.constant.ExceptionConstant;
import by.academy.model.factory.CoachFactory;
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
        Deque<Integer> salaries = new LinkedList<>();
        salaries.addFirst(100);
        salaries.addFirst(200);
        Coach coach = CoachFactory.createCoach(user, salaries);
            assertEquals(expected, coach.getAverageSalary(monthCount));
        try {
            monthCount = -1;
            coach.getAverageSalary(monthCount);
            Assert.fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException exception) {
            assertEquals(exception.getMessage(), ExceptionConstant.INVALID_MONTH_COUNT);
        }
    }
}