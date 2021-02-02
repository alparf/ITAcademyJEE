package by.academy.model.bean;

import by.academy.model.constant.ExceptionMessage;
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

    public static Builder newBuilder() {
        return new User().new Builder();
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
            throw new IllegalArgumentException(ExceptionMessage.INVALID_AGE_VALUE);
        } else {
            this.age = age;
        }
    }

    public class Builder {

        public Builder withId(long id) {
            User.this.setId(id);
            return this;
        }

        public Builder withFio(String fio) {
            User.this.setFio(fio);
            return this;
        }

        public Builder withAge(int age) {
            User.this.setAge(age);
            return this;
        }

        public Builder withUserName(String userName) {
            User.this.setUserName(userName);
            return this;
        }

        public Builder withPassword(String password) {
            User.this.setPassword(password);
            return this;
        }

        public Builder withUserType(UserType userType) {
            User.this.setUserType(userType);
            return this;
        }

        public User build() {
            return User.this;
        }

        private Builder() {

        }
    }
}
