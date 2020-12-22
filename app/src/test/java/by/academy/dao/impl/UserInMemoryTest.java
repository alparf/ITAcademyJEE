package by.academy.dao.impl;

import by.academy.constant.ExceptionConstant;
import by.academy.exception.UserNotFoundException;
import junit.framework.TestCase;
import org.junit.Test;

public class UserInMemoryTest extends TestCase {

    @Test
    public void testGetUser() {
        UserInMemory userInMemory = new UserInMemory();
        try {
            userInMemory.getUser("", "");
            fail("Expected UserNotFoundException");
        } catch (UserNotFoundException e) {
            assertEquals(e.getMessage(), ExceptionConstant.USER_NOT_FOUND);
        }
    }
}