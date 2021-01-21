package by.academy.model.bean;

import by.academy.model.builder.IUserBuilder;
import by.academy.model.builder.impl.UserBuilder;
import by.academy.model.constant.ExceptionConstant;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class User extends AbstractEntity {
    private String fio;
    private int age;
    private String userName;
    private String password;
    private UserType userType;

    public static IUserBuilder newBuilder() {
        return new UserBuilder();
    }

    public User(long id, String fio, int age, String userName, String password, UserType userType)
            throws IllegalArgumentException {
        super(id);
        this.fio = fio;
        this.setAge(age);
        this.userName = userName;
        this.password = password;
        this.userType = userType;
    }

    /**
     * Set age if age >= 0 or throw IllegalArgumentException if age < 0
     * @param age - age of user
     * @throws IllegalArgumentException if age < 0
     */

    public void setAge(int age) throws IllegalArgumentException {
        if (age < 0) {
            throw new IllegalArgumentException(ExceptionConstant.INVALID_AGE_VALUE);
        } else {
            this.age = age;
        }
    }
}
