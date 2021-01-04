package by.academy.dao.impl;

import by.academy.constant.ExceptionConstant;
import by.academy.exception.UserServiceException;
import by.academy.model.bean.User;
import by.academy.model.bean.UserType;
import by.academy.model.factory.UserFactory;
import junit.framework.TestCase;
import org.junit.Test;

public class UserInMemoryTest extends TestCase {

    @Test
    public void testGetUser() {
        UserInMemory userInMemory = new UserInMemory();
        try {
            userInMemory.getUser("", "");
            fail("Expected UserServiceException");
        } catch (UserServiceException e) {
            assertEquals(e.getMessage(), ExceptionConstant.USER_NOT_FOUND);
        }
    }

    @Test
    public void testAddUser() {
        UserInMemory userInMemory = new UserInMemory();
        User user = UserFactory.createUser(
                "FIO", 18, "userName", "password", UserType.STUDENT);
        try {
            userInMemory.addUser(user);
            userInMemory.addUser(user);
            fail("Expected UserServiceException");
        } catch (UserServiceException e) {
            assertEquals(e.getMessage(), ExceptionConstant.USER_NAME_ALREADY_USED);
        }
    }
}