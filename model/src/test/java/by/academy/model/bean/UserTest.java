package by.academy.model.bean;

import by.academy.model.constant.ModelExceptions;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

public class UserTest extends TestCase {

    @Test
    public void testSetAge() throws IllegalArgumentException {
        User user = User.newBuilder().build();
        int age = -1;
        try {
            user.setAge(age);
            Assert.fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException exception) {
            assertEquals(exception.getMessage(), ModelExceptions.INVALID_AGE_VALUE);
        }
    }
}