package by.academy.dao.impl;

import by.academy.constant.ExceptionConstant;
import by.academy.dao.IUserDAO;
import by.academy.exception.UserServiceException;
import by.academy.model.bean.User;
import by.academy.model.bean.UserType;
import by.academy.model.factory.UserFactory;
import junit.framework.TestCase;
import org.junit.Test;

public class UserInMemoryTest extends TestCase {

    private static final IUserDAO userDAO = new UserInMemory();

    @Test
    public void testAddUser() {
        User user = UserFactory.createUser(
                0,"FIO", 18, "userName", "password", UserType.STUDENT);
        try {
            userDAO.addUser(user);
            userDAO.addUser(user);
            fail("Expected UserServiceException");
        } catch (UserServiceException e) {
            assertEquals(e.getMessage(), ExceptionConstant.USER_NAME_ALREADY_USED);
        }
    }

    public void testGetUser() {
        assertTrue(null == userDAO.getUserByName(""));
        assertTrue(null != userDAO.getUserByName("userName"));
    }
}