package by.academy.model.bean;

import by.academy.model.constant.ExceptionMessage;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "users")
public class User extends AbstractEntity {
    @Column(name = "fio", nullable = false)
    private String fio;
    @Column(name = "age", nullable = false)
    private int age;
    @Column(name = "user_name", nullable = false, unique = true)
    private String userName;
    @Column(name = "password", nullable = false)
    private @JsonIgnore String password;
    @Column(name = "user_type", nullable = false)
    @Enumerated(EnumType.ORDINAL)
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
