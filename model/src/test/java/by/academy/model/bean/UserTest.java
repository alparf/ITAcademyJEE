package by.academy.model.bean;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

public class UserTest extends TestCase {

    @Test
    public void testSetAge() throws IllegalArgumentException {
        User user = new User();
        int age = -1;
        try {
            user.setAge(age);
            Assert.fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException exception) {
            Assert.assertTrue(true);
        }
    }
}